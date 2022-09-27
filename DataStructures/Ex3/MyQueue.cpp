#include<iostream>

using namespace std;

template<typename T>
struct MyQueue {
    T *data = nullptr;
    int capacity = 0;
    int fPos = 0, rPos = 0;
    int siz = 0;

    explicit MyQueue(int cap) {
        this->capacity = cap;
        data = (T *) malloc(sizeof(T) * cap);
    }

    T front() {
        return data[fPos];
    }

    int size() {
        return siz;
    }

    T pop() {
        T f = data[fPos];
        fPos = (fPos + 1) % capacity;
        siz--;
        return f;
    }

    bool empty() {
        return siz == 0;
    }

    bool push(T t) {
        if (siz == capacity) return false;
        data[rPos] = t;
        rPos = (rPos + 1) % capacity;
        siz++;
        return true;
    }

};


int main() {
    auto q = new MyQueue<int>(5);
    for (int i = 0; i < 3; i++) {
        int t;
        cin >> t;
        q->push(t);
    }
    while (!q->empty()) {
        cout << q->pop() << endl;
    }
}