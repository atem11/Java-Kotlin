package expression.exceptions;

/**
 * Created by atem1 on 05.04.2017.
 */
public class CheckedAbs extends UnOperator implements TripleExpression {

    public CheckedAbs(TripleExpression val) {
        super(val);
    }

    private int check(int a) throws ParseException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
    }

    protected int operator(int val) throws ParseException {
        return check(val);
    }
}
