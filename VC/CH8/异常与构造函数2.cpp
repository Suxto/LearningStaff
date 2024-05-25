#include <iostream.h>

class B
{
public:
	B(int k)
	{
		p = new int[k]; 
		cout<<" constructing B"<<endl;
		if(k >= 10)
		{
			delete []p;
			throw k;
		}
	}
	~B()
	{
		cout<<" destructing B... "<<endl;
	}
private:
	int* p;
};
void main()
{
	try 
	{
		B b(20);
	}
	catch (int e)
	{
		cout<<" catch an exception "<<e<<endl;
	}
}
