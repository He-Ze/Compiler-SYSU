package token;

/**
 * The type Operator token.
 */
public class Operator extends Token {
    private final String operator;

    /**
     * Instantiates a new Operator token.
     */
    public Operator(String operator, int tag) {
        type = "Operator";
        this.operator = operator;
        this.tag = tag;
    }

    public String getString() {
        return operator;
    }
}
