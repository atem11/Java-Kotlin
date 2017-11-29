package expression.generic;


/**
 * Created by atem1 on 09.04.2017.
 */
public class GenericTabulator implements Tabulator {
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        Operation op = null;

        switch (mode) {
            case "i":
                op = new OperationInteger();
                break;
            case "d":
                op = new OperationDouble();
                break;
            case "bi":
                op = new OperationBigInteger();
                break;
            case  "u":
                op = new OperationInt();
                break;
            case "b":
                op = new OperationByte();
                break;
            case "f":
                op = new OperationFloat();
                break;
        }

        return makeT(op, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] makeT(Operation<T> op, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        ExpressionParser<T> parser = new ExpressionParser<>(op);
        Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                for (int k = z1; k <= z2; k++) {
                    try {
                        res[i - x1][j - y1][k - z1] = parser.parse(expression).evaluate(op.parse(Integer.toString(i)), op.parse(Integer.toString(j)), op.parse(Integer.toString(k)));
                    } catch (Exception e) {
                        res[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }

        return res;
    }
}