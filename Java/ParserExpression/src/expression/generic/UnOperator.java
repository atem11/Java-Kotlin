package expression.generic;

/**
 * Created by atem1 on 12.04.2017.
 */
abstract public class UnOperator<T> implements TripleExpression<T> {
    private TripleExpression<T> value;

    Operation<T> op;

    protected UnOperator(TripleExpression<T> value, Operation<T> op) {
        this.value = value;
        this.op = op;
    }

    public T evaluate(T x, T y, T z) throws ParseException {
        return operator(value.evaluate(x, y, z));
    }

    abstract protected T operator(T val) throws ParseException;
}
