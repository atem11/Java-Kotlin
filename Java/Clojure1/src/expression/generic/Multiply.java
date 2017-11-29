package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public class Multiply<T> extends BinOperator<T> implements TripleExpression<T> {
    public Multiply(TripleExpression<T> first, TripleExpression<T> second, Operation<T> op) {
        super(first, second, op);
    }

    protected T operator(T a, T b) throws ParseException {
        return op.multiply(a, b);
    }
}
