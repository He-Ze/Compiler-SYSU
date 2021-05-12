package token;

import parser.Scanner;

/**
 * The type Boolean token.
 */
public class Boolean extends Token {
    /**
     * 布尔值
     */
    private final boolean value;

    /**
     * 构造函数
     *
     * @param str 字符串
     */
    public Boolean(String str) {
        type = "bool";
        tag = Scanner.kindOfChar.BOOL;
        value = str.equalsIgnoreCase("true");
    }

    public String getString() {
        return value ? "true" : "false";
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public boolean getValue() {
        return value;
    }
}
