package token;

/**
 * The type Punctuation token.
 */
public class PunctuationToken extends Token {
    private final String punctuation;

    /**
     * Instantiates a new Punctuation token.
     */
    public PunctuationToken(String punctuation, int tag) {
        this.tag = tag;
        this.punctuation = punctuation;
    }

    public String getString() {
        return punctuation;
    }

}
