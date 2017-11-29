package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public class Variable<T> implements TripleExpression<T> {
    private final String name;
    public Variable(String name) {
        this.name = name;
    }

    public T evaluate(T x, T y, T z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    }
}
