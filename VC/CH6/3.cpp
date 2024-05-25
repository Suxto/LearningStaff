#include <FSTREAM>
#include <IOSTREAM>
using namespace std;

int main() {
    fstream fin;
    fin.open("tmp.txt", ios::in);
    int cnt = 0;
    char line[256];
    char *obj = "the";
    while (fin.getline(line, 255)) {
        int len = strlen(line);
        for (int i = 0; i < len; i++) {
            if (line[i] == obj[0]) {
                bool flag = true;
                for (int j = 1; j < 3; j++) {
                    if (i + j >= len || line[i + j] != obj[j]) {
                        flag = false;
                        break;
                    }
                }
                cnt += flag;
            }
        }
    }
    cout << cnt;
    return 0;
}