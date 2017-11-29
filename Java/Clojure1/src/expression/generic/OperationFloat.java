package expression.generic;

/**
 * Created by atem1 on 10.04.2017.
 */
public class OperationFloat implements Operation<Float> {
    @Override
    public Float parse(String constant) throws ParseException {
        return Float.parseFloat(constant);
    }

    @Override
    public Float add(Float a, Float b) throws ParseException {
        return a + b;
    }

    @Override
    public Float subtract(Float a, Float b) throws ParseException {
        return a - b;
    }

    @Override
    public Float multiply(Float a, Float b) throws ParseException {
        return a * b;
    }

    @Override
    public Float divide(Float a, Float b) throws ParseException {
        return a / b;
    }

    @Override
    public Float negate(Float a) throws ParseException {
        return -a;
    }

    @Override
    public Float abs(Float a) throws ParseException {
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
    }

    @Override
    public Float square(Float a) throws ParseException {
        return a * a;
    }

    @Override
    public Float mod(Float a, Float b) throws ParseException {
        return a % b;
    }
}
