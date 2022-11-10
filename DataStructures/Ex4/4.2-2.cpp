#include<iostream>
#include <functional>

using namespace std;

template<typename T>
class myStack {
public:
    int topptr = 0;
    T *arr = nullptr;

    explicit myStack(int n) {
        arr = new T[n];
    }

    myStack() = default;

    [[nodiscard]] T top() const {
        return *(arr + topptr);
    };

    [[nodiscard]] bool empty() const {
        return topptr == 0;
    }

    void push(T x) {
        topptr++;
        *(arr + topptr) = x;
    }

    T pop() {
//        if (topptr == 0) return 0;
//        else
        return *(arr + topptr--);
    }

    friend ostream &operator<<(ostream &o, myStack &s) {
//        for (int i = 1; i <= s.topptr; i++) {
//            o << s.arr[i] << ' ';
//        }
        while (!s.empty())
            o << s.pop() << ' ';
        return o;
    }
};

struct node {
    node *lChild = nullptr, *rChild = nullptr;
    int data = 0;

    friend ostream &operator<<(ostream &o, node *n) {
        cout << n->data;
        return o;
    }
};


void build(node *now, int ch) {
    now->data = ch;
    int chr;
    cin >> chr;
    if (chr != 0) {
        now->lChild = new node;
        build(now->lChild, chr);
    }
    cin >> chr;
    if (chr != 0) {
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


int main() {
    //1 2 0 4 0 0 3 5 0 6 0 0 0
    node *head = new node;
    int x;
    cin >> x;
    build(head, x);
    cin >> x;
    auto stack = myStack<int>(100);
    function<bool(node *, int)> dfs = [&](node *now, int x) {
        if (now == nullptr) return false;
        if (now->data == x) {
            stack.push(now->data);
            return true;
        }
        if (dfs(now->lChild, x)) {
            stack.push(now->data);
            return true;
        }
        if (dfs(now->rChild, x)) {
            stack.push(now->data);
            return true;
        }
        return false;
    };
    dfs(head, x);
//    stack.push(head->data);
    cout << stack;
}
