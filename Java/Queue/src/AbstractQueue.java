import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue {
    protected int size = 0;

    public void enqueue(Object value) {
        assert value != null : "value is null";

        enqueueImpl(value);
        size++;
    }

    protected abstract void enqueueImpl(Object value);

    public Object element() {
        assert size > 0 : "Non-positive size " + size;

        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue() {
        assert size > 0 : "Non-positive size " + size;

        Object res = elementImpl();
        remove();
        size--;
        return res;
    }

    protected abstract void remove();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();

    public Queue filter(Predicate<Object> predicate) {
        Queue copy = copyQueue();
        int s = copy.size();
        for (int i = 0; i < s; i++) {
            Object copyElement = copy.dequeue();
            if (predicate.test(copyElement)) {
                copy.enqueue(copyElement);
            }
        }
        return copy;

    }

    public Queue map(Function<Object, Object> function) {
        Queue copy = copyQueue();
        int s = copy.size();
        for (int i = 0; i < s; i++) {
            copy.enqueue(function.apply(copy.dequeue()));
        }
        return copy;
    }

    protected abstract Queue copyQueue();
}
