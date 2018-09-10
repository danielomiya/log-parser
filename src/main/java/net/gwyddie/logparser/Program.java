package net.gwyddie.logparser;

import net.gwyddie.logparser.models.Game;

import java.util.List;

/**
 * Main class.
 */
public class Program {
    /**
     * Entry point for the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        final Reader reader = new Reader("games.log");

        if (!reader.isReachable()) { // silly verification
            System.err.println("Log file either does not exist or is not reachable by the app.");
            System.exit(1);
        }

        final List<String> lines = reader.read();
        final Parser parser = new Parser();
        final Evaluator evaluator = new Evaluator(parser, lines);
        evaluator.evaluate();

        final List<Game> games = evaluator.getGames();

        Writer writer;

        for (int i = 0; i < games.size(); i++) {
            final int j = i + 1;

            writer = new Writer("game-" + j + "-report.txt", j);
            writer.formatAndWrite(games.get(i));

            writer = new Writer("deaths-" + j + "-report.txt", j);
            writer.formatAndWrite(games.get(i).getKillsByMeans());
        }
    }
}
