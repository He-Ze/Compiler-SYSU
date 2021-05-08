/**
 * 
 */
package token;

/**
 * Operator类型的token，包含左右括号
 * @author Aaron-Qiu
 *
 */
public class OperatorToken extends Token {
	String operator;
	
	/**
	 * 构造函数
	 * @param _operator, 运算符
	 * @param _tag，运算符所属的tag
	 */
	public OperatorToken(String _operator, int _tag) {
		type = "Operator";
		operator = _operator;
		tag = _tag;
	}
	
	/**
	 * @return operator的string形式
	 */
	public String getValueofString() {
		return operator;
	}
}
