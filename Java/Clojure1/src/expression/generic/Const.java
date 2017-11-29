package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public class Const<T> implements TripleExpression<T> {
    private final T value;
    public Const(T value) {
        this.value = value;
    }

    public T evaluate(T x, T y, T z) throws ParseException {
        return value;
    }
}
