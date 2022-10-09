#include<iostream>

using namespace std;

struct node {
    node *lChild = nullptr, *rChild = nullptr;
    char data = 0;
    int num = 0;

    friend ostream &operator<<(ostream &o, node *n) {
        cout << n->data << n->num;
        return o;
    }
};

void build(node *now, char ch) {
    now->data = ch;
    char chr = static_cast<char>(cin.get());
    if (chr != ' ') {
        now->lChild = new node;
        build(now->lChild, chr);
    }
    chr = static_cast<char>(cin.get());
    if (chr != ' ') {
        now->rChild = new node;
        build(now->rChild, chr);
    }
}

void print(node *now, int lay = 0) {
//    cout.put(now->data);
//    cout.put((now->num) + '0');
    if (now == nullptr) return;
    print(now->rChild, lay + 1);
    for (int i = 0; i < lay; i++)cout << "   ";
    cout << now << ' ' << endl;
    print(now->lChild, lay + 1);

}

void count(node *now) {
    static int cnt = 0;
    if (now == nullptr) return;
    count(now->lChild);
    count(now->rChild);
    now->num = ++cnt;
}

int main() {
    node *head = new node;
    build(head, static_cast<char>(cin.get()));
    count(head);
    print(head);
}
