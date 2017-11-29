package expression.exceptions;

/**
 * Created by atem1 on 05.04.2017.
 */
public class CheckedMax extends BinOperator implements TripleExpression {
    public CheckedMax(TripleExpression first, TripleExpression second) {
        super(first, second);
    }


    protected int operator(int a, int b) throws ParseException {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}
