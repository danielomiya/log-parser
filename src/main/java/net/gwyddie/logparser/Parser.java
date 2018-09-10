package net.gwyddie.logparser;

import net.gwyddie.logparser.tokens.KillToken;
import net.gwyddie.logparser.tokens.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used to parse the logs.
 */
@SuppressWarnings("WeakerAccess")
public class Parser {
    /**
     * Ignore timestamp and retain only the log type and its payload
     */
    private static final Pattern TOKEN_PATTERN = Pattern.compile("^[ 0-9]+:\\d{2} (?<type>\\w+): ?(?<payload>.*)$");

    /**
     * Ignore the IDs of killer, victim and mean of death, to obtain only their names
     */
    private static final Pattern KILL_PATTERN = Pattern.compile("^\\d+ \\d+ \\d+: (?<killer>.*) killed (?<victim>.*) by (?<obit>.*)$");

    /**
     * Constructor
     */
    public Parser() {
    }

    /**
     * Try to convert String passed into a Token,
     * which contains its type, and a payload.
     *
     * @param source line from the log file
     * @return a Token or null
     */
    public Token tryGetToken(String source) {
        Matcher matcher = TOKEN_PATTERN.matcher(source);

        return matcher.find()
                ? new Token(matcher.group("type"), matcher.group("payload"))
                : null;
    }

    /**
     * Try to convert String passed into a KillToken,
     * which contains parsed info about a killing.
     *
     * @param source payload of Token obtained by Parse::tryGetToken
     * @return a KillToken or null
     */
    public KillToken tryGetKillToken(String source) {
        Matcher matcher = KILL_PATTERN.matcher(source);

        return matcher.find()
                ? new KillToken(
                matcher.group("killer"),
                matcher.group("victim"),
                matcher.group("obit"))
                : null;
    }
}
