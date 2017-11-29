public class StackTest {
    public static void test(Stack s1, Stack s2) {
        for (int i = 1; i <= 10; i++) {
            s1.push(i * 100);
            s2.push(i * 10);
        }
        while (!s1.isEmpty()) {
            System.out.println(s1.size() + " " + s1.pop() +
                    " " + s2.size() + " " + s2.pop());
        }
    }

    public static void main(String[] args) {
        test(new LinkedStack(), new ArrayStack());
    }
}
