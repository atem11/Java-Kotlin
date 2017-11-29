package expression.generic;


/**
 * Created by atem1 on 03.04.2017.
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        String expression = "-0.43535 + x - 5.34";
        Operation op = new OperationDouble();
        ExpressionParser<Double> parser = new ExpressionParser<>(op);
        try {
            System.out.println(parser.parse(expression).evaluate(0.0, 0.0, 0.0));
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
}
