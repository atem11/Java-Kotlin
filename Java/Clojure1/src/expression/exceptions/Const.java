package expression.exceptions;

/**
 * Created by atem1 on 19.03.2017.
 */
public class Const implements TripleExpression {
    private final int value;

    public Const(int val) {
        value = val;
    }

    public int evaluate(int x, int y, int z) {
        return value;
    }
}
