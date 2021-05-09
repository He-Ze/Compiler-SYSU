/**
 * 
 */
package symbol;

/**
 * @author Aaron-Qiu
 *
 */
public class Fraction extends Symbol{
	String dot = ".";
	Integral integral;
	
	/**
	 * 构造函数
	 * @param intStr 一个代表integral字符串
	 */
	public Fraction(String intStr) {
		integral = new Integral(intStr);
	}
	
	/**
	 * 构造函数, 深复制
	 * @param other
	 */
	public Fraction(Fraction other) {
		integral = other.integral;
	}
	
	/**
	 * 返回小数值
	 * @return 小数值
	 */
	public double getValue() {
		double length = integral.length();
		return Math.pow(0.1, length) * integral.getValue();
	}

	/**
	 * 返回带含有小数点的小数形式
	 */
	public String getString() {
		return dot + integral.getString();
	}	
}
