package ru.itmo.cttdev.Abramov.expression;

/**
 * Created by atem1 on 19.03.2017.
 */
public class Const implements ExpressionAll {
    private double value;

    public Const(double val) {
        value = val;
    }

    public Const(int val) {
        value = val;
    }
    public int evaluate(int val) {
        return (int) value;
    }

    public double evaluate(double val) {
        return value;
    }

    public int evaluate(int x, int y, int z) {
        return (int) value;
    }

}
