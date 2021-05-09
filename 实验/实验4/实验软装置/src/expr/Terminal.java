package expr;

import token.Token;

/**
 * The type Terminal.
 */
public class Terminal extends Expr {

    /**
     * 构造函数
     *
     * @param t token
     */
    public Terminal(Token t) {
        super(t);
        tag = t.getTag();
    }

    public boolean isTerminal() {
        return true;
    }

}
