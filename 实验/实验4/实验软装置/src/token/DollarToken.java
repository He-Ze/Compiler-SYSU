package token;

import parser.Scanner;

/**
 * The type Dollar token.
 */
public class DollarToken extends Token {
    /**
     * Instantiates a new Dollar token.
     */
    public DollarToken() {
        type = "$";
        tag = Scanner.kingOfChar.DOLLAR;
    }

    public String getString() {
        return type;
    }
}
