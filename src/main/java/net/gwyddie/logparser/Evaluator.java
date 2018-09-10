package net.gwyddie.logparser;

import net.gwyddie.logparser.models.Game;
import net.gwyddie.logparser.models.Kill;
import net.gwyddie.logparser.tokens.KillToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class used to evaluate tokens.
 */
@SuppressWarnings("WeakerAccess")
public class Evaluator {
    /**
     * An instance of our parser.
     */
    private final Parser parser;

    /**
     * List of lines from the log.
     */
    private final List<String> lines;

    /**
     * List of evaluated games.
     */
    private final List<Game> games;

    /**
     * Just a reference to the current
     * game being evaluated (on Evaluator::evaluate)
     * or null if the method is not called.
     */
    private Game game;

    /**
     * Constructor
     * @param parser an instance of the parser
     * @param lines list of lines from the file log
     */
    public Evaluator(Parser parser, List<String> lines) {
        this.parser = parser;
        this.lines = lines;
        this.games = new ArrayList<>();
    }

    /**
     * Gets a list of games
     * @return list of games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * Simply does as the name says.
     * @return list of evaluated games
     */
    @SuppressWarnings("UnusedReturnValue")
    public List<Game> evaluate() {
        lines.stream()
                .map(parser::tryGetToken)
                .filter(Objects::nonNull)
                .forEach(t -> {
                    switch (t.getType()) {
                        case "InitGame":
                            game = new Game();
                            games.add(game);
                            break;
                        case "Kill":
                            final KillToken killToken = parser.tryGetKillToken(t.getValue());
                            if (game == null)
                                throw new NullPointerException("Game's not been initialized");

                            game.addKill(new Kill(
                                    killToken.getKiller(), killToken.getVictim(), killToken.getMeanOfDeath()));
                            break;
                        default:
                            // Unknown or irrelevant token
                            break;
                    }
                });
        return games;
    }
}
