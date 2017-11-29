package expression.generic;

/**
 * Created by atem1 on 12.04.2017.
 */
public class Abs<T> extends UnOperator<T> implements TripleExpression<T> {
    public Abs(TripleExpression<T> value, Operation<T> op) {
        super(value, op);
    }

    protected T operator(T a) throws ParseException {
        return op.abs(a);
    }
}
