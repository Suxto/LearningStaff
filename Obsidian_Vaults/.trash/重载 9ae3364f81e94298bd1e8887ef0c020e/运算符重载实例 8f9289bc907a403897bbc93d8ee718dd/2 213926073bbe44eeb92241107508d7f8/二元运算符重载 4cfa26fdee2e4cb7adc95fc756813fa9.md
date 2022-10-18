# 二元运算符重载

**重载+的实例：**

```cpp
#include <iostream>
using namespace std;
class Box{
   double length;      // 长度
   double breadth;     // 宽度
   double height;      // 高度
public:
   double getVolume(void){
      return length * breadth * height;
   }
   void setLength( double len ){
       length = len;
   }
 
   void setBreadth( double bre ){
       breadth = bre;
   }
 
   void setHeight( double hei ){
       height = hei;
   }
   // 重载 + 运算符，用于把两个 Box 对象相加
	**//这个函数的实质是this.operator+(b)**
   Box operator+(const Box& b){
      Box box;
      box.length = this->length + b.length;
      box.breadth = this->breadth + b.breadth;
      box.height = this->height + b.height;
      return box;//注意返回的是对象
   }
};
// 程序的主函数
int main( )
{
   Box Box1;                // 声明 Box1，类型为 Box
   Box Box2;                // 声明 Box2，类型为 Box
   Box Box3;                // 声明 Box3，类型为 Box
   double volume = 0.0;     // 把体积存储在该变量中
 
   // Box1 详述
   Box1.setLength(6.0); 
   Box1.setBreadth(7.0); 
   Box1.setHeight(5.0);
 
   // Box2 详述
   Box2.setLength(12.0); 
   Box2.setBreadth(13.0); 
   Box2.setHeight(10.0);
 
   // Box1 的体积
   volume = Box1.getVolume();
   cout << "Volume of Box1 : " << volume <<endl;
 
   // Box2 的体积
   volume = Box2.getVolume();
   cout << "Volume of Box2 : " << volume <<endl;
 
   // 把两个对象相加，得到 Box3
   Box3 = Box1 + Box2;
 
   // Box3 的体积
   volume = Box3.getVolume();
   cout << "Volume of Box3 : " << volume <<endl;
 
   return 0;
}
```

**通过友元函数使+左右的数值可以调换**：

```cpp
#include<iostream>
using namespace std;
class A{
    private:
        int a;
    public:
            A();
            A(int n);
            A operator+(const A & obj);
            A operator+(const int b);
    friend A operator+(const int b, A obj); 
            void display(); 
} ;
A::A(){
    a=0;
}
A::A(int n){
    a=n;
}
A A::operator +(const A& obj)//重载+号用于 对象相加 
{
    return this->a+obj.a;
}
A A::operator+(const int b){//重载+号用于  对象与数相加
    return A(a+b);
}
A operator+(const int b,  A obj){//这个函数也可以写为全局函数
    return obj+b;//友元函数调用第二个重载+的成员函数  相当于 obj.operator+(b); 
}
//friend Box operator+(const Box& a, const Box& b);//写为全局函数的时候要声明为友元函数！
void A::display()
{
    cout<<a<<endl;
}
int main ()
{
    A a1(1);
    A a2(2);
    A a3,a4,a5;
    a1.display();
    a2.display();
    int m=1;
    a3=a1+a2;//可以交换顺序，相当于a3=a1.operator+(a2); 
    a3.display();
    a4=a1+m;//因为加了个友元函数所以也可以交换顺序了。
    a4.display();
    a5=m+a1;//使用了友元函数
    a5.display();
}
```