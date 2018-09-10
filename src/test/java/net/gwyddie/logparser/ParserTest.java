package net.gwyddie.logparser;

import net.gwyddie.logparser.tokens.KillToken;
import net.gwyddie.logparser.tokens.Token;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    private final List<String> lines;

    public ParserTest() {
        lines = new Reader("games.log").read();
    }

    @Test
    public void testTryGetTokenKill() {
        final Parser parser = new Parser();
        final List<KillToken> killTokens = lines.stream()
                .map(l -> {
                    final Token token = parser.tryGetToken(l); // sanitize
                    if (token == null) return null; // in case it's null, we don't have a token

                    return token.getType().equals("Kill") // if it's a Kill, parse it
                            ? parser.tryGetKillToken(token.getValue())
                            : null;
                })
                .filter(Objects::nonNull) // now remove null items
                .collect(toList()); // and turn it into a List<>

        // games.log has 1069 kills
        assertEquals("List<KillToken>::size should be equal 1069", 1069, killTokens.size());
    }

    @Test
    public void testTryAddToken() {
        final Parser parser = new Parser();
        final List<Token> tokens = lines.stream()
                .map(parser::tryGetToken)
                .filter(Objects::nonNull)
                .collect(toList());

        // File has 5224 lines/tokens, when
        // break lines used, such as
        // ------------------------------------------------------------
        // are not counted
        assertEquals("List<Token>::size must be equal 5265", 5265, tokens.size());
    }
}
