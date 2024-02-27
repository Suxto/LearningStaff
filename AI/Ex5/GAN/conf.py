import torch
from typing import Literal

# 设置超参数
batch_size = 64
input_dim = 100
output_dim = 784
num_epochs = 100
num_classes = 10
data_path = '../../Data'

device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
print(f"using {device}")


def model_path(model_type: Literal["discriminator", "generator"], idx: int | None = None):
    if idx is None:
        return './models/' + model_type + '.pth'
    else:
        return './models/' + model_type + '_' + str(idx) + '.pth'
