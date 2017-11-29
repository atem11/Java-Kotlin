import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue {

    // inv:
    // size >= 0 && for all element != null

    // pre: true
    void enqueue(Object value);
    // post: size` = size + 1 && (old queue not change)
    // && (end of queue = value)

    // pre: size > 0
    Object element();
    // post: size` = size && Res = first of queue && (queue not change)

    // pre: size > 0
    Object dequeue();
    // post: size` = size - 1 && (queue without first not change)
    // && Res = first of queue && new first = next of first && end not change

    // pre: true
    int size();
    // post: size` = size && (queue not change) && Res = size

    // pre: true
    boolean isEmpty();
    // post: size` = size && (queue not change) && Res = (size == 0)

    // pre: true
    void clear();
    // size` = 0 && (queue isEmpty)

    // pre: queue != null
    Queue filter(Predicate<Object> predicate);
    // post: for all element predicate(element) == true && size >= 0 && order not change && size' <= size && Res = Queue

    // pre: queue != null
    Queue map(Function<Object, Object> function);
    // post: for all element` == function(element) && size >= 0 && order not change && size' = size && Res = Queue
}
