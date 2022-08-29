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

    while (x--) {
        int val;
        cin >> val;
        add(val);
    }
    cout << "Before:\n";
    node *mv = head->next;
    while (mv != nullptr) {
        cout << mv->val << ' ';
        mv = mv->next;
    }

    cout << "\nAfter:\n";
    mv = head->next;
    while (mv != nullptr) {
        int now = mv->val;
        node *mov = mv->next;
        while (mov != nullptr && mov->val == now) {
            mov = mov->next;
        }
        mv->next = mov;
        cout << mv->val << ' ';
        mv = mv->next;
    }
}