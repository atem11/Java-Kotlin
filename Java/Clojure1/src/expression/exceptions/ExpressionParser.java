package expression.exceptions;

import java.util.Stack;

/**
 * Created by atem1 on 27.03.2017.
 */
public class ExpressionParser implements Parser {
    private String expression, variable;
    private int pointer, constant;

    private enum Operator {
        CONSTANT, PLUS, MINUS, MULTIPLY, DIVIDE, END,
        VARIABLE, LBRACE, RBRACE, ABS, SQRT, MIN, MAX,
        NEGATE
    }

    private Operator operator;
    Stack brace = new Stack();


    public TripleExpression parse(String expression) throws ParseException {
        pointer = 0;
        this.expression = expression;
        operator = null;
        brace.clear();
        TripleExpression res = minMax();
        if (!brace.isEmpty()) {
            throw new ParseException("Lose ) at position: " + pointer + " for ( at position: " + brace.peek());
        }
        return res;
    }

    private void check(TripleExpression res1, TripleExpression res2, String func) throws ParseException {
        if (res1 == null) {
            throw new ParseException("Lose first argument for " + func + " at position: " + pointer);
        }
        if (res2 == null) {
            throw new ParseException("Lose second argument for " + func + " at position: " + pointer);
        }
    }

    private TripleExpression minMax() throws ParseException {
        TripleExpression prev = subAdd();
        while (true) {
            switch (operator) {
                case MIN:
                    TripleExpression next = subAdd();
                    check(prev, next, "MIN");
                    prev = new CheckedMin(prev, next);
                    break;
                case MAX:
                    next = subAdd();
                    check(prev, next, "MAX");
                    prev = new CheckedMax(prev, next);
                    break;
                default:
                    return prev;
            }
        }
    }

    private TripleExpression subAdd() throws ParseException {
        TripleExpression prev = divMul();
        while (true) {
            switch (operator) {
                case PLUS:
                    TripleExpression next = divMul();
                    check(prev, next, "+");
                    prev = new CheckedAdd(prev, next);
                    break;
                case MINUS:
                    next = divMul();
                    check(prev, next, "-");
                    prev = new CheckedSubtract(prev, next);
                    break;
                default:
                    return prev;
            }
        }
    }

    private TripleExpression divMul() throws ParseException {
        TripleExpression prev = unary();
        while (true) {
            switch (operator) {
                case MULTIPLY:
                    TripleExpression next = unary();
                    check(prev, next, "*");
                    prev = new CheckedMultiply(prev, next);
                    break;
                case DIVIDE:
                    next = unary();
                    check(prev, next, "/");
                    prev = new CheckedDivide(prev, next);
                    break;
                default:
                    return prev;
            }
        }
    }

    private TripleExpression unary() throws ParseException {
        getNext();
        TripleExpression res;
        switch (operator) {
            case CONSTANT:
                res = new Const(constant);
                getNext();
                break;
            case VARIABLE:
                res = new Variable(variable);
                getNext();
                break;
            case ABS:
                TripleExpression next = unary();
                if (next == null) {
                    throw new ParseException("Lose argument for abs at: " + pointer);
                }
                res = new CheckedAbs(next);
                break;
            case SQRT:
                next = unary();
                if (next == null) {
                    throw new ParseException("Lose argument for sqrt at: " + pointer);
                }
                res = new CheckedSqrt(next);
                break;
            case NEGATE:
                res = new CheckedNegate(unary());
                break;
            case LBRACE:
                res = minMax();
                getNext();
                break;
            default:
                res = null;
        }
        return res;
    }

    private void check1(String func) throws ParseException {
        char nxt;
        if (pointer < expression.length()) {
            nxt = expression.charAt(pointer);
            if (!Character.isWhitespace(nxt) && nxt != '(' && nxt != '-') {
                throw new ParseException("Unbound function: " + func + nxt + " at position: " + pointer);
            }
        }
    }

    private void getNext() throws ParseException {
        char ch = nextChar();
        if (Character.isDigit(ch)) {
            int left = pointer - 1, right = left;
            while (Character.isDigit(ch)) {
                right++;
                ch = nextChar();
            }
            pointer--;
            try {
                constant = Integer.parseInt(expression.substring(left, right));
            } catch (NumberFormatException e) {
                throw new ParseException("OVERFLOW constant " + expression.substring(left, right) + " at position: " + pointer);
            }
            operator = Operator.CONSTANT;
            return;
        } else if ((pointer + 2) <= expression.length() && expression.substring(pointer - 1, pointer + 2).equals("abs")) {
            operator = Operator.ABS;
            pointer += 2;
            check1("abs");
            return;
        } else if ((pointer + 3) <= expression.length() && expression.substring(pointer - 1, pointer + 3).equals("sqrt")) {
            operator = Operator.SQRT;
            pointer += 3;
            check1("sqrt");
            return;
        } else if ((pointer + 2) <= expression.length() && expression.substring(pointer - 1, pointer + 2).equals("min")) {
            operator = Operator.MIN;
            pointer += 2;
            check1("min");
            return;
        } else if ((pointer + 2) <= expression.length() && expression.substring(pointer - 1, pointer + 2).equals("max")) {
            operator = Operator.MAX;
            pointer += 2;
            check1("max");
            return;
        }
        switch (ch) {
            case '-':
                if (operator == Operator.RBRACE || operator == Operator.CONSTANT || operator == Operator.VARIABLE) {
                    operator = Operator.MINUS;
                } else {
                    ch = nextChar();
                    if (Character.isDigit(ch)) {
                        int left = pointer - 1, right = left;
                        while (Character.isDigit(ch)) {
                            right++;
                            ch = nextChar();
                        }
                        pointer--;
                        try {
                            constant = Integer.parseInt("-" + expression.substring(left, right));
                        } catch (NumberFormatException e) {
                            throw new ParseException("OVERFLOW constant -" + expression.substring(left, right) + " at position: " + pointer);
                        }
                        operator = Operator.CONSTANT;
                    } else {
                        pointer--;
                        operator = Operator.NEGATE;
                    }
                }
                return;
            case '+':
                operator = Operator.PLUS;
                return;
            case '*':
                operator = Operator.MULTIPLY;
                return;
            case '/':
                operator = Operator.DIVIDE;
                return;
            case '(':
                brace.push(pointer);
                operator = Operator.LBRACE;
                return;
            case ')':
                if (brace.isEmpty()) {
                    throw new ParseException("Lose ( at position: " + pointer);
                } else {
                    brace.pop();
                }
                operator = Operator.RBRACE;
                return;
            case 'x':
            case 'y':
            case 'z':
                operator = Operator.VARIABLE;
                variable = Character.toString(ch);
                return;
        }
        if (!Character.isWhitespace(ch)) {
            throw new ParseException("Uncorrected symbol: '" + ch + "' at position: " + pointer);
        }
    }

    private char nextChar() {
        if (pointer < expression.length()) {
            while (Character.isWhitespace(expression.charAt(pointer++)) && pointer < expression.length()) {

            }
            pointer--;
            return expression.charAt(pointer++);
        } else {
            operator = Operator.END;
            return ' ';
        }
    }
}
