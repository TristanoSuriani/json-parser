package nl.suriani.json.parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public List<Token> lex(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }

        var tokens = new ArrayList<Token>();
        var currentWord = "";
        var lineNumber = 1;
        var mode = Mode.NORMAL;

        for (char character: text.toCharArray()) {
            if (mode == Mode.TEXT && !isDoubleQuote(character)) {
                currentWord += character;
                continue;
            }

            if (isEndOfLine(character)) {
                pushToken(tokens, lineNumber, currentWord, mode);
                currentWord = "";
                lineNumber += 1;
                continue;
            }

            if (characterMatchesAConstant(character)) {
                pushToken(tokens, lineNumber, currentWord, mode);
                currentWord = "";

                pushToken(tokens, lineNumber, "" + character, mode);

                if (isDoubleQuote(character)) {
                    mode = mode.toggle();
                }
                continue;
            }


            if (isSpace(character)) {
                pushToken(tokens, lineNumber, currentWord, mode);
                currentWord = "";
                continue;
            }

            currentWord += character;
        }

        pushToken(tokens, lineNumber, currentWord, mode);

        return tokens;
    }

    private static boolean characterMatchesAConstant(char character) {
        return !resolveTokenType("" + character).equals(TokenType.ID);
    }

    private boolean isEndOfLine(char character) {
        return character == '\n';
    }

    private boolean isDoubleQuote(char character) {
        return character == '"';
    }

    private boolean isSpace(char character) {
        return List.of(' ', '\t', '\r')
                .contains(character);
    }

    private static void pushToken(ArrayList<Token> tokens, int lineNumber, String currentWord, Mode mode) {
        if (currentWord.isEmpty()) {
            return;
        }

        if (mode == Mode.TEXT) {
            tokens.add(new Token(lineNumber, currentWord, TokenType.TEXT));
        }

        var tokenType = resolveTokenType(currentWord);
        tokens.add(new Token(lineNumber, currentWord, tokenType));

    }

    private static TokenType resolveTokenType(String currentWord) {
        var maybeConstant = TokenType.matches(currentWord);
        if (maybeConstant.isPresent()) {
            return maybeConstant.get();
        }

        return TokenType.ID;
    }

    private enum Mode {
        NORMAL, TEXT;

        public Mode toggle() {
            return switch (this) {
                case NORMAL -> TEXT;
                case TEXT -> NORMAL;
            };
        }
    }

}
