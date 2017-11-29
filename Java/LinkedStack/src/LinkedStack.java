public class LinkedStack extends AbstractStack implements Stack {
    private Node head;

    protected void pushImpl(Object value) {
        head = new Node(head, value);
    }

    protected Object peekImpl() {
        return head.value;
    }

    protected void remove() {
        head = head.next;
    }

    private class Node {
        private Node next;
        private Object value;

        public Node(Node next, Object value) {
            assert value != null : "value is null";

            this.next = next;
            this.value = value;
        }
    }
}
