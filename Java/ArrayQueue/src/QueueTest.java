public class QueueTest {
    public static void main(String[] args) {
        ArrayQueue q1 = new ArrayQueue();
        ArrayQueueADT q2 = new ArrayQueueADT();

        for (int i = 1; i <= 12; i++) {
            q1.enqueue(i * 100);
            ArrayQueueADT.enqueue(q2, i * 10);
            ArrayQueueModule.enqueue(i);
        }

        while (!q1.isEmpty()) {
            System.out.println(
                    q1.size() + " " + q1.dequeue()
            );
        }
        System.out.println();

        while (!ArrayQueueADT.isEmpty(q2)) {
            System.out.println(
                    ArrayQueueADT.element(q2) + " "
                            + ArrayQueueADT.size(q2)
            );
            ArrayQueueADT.dequeue(q2);
            ArrayQueueADT.dequeue(q2);
            ArrayQueueADT.enqueue(q2, 77);
        }
        System.out.println();

        ArrayQueueModule.clear();
        ArrayQueueModule.enqueue("Hello");
        ArrayQueueModule.enqueue(", ");
        ArrayQueueModule.enqueue("world");
        ArrayQueueModule.enqueue("!!!");
        while (!ArrayQueueModule.isEmpty()) {
            System.out.print(ArrayQueueModule.dequeue());
        }
        System.out.println();
    }
}
