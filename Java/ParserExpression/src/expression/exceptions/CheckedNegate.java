package expression.exceptions;

/**
 * Created by atem1 on 02.04.2017.
 */
public class CheckedNegate extends UnOperator implements TripleExpression {

    public CheckedNegate(TripleExpression val) {
        super(val);
    }

    private void check(int a) throws ParseException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    protected int operator(int val) throws ParseException {
        check(val);
        return -val;
    }
}
