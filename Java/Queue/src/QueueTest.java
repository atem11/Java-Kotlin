public class QueueTest {
    public static void test(Queue q1, Queue q2) {
        for (int i = 1; i <= 10; i++) {
            q1.enqueue(i * 100);
            q2.enqueue(i * 10);
        }

        while (!q1.isEmpty()) {
            System.out.println(q1.size() + " " + q1.dequeue());
        }
        System.out.println();

        while (!q2.isEmpty()) {
            System.out.println(q2.size() + " " + q2.element());
            q2.dequeue();
            q2.enqueue("Hello");
            q2.dequeue();
        }

    }

    public static void main(String[] args) {
        test(new LinkedQueue(), new ArrayQueue());
    }
}
