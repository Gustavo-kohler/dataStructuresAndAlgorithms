package classes;

public class Queue<T> {

        int length;
        int front;
        int rear;
        T[] array;

        public Queue(int length) {
            this.length = length;
            this.front = -1;
            this.rear = -1;
            this.array = (T[]) new Object[length];
        }

        public boolean isEmpty() {
            return front == -1;
        }

        public boolean isFull() {
            return (front == 0 && rear == length - 1) || front == rear + 1;
        }

        public void enqueue(T item) {
            if (isFull()) {
                throw new IllegalStateException("Queue is full");
            }
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % length;
            array[rear] = item;
        }

        public T dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            T item = array[front];
            if (front == rear) {
                front = rear = -1;
            } else {
                front = (front + 1) % length;
            }
            return item;
        }

        public T front() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return array[front];
        }
}
