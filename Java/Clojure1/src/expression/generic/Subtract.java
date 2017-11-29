package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public class Subtract<T> extends BinOperator<T> implements TripleExpression<T> {
    public Subtract(TripleExpression<T> first, TripleExpression<T> second, Operation<T> op) {
        super(first, second, op);
    }

    protected T operator(T a, T b) throws ParseException {
        return op.subtract(a, b);
    }
}
