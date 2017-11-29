public class ArrayQueue extends AbstractQueue implements Queue {
    private int head = 0, tail = 0;
    private Object[] elements = new Object[10];

    private void copy(Object[] el, Object[] es, int src, int dest, int len) {
        System.arraycopy(el, src, es, dest, len);
    }

    private void ensureCapacity(int s) {
        if (s < elements.length) {
            return;
        }
        Object[] es = new Object[s * 2];

        if (head >= tail) {
            copy(elements, es, head, 0, s - head);
            copy(elements, es, 0, s - head, tail);
        } else {
            copy(elements, es, head, 0, s);
        }

        elements = es;
        head = 0;
        tail = s;
    }

    protected void enqueueImpl(Object el) {
        ensureCapacity(size);
        elements[tail] = el;
        tail = (tail + 1) % elements.length;
    }

    protected Object elementImpl() {
        return elements[head];
    }

    protected void remove() {
        elements[head] = null;
        head = (head + 1) % elements.length;
    }

    protected void clearImpl() {
        Object[] es = new Object[10];
        elements = es;
        head = 0;
        tail = 0;
    }

    protected ArrayQueue copyQueue() {
        ArrayQueue copyQueue = new ArrayQueue();
        copyQueue.elements = new Object[elements.length];
        copy(elements, copyQueue.elements, 0, 0, elements.length);
        copyQueue.size = size;
        copyQueue.head = head;
        copyQueue.tail = tail;
        return copyQueue;
    }
}
