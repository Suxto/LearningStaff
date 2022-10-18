# 函数调用运算符 () 重载

```cpp
#include <iostream>
using namespace std;
class Distance{
   private:
      int feet;             // 0 到无穷
      int inches;           // 0 到 12
   public:
      // 所需的构造函数
      Distance(){
         feet = 0;
         inches = 0;
      }
      Distance(int f, int i){
         feet = f;
         inches = i;
      }
      // 重载函数调用运算符
      Distance operator()(int a, int b, int c){
         Distance D;
         // 进行随机计算
         D.feet = a + c + 10;
         D.inches = b + c + 100 ;
         return D;
      }
      // 显示距离的方法
      void displayDistance(){
         cout << "F: " << feet <<  " I:" <<  inches << endl;
      }
      
};
int main(){
   Distance D1(11, 10), D2;
   cout << "First Distance : "; 
   D1.displayDistance();
   D2 = D1(10, 10, 10); // invoke operator()
	 //此后D1依旧保持原有的值，因为函数体内没有改变原对象的值的语句
   cout << "Second Distance :"; 
   D2.displayDistance();
   return 0;
}
```