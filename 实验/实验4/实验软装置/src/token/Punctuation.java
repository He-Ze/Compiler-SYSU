package token;

/**
 * The type Punctuation token.
 */
public class Punctuation extends Token {
    private final String punctuation;

    /**
     * Instantiates a new Punctuation token.
     */
    public Punctuation(String punctuation, int tag) {
        this.tag = tag;
        this.punctuation = punctuation;
    }

    public String getString() {
        return punctuation;
    }

}
