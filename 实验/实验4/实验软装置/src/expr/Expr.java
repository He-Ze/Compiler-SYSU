/**
 * Expr.java
 */
package expr;

import scanner.Tag;
import token.Token;

/**
 * @author Aaron-Qiu
 *
 */
public abstract class Expr{
	public Token token;
	public int tag;

	Expr(Token t) {
		token = t;
		tag = Tag.Expr;
	}
	
	public Expr() {
		token = null;
		tag = Tag.NULL;
	}
	
	public int getTag() {
		return tag;
	}
	
	/**
	 * 用于查看当前对象是否为终结符
	 */
	public abstract boolean isTerminal();
}
