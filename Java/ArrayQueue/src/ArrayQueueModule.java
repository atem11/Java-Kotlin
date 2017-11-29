public class ArrayQueueModule {
    private static int size = 0, start = 0, finish = 0;
    private static Object[] elements = new Object[10];

    private static void copy(Object[] es, int src, int dest, int len){
        System.arraycopy(elements, src, es, dest, len);
    }

    // pre: true
    private static void ensureCapacity(int s) {
        if (s < elements.length) {
            return;
        }
        Object[] es = new Object[s * 2];
        // es[0] = elements[start] etc
        // es.lenght > elements.lenght

        if (start >= finish) {
            copy(es, start, 0, s - start);
            copy(es, 0, s - start, finish);
        } else {
            copy(es, start, 0, s);
        }

        /*for (int i = 0; i <= s; i++) {
            es[i] = elements[(start + i) % elements.length];
        }*/
        elements = es;
        start = 0;
        finish = s;
    }
    // post: elements not change ||
    // (elements.lenght *= 2 && start = 0 && finish = size && size` = size &&)

    // pre: true
    public static void enqueue(Object el) {
        ensureCapacity(size++);
        elements[finish] = el;
        finish = (finish + 1) % elements.length;
    }
    // post: size` = size++ && el[0]`...el[size]` = el[0]...el[size]
    // && el[finish] = element && (start` = start || 0) && (finish -> || size`)

    // pre: true
    public static void push(Object el) {
        ensureCapacity(size++);
        start = (start + elements.length - 1) % elements.length;
        elements[start] = el;
    }
    // post: size` = size++ && el[0]`...el[size]` = el[0]...el[size]
    // && el[start - 1] = element && (finish` = finish) && (start <- || size`)

    // pre: size > 0
    public static Object element() {
        assert size > 0 : "Non-positive size: " + size;

        return elements[start];
    }
    // post: size` = size && Res = el[start] && el[0]`...el[size]` = el[0]...el[size]
    // && start` = start && finish` = finish

    // pre: size > 0
    public static Object peek() {
        assert size > 0 : "Non-positive size: " + size;

        return elements[(finish - 1 + elements.length) % elements.length];
    }
    // post: size` = size && Res = el[start] && el[0]`...el[size]` = el[0]...el[size]
    // && start` = start && finish` = finish

    // pre: size > 0
    public static Object dequeue() {
        assert size > 0 : "Non-positive size: " + size;

        Object ans = elements[start];
        elements[start] = null;
        size--;
        start = (start + 1) % elements.length;
        return ans;
    }
    // post: size` = size - 1 && el[0]`...el[size]` = el[0]...el[size]
    // && Res = el[start] && finish` = finish && start` ->

    // pre: size > 0
    public static Object remove() {
        assert size > 0 : "Non-positive size: " + size;

        size--;
        finish = (finish - 1 + elements.length) % elements.length;
        Object ans = elements[finish];
        elements[finish] = null;
        return ans;
    }
    // post: size` = size - 1 && el[0]`...el[size]` = el[0]...el[size]
    // && Res = el[finish <-] && start` = start && finish` <-

    // pre: true
    public static int size() {
        return size;
    }
    // post: size` = size && Res = size && el[0]`...el[size]` = el[0]...el[size]
    // start` = start && finish` = finish

    // pre: true
    public static boolean isEmpty() {
        return size == 0;
    }
    // post: size` = size && Res = (size == 0) && el[0]`...el[size]` = el[0]...el[size]
    // start` = start && finish` = finish

    // pre: true
    public static void clear() {
        Object[] es = new Object[10];
        elements = es;
        size = 0;
        start = 0;
        finish = 0;
    }
    // size` = 0 && element isEmpty && start` = 0 && finish` = 0
}
