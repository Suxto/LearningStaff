package Ch13;

import java.util.ArrayList;

import static java.lang.System.out;

public class P13_8 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        MyStack stack1 = stack.clone();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        out.print(stack1);
        out.print(stack);
    }
}


class MyStack implements Cloneable {
    ArrayList<Object> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Object peek() {
        return list.get(getSize() - 1);
    }

    public Object pop() {
        Object o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public void push(Object o) {
        list.add(o);
    }

    @Override
    public String toString() {
        return "stack:" + list;
    }

    @Override
    public MyStack clone() {
        try {
            MyStack clone = (MyStack) super.clone();
            clone.list = (ArrayList<Object>) this.list.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}