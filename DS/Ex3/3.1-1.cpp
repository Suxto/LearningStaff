#include<bits/stdc++.h>
#define MAXN 100000
using namespace std;

int main() {
	int stack[MAXN], idx = 0;
	string str;
	cin >> str;
	int len = str.length() >> 1;
	int i = 0;
	for(; i < len; i++) {
		stack[idx++] = str[i];
	}
	if(str.length() & 1) i++;
	for(; i < str.length(); i++) {
		if(stack[--idx] != str[i]) {
			cout << "No\n";
			return 0;
		}
	}
	cout << "Yes\n";
}