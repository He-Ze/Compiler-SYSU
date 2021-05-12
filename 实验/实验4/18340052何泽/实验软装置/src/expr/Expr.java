package expr;

import parser.Scanner;
import token.Token;

/**
 * 接口
 */
public abstract class Expr {

    public Token token;
    public int tag;

    /**
     * 构造函数
     *
     * @param t 一个token
     */
    public Expr(Token t) {
        token = t;
        tag = Scanner.kindOfChar.Expr;
    }

    /**
     * 构造函数
     */
    public Expr() {
        token = null;
        tag = Scanner.kindOfChar.NULL;
    }

    /**
     * 获取tag.
     *
     * @return the tag
     */
    public int getTag() {
        return tag;
    }

    /**
     * 判断是否是终结符号
     *
     * @return 是否是终结符号
     */
    public abstract boolean isTerminal();
}
