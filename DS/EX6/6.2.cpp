#include <iostream>
#include <fstream>

using namespace std;
const int MOD = 80;
const int EXTRA = 40;
struct student {
    int age = -1;
    string name;
    string gender;
} students[MOD + EXTRA];

int getHash(const string &str) {
    int hash = 0;
    for (const char &ch: str) hash += ch - 'a';
    return hash % MOD;
}

void insert(string &name, string &gender, int age) {
    int pos = getHash(name);
    while (students[pos].age != -1) {
        pos++;
        if (pos >= MOD + EXTRA) {
            cout << "No enough space!\n";
            return;
        }
    }
    students[pos].age = age;
    students[pos].name = name;
    students[pos].gender = gender;
}

int find(const string &name) {
    int cnt = 1;
    int pos = getHash(name);
    while (students[pos].name != name) {
        if (students[pos].age == -1) {
            cout << "No such a student\n";
            return cnt;
        }
        pos++;
        cnt++;
        if (pos >= MOD + EXTRA) {
            cout << "No such a student\n";
            return cnt;
        }
    }
    cout << "Name: " << students[pos].name << endl;
    cout << "Age: " << students[pos].gender << endl;
    cout << "Gender: " << students[pos].gender << endl;
    return cnt;
}

void fileIn() {
    fstream f("stu.txt", ios::in);
    int t;
    f >> t;
    while (t--) {
        string name, gender;
        int age;
        f >> name >> gender >> age;
        insert(name, gender, age);
    }
}

int main() {
    fileIn();
    while (true) {
        cout << "Please enter a name(enter 0 for exit):";
        string str;
        cin >> str;
        if (str == "0") break;
        int t = find(str);
        cout << "It takes " << t << (t > 1 ? " times" : " time") << endl << endl;
    }
}

//ryz nkf zetrezg
