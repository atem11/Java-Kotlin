package expression.generic;

import java.util.Stack;

/**
 * Created by atem1 on 27.03.2017.
 */
public class ExpressionParser<T> implements Parser<T> {
    private String expression, variable;
    private int pointer, left, right;
    private T constant;

    private enum Operator {
        CONSTANT, PLUS, MINUS, MULTIPLY, DIVIDE, END,
        VARIABLE, LBRACE, RBRACE, ABS, SQUARE, MOD,
        NEGATE
    }

    private Operator operator;
    Operation<T> op;
    Stack brace = new Stack();

    public ExpressionParser(Operation<T> op) {
        this.op = op;
    }

    public TripleExpression<T> parse(String expression) throws ParseException {
        pointer = 0;
        this.expression = expression;
        operator = null;
        brace.clear();
        TripleExpression res = subAdd();
        if (!brace.isEmpty()) {
            throw new ParseException("Lose ) at position: " + pointer + " for ( at position: " + brace.peek());
        }
        return res;
    }

    private void check(TripleExpression<T> res1, TripleExpression<T> res2, String func) throws ParseException {
        if (res1 == null) {
            throw new ParseException("Lose first argument for " + func + " at position: " + pointer);
        }
        if (res2 == null) {
            throw new ParseException("Lose second argument for " + func + " at position: " + pointer);
        }
    }

    private TripleExpression<T> subAdd() throws ParseException {
        TripleExpression<T> prev = divMul();
        while (true) {
            switch (operator) {
                case PLUS:
                    TripleExpression<T> next = divMul();
                    check(prev, next, "+");
                    prev = new Add<T>(prev, next, op);
                    break;
                case MINUS:
                    next = divMul();
                    check(prev, next, "-");
                    prev = new Subtract<T>(prev, next, op);
                    break;
                default:
                    return prev;
            }
        }
    }

    private TripleExpression<T> divMul() throws ParseException {
        TripleExpression<T> prev = unary();
        while (true) {
            switch (operator) {
                case MULTIPLY:
                    TripleExpression<T> next = unary();
                    check(prev, next, "*");
                    prev = new Multiply<T>(prev, next, op);
                    break;
                case DIVIDE:
                    next = unary();
                    check(prev, next, "/");
                    prev = new Divide<T>(prev, next, op);
                    break;
                case MOD:
                    next = unary();
                    check(prev, next, "mod");
                    prev = new Mod<T>(prev, next, op);
                    break;
                default:
                    return prev;
            }
        }
    }

    private TripleExpression<T> unary() throws ParseException {
        getNext();
        TripleExpression res;
        switch (operator) {
            case CONSTANT:
                res = new Const<T>(constant);
                getNext();
                break;
            case VARIABLE:
                res = new Variable<T>(variable);
                getNext();
                break;
            case ABS:
                TripleExpression<T> next = unary();
                if (next == null) {
                    throw new ParseException("Lose argument for abs at: " + pointer);
                }
                res = new Abs<T>(next, op);
                break;
            case SQUARE:
                next = unary();
                if (next == null) {
                    throw new ParseException("Lose argument for square at: " + pointer);
                }
                res = new Square<T>(next, op);
                break;
            case NEGATE:
                res = new Negate<T>(unary(), op);
                break;
            case LBRACE:
                res = subAdd();
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

    private void praseIn(char ch) {
        left = pointer - 1;
        right = left;
        while (Character.isDigit(ch) || ch == '.' || Character.toString(ch).toLowerCase().equals("e")) {
            right++;
            ch = nextChar();
        }
        pointer--;
    }

    private void getNext() throws ParseException {
        char ch = nextChar();
        if (Character.isDigit(ch)) {
            praseIn(ch);
            try {
                constant = op.parse(expression.substring(left, right));
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
        } else if ((pointer + 5) <= expression.length() && expression.substring(pointer - 1, pointer + 5).equals("square")) {
            operator = Operator.SQUARE;
            pointer += 5;
            check1("square");
            return;
        } else if ((pointer + 2) <= expression.length() && expression.substring(pointer - 1, pointer + 2).equals("mod")) {
            operator = Operator.MOD;
            pointer += 2;
            check1("mod");
            return;
        }
        switch (ch) {
            case '-':
                if (operator == Operator.RBRACE || operator == Operator.CONSTANT || operator == Operator.VARIABLE) {
                    operator = Operator.MINUS;
                } else {
                    ch = nextChar();
                    if (Character.isDigit(ch)) {
                        praseIn(ch);
                        try {
                            constant = op.parse("-" + expression.substring(left, right));
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
