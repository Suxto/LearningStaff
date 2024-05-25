import torch
import cv2
import numpy as np
import os
import glob as glob
import albumentations as A
from albumentations.pytorch import ToTensorV2

from xml.etree import ElementTree as et
from torch.utils.data import Dataset, DataLoader


def in_blue(s):
    return "\033[94m" + str(s) + "\033[0m"


# the dataset class
class CustomDataset(Dataset):
    def __init__(
        self,
        images_path,
        labels_path,
        width,
        height,
        classes,
        image_format,
        transforms=None,
        train=False,
    ):
        self.transforms = transforms
        # self.use_train_aug = use_train_aug
        self.images_path = images_path
        self.labels_path = labels_path
        self.height = height
        self.width = width
        self.classes = classes
        self.train = train
        # self.image_file_types = ["*.jpg", "*.jpeg", "*.png", "*.ppm"]
        self.image_format = image_format
        self.all_image_paths = []

        # get all the image paths in sorted order
        self.all_image_paths.extend(
            glob.glob(os.path.join(self.images_path, "*" + image_format))
        )
        # print(in_blue(self.all_image_paths))

        self.all_annot_paths = glob.glob(os.path.join(self.labels_path, "*.xml"))
        self.all_images = [
            image_path.split(os.path.sep)[-1] for image_path in self.all_image_paths
        ]

        self.all_images = sorted(self.all_images)
        # Remove all annotations and images when no object is present.
        self.read_and_clean()

    def read_and_clean(self):
        # Discard any images and labels when the XML
        # file does not contain any object.
        for annot_path in self.all_annot_paths:
            tree = et.parse(annot_path)
            root = tree.getroot()
            object_present = False
            for member in root.findall("object"):
                if member.find("bndbox"):
                    object_present = True

            if object_present is False:
                image_name = annot_path.split(os.path.sep)[-1].split(".xml")[0]
                image_root = self.all_image_paths[0].split(os.path.sep)[:-1]
                # remove_image = f"{'/'.join(image_root)}/{image_name}.jpg"
                remove_image = os.path.join(
                    # os.sep.join(image_root), image_name + ".jpg"
                    os.sep.join(image_root),
                    image_name + self.image_format,
                )
                print(f"Removing {annot_path} and corresponding {remove_image}")
                self.all_annot_paths.remove(annot_path)
                self.all_image_paths.remove(remove_image)

        # Discard any image file when no annotation file
        # is not found for the image.
        for image_name in self.all_images:
            possible_xml_name = os.path.join(
                self.labels_path, image_name.split(self.image_format)[0] + ".xml"
            )
            if possible_xml_name not in self.all_annot_paths:
                print(f"{possible_xml_name} not found...")
                print(f"Removing {image_name} image")
                # items = [item for item in items if item != element]
                self.all_images = [
                    image_instance
                    for image_instance in self.all_images
                    if image_instance != image_name
                ]
                # self.all_images.remove(image_name)

    def load_image_and_labels(self, index):
        image_name = self.all_images[index]
        image_path = os.path.join(self.images_path, image_name)

        # Read the image.
        image = cv2.imread(image_path)
        # Convert BGR to RGB color format.
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB).astype(np.float32)
        image_resized = cv2.resize(image, (self.width, self.height))
        image_resized /= 255.0

        # Capture the corresponding XML file for getting the annotations.
        annot_filename = image_name[:-4] + ".xml"
        annot_file_path = os.path.join(self.labels_path, annot_filename)

        boxes = []
        orig_boxes = []
        labels = []
        tree = et.parse(annot_file_path)
        root = tree.getroot()

        # Get the height and width of the image.
        image_width = image.shape[1]
        image_height = image.shape[0]

        # Box coordinates for xml files are extracted and corrected for image size given.
        for member in root.findall("object"):
            # Map the current object name to `classes` list to get
            # the label index and append to `labels` list.
            labels.append(self.classes.index(member.find("name").text))

            # xmin = left corner x-coordinates
            xmin = int(member.find("bndbox").find("xmin").text)
            # xmax = right corner x-coordinates
            xmax = int(member.find("bndbox").find("xmax").text)
            # ymin = left corner y-coordinates
            ymin = int(member.find("bndbox").find("ymin").text)
            # ymax = right corner y-coordinates
            ymax = int(member.find("bndbox").find("ymax").text)

            ymax, xmax = self.check_image_and_annotation(
                xmax, ymax, image_width, image_height
            )

            orig_boxes.append([xmin, ymin, xmax, ymax])

            # Resize the bounding boxes according to the
            # desired `width`, `height`.
            xmin_final = (xmin / image_width) * self.width
            xmax_final = (xmax / image_width) * self.width
            ymin_final = (ymin / image_height) * self.height
            ymax_final = (ymax / image_height) * self.height

            boxes.append([xmin_final, ymin_final, xmax_final, ymax_final])

        # Bounding box to tensor.
        boxes = torch.as_tensor(boxes, dtype=torch.float32)
        # Area of the bounding boxes.
        area = (boxes[:, 3] - boxes[:, 1]) * (boxes[:, 2] - boxes[:, 0])
        # No crowd instances.
        iscrowd = torch.zeros((boxes.shape[0],), dtype=torch.int64)
        # Labels to tensor.
        labels = torch.as_tensor(labels, dtype=torch.int64)
        return (
            image,
            image_resized,
            orig_boxes,
            boxes,
            labels,
            area,
            iscrowd,
            (image_width, image_height),
        )

    def check_image_and_annotation(self, xmax, ymax, width, height):
        """
        Check that all x_max and y_max are not more than the image
        width or height.
        """
        if ymax > height:
            ymax = height
        if xmax > width:
            xmax = width
        return ymax, xmax

    def __getitem__(self, idx):
        # Capture the image name and the full image path.
        # if not self.mosaic:
        image, image_resized, orig_boxes, boxes, labels, area, iscrowd, dims = (
            self.load_image_and_labels(index=idx)
        )

        # visualize_mosaic_images(boxes, labels, image_resized, self.classes)

        # Prepare the final `target` dictionary.
        target = {}
        target["boxes"] = boxes
        target["labels"] = labels
        target["area"] = area
        target["iscrowd"] = iscrowd
        image_id = torch.tensor([idx])
        target["image_id"] = image_id

        train_aug = get_train_aug()
        sample = train_aug(image=image_resized, bboxes=target["boxes"], labels=labels)
        image_resized = sample["image"]
        target["boxes"] = torch.Tensor(sample["bboxes"])

        return image_resized, target

    def __len__(self):
        return len(self.all_images)


def collate_fn(batch):
    """
    To handle the data loading as different images may have different number
    of objects and to handle varying size tensors as well.
    """
    return tuple(zip(*batch))


# Prepare the final datasets and data loaders.
def create_train_dataset(
    train_dir_images,
    train_dir_labels,
    image_format,
    resize_width,
    resize_height,
    classes,
):
    train_dataset = CustomDataset(
        train_dir_images,
        train_dir_labels,
        resize_width,
        resize_height,
        classes,
        image_format,
        train=True,
    )
    return train_dataset


def create_valid_dataset(
    valid_dir_images,
    valid_dir_labels,
    image_format,
    resize_width,
    resize_height,
    classes,
):
    valid_dataset = CustomDataset(
        valid_dir_images,
        valid_dir_labels,
        resize_width,
        resize_height,
        classes,
        image_format,
        train=False,
    )
    return valid_dataset


def create_train_loader(train_dataset, batch_size, num_workers=0):
    train_loader = DataLoader(
        train_dataset,
        batch_size=batch_size,
        shuffle=True,
        num_workers=num_workers,
        collate_fn=collate_fn,
    )
    return train_loader


def create_valid_loader(valid_dataset, batch_size, num_workers=0):
    valid_loader = DataLoader(
        valid_dataset,
        batch_size=batch_size,
        shuffle=False,
        num_workers=num_workers,
        collate_fn=collate_fn,
    )
    return valid_loader


def get_train_aug():
    return A.Compose(
        [
            A.MotionBlur(blur_limit=3, p=0.5),
            A.Blur(blur_limit=3, p=0.5),
            A.RandomBrightnessContrast(brightness_limit=0.2, p=0.5),
            A.ColorJitter(p=0.5),
            # A.Rotate(limit=10, p=0.2),
            A.RandomGamma(p=0.2),
            A.RandomFog(p=0.2),
            # A.RandomSunFlare(p=0.1),
            # `RandomScale` for multi-res training,
            # `scale_factor` should not be too high, else may result in
            # negative convolutional dimensions.
            # A.RandomScale(scale_limit=0.15, p=0.1),
            # A.Normalize(
            #     (0.485, 0.456, 0.406),
            #     (0.229, 0.224, 0.225)
            # ),
            ToTensorV2(p=1.0),
        ],
        bbox_params={"format": "pascal_voc", "label_fields": ["labels"]},
    )
