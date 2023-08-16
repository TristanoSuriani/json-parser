package nl.suriani.json.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

enum TokenType {
    L_BRACKET("["),
    R_BRACKET("]"),
    L_BRACE("{"),
    R_BRACE("}"),
    D_QUOTE("\""),
    COMMA(","),
    SEMICOLON(":"),
    TRUE("true"),
    FALSE("false"),
    NULL("null"),
    NUMBER(null),
    TEXT(null),
    ID(null);
    
    private String value;

    TokenType(String value) {
        this.value = value;
    }

    public static List<TokenType> constants() {
        return Arrays.stream(values())
                .filter(v -> v.value != null)
                .toList();
    }

    public static Optional<TokenType> matches(String value) {
        return constants().stream()
                .filter(type -> type.value.equals(value))
                .findFirst();
    }
}