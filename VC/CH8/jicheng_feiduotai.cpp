#include <iostream.h>

// 异常类继承体系结构---非多态性版
class BasicException {
   public:
    char* Where() { return "BasicException"; }
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
    } catch (DiskNotFound p) {
        cout << p.Where() << endl;
    } catch (FileNotFound p) {
        cout << p.Where() << endl;
    } catch (FileException p) {
        cout << p.Where() << endl;
    } catch (BasicException p) {
        cout << p.Where() << endl;
    }

    try {
        throw DiskNotFound();
    }

    catch (DiskNotFound p) {
        cout << p.Where() << endl;
    } catch (FileNotFound p) {
        cout << p.Where() << endl;
    } catch (FileException p) {
        cout << p.Where() << endl;
    } catch (BasicException p) {
        cout << p.Where() << endl;
    }
}
