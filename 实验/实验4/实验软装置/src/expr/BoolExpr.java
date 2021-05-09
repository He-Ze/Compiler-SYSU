package expr;

import parser.Scanner;
import token.BooleanToken;

/**
 * The type Bool expr.
 */
public class BoolExpr extends Expr {
    private final boolean value;

    /**
     * 构造函数
     *
     * @param t BooleanToken
     */
    public BoolExpr(BooleanToken t) {
        super(t);
        tag = Scanner.kingOfChar.BoolExpr;
        value = t.getValue();
    }

    /**
     * 构造函数
     *
     * @param v 布尔值
     */
    public BoolExpr(boolean v) {
        super(null);
        tag = Scanner.kingOfChar.BoolExpr;
        value = v;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public boolean getValue() {
        return value;
    }

    public boolean isTerminal() {
        return false;
    }
}
