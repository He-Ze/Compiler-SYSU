package token;

import parser.Scanner;

/**
 * The type Dollar token.
 */
public class Dollar extends Token {
    /**
     * Instantiates a new Dollar token.
     */
    public Dollar() {
        type = "$";
        tag = Scanner.kindOfChar.DOLLAR;
    }

    public String getString() {
        return type;
    }
}
