import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class FileManagementSystemGUI extends Application {
    static final int BLOCK_SIZE = 64;
    static final byte ATTR_POS = 5;
    static final byte START_POS = 6;
    static final byte LEN_POS = 7;
    static final byte DIR_ITEM_NUM = 8;
    static final byte DIR_ITEM_LEN = 8;
    static final int BLOCK_NUM = 128;
    static final byte FOLDER_ATTR = 1 << 3;
    static final byte FILE_ATTR = 1 << 2;
    static final int ROOT_BLOCK = 4;
    private final static byte[] buffer = new byte[BLOCK_SIZE];
    private final static byte[] FAT = new byte[BLOCK_NUM];
    private final static byte[] PRE = new byte[BLOCK_NUM];
    private static RandomAccessFile disk;
    private static final TextField pathField = new TextField("/");
    private static ObservableList<Node> items;
    private static byte currentDir = ROOT_BLOCK;

    public static void main(String[] args) {
        launch(args);
    }

    public static void readBuffer(long blockNum) {
        try {
            disk.seek(blockNum * BLOCK_SIZE);
            disk.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeBuffer(long blockNum) {
        try {
            disk.seek(blockNum * BLOCK_SIZE);
            disk.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFAT() {
        readBuffer(0);
        System.arraycopy(buffer, 0, FAT, 0, BLOCK_SIZE);
        readBuffer(1);
        System.arraycopy(buffer, 0, FAT, BLOCK_SIZE, BLOCK_SIZE);
    }

    public static void readPRE() {
        readLongBuffer(2, PRE);
    }

    public static void writeFAT() {
        byte[] half = new byte[BLOCK_SIZE];
        System.arraycopy(FAT, 0, half, 0, BLOCK_SIZE);
        try {
            disk.seek(0);
            disk.write(half);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.arraycopy(FAT, BLOCK_SIZE, half, 0, BLOCK_SIZE);
        try {
            disk.seek(BLOCK_SIZE);
            disk.write(half);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writePRE() {
        byte[] half = new byte[BLOCK_SIZE];
        System.arraycopy(PRE, 0, half, 0, BLOCK_SIZE);
        try {
            disk.seek(2 * BLOCK_SIZE);
            disk.write(half);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.arraycopy(PRE, BLOCK_SIZE, half, 0, BLOCK_SIZE);
        try {
            disk.seek(3 * BLOCK_SIZE);
            disk.write(half);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public void initDisk() {
        try {
            disk = new RandomAccessFile("disk.bin", "rw");
            disk.setLength(BLOCK_NUM * BLOCK_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        readFAT();
        if (FAT[0] == 0) {//New File
            FAT[0] = 1;
            FAT[2] = 3;
            FAT[1] = FAT[3] = FAT[ROOT_BLOCK] = -1;
            writeFAT();
        } else readPRE();
    }

    static void readLongBuffer(int beg, byte[] buf) {
        int i = 0;
        while (beg != -1) {
            readBuffer(beg);
            System.arraycopy(buffer, 0, buf, i * BLOCK_SIZE, BLOCK_SIZE);
            i++;
            beg = FAT[beg];
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("文件系统");
        MenuItem miNewFolder = new MenuItem("新建文件夹");
        miNewFolder.setOnAction(e -> createFolder());
        MenuItem miNewFile = new MenuItem("新建文件");
        miNewFile.setOnAction(e -> createFile());
        Menu mFile = new Menu("文件");
        mFile.getItems().addAll(miNewFolder, miNewFile);
        MenuBar menuBar = new MenuBar(mFile);
        Label label = new Label("当前路径 ");
        Button btBack = new Button("返回上级");
        btBack.setOnAction(e -> exitDirectory());
        pathField.prefWidthProperty().bind(primaryStage.widthProperty().subtract(label.widthProperty().multiply(4)));
        pathField.setEditable(false);
        HBox hBox = new HBox(label, pathField, btBack);
        hBox.setSpacing(10);
        FlowPane itemPane = new FlowPane();
        itemPane.setHgap(10);
        itemPane.setVgap(10);
        items = itemPane.getChildren();

        initDisk();
        loadDirectory();
        VBox root = new VBox(menuBar, hBox, itemPane);
        root.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.SECONDARY) {
//                Item.clearSelection();
                return;
            }
            ContextMenu contextMenu = new ContextMenu();

            MenuItem newFolder = new MenuItem("新建文件夹");
            newFolder.setOnAction(e -> createFolder());

            MenuItem newFile = new MenuItem("新建文件");
            newFile.setOnAction(e -> createFile());

            MenuItem refresh = new MenuItem("刷新");
            refresh.setOnAction(e -> loadDirectory());

            contextMenu.getItems().addAll(newFolder, newFile, refresh);
            // 在鼠标位置显示右键菜单
            contextMenu.show(itemPane, event.getScreenX(), event.getScreenY());
            event.consume();
        });


        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static boolean saveFile(Item item, String data) {
        int inputLen = data.getBytes().length;
        int len = (inputLen + BLOCK_SIZE - 1) / BLOCK_SIZE;
        if (len > item.getLength() && !enoughSpace(len - item.getLength())) return false;
        byte[] bytes = new byte[len * BLOCK_SIZE];
        System.arraycopy(data.getBytes(StandardCharsets.US_ASCII), 0, bytes, 0, inputLen);
        int block = item.getStartBlock();
        //System.out.println("len: " + len + "\nSiz: " + item.getLength());
        if (len < item.getLength()) { //shorter
            int nex = 0;
            for (int i = 0; i < len; i++) {
                try {
                    disk.seek(block * BLOCK_SIZE);
                    disk.write(bytes, i * BLOCK_SIZE, BLOCK_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (i == len - 1) {
                    nex = FAT[block];
                    FAT[block] = -1;
                } else block = FAT[block];
            }
            for (int i = len; i < item.getLength() && nex != -1; i++) {
                delBlock(nex);
                int pre = nex;
                nex = FAT[nex];
                FAT[pre] = 0;
            }
            item.setLength((byte) len);
            updateCode(item);
            writeFAT();
        } else if (len == item.getLength()) {
            for (int i = 0; i < len; i++) {
                try {
                    disk.seek(block * BLOCK_SIZE);
                    disk.write(bytes, i * BLOCK_SIZE, BLOCK_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                block = FAT[block];
            }
        } else {
            for (int i = 0; i < item.getLength(); i++) {
                try {
                    disk.seek(block * BLOCK_SIZE);
                    disk.write(bytes, i * BLOCK_SIZE, BLOCK_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (FAT[block] == -1) {
                    break;
                } else block = FAT[block];
            }
            for (int i = item.getLength(); i < len; i++) {
                int nxt = freeSpace();
                FAT[block] = (byte) nxt;
                block = nxt;
                try {
                    disk.seek((long) block * BLOCK_SIZE);
                    disk.write(bytes, i * BLOCK_SIZE, BLOCK_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FAT[block] = -1;
            item.setLength((byte) len);
            updateCode(item);
            writeFAT();
        }
        return true;
    }

    public static void updateCode(Item item) {
        byte startBlock = item.getStartBlock();
        byte dirIdx = PRE[startBlock];
        readBuffer(dirIdx);
        int idx = -1;
        for (int i = 0; i < DIR_ITEM_NUM; i++) {
            if (buffer[i * DIR_ITEM_LEN + START_POS] == startBlock) {
                idx = i;
                break;
            }
        }
        System.arraycopy(item.getCode(), 0, buffer, idx * DIR_ITEM_NUM, DIR_ITEM_LEN);
        writeBuffer(dirIdx);
    }

    public static boolean enoughSpace(int x) {
        int cnt = 0;
        for (int i = ROOT_BLOCK + 1; i < BLOCK_NUM; i++) {
            if (FAT[i] == 0) {
                cnt++;
                if (cnt == x) return true;
            }
        }
        return false;
    }

    public static void openFile(Item item) {
        byte[] buf = new byte[item.getLength() * BLOCK_SIZE];
        readLongBuffer(item.getStartBlock(), buf);
        new editStage(buf, item);
    }

    private void createFile() {
        readBuffer(currentDir);
        int idx = addableDirectory();
        if (idx == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("无法新建文件");
            alert.setContentText("文件和文件夹数量达到目录项上限");
            // 显示警告窗口
            alert.showAndWait();
            return;
        }
        byte blockNum = (byte) freeSpace();
        if (blockNum == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("无法新建文件");
            alert.setContentText("磁盘已满");
            // 显示警告窗口
            alert.showAndWait();
            return;
        }
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("新建文件");
        dialog.setHeaderText("请填写文件名和文件类型");
        // 创建表单字段
        TextField nameField = new TextField();
        nameField.setPromptText("文件名");
        TextField typeField = new TextField();
        typeField.setPromptText("类型");

        // 创建布局
        HBox hBox = new HBox(nameField, new Label("."), typeField);
        hBox.setSpacing(10);
        // 设置对话框内容
        dialog.getDialogPane().setContent(hBox);

        // 添加按钮到对话框
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        // 处理对话框结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // 获取输入的名称
                String name = nameField.getText();
                if (name.getBytes(StandardCharsets.US_ASCII).length > 3) {
                    // 弹出提示
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText(null);
                    alert.setContentText("文件名称长度不能超过3个字节，且只能是英文");
                    alert.showAndWait();
                    return null;
                }
                String type = typeField.getText();
                if (type.getBytes(StandardCharsets.US_ASCII).length > 2) {
                    // 弹出提示
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText(null);
                    alert.setContentText("文件类型长度不能超过2个字节，且只能是英文");
                    alert.showAndWait();
                    return null;
                }
                // 返回表单字段的值
                return new String[]{name, type};
            }
            return null;
        });
        dialog.showAndWait().ifPresent(res -> {
            Item item = new Item(res[0] + "." + res[1], FILE_ATTR, blockNum, (byte) 1);
            FAT[blockNum] = -1;
            writeFAT();
            PRE[blockNum] = currentDir;
            writePRE();
            System.arraycopy(item.getCode(), 0, buffer, idx * DIR_ITEM_LEN, DIR_ITEM_LEN);
            writeBuffer(currentDir);
            loadDirectory();
        });
    }

    static void delFile(int block) {
        int dirBlock = PRE[block];
        int idx = -1;
        readBuffer(dirBlock);
        for (int i = 0; i < DIR_ITEM_NUM; i++) {
            if (buffer[i * DIR_ITEM_LEN + START_POS] == block) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println(-1);
            return;
        }
        PRE[block] = 0;
        writePRE();
        Arrays.fill(buffer, idx * DIR_ITEM_LEN, (idx + 1) * DIR_ITEM_LEN, (byte) 0);
        writeBuffer(dirBlock);
        while (block != -1) {
            delBlock(block);
            int preBlock = block;
            block = FAT[block];
            FAT[preBlock] = 0;
        }
        writeFAT();
        loadDirectory();
    }

    static void delFolder(int block) {
        int dirBlock = PRE[block];
        int idx = -1;
        readBuffer(dirBlock);
        for (int i = 0; i < DIR_ITEM_NUM; i++) {
            if (buffer[i * DIR_ITEM_LEN + START_POS] == block) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println(-1);
            return;
        }
        Arrays.fill(buffer, idx * DIR_ITEM_LEN, (idx + 1) * DIR_ITEM_LEN, (byte) 0);
        writeBuffer(dirBlock);

        for (int i = 0; i < DIR_ITEM_NUM; i++) {
            readBuffer(block);
            byte startBlock = buffer[i * DIR_ITEM_LEN + START_POS];
            if (startBlock == 0) continue;
            if ((buffer[i * DIR_ITEM_LEN + ATTR_POS] & FOLDER_ATTR) == 0) delFile(startBlock);
            else delFolder(startBlock);
        }
        delBlock(block);
        FAT[block] = 0;
        PRE[block] = 0;
        writeFAT();
        writePRE();
    }

    static void rename(Item item) {
        if (item.isFolder()) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("重命名文件夹");
            dialog.setHeaderText("请填写以下字段");
            // 创建表单字段
            TextField nameField = new TextField();
            nameField.setPromptText("文件夹名称");

            // 创建布局
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));
            gridPane.add(new Label("文件夹名称:"), 0, 0);
            gridPane.add(nameField, 1, 0);

            // 设置对话框内容
            dialog.getDialogPane().setContent(gridPane);

            // 添加按钮到对话框
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            // 处理对话框结果
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    // 获取输入的名称
                    String name = nameField.getText();
                    if (name.getBytes(StandardCharsets.US_ASCII).length > 5) {
                        // 弹出提示
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误");
                        alert.setHeaderText(null);
                        alert.setContentText("文件夹名称长度不能超过5个字节，且只能是英文");
                        alert.showAndWait();
                        return null;
                    }
                    // 返回表单字段的值
                    return name;
                }
                return null;
            });
            dialog.showAndWait().ifPresent(res -> {
                item.setName(res);
                updateCode(item);
                loadDirectory();
            });
        } else {
            Dialog<String[]> dialog = new Dialog<>();
            dialog.setTitle("重命名文件");
            dialog.setHeaderText("请填写文件名和文件类型");
            // 创建表单字段
            TextField nameField = new TextField();
            nameField.setPromptText("文件名");
            TextField typeField = new TextField();
            typeField.setPromptText("类型");

            // 创建布局
            HBox hBox = new HBox(nameField, new Label("."), typeField);
            hBox.setSpacing(10);
            // 设置对话框内容
            dialog.getDialogPane().setContent(hBox);

            // 添加按钮到对话框
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            // 处理对话框结果
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    // 获取输入的名称
                    String name = nameField.getText();
                    if (name.getBytes(StandardCharsets.US_ASCII).length > 3) {
                        // 弹出提示
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误");
                        alert.setHeaderText(null);
                        alert.setContentText("文件名称长度不能超过3个字节，且只能是英文");
                        alert.showAndWait();
                        return null;
                    }
                    String type = typeField.getText();
                    if (type.getBytes(StandardCharsets.US_ASCII).length > 2) {
                        // 弹出提示
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("错误");
                        alert.setHeaderText(null);
                        alert.setContentText("文件类型长度不能超过2个字节，且只能是英文");
                        alert.showAndWait();
                        return null;
                    }
                    // 返回表单字段的值
                    return new String[]{name, type};
                }
                return null;
            });
            dialog.showAndWait().ifPresent(res -> {
                item.setName(res[0] + "." + res[1]);
                updateCode(item);
                loadDirectory();
            });
        }
    }

    static void delBlock(int blockNum) {
        Arrays.fill(buffer, (byte) 0);
        writeBuffer(blockNum);
    }

    static void enterDirectory(Item item) {
        currentDir = item.getStartBlock();
        pathField.appendText(item.getName() + "/");
        loadDirectory();
    }

    static void exitDirectory() {
        if (currentDir == ROOT_BLOCK) return;
        currentDir = PRE[currentDir];
        String tmp = pathField.getText();
        int i;
        for (i = tmp.length() - 2; i >= 0; i--) {
            if (tmp.charAt(i) == '/') break;
        }
        pathField.setText(tmp.substring(0, i + 1));
        loadDirectory();
    }

    static void loadDirectory() {
        items.clear();
        readBuffer(currentDir);
        for (int i = 0; i < DIR_ITEM_NUM; i++) {
            byte[] tmp = new byte[DIR_ITEM_LEN];
            System.arraycopy(buffer, i * DIR_ITEM_LEN, tmp, 0, DIR_ITEM_LEN);
            if (tmp[LEN_POS] == 0) continue;
            items.add(new Item(tmp));
        }
    }

    void createFolder() {
        readBuffer(currentDir);
        int idx = addableDirectory();
        if (idx == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("无法新建文件夹");
            alert.setContentText("文件和文件夹数量达到目录项上限");
            // 显示警告窗口
            alert.showAndWait();
            return;
        }
        byte blockNum = (byte) freeSpace();
        if (blockNum == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("无法新建文件夹");
            alert.setContentText("磁盘已满");
            // 显示警告窗口
            alert.showAndWait();
            return;
        }
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("新建文件夹");
        dialog.setHeaderText("请填写以下字段");
        // 创建表单字段
        TextField nameField = new TextField();
        nameField.setPromptText("文件夹名称");

        // 创建布局
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        gridPane.add(new Label("文件夹名称:"), 0, 0);
        gridPane.add(nameField, 1, 0);

        // 设置对话框内容
        dialog.getDialogPane().setContent(gridPane);

        // 添加按钮到对话框
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        // 处理对话框结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // 获取输入的名称
                String name = nameField.getText();
                if (name.getBytes(StandardCharsets.US_ASCII).length > 5) {
                    // 弹出提示
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误");
                    alert.setHeaderText(null);
                    alert.setContentText("文件夹名称长度不能超过5个字节，且只能是英文");
                    alert.showAndWait();
                    return null;
                }
                // 返回表单字段的值
                return name;
            }
            return null;
        });
        dialog.showAndWait().ifPresent(res -> {
            Item item = new Item(res, FOLDER_ATTR, blockNum, (byte) 1);
            FAT[blockNum] = -1;
            PRE[blockNum] = currentDir;
            writeFAT();
            writePRE();
            System.arraycopy(item.getCode(), 0, buffer, idx * DIR_ITEM_LEN, DIR_ITEM_LEN);
            writeBuffer(currentDir);
            loadDirectory();
        });
    }


    static int addableDirectory() {
        readBuffer(currentDir);
        for (int i = 0; i < DIR_ITEM_NUM; i++) {
            if (buffer[i * DIR_ITEM_LEN + LEN_POS] == 0) return i;
        }
        return -1;
    }

    static int freeSpace() {
        for (int i = ROOT_BLOCK + 1; i < BLOCK_NUM; i++) {
            if (FAT[i] == 0) return i;
        }
        return -1;
    }
}

