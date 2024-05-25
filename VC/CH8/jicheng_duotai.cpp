#include <iostream.h>
// �쳣��̳���ϵ�ṹ---��̬�԰�
class BasicException {
   public:
    virtual char* Where() { return "BasicException"; }
};

class FileException : public BasicException {
   public:
    char* Where() { return "FileException"; }
};

class FileNotFound : public FileException {
   public:
    char* Where() { return "FileNotFound"; }
};

class DiskNotFound : public FileException {
   public:
    char* Where() { return "DiskNotFound"; }
};

void main() {
    try {
        throw FileException();
    } catch (BasicException& p) {
        cout << p.Where() << endl;
    }

    try {
        throw DiskNotFound();
    }

    catch (BasicException& p) {
        cout << p.Where() << endl;
    }
}
