package expression.exceptions;

/**
 * Created by atem1 on 02.04.2017.
 */
public class CheckedDivide extends BinOperator implements TripleExpression {
    public CheckedDivide(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    private void check(int a, int b) throws ParseException {
        if (b == 0) {
            throw new ParseException("Division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
    }

    protected int operator(int a, int b) throws ParseException {
        check(a, b);
        return a / b;
    }
}
