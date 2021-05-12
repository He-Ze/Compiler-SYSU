package expr;

import parser.Scanner;
import token.Decimal;

/**
 * The type Arith expr.
 */
public class ArithExpr extends Expr {
    private final double value;

    public ArithExpr(Decimal t) {
        super(t);
        tag = Scanner.kindOfChar.ArithExpr;
        value = t.getValue();
    }

    public ArithExpr(double v) {
        super(null);
        tag = Scanner.kindOfChar.ArithExpr;
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
