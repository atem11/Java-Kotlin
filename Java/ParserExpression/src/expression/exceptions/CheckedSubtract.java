package expression.exceptions;

/**
 * Created by atem1 on 02.04.2017.
 */
public class CheckedSubtract extends BinOperator implements TripleExpression {
    public CheckedSubtract(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    private void check(int a, int b) throws ParseException {
        if (b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        }
        if (b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
    }

    protected int operator(int a, int b) throws ParseException {
        check(a, b);
        return a - b;
    }
}
