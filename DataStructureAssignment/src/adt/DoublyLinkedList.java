package adt;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {

        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T elem) {
        addLast(elem);
    }

    public void addFirst(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T elem) {
        if (isEmpty()) {
            head = tail = new Node<T>(elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        //Extract the data at the head and move
        //the head pointer forwards one node
        T data = head.data;
        head = head.next;
        --size;

        //If the list is empty then tail turn to tail as well
        if (isEmpty()) {
            tail = null;
        } //Do a memory cleaning of the previous node
        else {
            head.prev = null;
        }

        //return the data that was at the first node we just removed
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    private T remove(Node<T> node) {

        //If the node to remove is somewhere either at the
        //head or the tail handle those independently
        if (node.prev == null) {
            return removeFirst();
        }
        if (node.next == null) {
            return removeLast();
        }

        //Make pointers of adjacent nodes skip over 'node'
        node.next.prev = node.prev;
        node.prev.next = node.next;

        //Temporary store the data we want to return
        T data = node.data;

        //Memory Cleanup
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        return data;
    }

    public T removeAt(int index) {
        // Make sure the index provided is valid
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav;

        // Search from the front of the list
        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
            // Search from the back of the list
        } else {
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
            }
        }

        return remove(trav);
    }

    // Remove a particular value in the linked list, O(n)
    public boolean remove(T obj) {
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
            // Search for non null object
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean search(T entry) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(entry)) {
                return true; // Found the element
            }
            current = current.next;
        }
        return false; // Element not found
    }
    
    // Find the index of a particular value in the linked list, O(n)
    public int indexOf(T obj) {
        int index = 0;
        Node<T> trav = head;

        // Support searching for null
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
            // Search for non null object
        } else {
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public T getDataAtPosition(int position) {
        Node<T> current = head;
        int currentPosition = 0;

        while (current != null && currentPosition < position) {
            current = current.next;
            currentPosition++;
        }

        if (current != null) {
            return current.data;
        } else {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
    }

    // Check is a value is contained within the linked list
    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

        };
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node<T> trav = head;
        while (trav != null) {
            str += trav.data + ", ";
            trav = trav.next;
        }
        str += "\b\b\n]";
        return str;
    }
}
