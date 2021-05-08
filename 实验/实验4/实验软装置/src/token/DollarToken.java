/**
 * 
 */
package token;

import scanner.Tag;

/**
 * Dollar类token
 * @author Aaron-Qiu
 *
 */
public class DollarToken extends Token {
	/**
	 * 构造函数
	 */
	public DollarToken() {
		type = "$";
		tag = Tag.DOLLAR;
	}

	/**
	 * 返回$符号
	 * @return $符号
	 */
	public String getValueofString() {
		return type;
	}
}
