#include <FSTREAM>
#include <IOSTREAM>
using namespace std;

int main() {
    fstream fin;
    fin.open("tmp.txt", ios::in);
    int cnt = 0;
    char line[256];
    while (fin.getline(line, 255)) cnt++;
    cout << cnt;
    return 0;
}