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

    private void advanceCursor(int Steps) {
        for (int i = 0; i < Steps; i++) {
            cursor = cursor.getNext();
        }
    }

    private void retreatCursor(int Steps) {
        for (int i = 0; i < Steps; i++) {
            cursor = cursor.getPrevious();
        }
    }

    private void goToFirst() {
        cursor = first;
    }

    private void goToLast() {
        cursor = last;
    }

    private int indexOf(T data) {
        DoublyLinkedListNode<T> temp = first;
        int index = 0;
        while (temp != null) {
            if (temp.getData().equals(data)) {
                return index;
            }
            temp = temp.getNext();
            index++;
        }
        return -1;
    }

    public T gatCursor() {
        return cursor.getData();
    }

    public void insertBeforeCursor(T data) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (cursor == null) {
            cursor = newNode;
            first = cursor;
            last = cursor;
        } else {
            newNode.setNext(cursor);
            newNode.setPrevious(cursor.getPrevious());
            if (cursor.getPrevious() != null) {
                cursor.getPrevious().setNext(newNode);
            } else {
                first = newNode;
            }
            cursor.setPrevious(newNode);
            cursor = newNode;
        }
        size++;
    }

    public void insertAfterCursor(T data) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);
        if (cursor == null) {
            cursor = newNode;
            first = cursor;
            last = cursor;
        } else {
            newNode.setPrevious(cursor);
            newNode.setNext(cursor.getNext());
            if (cursor.getNext() != null) {
                cursor.getNext().setPrevious(newNode);
            } else {
                last = newNode;
            }
            cursor.setNext(newNode);
            cursor = newNode;
        }
        size++;
    }

    public void insertAtFirst(T data) {
        goToFirst();
        insertBeforeCursor(data);
    }

    public void insertAtLast(T data) {
        goToLast();
        insertAfterCursor(data);
    }

    public void insertAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            insertAtFirst(data);
        } else if (index == size) {
            insertAtLast(data);
        } else {
            goToFirst();
            advanceCursor(index);
            insertBeforeCursor(data);
        }
    }

    public void removeCursor() {
        if (cursor == null) {
            throw new NullPointerException("Cursor is null");
        }
        if (cursor == first) {
            first = cursor.getNext();
        } else {
            cursor.getPrevious().setNext(cursor.getNext());
        }
        if (cursor == last) {
            last = cursor.getPrevious();
        } else {
            cursor.getNext().setPrevious(cursor.getPrevious());
        }
        cursor = cursor.getNext();
        size--;
    }

    public void removeFirst() {
        goToFirst();
        removeCursor();
    }

    public void removeLast() {
        goToLast();
        removeCursor();
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        goToFirst();
        advanceCursor(index);
        removeCursor();
    }

    public void removeElement(T data) {
        int index = indexOf(data);
        if (index == -1) {
            throw new NullPointerException("Element not found");
        }
        removeAt(index);
    }

    public boolean find(T data) {
        return indexOf(data) != -1;
    }

    public int size() {
        return size;
    }

    public void clear() {
        cursor = null;
        first = null;
        last = null;
        size = 0;
    }

}



