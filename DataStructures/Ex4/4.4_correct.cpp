#include <bits/stdc++.h>

#define SP '$'
#define NU '#'
#define NL '@'
using namespace std;
using pci = pair<char, int>;

template<typename T>
ostream &operator<<(ostream &o, vector<T> v) {
    for (int &i: v) o << i;
    return o;
}

struct node {
    char data = 0;
    int weight = 0;
    node *lChild = nullptr, *rChild = nullptr;

    node() = default;

    explicit node(char a, int b) {
        this->data = a;
        this->weight = b;
    }

    explicit node(const pci &p) {
        this->data = p.first;
        this->weight = p.second;
    }

    explicit node(node *a, node *b) {
        this->data = 0;
        this->weight = a->weight + b->weight;
        this->lChild = a;
        this->rChild = b;
    }


};

struct cmp {
    bool operator()(node *a, node *b) {
        if (a->weight != b->weight)
            return a->weight > b->weight;
        else if (b->data == 0) return true;
        else if (a->data == 0) return false;
        else return a->data < b->data;
    }
};

void encode() {
    fstream fin = fstream("data.txt", ios::in);
    fstream fout = fstream("code.txt", ios::out | ios::trunc);
    if (!fin.is_open()) {
        cout << "Cannot find data.txt";
        return;
    }
    if (!fout.is_open()) {
        cout << "Cannot open code.txt";
        return;
    }
    array<int, 128> cnt{0};
    char ch;
    while (fin.peek() != EOF) {
        ch = static_cast<char>(fin.get());
        if (ch == '\n') ch = NL;
        cnt[ch]++;
    }
    priority_queue<node *, vector<node *>, cmp> pq;
    cout << "char    weight\n";
    for (char i = 0; i <= 120; i++) {
        if (cnt[i]) {
            cout << i << '\t' << cnt[i] << endl;
            pq.push(new node(i, cnt[i]));
        }
    }

    function<node *(void)> build = [&]() {
//        cout << "Begin build\n";
        while (pq.size() > 1) {
            node *tmp = pq.top();
            pq.pop();
            node *nd = new node(tmp, pq.top());
//            cout << (tmp->data == 0 ? 'x' : tmp->data) << ' ' << (pq.ptr()->data == 0 ? 'x' : pq.ptr()->data) << ' '
//                 << nd->weight << endl;
            pq.pop();
            pq.push(nd);
        }
        return pq.top();
    };
    node *tree = build();

    function<void(node *, int)> print = [&](node *now, int lay) {
        if (now == nullptr) {
            fout << NU;
            return;
        }
//        for (int i = 0; i < lay; i++)cout << "   ";
//        cout << (now->data == 0 ? 'x' : now->data) << ' ' << endl;
//        cout << (now->data == 0 ? 'x' : now->data);
        fout << (now->data == 0 ? SP : now->data);
        print(now->lChild, lay + 1);
        print(now->rChild, lay + 1);
    };
    print(tree, 0);

    map<char, vector<int>> table;
    function<void(node *, vector<int>)> go = [&](node *now, vector<int> here) {
        if (now == nullptr) return;
        if (now->data) {
            table[now->data] = here;
        }
        here.emplace_back(0);
        go(now->lChild, here);
        *(here.end() - 1) = 1;
        go(now->rChild, here);
    };
    go(tree, vector<int>());

    cout << "char    weight    code\n";
    for (const auto &[a, x]: table) {
        cout << a << '\t' << cnt[a] << "\t  " << x << endl;
    }

    print(tree, 0);
    fout << endl;
    fin.seekg(0, ios::beg);
    while (fin.peek() != EOF) {
        ch = static_cast<char>(fin.get());
        ch = ch == '\n' ? NL : ch;
        fout << table[ch];
//        cout << table[ch];
    }
    fout.close();
    fin.close();

}

void decode() {
    fstream fin = fstream("code.txt", ios::in);
    if (!fin.is_open()) {
        cout << "Cannot open code.txt";
        return;
    }
    string tableStr;
    getline(fin, tableStr);
    function<void(node *)> build = [&](node *now) {
        static int pos = 0;
        now->data = tableStr[pos];
        if (tableStr[++pos] != NU) {
            now->lChild = new node;
            build(now->lChild);
        }
        if (tableStr[++pos] != NU) {
            now->rChild = new node;
            build(now->rChild);
        }
    };
    function<void(node *, int)> print = [&](node *now, int lay) -> void {
        if (now == nullptr) return;
        for (int i = 0; i < lay; i++)cout << "   ";
        cout << now->data << ' ' << endl;
        print(now->lChild, lay + 1);
        print(now->rChild, lay + 1);
    };
    node *tree = new node;
    build(tree);
//    print(tree, 0);
    char ch = static_cast<char>(fin.get());
    function<char(node *)> fd = [&](node *now) -> char {
        if (ch - '0') {//1 -> right
            if (now->rChild == nullptr) return now->data;
            else {
                ch = static_cast<char>(fin.get());
                fd(now->rChild);
            }
        } else {//left
            if (now->lChild == nullptr) return now->data;
            else {
                ch = static_cast<char>(fin.get());
                fd(now->lChild);
            }
        }
//        return '$';
    };

    while (fin.peek() != EOF) {
        char chr = fd(tree);
        if (chr == NL) cout << '\n';
        else cout << chr;
    }
}

int main() {
    cout << "Enter 'e' for encode, 'd' for decode:";
    string str;
    cin >> str;
    switch (str[0]) {
        case 'e':
            encode();
            break;
        case 'd':
            decode();
            break;
    }
}