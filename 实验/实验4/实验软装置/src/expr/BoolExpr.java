/**
 * 
 */
package expr;

import token.BooleanToken;
import scanner.Tag;

/**
 * BoolExpr类
 * @author Aaron-Qiu
 *
 */
public class BoolExpr extends Expr {
	boolean value;
	
	/**
	 * 构造函数，相当于BoolExpr -> BooleanToken
	 * @param t，传入一个BooleanToken
	 */
	public BoolExpr(BooleanToken t) {
		super(t);
		tag = Tag.BoolExpr;
		value = t.getValue();
	}
	
	/**
	 * 构造函数
	 * @param v，传入一个布尔值
	 */
	public BoolExpr(boolean v) {
		super(null);
		tag = Tag.BoolExpr;
		value = v;
	}
	
	/**
	 * 用于获取BoolExpr储存的布尔值
	 * @return
	 */
	public boolean getValue() {
		return value;
	}
	
	/**
	 * 用于查看当前对象是否为终结符
	 */
	public boolean isTerminal() {
		return false;
	}
}
