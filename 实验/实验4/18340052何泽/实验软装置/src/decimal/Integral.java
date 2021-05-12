package decimal;

import java.util.ArrayList;

/**
 * integral->digit+
 */
public class Integral extends Decimal {
    /**
     * 数字列表
     */
    private ArrayList<Digit> integral = new ArrayList<>();

    /**
     * 构造函数
     *
     * @param a 另一个数
     */
    public Integral(Integral a) {
        integral = a.integral;
    }

    /**
     * 构造函数
     *
     * @param str 字符串形式
     */
    public Integral(String str) {
        for (int i = 0; i < str.length(); i++) {
            integral.add(new Digit(String.valueOf(str.charAt(i))));
        }
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public double getValue() {
        double value = 0, j = 1;
        for (int i = integral.size() - 1; i >= 0; i--, j *= 10) {
            value += integral.get(i).getValue() * j;
        }
        return value;
    }

    public String getString() {
        String s = "";
        for (Digit digit : integral) {
            s += digit.getString();
        }
        return s;
    }

    /**
     * 获取长度
     *
     * @return 个数
     */
    public int length() {
        return integral.size();
    }
}
