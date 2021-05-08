/**
 * 
 */
package symbol;

import java.util.ArrayList;

/**
 * 这个类用于表示一个整数
 * @author Aaron-Qiu
 *
 */
public class Integral extends Symbol {
	ArrayList<Digit> integral;
	
	/**
	 * 构造函数
	 */
	public Integral() {
		integral = new ArrayList<Digit>();
	}
	
	/**
	 * 深复制构造函数
	 * @param other integral
	 */
	public Integral(Integral other) {
		integral = new ArrayList<Digit>();
		integral = other.integral;
	}
	
	/**
	 * 构造函数，接收一个字符串构造
	 * @param str
	 */
	public Integral(String str) {
		integral = new ArrayList<Digit>();
		for (int i = 0; i < str.length(); i++) {
			integral.add(new Digit(str.charAt(i) + ""));
		}
	}
	
	public void addDigit(String str) {
		integral.add(new Digit(str));
	}
	
	public double getValue() {
		double re = 0;
		double j = 1;
		for (int i = integral.size() - 1; i >= 0; i--) {
			re += integral.get(i).getValue() * j;
			j *= 10;
		}
		return re;
	}
	
	public String getValueOfString() {
		String re = "";
		for (int i = 0; i < integral.size(); i++) {
			re += integral.get(i).getValueOfString();
		}
		return re;
	}
	
	public int length() {
		return integral.size();
	}
}
