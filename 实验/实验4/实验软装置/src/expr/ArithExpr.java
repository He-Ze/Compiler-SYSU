/**
 * 
 */
package expr;

import token.DecimalToken;
import scanner.Tag;

/**
 * ArithExpr符号类
 * @author Aaron-Qiu
 *
 */
public class ArithExpr extends Expr{
	double value;
	
	/**
	 * 构造函数
	 * @param t，传入一个decimal类对象
	 */
	public ArithExpr(DecimalToken t) {
		super(t);
		tag = Tag.ArithExpr;
		value = t.getValue();
	}
	
	/**
	 * 构造函数
	 * @param v，传入一个double值，构造一个相应的ArithExpr
	 */
	public ArithExpr(double v) {
		super(null);
		tag = Tag.ArithExpr;
		value = v;
	}
	
	/**
	 * 获取ArithExpr对应的value值
	 * @return the value
	 */
	public double getValue() {
		return value;
	} 
	
	/**
	 * 用于查看当前对象是否为终结符
	 */
	public boolean isTerminal() {
		return false;
	}
	
}
