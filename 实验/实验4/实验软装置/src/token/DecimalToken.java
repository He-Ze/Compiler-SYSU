package token;

import decimal.Exponent;
import decimal.Fraction;
import decimal.Integral;
import parser.Scanner;

/**
 * The type Decimal token.
 */
public class DecimalToken extends Token {
    Integral integral;
    Fraction fraction;
    Exponent exponent;

    /**
     * 构造函数1
     */
    public DecimalToken(Integral i, Fraction f, Exponent e) {
        type = "Decimal";
        tag = Scanner.kingOfChar.NUM;
        integral = new Integral(i);
        fraction = new Fraction(f);
        exponent = new Exponent(e);
    }

    /**
     * 构造函数2
     */
    public DecimalToken(Integral i, Exponent e) {
        type = "Decimal";
        tag = Scanner.kingOfChar.NUM;
        integral = new Integral(i);
        fraction = null;
        exponent = new Exponent(e);
    }

    /**
     * 构造函数3
     */
    public DecimalToken(Integral i, Fraction f) {
        type = "Decimal";
        tag = Scanner.kingOfChar.NUM;
        integral = new Integral(i);
        fraction = new Fraction(f);
        exponent = null;
    }

    /**
     * 构造函数4
     */
    public DecimalToken(Integral i) {
        type = "Decimal";
        tag = Scanner.kingOfChar.NUM;
        integral = new Integral(i);
        fraction = null;
        exponent = null;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public double getValue() {
        double a = integral.getValue();
        if (fraction != null)
            a += fraction.getValue();
        if (exponent != null)
            a *= Math.pow(10, exponent.getValue());
        return a;
    }

    public String getString() {
        String a = integral.getString();
        if (fraction != null)
            a += fraction.getString();
        if (exponent != null)
            a += exponent.getString();
        return a;
    }
}
