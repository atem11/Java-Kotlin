package expression.generic;

/**
 * Created by atem1 on 10.04.2017.
 */
public class OperationInt implements Operation<Integer> {
    @Override
    public Integer parse(String constant) throws ParseException {
        return Integer.parseInt(constant);
    }

    @Override
    public Integer add(Integer a, Integer b) throws ParseException {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) throws ParseException {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) throws ParseException {
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) throws ParseException {
        return a / b;
    }

    @Override
    public Integer negate(Integer a) throws ParseException {
        return -a;
    }

    @Override
    public Integer abs(Integer a) throws ParseException {
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
    }

    @Override
    public Integer square(Integer a) throws ParseException {
        return a * a;
    }

    @Override
    public Integer mod(Integer a, Integer b) throws ParseException {
        return a % b;
    }
}
