package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
abstract public class BinOperator<T> implements TripleExpression<T> {
    private TripleExpression<T> first, second;

    Operation<T> op;

    protected BinOperator(TripleExpression<T> first, TripleExpression<T> second, Operation<T> op) {
        this.first = first;
        this.second = second;
        this.op = op;
    }

    public T evaluate(T x, T y, T z) throws ParseException {
        return operator(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    abstract protected T operator(T a, T b) throws ParseException;
}
