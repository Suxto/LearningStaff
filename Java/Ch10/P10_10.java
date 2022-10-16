package Ch10;

import java.util.Arrays;

import static java.lang.System.out;

public class P10_10 {
    public static void main(String[] args) {
        Queue queue = new Queue();
        for (int i = 1; i < 21; i++) queue.enqueue(i);
        while (!queue.empty()) out.print(queue.dequeue() + " ");
    }
}

class Queue {
    private int size = 0;
    private int front = 0, back = -1;
    private int capacity;
    private int[] element;

    public Queue() {
        this(8);
    }

    public Queue(int x) {
        capacity = x;
        element = new int[x];
    }

    @Override
    public String toString() {
        return "Queue{" + "element=" + Arrays.toString(element) + '}';
    }

    public void enqueue(int x) {
        if (size == capacity) this.reserve(capacity << 1);
        element[++back % capacity] = x;
        size++;
    }

    public int dequeue() {
        size--;
        return element[front++ % capacity];
    }

    public boolean empty() {
        return size == 0;
    }

    public void reserve(int x) {
        front %= capacity;
        back %= capacity;
        capacity = x;
        element = Arrays.copyOfRange(element, 0, capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
}