package adt;

/**
 * @author DSA Lecture & Tutorial
 */
public class LinkedStack<T> implements StackInterface<T> {

    /**
     * Step 1 : Node newNode = new Node(newEntry); Step 2 : newNode.next =
     * topNode; Step 3 : topNode = newNode ( base on the rule FIFO )
     *
     * Note : pointer contain the reference of the object ( address of the
     * object ) reference value = address of an object \ String v1 = "aaa";
     * String v2 = "bbb"; v1 = v2; ( let v1 pointing to "bbb" address value In
     * the pointer assignment, the LHS pointer will point to the same object as
     * the RHS pointer
     */
    private Node topNode;

    public LinkedStack() {
        this.topNode = null; // empty stack
    }

    public String toString() {
        String str = "";
        Node currentNode = topNode;
        while (currentNode != null) {
            str += currentNode.data + "\n";
            currentNode = currentNode.next;// to update the currentNode to point to the next node
        }

        return str;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = topNode;
        topNode = newNode;
    }

    @Override
    public T pop() {
        T topElement = null;
        if (!isEmpty()) {
            topElement = topNode.data;
            topNode = topNode.next;// update the pointer to point to the next node
        }
        return topElement;
    }

    @Override
    public T peek() {
        T topElement = null;
        if (!isEmpty()) {
            topElement = topNode.data;
        }
        return topElement;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }

    private class Node { // subclass will inheritance the Generic type of super class

        T data;
        Node next;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
