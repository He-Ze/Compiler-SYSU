package decimal;

/**
 * fraction->.integral
 */
public class Fraction extends Decimal {
    public final String dot = ".";
    public final Integral integral;

    /**
     * 构造函数
     *
     * @param s 数字字符串
     */
    public Fraction(String s) {
        integral = new Integral(s);
    }

    /**
     * 构造函数
     *
     * @param other 另一个小数部分
     */
    public Fraction(Fraction other) {
        integral = other.integral;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public double getValue() {
        double length = integral.length();
        return Math.pow(0.1, length) * integral.getValue();
    }

    public String getString() {
        return dot + integral.getString();
    }
}
