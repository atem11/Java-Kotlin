package expression.generic;

/**
 * Created by atem1 on 09.04.2017.
 */
public interface Operation<T> {
    T parse(String constant) throws ParseException;
    T add(T a, T b) throws ParseException;
    T subtract(T a, T b) throws ParseException;
    T multiply(T a, T b) throws ParseException;
    T divide(T a, T b) throws ParseException;
    T negate(T a) throws ParseException;
    T abs(T a) throws ParseException;
    T square(T a) throws ParseException;
    T mod(T a, T b) throws ParseException;
}
