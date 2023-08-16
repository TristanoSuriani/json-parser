package nl.suriani.json.parser;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    private final Lexer lexer = new Lexer();

    @Test
    void nullText() {
        assertThrows(IllegalArgumentException.class,
                () -> lexer.lex(null));
    }

    @Test
    void emptyText() {
        var text = "";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(List.of(), tokens);
    }

    @Test
    void oneId() {
        var text = "pizza";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(List.of(new Token(1, "pizza", TokenType.ID)), tokens);
    }

    @Test
    void oneIdOneLineAfter() {
        var text = """
                
                pizza""";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(List.of(new Token(2, "pizza", TokenType.ID)), tokens);
    }

    @Test
    void twoIds_differentLines() {
        var text = """
                pizza
                ananas""";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(
                List.of(
                    new Token(1, "pizza", TokenType.ID),
                    new Token(2, "ananas", TokenType.ID)
                ),
                tokens);
    }

    @Test
    void emptyObject() {
        var text = """
                {}""";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(
                List.of(
                        new Token(1, "{", TokenType.L_BRACE),
                        new Token(1, "}", TokenType.R_BRACE)
                ),
                tokens);
    }

    @Test
    void emptyArray() {
        var text = """
                []""";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(
                List.of(
                        new Token(1, "[", TokenType.L_BRACKET),
                        new Token(1, "]", TokenType.R_BRACKET)
                ),
                tokens);
    }

    @Test
    void objectWithANumberProperty() {
        var text = """
                {
                    "key": 1
                }""";

        var tokens = lexer.lex(text);

        thenTheTokensStreamLooksLikeThis(
                List.of(
                        new Token(1, "{", TokenType.L_BRACE),
                        new Token(2, "\"", TokenType.D_QUOTE),
                        new Token(2, "key", TokenType.ID),
                        new Token(2, "\"", TokenType.D_QUOTE),
                        new Token(2, ":", TokenType.SEMICOLON),
                        new Token(2, "1", TokenType.NUMBER),
                        new Token(3, "]", TokenType.R_BRACKET)
                ),
                tokens);
    }

    private void thenTheTokensStreamLooksLikeThis(List<Token> expectedTokens, List<Token> actualTokens) {
        assertEquals(expectedTokens.size(), actualTokens.size());
        Stream.iterate(0, i -> i < actualTokens.size(), i -> i + 1)
                .forEach(i -> assertEquals(expectedTokens.get(i), actualTokens.get(i)));
    }


}