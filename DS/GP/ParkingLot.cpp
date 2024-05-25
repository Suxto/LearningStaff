#include<stdio.h>
#include <time.h>

const int MAXN = 5;
const int YUAN_PER_HOUR = 10;
struct Car {
    const Car static NO_SUCH_CAR;
    int id;
    time_t beg;
};

const Car Car::NO_SUCH_CAR = {-1, 23333};

struct MyStack {
    int ptr = -1;
    Car values[MAXN]{};

    Car top() {
        if (ptr < 0) return Car::NO_SUCH_CAR;
        return values[ptr];
    }

    Car pop() {
        if (ptr < 0) return Car::NO_SUCH_CAR;
        return values[ptr--];
    }

    int push(Car x) {
        if (ptr + 1 >= MAXN) {
            puts("There is no space in the parking area, please park in side rode temporary");
            return 0;
        }
        values[++ptr] = x;
        return 1;
    }
};


struct MyQueue {
    int front = 0, back = 0, size = 0;
    Car values[MAXN]{};


    Car pop() {
        if (size == 0) return Car::NO_SUCH_CAR;
        Car t = values[front % MAXN];
        front = (front + 1) % MAXN;
        size--;
        return t;
    }

    void push(Car x) {
        if (front == back && size) {
            puts("There is no more space in side rode");
            return;
        }
        values[back % MAXN] = x;
        back = (back + 1) % MAXN;
        size++;
    }

};

MyQueue queue;
MyStack stack;

void park() {
    puts("Please enter your car id in integer");
    int id;
    scanf("%d", &id);
    if (stack.push({id, time(0)}) == 0) queue.push({id, 0});
}

void leave() {
    MyStack stack1;
    puts("Please enter your id in integer:");
    int id;
    scanf("%d", &id);
    while (stack.top().id != id) {
        stack1.push(stack.pop());
        if (stack.top().id == -1) {
            puts("No suck a car!");
            break;
        }
    }
    time_t end = time(0);
    if (stack.top().id != -1) {
        Car car = stack.pop();
        time_t diff = (end - car.beg);
        int h = diff / 3600;
        diff -= h * 60;
        int m = (diff - h * 3600) / 60;
        int s = diff % 60;
        printf("Your id is %d, you have parked %d hour(s) %d minutes(s) %d second(s)\n", car.id, h, m, s);
        printf("You need to be charged %d\n", h * YUAN_PER_HOUR + m / 6 * YUAN_PER_HOUR);
    }
    while (stack1.top().id != -1) {
        stack.push(stack1.pop());
    }
    if (queue.size) {
        Car car = queue.pop();
        printf("Time for whose id is %d go in the park lot\n", car.id);
        car.beg = time(0);
        stack.push(car);
    }
}

int main() {
    while (1) {
        puts("Welcome to parking lot");
        puts("Enter a number indicates the service you want");
        puts("1.Park a car");
        puts("2.Exit the parking lot");
        int op;
        scanf("%d", &op);
        while (op != 1 && op != 2) {
            puts("Please enter 1 or 2");
            scanf("%d", &op);
        }
        if (op == 1) park();
        else leave();
        putchar('\n');
    }
}
