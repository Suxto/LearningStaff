#include <iostream>
#include <stdexcept>
#include <string>
using namespace std;
int main() {
    string str("foo");
    try {
        cout << str.at(5) << endl;
    } catch (const std::out_of_range& e) {
        cout << e.what() << endl;
    }
}