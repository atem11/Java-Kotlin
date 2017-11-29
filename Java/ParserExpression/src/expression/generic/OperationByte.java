package expression.generic;

/**
 * Created by atem1 on 10.04.2017.
 */
public class OperationByte implements Operation<Byte> {
    private Byte make(Integer val) {
        val = val & 0xFF;
        return val.byteValue();
    }

    @Override
    public Byte parse(String constant) throws ParseException {
        return Byte.parseByte(constant);
    }

    @Override
    public Byte add(Byte a, Byte b) throws ParseException {
        return make(a + b);
    }

    @Override
    public Byte subtract(Byte a, Byte b) throws ParseException {
        return make(a - b);
    }

    @Override
    public Byte multiply(Byte a, Byte b) throws ParseException {
        return make(a * b);
    }

    @Override
    public Byte divide(Byte a, Byte b) throws ParseException {
        return make(a / b);
    }

    @Override
    public Byte negate(Byte a) throws ParseException {
        return make(-a);
    }

    @Override
    public Byte abs(Byte a) throws ParseException {
        if (a < 0) {
            return make(-a);
        } else {
            return a;
        }
    }

    @Override
    public Byte square(Byte a) throws ParseException {
        return make(a * a);
    }

    @Override
    public Byte mod(Byte a, Byte b) throws ParseException {
        return make(a % b);
    }
}
