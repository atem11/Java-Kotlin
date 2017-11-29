public abstract class AbstractStack {
    public int size = 0;

    public void push(Object value) {
        pushImpl(value);
        size++;
    }

    protected abstract void pushImpl(Object value);

    public Object peek() {
        assert size > 0 : "Stack is empty";
        return peekImpl();
    }

    protected abstract Object peekImpl();

    public Object pop() {
        Object result = peekImpl();
        remove();
        size--;
        return result;
    }

    protected abstract void remove();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
