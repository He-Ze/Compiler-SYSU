package decimal;

/**
 * digit->0|1|2|3|4|5|6|7|8|9
 */
public class Digit extends Decimal {
    /**
     * 数字值
     */
    private final int value;

    /**
     * 构造函数
     *
     * @param str 数字的字符串
     */
    public Digit(String str) {
        value = Integer.parseInt(str);
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public int getValue() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }

}
