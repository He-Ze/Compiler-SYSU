/**
 * 
 */
package token;

import scanner.Tag;
import symbol.Exponent;
import symbol.Fraction;
import symbol.Integral;
import expr.*;

/**
 * Decimal类token
 * @author Aaron-Qiu
 *
 */
public class DecimalToken extends Token{
	Integral integral;
	Fraction fraction;
	Exponent exponent;
	
	/**
	 * 构造函数, 形式：111.111e+123
	 * @param i
	 * @param f
	 * @param e
	 */
	public DecimalToken(Integral i, Fraction f, Exponent e) {
		type = "Decimal";
		tag = Tag.NUM;
		integral = new Integral(i);
		fraction = new Fraction(f);
		exponent = new Exponent(e);
	}
	
	/**
	 * 构造函数, 形式：111e+123
	 * @param i
	 * @param e
	 */
	public DecimalToken(Integral i, Exponent e) {
		type = "Decimal";
		tag = Tag.NUM;
		integral = new Integral(i);
		fraction = null;
		exponent = new Exponent(e);
	}
	
	/**
	 * 构造函数, 形式：111.111
	 * @param i
	 * @param f
	 */
	public DecimalToken(Integral i, Fraction f) {
		type = "Decimal";
		tag = Tag.NUM;
		integral = new Integral(i);
		fraction = new Fraction(f);
		exponent = null;
	}
	
	/**
	 * 构造函数, 形式：111
	 * @param i
	 */
	public DecimalToken(Integral i) {
		type = "Decimal";
		tag = Tag.NUM;
		integral = new Integral(i);
		fraction = null;
		exponent = null;
	}
	
	/**
	 * 用于返回该decimal的double值
	 * @return decimal的double值
	 */
	public double getValue() {
		double re = integral.getValue();
		if (fraction != null) {
			re += fraction.getValue();
		}
		if (exponent != null) {
			re *= Math.pow(10, exponent.getValue());
		}
		return re;
	}
	
	/**
	 * 用于返回该decimal的string形式值
	 * @return decimal的string值
	 */
	public String getValueofString() {
		String re = integral.getValueOfString();
		if (fraction != null) {
			re += fraction.getValueOfString();
		}
		if (exponent != null) {
			re += exponent.getValueOfString();
		}
		return re;
	}

}
