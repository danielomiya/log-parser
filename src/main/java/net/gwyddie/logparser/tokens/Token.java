package net.gwyddie.logparser.tokens;

/**
 * A simple Token
 */
public class Token {
    /**
     * Type of the Token, such as InitGame, ShutdownGame and so on.
     */
    private final String type;

    /**
     * The payload/content of the Token.
     */
    private final String value;

    /**
     * Constructor
     *
     * @param type  type of Token
     * @param value payload of Token
     */
    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the type of Token.
     *
     * @return a String of the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the value of the Token.
     *
     * @return a String of the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Override of Object::toString
     *
     * @return a stringified version of the instance
     */
    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
