package expr;

import parser.Scanner;

/**
 * Arithexpr列表
 */
public class ArithExprList extends Expr {
    /**
     * 此时列表中的最大和最小值
     */
    public double max, min;

    /**
     * ArithExprList -> ArithExpr
     *
     * @param ae ArithExpr对象
     */
    public ArithExprList(ArithExpr ae) {
        tag = Scanner.kindOfChar.ArithExprList;
        max = min = ae.getValue();
    }

    /**
     * ArithExprList -> ArithExpr , ArithExprList
     *
     * @param ae  ArithExpr
     * @param ael ArithExprList
     */
    public ArithExprList(ArithExpr ae, ArithExprList ael) {
        tag = Scanner.kindOfChar.ArithExprList;
        double a = ae.getValue();
        max = Math.max(a, ael.max);
        min = Math.min(a, ael.min);
    }

    public boolean isTerminal() {
        return false;
    }
}
