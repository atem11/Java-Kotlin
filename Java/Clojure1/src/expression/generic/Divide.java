package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public class Divide<T> extends BinOperator<T> implements TripleExpression<T> {
    public Divide(TripleExpression<T> first, TripleExpression<T> second, Operation<T> op) {
        super(first, second, op);
    }

    protected T operator(T a, T b) throws ParseException {
        return op.divide(a, b);
    }
}
