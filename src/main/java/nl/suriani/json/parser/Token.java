package nl.suriani.json.parser;

import java.util.Objects;

public record Token(int lineNumber, String value, TokenType type) {

    public Token {
        if (lineNumber < 1) {
            throw new IllegalArgumentException("Linenumber should be positive");
        }
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);
    }
}
