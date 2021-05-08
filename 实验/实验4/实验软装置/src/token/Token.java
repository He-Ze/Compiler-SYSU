/**
 * 
 */
package token;
import expr.*;

/**
 * Token抽象类，派生出其他Token
 * @author Aaron-Qiu
 */
public abstract class Token {
	protected String type;
	protected int tag;
	/**
	 * Token构造函数
	 */
	public Token() {
		type = "";
	}
	
	/**
	 * 用于获取token类型
	 * @return token的类型
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * 用于获取token的tag值
	 * @return token的tag值
	 */
	public int getTag() {
		return tag;
	}
	
	/**
	 * 获取token的值
	 * @return token的值
	 */
	public abstract String getValueofString();
}
