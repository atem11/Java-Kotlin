public class ArrayQueueADT {
    private int size = 0, start = 0, finish = 0;
    private Object[] elements = new Object[10];

    private static void copy(ArrayQueueADT queue, Object[] es, int src, int dest, int len) {
        System.arraycopy(queue.elements, src, es, dest, len);
    }

    // pre: true
    private static void ensureCapacity(ArrayQueueADT queue, int s) {
        if (s < queue.elements.length) {
            return;
        }
        Object[] es = new Object[s * 2];
        // es[0] = elements[start] etc
        // es.lenght > elements.lenght

        if (queue.start >= queue.finish) {
            copy(queue, es, queue.start, 0, s - queue.start);
            copy(queue, es, 0, s - queue.start, queue.finish);
        } else {
            copy(queue, es, queue.start, 0, s);
        }

        /*for (int i = 0; i <= s; i++) {
            es[i] = elements[(start + i) % elements.length];
        }*/
        queue.elements = es;
        queue.start = 0;
        queue.finish = s;
    }
    // post: elements not change ||
    // (elements.lenght *= 2 && start = 0 && finish = size && size` = size &&)

    // pre: true
    public static void enqueue(ArrayQueueADT queue, Object el) {
        ensureCapacity(queue, queue.size++);
        queue.elements[queue.finish] = el;
        queue.finish = (queue.finish + 1) % queue.elements.length;
    }
    // post: size` = size++ && el[0]`...el[size]` = el[0]...el[size]
    // && el[finish] = element && (start` = start || 0) && (finish -> || size`)

    // pre: true
    public static void push(ArrayQueueADT queue, Object el) {
        ensureCapacity(queue,queue.size++);
        queue.start = (queue.start + queue.elements.length - 1) % queue.elements.length;
        queue.elements[queue.start] = el;
    }
    // post: size` = size++ && el[0]`...el[size]` = el[0]...el[size]
    // && el[start - 1] = element && (finish` = finish) && (start <- || size`)

    // pre: size > 0
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0 : "Non-positive size: " + queue.size;

        return queue.elements[queue.start];
    }
    // post: size` = size && Res = el[start] && el[0]`...el[size]` = el[0]...el[size]
    // && start` = start && finish` = finish

    // pre: size > 0
    public static Object peek(ArrayQueueADT queue) {
        assert queue.size > 0 : "Non-positive size: " + queue.size;

        return queue.elements[(queue.finish - 1 + queue.elements.length) % queue.elements.length];
    }
    // post: size` = size && Res = el[start] && el[0]`...el[size]` = el[0]...el[size]
    // && start` = start && finish` = finish

    // pre: size > 0
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0 : "Non-positive size: " + queue.size;

        Object ans = queue.elements[queue.start];
        queue.elements[queue.start] = null;
        queue.size--;
        queue.start = (queue.start + 1) % queue.elements.length;
        return ans;
    }
    // post: size` = size - 1 && el[0]`...el[size]` = el[0]...el[size]
    // && Res = el[start] && finish` = finish && start` ->

    // pre: size > 0
    public static Object remove(ArrayQueueADT queue) {
        assert queue.size > 0 : "Non-positive size: " + queue.size;

        queue.size--;
        queue.finish = (queue.finish - 1 + queue.elements.length) % queue.elements.length;
        Object ans = queue.elements[queue.finish];
        queue.elements[queue.finish] = null;
        return ans;
    }
    // post: size` = size - 1 && el[0]`...el[size]` = el[0]...el[size]
    // && Res = el[finish <-] && start` = start && finish` <-

    // pre: true
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }
    // post: size` = size && Res = size && el[0]`...el[size]` = el[0]...el[size]
    // start` = start && finish` = finish

    // pre: true
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    // post: size` = size && Res = (size == 0) && el[0]`...el[size]` = el[0]...el[size]
    // start` = start && finish` = finish

    // pre: true
    public static void clear(ArrayQueueADT queue) {
        Object[] es = new Object[10];
        queue.elements = es;
        queue.size = 0;
        queue.start = 0;
        queue.finish = 0;
    }
    // size` = 0 && element isEmpty && start` = 0 && finish` = 0
}
