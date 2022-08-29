#include <bits/stdc++.h>

#define GET static_cast<node *>(malloc(sizeof(node)))
using namespace std;

struct node {
    int val;
    node *next;
};


int main() {
    node *head = GET;
    head->val = 0x1ead;
    head->next = nullptr;
    node *tail = head;
    int x;
    cin >> x;

    function<void(int)> add = [&](int val) {
        node *newNode = GET;
        newNode->val = val;
        newNode->next = nullptr;
        tail->next = newNode;
        tail = newNode;
    };

    function<void(node *)> print = [](node *head) {
        node *mv = head->next;
        while (mv != nullptr) {
            cout << mv->val << ' ';
            mv = mv->next;
        }
    };
    while (x--) {
        int val;
        cin >> val;
        add(val);
    }

//    print(head);

    node *h1 = GET, *h2 = GET, *mv = head->next;
    int cnt = 1;
    tail = h1;
    while (mv != nullptr) {
        node *next = mv->next;
        if (cnt & 1) {
            tail->next = mv;
            tail = mv;
            mv->next = nullptr;
        } else {
            if (cnt == 2)
                mv->next = h1->next;
            else
                mv->next = h2->next;
            h2->next = mv;
        }
        cnt++;
        mv = next;
    }

    print(h2);

}