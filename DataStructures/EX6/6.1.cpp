#include<iostream>

using namespace std;

struct node {
    int val = 0;
    string name;
    string author;
    double price;
    node *l = nullptr, *r = nullptr;
};

int getVal(const string &str) {
    int ans = 0;
    for (const char &ch: str) {
        ans += ch;
        ans %= 100000009;
    }
    return ans;
}

node *insert(node *&head, int v) {
    if (head == nullptr) {
        head = new node;
        head->val = v;
        return head;
    }
    if (v < head->val) return insert(head->l, v);
    else return insert(head->r, v);
}

node *find(node *head, const string &name, int v) {
    if (head == nullptr) return nullptr;
    if (v < head->val) return find(head->l, name, v);
    else if (v > head->val) return find(head->r, name, v);
    else {
        if (head->name == name) return head;
        else return find(head->r, name, v);
    }
}

void go(node *head) {
    if (head == nullptr) return;
    go(head->l);
    cout << head->val << ' ';
    go(head->r);
}

void printABook(node *n) {
    if (n == nullptr)return;
    cout << "Name: " << n->name << endl;
    cout << "Author: " << n->author << endl;
    cout << "Price: " << n->price << endl;
}

int main() {
    node *head = nullptr;
    int t;
    cin >> t;
    while (t--) {
        string name, author;
        double price;
        cin >> name >> author >> price;
        int val = getVal(name);
        node *now = insert(head, val);
        now->name = name;
        now->author = author;
        now->price = price;
    }
    while (true) {
        cout << "Please enter a name you want to find(enter 0 for exit):";
        string name;
        cin >> name;
        if (name == "0") break;
        node *now = find(head, name, getVal(name));
        if (now == nullptr) {
            cout << "No such a book!\n";
            continue;
        }
        printABook(now);
        now = now->r;
        while (now != nullptr) {
            if (now->name == name) {
                cout << "--------------------------------------\n";
                printABook(now);
            } else break;
            now = now->r;
        }
    }
}
/*
7
C++_primer Stanley 50
Java occ 30
Python py 34
Linux Linus 23
Python py1 34
JavaScript kami 30
Python py2 34
 */