package Structures;

import Models.ListNode;

public class List<T> {

        ListNode<T> first;
        ListNode<T> last;
        int size;

        public List() {
            this.first = null;
            this.last = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void addFirst(T data) {
            ListNode<T> newNode = new ListNode<>(data);
            if (isEmpty()) {
                first = last = newNode;
            } else {
                newNode.setNext(first);
                first = newNode;
            }
            size++;
        }

        public void addLast(T data) {
            ListNode<T> newNode = new ListNode<>(data);
            if (isEmpty()) {
                first = last = newNode;
            } else {
                last.setNext(newNode);
                last = newNode;
            }
            size++;
        }

        private void add(ListNode<T> current, T data) {
            ListNode<T> newNode = new ListNode<>(data);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size++;
        }


        public void addPosition(int position, T data) {
            if (position < 0 || position > size) {
                throw new IllegalArgumentException("Invalid position");
            }
            if (position == 0) {
                addFirst(data);
            } else if (position == size) {
                addLast(data);
            } else {
                ListNode<T> current = first;
                for (int i = 1; i < position; i++) {
                    current = current.getNext();
                }
                add(current, data);
            }
        }

        public void addBefore(T reference, T data) {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            if (first.getData() == reference) {
                addFirst(data);
            } else {
                ListNode<T> current = first;
                ListNode<T> ghostValue = new ListNode<>(reference);
                last.setNext(ghostValue);
                while (current.getNext().getData() != reference) {
                    current = current.getNext();
                }
                if(current.getNext() == ghostValue) {
                    last.setNext(null);
                    throw new IllegalStateException("Reference not found");
                }
                add(current, data);
                last.setNext(null);
            }
        }

        public void addAfter(T reference, T data) {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            if (last.getData() == reference) {
                addLast(data);
            } else {
                ListNode<T> current = first;
                ListNode<T> ghostValue = new ListNode<>(reference);
                last.setNext(ghostValue);
                while (current.getData() != reference) {
                    current = current.getNext();
                }
                if(current == ghostValue) {
                    last.setNext(null);
                    throw new IllegalStateException("Reference not found");
                }
                add(current, data);
                last.setNext(null);
            }
        }

        public T removeFirst() {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            T data = first.getData();
            if (first == last) {
                first = last = null;
            } else {
                first = first.getNext();
            }
            size--;
            return data;
        }

        public T removeLast() {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            T data = last.getData();
            if (first == last) {
                first = last = null;
            } else {
                ListNode<T> current = first;
                while (current.getNext() != last) {
                    current = current.getNext();
                }
                last = current;
                last.setNext(null);
            }
            size--;
            return data;
        }

        private T rm(ListNode<T> current) {
            T data = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            size--;
            return data;
        }

        public T removePosition(int position) {
            if (position < 0 || position >= size) {
                throw new IllegalArgumentException("Invalid position");
            }
            if (position == 0) {
                return removeFirst();
            } else if (position == size - 1) {
                return removeLast();
            } else {
                ListNode<T> current = first;
                for (int i = 1; i < position; i++) {
                    current = current.getNext();
                }
                return rm(current);
            }
        }

        public T remove(T reference) {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            if (first.getData() == reference) {
                return removeFirst();
            } else if (last.getData() == reference) {
                return removeLast();
            } else {
                ListNode<T> current = first;
                ListNode<T> ghostValue = new ListNode<>(reference);
                last.setNext(ghostValue);
                while (current.getNext().getData() != reference) {
                    current = current.getNext();
                }
                if(current.getNext() == ghostValue) {
                    last.setNext(null);
                    throw new IllegalStateException("Reference not found");
                }
                last.setNext(null);
                return rm(current);
            }
        }

        public T removePrevious(T reference) {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            if (first.getData() == reference) {
                throw new IllegalStateException("Reference is the first element");
            }else if(this.get(1) == reference) {
                return removeFirst();
            }else {
                ListNode<T> current = first;
                ListNode<T> ghostValue = new ListNode<>(reference);
                last.setNext(ghostValue);
                while (current.getNext().getNext().getData() != reference) {
                    current = current.getNext();
                }
                if(current.getNext().getNext() == ghostValue) {
                    last.setNext(null);
                    throw new IllegalStateException("Reference not found");
                }
                last.setNext(null);
                return rm(current);
            }
        }

        public T removeNext(T reference) {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            if (last.getData() == reference) {
                throw new IllegalStateException("Reference is the last element");
            } else {
                ListNode<T> current = first;
                ListNode<T> ghostValue = new ListNode<>(reference);
                last.setNext(ghostValue);
                while (current.getData() != reference) {
                    current = current.getNext();
                }
                if(current == ghostValue) {
                    last.setNext(null);
                    throw new IllegalStateException("Reference not found");
                }
                last.setNext(null);
                return rm(current);
            }
        }

        public T getFirst() {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            return first.getData();
        }

        public T getLast() {
            if (isEmpty()) {
                throw new IllegalStateException("List is empty");
            }
            return last.getData();
        }

        public T get(int position) {
            if (position < 0 || position >= size) {
                throw new IllegalArgumentException("Invalid position");
            }
            ListNode<T> current = first;
            for (int i = 0; i < position; i++) {
                current = current.getNext();
            }
            return current.getData();
        }

        public int size() {
            return size;
        }

        public void clear() {
            first = last = null;
            size = 0;
        }
}
