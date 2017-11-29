package expression.generic;

/**
 * Created by atem1 on 10.04.2017.
 */
public class OperationDouble implements Operation<Double> {
    @Override
    public Double parse(String constant) throws ParseException {
        return Double.parseDouble(constant);
    }

    @Override
    public Double add(Double a, Double b) throws ParseException {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) throws ParseException {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) throws ParseException {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) throws ParseException {
        return a / b;
    }

    @Override
    public Double negate(Double a) throws ParseException {
        return -a;
    }

    @Override
    public Double abs(Double a) throws ParseException {
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
    }

    @Override
    public Double square(Double a) throws ParseException {
        return a * a;
    }

    @Override
    public Double mod(Double a, Double b) throws ParseException {
        return a % b;
    }
}
