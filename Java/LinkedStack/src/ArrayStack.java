public class ArrayStack extends AbstractStack implements Stack {
    private Object[] elements = new Object[10];

    private void ensureCapacity(int s) {
        if (s <= elements.length) {
            return;
        }
        Object[] es = new Object[s * 2];
        for (int i = 0; i < size; i++) {
            es[i] = elements[i];
        }
        elements = es;
    }

    protected void pushImpl(Object e) {
        ensureCapacity(size + 1);
        elements[size] = e;
    }

    protected Object peekImpl() {
        return elements[size - 1];
    }

    protected void remove() {
        elements[size - 1] = null;
    }
}
