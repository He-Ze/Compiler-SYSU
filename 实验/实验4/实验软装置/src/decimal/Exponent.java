package decimal;

/**
 * exponent->(E|e)(+|–|epsilon)integral
 */
public class Exponent extends Decimal {
    /**
     * 数字部分
     */
    private final Integral integral;
    /**
     * +|–|epsilon
     */
    private final String a;
    /**
     * The E.
     */
    private final String e = "e";

    /**
     * 构造函数
     *
     * @param integral 数字部分
     * @param op       正负号
     */
    public Exponent(Integral integral, String op) {
        this.integral = integral;
        this.a = op;
    }

    /**
     * 构造函数
     *
     * @param other 另一个exponent
     */
    public Exponent(Exponent other) {
        integral = other.integral;
        a = other.a;
    }

    /**
     * 获取数值
     *
     * @return the value
     */
    public double getValue() {
        return a.equals("-") ? -integral.getValue() : integral.getValue();
    }

    public String getString() {
        return e + a + integral.getString();
    }

}
