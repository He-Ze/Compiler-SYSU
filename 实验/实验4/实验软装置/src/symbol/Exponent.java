/**
 * 
 */
package symbol;

/**
 * @author Aaron-Qiu
 *
 */
public class Exponent extends Symbol {
	Integral integral;
	String ex = "e";
	String op;
	
	/**
	 * 构造函数，传入integral和运算符构造
	 * @param _integral
	 * @param _op
	 */
	public Exponent(Integral _integral, String _op) {
		integral = _integral;
		op = _op;
	}
	
	/**
	 * 构造函数，传入另一exponent深复制
	 * @param other
	 */
	public Exponent(Exponent other) {
		integral = other.integral;
		op = other.op;
	}
	
	/**
	 * 用于获取exponent的正负integral值
	 * @return exponent的正负integral值
	 */
	public double getValue() {
		return op.equals("-") ? -integral.getValue() : integral.getValue(); 
	}
	
	/**
	 * 用于获得符号正负值
	 * @return op's value
	 */
	public boolean isPositive() {
		return op.equals("-") ? false : true;
	}
	
	/**
	 * 用于获取exponent的字符串形式
	 * @return exponent的字符串形式
	 */
	public String getValueOfString() {
		return ex + op + integral.getValueOfString();
	}
	
}
