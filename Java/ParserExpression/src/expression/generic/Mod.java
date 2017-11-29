package expression.generic;

/**
 * Created by atem1 on 12.04.2017.
 */
public class Mod<T> extends BinOperator<T> implements TripleExpression<T> {
    public Mod(TripleExpression<T> first, TripleExpression<T> second, Operation<T> op) {
        super(first, second, op);
    }

    protected T operator(T a, T b) throws ParseException {
        return op.mod(a, b);
    }
}
