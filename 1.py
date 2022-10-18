import os

path = "C:\\Users\\LY\\OneDrive\\Documents\\LearningStaff\\Java\\Ch8"
names=os.listdir(path)
# names.sort()
print("左边是题目，右边是对应文件")
for i in names:
    if(i[4]=='.'):
        print(i[1]+"."+i[3]+" ==> "+i)
    else:
        print(i[1]+"."+i[3]+i[4]+" ==> "+i)
    
