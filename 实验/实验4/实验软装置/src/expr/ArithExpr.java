package expr;

import parser.Scanner;
import token.DecimalToken;

/**
 * The type Arith expr.
 */
public class ArithExpr extends Expr {
    private final double value;

    public ArithExpr(DecimalToken t) {
        super(t);
        tag = Scanner.kingOfChar.ArithExpr;
        value = t.getValue();
    }

    public ArithExpr(double v) {
        super(null);
        tag = Scanner.kingOfChar.ArithExpr;
        value = v;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public double getValue() {
        return value;
    }

    public boolean isTerminal() {
        return false;
    }

}
