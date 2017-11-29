package ru.itmo.cttdev.Abramov.expression;

/**
 * Created by atem1 on 19.03.2017.
 */
abstract public class BinOperator implements ExpressionAll {
    private ExpressionAll firstOp;
    private ExpressionAll secondOp;

    protected BinOperator(ExpressionAll first, ExpressionAll second) {
        firstOp = first;
        secondOp = second;
    }

    public int evaluate(int val) {
        return operator(firstOp.evaluate(val), secondOp.evaluate(val));
    }

    public double evaluate(double val) {
        return operator(firstOp.evaluate(val), secondOp.evaluate(val));
    }

    public int evaluate(int x, int y, int z) {
        return operator(firstOp.evaluate(x, y, z), secondOp.evaluate(x, y, z));
    }

    abstract protected int operator(int x, int y);

    abstract protected double operator(double x, double y);

}
