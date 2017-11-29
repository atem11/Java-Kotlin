package expression.exceptions;

/**
 * Created by atem1 on 02.04.2017.
 */
public class CheckedAdd extends BinOperator implements TripleExpression {
    public CheckedAdd(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    private void check(int a, int b) throws ParseException {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException();
        }
        if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new OverflowException();
        }
    }

    protected int operator(int a, int b) throws ParseException {
        check(a, b);
        return a + b;
    }
}
