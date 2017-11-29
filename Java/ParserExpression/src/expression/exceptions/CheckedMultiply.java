package expression.exceptions;

/**
 * Created by atem1 on 02.04.2017.
 */
public class CheckedMultiply extends BinOperator implements TripleExpression {
    public CheckedMultiply(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    private void check(int a1, int b1) throws ParseException {
        int a, b;
        if (a1 > b1) {
            b = a1;
            a = b1;
        } else {
            a = a1;
            b = b1;
        }
        if (a < 0) {
            if (b < 0) {
                if (a < Integer.MAX_VALUE / b) {
                    throw new OverflowException();
                }
            } else if (b > 0) {
                if (Integer.MIN_VALUE / b > a) {
                    throw new OverflowException();
                }
            }
        } else if (a > 0) {
            if (a > Integer.MAX_VALUE / b) {
                throw new OverflowException();
            }
        }
    }


    protected int operator(int a, int b) throws ParseException {
        check(a, b);
        return a * b;
    }
}
