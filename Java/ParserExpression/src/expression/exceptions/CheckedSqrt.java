package expression.exceptions;


/**
 * Created by atem1 on 29.03.2017.
 */
public class CheckedSqrt extends UnOperator implements TripleExpression {
    private TripleExpression value;

    public CheckedSqrt(TripleExpression val) {
        super(val);
    }

    private void check(int a) throws ParseException {
        if (a < 0) {
            throw new ParseException("Negative value of sqrt");
        }
    }

    private int sqrt(int val) {
        if (val <= 1) {
            return val;
        }
        int l = 0, r = val;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (mid < Integer.MAX_VALUE / mid) {
                if (mid * mid > val) {
                    r = mid;
                } else {
                    l = mid;
                }
            } else {
                r = mid;
            }
        }
        return l;
    }

    protected int operator(int val) throws ParseException {
        check(val);
        return sqrt(val);
    }
}
