package expression.generic;

/**
 * Created by atem1 on 10.04.2017.
 */
public class OperationInteger implements Operation<Integer> {

    @Override
    public Integer parse(String constant) throws ParseException {
        return Integer.parseInt(constant);
    }

    private void checkAdd(int a, int b) throws ParseException {
        if (a > 0 && b > Integer.MAX_VALUE - a) {
            throw new OverflowException();
        }
        if (a < 0 && b < Integer.MIN_VALUE - a) {
            throw new OverflowException();
        }
    }

    @Override
    public Integer add(Integer a, Integer b) throws ParseException {
        checkAdd(a, b);
        return a + b;
    }

    private void checkSubtract(int a, int b) throws ParseException {
        if (b > 0 && a < Integer.MIN_VALUE + b) {
            throw new OverflowException();
        }
        if (b < 0 && a > Integer.MAX_VALUE + b) {
            throw new OverflowException();
        }
    }

    @Override
    public Integer subtract(Integer a, Integer b) throws ParseException {
        checkSubtract(a, b);
        return a - b;
    }

    private void checkDivide(int a, int b) throws ParseException {
        if (b == 0) {
            throw new ParseException("Division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException();
        }
    }

    @Override
    public Integer divide(Integer a, Integer b) throws ParseException {
        checkDivide(a, b);
        return a / b;
    }

    private void checkMultiply(int a1, int b1) throws ParseException {
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

    @Override
    public Integer multiply(Integer a, Integer b) throws ParseException {
        checkMultiply(a, b);
        return a * b;
    }

    private void checkNegate(int a) throws ParseException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    @Override
    public Integer negate(Integer a) throws ParseException {
        checkNegate(a);
        return -a;
    }

    @Override
    public Integer abs(Integer a) throws ParseException {
        if (a < 0) {
            return negate(a);
        } else {
            return a;
        }
    }

    @Override
    public Integer square(Integer a) throws ParseException {
        return multiply(a, a);
    }

    @Override
    public Integer mod(Integer a, Integer b) throws ParseException {
        return a % b;
    }
}
