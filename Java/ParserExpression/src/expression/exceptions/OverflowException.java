package expression.exceptions;

/**
 * Created by atem1 on 02.04.2017.
 */
public class OverflowException extends ParseException {
    public OverflowException(){
        super("overflow");
    }
}
