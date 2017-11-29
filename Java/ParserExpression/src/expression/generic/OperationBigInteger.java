package expression.generic;

import java.math.BigInteger;

/**
 * Created by atem1 on 10.04.2017.
 */
public class OperationBigInteger implements Operation<BigInteger> {
    @Override
    public BigInteger parse(String constant) throws ParseException {
        return new BigInteger(constant);
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) throws ParseException {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) throws ParseException {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) throws ParseException {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) throws ParseException {
        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) throws ParseException {
        return a.negate();
    }

    @Override
    public BigInteger abs(BigInteger a) throws ParseException {
        return a.abs();
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) throws ParseException {
        return a.remainder(b);
    }

    @Override
    public BigInteger square(BigInteger a) throws ParseException {
        return a.multiply(a);
    }
}
