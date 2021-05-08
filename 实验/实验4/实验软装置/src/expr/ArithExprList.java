/**
 * 
 */
package expr;

import scanner.Tag;

/**
 * ArithExprList符号类
 * @author Aaron-Qiu
 *
 */
public class ArithExprList extends Expr {
	/**
	 * 用于记录当前list内的最大值最小值
	 */
	public double max, min;
	
	/**
	 * 构造函数，对应ArithExprList -> ArithExpr
	 * @param ae
	 */
	public ArithExprList(ArithExpr ae) {
		tag = Tag.ArithExprList;
		max = min = ae.getValue();
	}
	
	/**
	 * 构造函数，对应ArithExprList -> ArithExpr, ArithExprList
	 * @param ae
	 */
	public ArithExprList(ArithExpr ae, ArithExprList ael) {
		tag = Tag.ArithExprList;
		double v = ae.getValue();
		max = v > ael.max ? v : ael.max;
		min = v < ael.min ? v : ael.min;
	}
	
	/**
	 * 用于查看当前对象是否为终结符
	 */
	public boolean isTerminal() {
		return false;
	}
}
