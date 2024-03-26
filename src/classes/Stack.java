package classes;

public class Stack<T> {

    int length;
    int top;
    T[] array;

    public Stack(int length) {
        this.length = length;
        this.top = -1;
        this.array = (T[]) new Object[length];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == length - 1;
    }

    public void push(T item) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        array[++top] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[top--];
    }

    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array[top];
    }
}