# 动态内存

C++ 程序中的内存分为两个部分：

- **栈：**在函数内部声明的所有变量都将占用栈内存。
- **堆：**这是程序中未使用的内存，在程序运行时可用于动态分配内存。

**内存分配有三种方式：**

1. 从静态存储区域分配，内存在编译期间就已经分配好，这块内存在整个运行期间都存在，例如static变量。
2. **从栈上创建**：函数内局部变量，**自动分配与回收**，效率高，但是分配的内存量有限。(不用new建立对象）
3. **从堆上创建**：由程序员控制，malloc、new，free、delete。

## new和delete运算符

- new可以返回分配空间的地址，new和malloc的不同是new在分配内存的同时创建了对象（如果无法分配内存会返回NULL指针）
    
    ```cpp
    double* pvalue  = NULL;
    if( !(pvalue  = new double )){//检查指针是否为null
       cout << "Error: out of memory." <<endl;
    }
    ```
    
- delete用于删除 **new** 分配的内存
    
    ```cpp
    int main (){
       double* pvalue  = NULL; // 初始化为 null 的指针
       pvalue  = new double;   // 为变量请求内存
       *pvalue = 29494.99;     // 在分配的地址存储值
       cout << "Value of pvalue : " << *pvalue ;
       delete pvalue;         // 释放内存
       return 0;
    }
    ```
    
    ## 数组：（可以建立对象数组）
    
    ### 一维数组：
    
    ```cpp
    int *array=new int [m];
    
    delete [] array;
    ```
    
    ### 二维数组
    
    ```cpp
    #include <iostream>
    using namespace std;
     
    int main(){
        int **p;   //指向指针的指针 
        int i,j;   //p[4][8] 
        //开始分配4行8列的二维数据   
        p = new int *[4];//建立**指针**数组对象
        for(i=0;i<4;i++){
            p[i]=new int [8];//将new出来的数组对象的指针塞入上一个指针数组
        }
     
        for(i=0; i<4; i++){
            for(j=0; j<8; j++){
                p[i][j] = j*i;
            }
        }   
        //打印数据   
        for(i=0; i<4; i++){
            for(j=0; j<8; j++)     
            {   
                if(j==0) cout<<endl;   
                cout<<p[i][j]<<"\t";   
            }
        }   
        //开始释放申请的堆   
        for(i=0; i<4; i++){
            delete [] p[i];//删除每一行的数组对象   
        }
        delete [] p;  //删除指针数组 
        return 0;
    }
    ```
    
    ### 三维数组：
    
    ```cpp
    #include <iostream>
    using namespace std;
    int main(){   
        int i,j,k;   // p[2][3][4]
        int ***p;
        p = new int **[2]; 
        for(i=0; i<2; i++) { 
            p[i]=new int *[3]; 
            for(j=0; j<3; j++) 
                p[i][j]=new int[4]; 
        }
        
        //输出 p[i][j][k] 三维数据
        for(i=0; i<2; i++) {
            for(j=0; j<3; j++) { 
                for(k=0;k<4;k++) { 
                    p[i][j][k]=i+j+k;
                    cout<<p[i][j][k]<<" ";
                }
                cout<<endl;
            }
            cout<<endl;
        }
        
        // 释放内存
        for(i=0; i<2; i++) {
            for(j=0; j<3; j++) {   
                delete [] p[i][j];   
            }   
        }       
        for(i=0; i<2; i++) {       
            delete [] p[i];   
        }   
        delete [] p;  
        return 0;
    }
    ```
    
    **总结：**
    
    - **从外到内建立:**
        - 要建立几维数组就先建立几维指针
        - 从第一层开始一层一层的用循环建立数组，塞到上一层建立的数组中去
    - **从外到内释放**
        - 从最外层数组开始delete