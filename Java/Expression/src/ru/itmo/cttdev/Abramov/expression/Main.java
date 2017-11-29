package ru.itmo.cttdev.Abramov.expression;

public class Main {

    public static void main(String[] args) {
        double value = Double.parseDouble(args[0]);

        System.out.println(
                new Add(
                        new Const(1),
                        new Subtract(
                                new Multiply(
                                        new Variable("x"),
                                        new Variable("x")
                                ),
                                new Multiply(
                                        new Const(2),
                                        new Variable("x")
                                )
                        )
                ).evaluate(value)
        );
    }
}
