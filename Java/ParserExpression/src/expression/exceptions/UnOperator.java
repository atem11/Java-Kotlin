package expression.exceptions;

/**
 * Created by atem1 on 12.04.2017.
 */
abstract public class UnOperator implements TripleExpression {
    private TripleExpression value;

    protected UnOperator(TripleExpression value) {
        this.value = value;
    }

    public int evaluate(int x, int y, int z) throws ParseException {
        return operator(value.evaluate(x, y, z));
    }

    abstract protected int operator(int val) throws ParseException;
}
