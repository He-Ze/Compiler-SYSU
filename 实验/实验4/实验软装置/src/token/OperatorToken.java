package token;

/**
 * The type Operator token.
 */
public class OperatorToken extends Token {
    private final String operator;

    /**
     * Instantiates a new Operator token.
     */
    public OperatorToken(String operator, int tag) {
        type = "Operator";
        this.operator = operator;
        this.tag = tag;
    }

    public String getString() {
        return operator;
    }
}
