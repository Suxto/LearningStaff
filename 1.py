import os

path = "C:\\Users\\Suxto\\OneDrive\\Documents\\LearningStaff\\Java\\Ch15"
names=os.listdir(path)
# names.sort()
print("左边是题目，右边是对应文件")
for i in names:
    if(i[5]=='.'): 
        if(i[-1]=='g'): print(i[1]+i[2]+"."+i[4]+" 的类图 ==> "+i)
        else: print(i[1]+i[2]+"."+i[4]+" ==> "+i)

    else:
        if(i[-1]=='g'): print(i[1]+i[2]+"."+i[4]+i[5]+" 的类图 ==> "+i)
        print(i[1]+i[2]+"."+i[4]+i[5]+" ==> "+i)
    
