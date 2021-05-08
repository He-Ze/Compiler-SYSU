/**
 * Terminal.java
 */
package expr;

import token.Token;

/**
 * 终结符类
 * @author Aaron-Qiu
 *
 */
public class Terminal extends Expr {
	
	/**
	 * 构造函数
	 * @param t,传入一个token构造
	 */
	public Terminal(Token t) {
		super(t);
		tag = t.getTag();		
	}
	
	public Terminal() {
		super();
	}

	/**
	 * 用于查看当前对象是否为终结符
	 */
	public boolean isTerminal() {
		return true;
	}
	
}
