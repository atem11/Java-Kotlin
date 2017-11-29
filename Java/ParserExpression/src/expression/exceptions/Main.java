package expression.exceptions;

/**
 * Created by atem1 on 03.04.2017.
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        String expression = "abs 4";
        System.out.println("x       f");
        for (int i = 0; i <= 10; i++) {
            try {
                System.out.print(i + "       ");
                System.out.println(new ExpressionParser().parse(expression).evaluate(i, 0, 0));
            } catch (ParseException e) {
                System.out.println(e);
            }
        }
    }
}
