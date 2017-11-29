package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public class Negate<T> implements TripleExpression<T> {
    private final TripleExpression<T> value;
    private final Operation<T> op;
    public Negate(TripleExpression<T> value, Operation<T> op) {
        this.value = value;
        this.op = op;
    }

    public T evaluate(T x, T y, T z) throws ParseException {
        T a = value.evaluate(x, y, z);
        return op.negate(a);
    }
}
