package ru.itmo.cttdev.Abramov.expression;

/**
 * Created by atem1 on 19.03.2017.
 */
public class Divide extends BinOperator implements ExpressionAll {
    public Divide(ExpressionAll first, ExpressionAll second) {
        super(first, second);
    }

    protected int operator(int a, int b) {
        return a / b;
    }

    protected double operator(double a, double b) {
        return a / b;
    }
}
