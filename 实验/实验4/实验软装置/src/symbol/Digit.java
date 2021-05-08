/**
 * 
 */
package symbol;

/**
 * 这个类用于表示一个0到9数字
 * @author Aaron-Qiu
 *
 */
public class Digit extends Symbol {
	 int value;
	 /**
	  * 构造函数
	  * @param str 用于初始化数字的字符串
	  */
	 public Digit(String str) {
		 value = Integer.parseInt(str);
	 }
	 
	 /**
	  * 获取数字的值
	  * @return 数字的值
	  */
	 public int getValue() {
		 return value;
	 }
	 
	 /**
	  * 获取数字的值的字符串形式
	  * @return 数字的值的字符串形式
	  */
	 public String getValueOfString() {
		 return "" + value;
	 }

}
