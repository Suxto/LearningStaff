#include <bits/stdc++.h>

#include <utility>

#define SP '$'
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

    explicit node(const pci &p) {
        this->data = p.first;
        this->weight = p.second;
    }

    explicit node(node *a, node *b) {
        this->data = 0;
        this->weight = a->weight + b->weight;
//        if (a->weight < b->weight) {
        this->lChild = a;
        this->rChild = b;
//        } else {
//            this->lChild = b;
//            this->rChild = a;
//    }
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

    auto *mp = new map<char, int>;
    char ch;
    while (fin.peek() != EOF) {
        ch = static_cast<char>(fin.get());
        if (ch == '\n') ch = NL;
        (*mp)[ch]++;
    }

    vector<pci> weights(mp->begin(), mp->end());
    delete mp;

    sort(weights.begin(), weights.end(), [](const pci &a, const pci &b) {
        return a.second < b.second;
    });

//    cout << "char    weight\n";
//    for (pci &p: weights) {
//        cout << p.first << '\t' << p.second << endl;
//    }


    function<node *(void)> build = [&]() {
        auto *ptrs = new vector<node *>(weights.size(), nullptr);
        for (int i = 0; i < weights.size(); i++) {
            (*ptrs)[i] = new node(weights[i]);
        }
        node *pre = new node((*ptrs)[0], (*ptrs)[1]);
        for (int i = 2; i < weights.size(); i++) {
            pre = new node((*ptrs)[i], pre);
        }
        delete ptrs;
        return pre;
    };
    node *tree = build();

    function<void(node *, int)> print = [&](node *now, int lay) {
        if (now == nullptr) return;
//        for (int i = 0; i < lay; i++)cout << "   ";
//        cout << (now->data == 0 ? 'x' : now->data) << ' ' << endl;
//        cout << (now->data == 0 ? 'x' : now->data);
        fout << (now->data == 0 ? SP : now->data);
        print(now->lChild, lay + 1);
        print(now->rChild, lay + 1);
    };

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
    go(tree, vector<int>(1));

    cout << "char    weight    code\n";
    auto it = weights.begin();
    for (const auto &[a, x]: table) {
        cout << a << '\t' << it->second << "\t  " << x << endl;
        it++;
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
    function<void(node *, int)> build = [&](node *now, int pos) {
        if (tableStr[pos] == SP) {
            now->lChild = new node;
            build(now->lChild, pos + 1);
            now->rChild = new node;
            build(now->rChild, pos + 2);
        } else {
            now->data = tableStr[pos];
        }
    };
    function<void(node *, int)> print = [&](node *now, int lay) -> void {
        if (now == nullptr) return;
        for (int i = 0; i < lay; i++)cout << "   ";
        cout << (now->data == 0 ? 'x' : now->data) << ' ' << endl;
        print(now->lChild, lay + 1);
        print(now->rChild, lay + 1);
    };
    node *tree = new node;
    build(tree, 0);
//    print(tree, 0);
    char ch;
    function<char(node *)> fd = [&](node *now) -> char {
        ch = static_cast<char>(fin.get());
        if (ch - '0') {//right
            if (now->rChild == nullptr) return now->data;
            else fd(now->rChild);
        } else {
            if (now->lChild == nullptr) return now->data;
            else fd(now->lChild);
        }
//        return '$';
    };

    ch = static_cast<char>(fin.get());
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