package Structures;

import Models.DoublyLinkedListNode;

public class DoublyLinkedList<T> {

    DoublyLinkedListNode<T> cursor;
    DoublyLinkedListNode<T> first;
    DoublyLinkedListNode<T> last;
    int size;

    public DoublyLinkedList() {
        cursor = null;
        first = null;
        last = null;
        size = 0;
    }

    public void goFirst() {
        cursor = first;
    }

    public void goLast() {
        cursor = last;
    }

    public void goNext() {
        if (cursor != null) {
            cursor = cursor.getNext();
        }
    }

    public void goPrevious() {
        if (cursor != null){
            cursor = cursor.getPrevious();
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isInList(T key) {
        this.goFirst();
        while (cursor != null) {
            if (cursor.getData().equals(key)) {
                return true;
            }
            this.goNext();
        }
        this.goLast();
        return false;
    }

    public int indexOf(T key) {
        int index = 0;
        this.goFirst();
        while (cursor != null) {
            if (cursor.getData().equals(key)) {
                return index;
            }
            this.goNext();
            index++;
        }
        this.goLast();
        return -1;
    }

    public T getCursor() {
        return cursor.getData();
    }

    public T getFirst() {
        return first.getData();
    }

    public T getLast() {
        return last.getData();
    }

    public void insertNext(T data) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (cursor == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(cursor.getNext());
            newNode.setPrevious(cursor);
            if (cursor.getNext() != null) {
                cursor.getNext().setPrevious(newNode);
            } else {
                last = newNode;
            }
            cursor.setNext(newNode);
        }
        cursor = newNode;
        size++;
    }

    public void insertPrevious(T data) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (cursor == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(cursor);
            newNode.setPrevious(cursor.getPrevious());
            if (cursor.getPrevious() != null) {
                cursor.getPrevious().setNext(newNode);
            } else {
                first = newNode;
            }
            cursor.setPrevious(newNode);
        }
        cursor = newNode;
        size++;
    }

    public void removeCurrent() {
        if (cursor != null) {
            if (cursor.getPrevious() != null) {
                cursor.getPrevious().setNext(cursor.getNext());
            } else {
                first = cursor.getNext();
            }
            if (cursor.getNext() != null) {
                cursor.getNext().setPrevious(cursor.getPrevious());
            } else {
                last = cursor.getPrevious();
            }
            if (cursor.getNext() != null) {
                cursor = cursor.getNext();
            } else {
                cursor = cursor.getPrevious();
            }
            size--;
        }
    }

    public void clear() {
        cursor = null;
        first = null;
        last = null;
        size = 0;
    }

    public void insertFirst(T data) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        }
        size++;
    }

    public void insertLast(T data) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
            last = newNode;
        }
        size++;
    }

    public void insertPosition(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            this.insertFirst(data);
            return;
        }
        if (index == size) {
            this.insertLast(data);
            return;
        }
        this.goFirst();
        for (int i = 0; i < index; i++) {
            this.goNext();
        }
        this.insertPrevious(data);
    }

public void insertOrdered(T data) {
    if (!(data instanceof Comparable)) {
        throw new IllegalArgumentException("Data must be Comparable");
    }

    this.goFirst();
    while (cursor != null && ((Comparable<T>) cursor.getData()).compareTo(data) < 0) {
        this.goNext();
    }
    if (cursor == null) {
        this.insertLast(data);
    } else {
        this.insertPrevious(data);
    }
}

    public void removeFirst() {
        if (first != null) {
            if (first.getNext() != null) {
                first.getNext().setPrevious(null);
            } else {
                last = null;
            }
            first = first.getNext();
            size--;
        }
    }

    public void removeLast() {
        if (last != null) {
            if (last.getPrevious() != null) {
                last.getPrevious().setNext(null);
            } else {
                first = null;
            }
            last = last.getPrevious();
            size--;
        }
    }

    public void removePosition(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            this.removeFirst();
            return;
        }
        if (index == size - 1) {
            this.removeLast();
            return;
        }
        this.goFirst();
        for (int i = 0; i < index; i++) {
            this.goNext();
        }
        this.removeCurrent();
    }

    public void removeElement(T key) {
        if (this.isInList(key)) {
            this.removeCurrent();
        }
    }
}



