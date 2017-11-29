package expression.exceptions;

/**
 * Created by atem1 on 27.03.2017.
 */
abstract public class BinOperator implements TripleExpression {
    private TripleExpression firstOp;
    private TripleExpression secondOp;

    protected BinOperator(TripleExpression first, TripleExpression second) {
        firstOp = first;
        secondOp = second;
    }

    public int evaluate(int x, int y, int z) throws ParseException {
        return operator(firstOp.evaluate(x, y, z), secondOp.evaluate(x, y, z));
    }

    abstract protected int operator(int x, int y) throws ParseException;
}
