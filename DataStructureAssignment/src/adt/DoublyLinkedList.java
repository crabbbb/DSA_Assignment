package adt;

import java.io.Serializable;
import java.util.Iterator;

public class DoublyLinkedList<T> implements DoublyListInterface<T>, Serializable {

    private int size;
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        Node currentNode = head;
        while (currentNode != null) {
            Node next = currentNode.next;
            currentNode.prev = currentNode.next = null;
            currentNode.data = null;
            currentNode = next;
        }
        head = tail = currentNode = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Find the index of a particular value in the linked list, O(n)
     *
     * @param entry
     * @return -1 when entry is not found in list or the list is empty
     */
    @Override
    public int indexOf(T entry) {

        if (!isEmpty()) {
            Node currentNode = head;
            // targetNode = 0
            int i = 0;
            while (currentNode != null) {
                if (entry.equals(currentNode.data)) {
                    return i;
                }

                // if not
                currentNode = currentNode.next;
                i++;
            }
        }

        return -1; // -1 will be not found or the list is empty
    }

    @Override
    public boolean contains(T entry) {
        return indexOf(entry) != -1;
    }

    @Override
    public T[] asArray() {
        if (!isEmpty()) {
            T[] array = (T[]) new Object[size];
            Node currentNode = head;

            int i = 0;
            while (currentNode != null) {
                array[i] = currentNode.data;
                currentNode = currentNode.next;
                i++;
            }

            return array;
        }

        return null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    /**
     * @param newEntry
     * @return false when the newEntry is null
     */
    @Override
    public boolean add(T newEntry) {
        return addLast(newEntry);
    }

    /**
     * position should be > 0 && <= size + 1 @pa
     *
     *
     * ram position
     *
     * ram position @param newEntry @return true when it is successful added,
     * fal
     *
     * s
     * e will be cause when position is not in range and newEntry is null
     */
    @Override
    public boolean add(int position, T newEntry) {
        if ((position > 0 && position <= size() + 1) && newEntry != null) {
            // in range
            if (position == 1) {
                // call addFirst
                addFirst(newEntry);
            } else if (position == size() + 1) {
                // call addLast
                addLast(newEntry);
            } else {
                // here doesnt have empty problem because already handle by addFirst
                // get index
                position--;
                Node targetNode = returnTargetNode(position);

                Node newNode = new Node(newEntry);
                // change the upper of newNode to upper of the targetNode
                newNode.prev = targetNode.prev;
                // change the lower of the upper targetNode to newNode
                targetNode.prev.next = newNode;
                // the upper of the targetNode will become newNode
                targetNode.prev = newNode;
                newNode.next = targetNode;

                size++;
            }
            return true;
        }

        return false;
    }

    /**
     * @return false when the newEntry is null
     * @param newEntry
     */
    @Override
    public boolean addFirst(T newEntry) {
        if (newEntry != null) {
            if (isEmpty()) {
                head = tail = new Node(newEntry);
            } else {
                head.prev = new Node(newEntry, null, head);
                head = head.prev;
            }
            size++;
            return true;
        }

        return false;
    }

    /**
     * @return false when the newEntry is null
     * @param newEntry
     */
    @Override
    public boolean addLast(T newEntry) {
        if (newEntry != null) {
            if (isEmpty()) {
                head = tail = new Node(newEntry);
            } else {
                tail.next = new Node(newEntry, tail, null);
                tail = tail.next;
            }
            size++;
            return true;
        }

        return false;
    }

    @Override
    public T peekFirst() {
        if (!isEmpty()) {
            return head.data;
        }

        return null;
    }

    @Override
    public T peekLast() {
        if (!isEmpty()) {
            return tail.data;
        }

        return null;
    }

    @Override
    public T get(int position) {
        // check position
        if (position > 0 && position <= size()) {
            // in range
            Node currentNode = null;

            // get index
            position--;

            if (position < size() / 2) {
                // ssarch start from head
                currentNode = head;
                for (int i = 0; i < position; i++) {
                    currentNode = currentNode.next;
                }
            } else {
                // search start from tail
                currentNode = tail;
                for (int i = size() - 1; i > position; i--) {
                    currentNode = currentNode.prev;
                }
            }

            return currentNode.data;
        }

        return null;
    }

    @Override
    public T removeFirst() {
        if (!isEmpty()) {
            T data = head.data;
            // update head to next
            head = head.next;
            size--;

            // check isit no value already
            if (isEmpty()) {
                // make head and tail all become null
                clear();
            } else {
                // change the upper of the head become null
                head.prev = null;
            }

            return data;
        }

        return null;
    }

    @Override
    public T removeLast() {
        if (!isEmpty()) {
            T data = tail.data;
            // update tail to previous
            tail = tail.prev;
            size--;

            // check isit no value already
            if (isEmpty()) {
                // make head and tail all become null
                clear();
            } else {
                // change the lower of the tail become null
                tail.next = null;
            }

            return data;
        }

        return null;
    }

    /**
     * use to delete value from list by using position
     *
     * @param position
     * @return data when the position is correct
     */
    @Override
    public T remove(int position) {
        // check position
        if (position > 0 && position <= size()) {
            // in range
            if (position == 1) {
                return removeFirst();
            } else if (position == size()) {
                return removeLast();
            } else {
                // get index
                position--;

                // get node
                Node deleteNode = returnTargetNode(position);

                deleteNode.prev.next = deleteNode.next;
                deleteNode.next.prev = deleteNode.prev;

                return deleteNode.data;
            }

        }

        return null;
    }

    /**
     * <b> will only delete the first match value </b>
     *
     * @return false when the entry is null && data is no exist && list is empty
     */
    @Override
    public T remove(T entry) {
        if (entry != null && !isEmpty()) {

            // find in list
            int index = indexOf(entry);

            if (index != -1) {
                return remove(++index);
            }
        }

        return null;
    }

    @Override
    public boolean replace(int position, T newEntry) {
        // check empty + newEntry cannot be null
        if (newEntry != null && !isEmpty() && (position > 0 && position <= size())) {
            // get index
            position--;
            Node targetNode = returnTargetNode(position);
            if (targetNode != null) {
                // exist in list
                targetNode.data = newEntry;

                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node trav = head;
        while (trav != null) {
            str += trav.data + ", ";
            trav = trav.next;
        }
        str += "\b\b\n]";
        return str;
    }

    /**
     * in returnTargetNode will not check the index isit in range or not need to
     * check before using it
     */
    private Node returnTargetNode(int index) {
        Node currentNode = null;

        if (index < size() / 2) {
            // loop start from head
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            // loop start from tail
            currentNode = tail;
            for (int i = size() - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }

        return currentNode;
    }

    private class Node implements Serializable {

        T data;
        Node prev, next;

        public Node() {
            this(null);
        }

        public Node(T data) {
            this(data, null, null);
        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private class DoublyLinkedListIterator implements Iterator<T> {

        private Node trav;

        public DoublyLinkedListIterator() {
            this.trav = head;
        }

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
    }
}
