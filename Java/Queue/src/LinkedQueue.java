public class LinkedQueue extends AbstractQueue implements Queue {
    private Node head, tail;

    protected void enqueueImpl(Object value) {
        if (size == 0) {
            tail = new Node(value);
            head = tail;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void remove() {
        if (head.next != null) {
            head = head.next;
        } else {
            head = null;
            tail = head;
        }
    }

    protected void clearImpl() {
        tail = null;
        head = null;
    }

    private class Node {
        private Node next;
        private Object value;

        public Node(Object value) {
            assert value != null : "value is null";

            this.next = null;
            this.value = value;
        }
    }

    protected LinkedQueue copyQueue() {
        LinkedQueue copyQueue = new LinkedQueue();
        Node copy = head;
        while (copy != null) {
            copyQueue.enqueue(copy.value);
            copy = copy.next;
        }
        copyQueue.head = head;
        copyQueue.tail = tail;
        copyQueue.size = size;
        return copyQueue;
    }
}
