package net.gwyddie.logparser;

import net.gwyddie.logparser.models.Game;
import net.gwyddie.logparser.models.MeansOfDeath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static java.util.Map.Entry;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toList;

/**
 * Class used to write stuff to a file.
 */
@SuppressWarnings("WeakerAccess")
public class Writer {
    /**
     * File instance of the file we're writting
     */
    private final File file;

    /**
     * Id of the game being saved.
     */
    private final int gameId;

    /**
     * Instance of the Writer
     */
    private BufferedWriter writer;

    /**
     * Constructor
     *
     * @param path   path to the file
     * @param gameId id of the game being written
     */
    public Writer(String path, int gameId) {
        File directory = new File("reports");
        if (!directory.exists()) directory.mkdir();

        this.file = new File("reports/" + path);
        this.gameId = gameId;
    }

    /**
     * Gets an instance of BufferedWriter
     *
     * @return an instance of BufferedWriter
     * @throws IOException can be thrown by the BufferedWriter constructor itself
     */
    private BufferedWriter getWriter() throws IOException {
        if (writer == null)
            writer = new BufferedWriter(new FileWriter(file));

        return writer;
    }

    /**
     * Formats a game as a String.
     *
     * @param game the Game to be stringified
     * @return the formatted Game
     */
    public String format(Game game) {
        return String.format(
                "\"game_%d\": {\n" +
                        "\t\"total_kills\": %d,\n" +
                        "\t\"players\": [\n" +
                        "\t\t\"%s\"\n" +
                        "\t], \n" +
                        "\t\"kills\": {\n" +
                        "\t\t%s\n" +
                        "\t}\n" +
                        "}\n",
                gameId,
                game.getTotalKills(),
                String.join("\",\n\t\t\"", game.getPlayers()), // surround player names with " and indent them
                String.join(", \n\t\t", game.getKillsByPlayer().entrySet().stream() // format keys and values
                        .sorted(comparingByKey()) // as JSON key-value paired alike and list them ordered
                        .map(k -> "\"" + k.getKey() + "\": " + k.getValue()).collect(toList()))); // alphabetically
    }

    /**
     * Format the kills by player as a String.
     *
     * @param killsByPlayer the Map to stringify
     * @return formatted String
     */
    public String format(Map<MeansOfDeath, Integer> killsByPlayer) {
        return String.format(
                "\"game_%d\": {\n" +
                        "\t\"kill_by_means\": {\n" +
                        "\t\t%s\n" +
                        "\t}\n" +
                        "}\n",
                gameId,
                String.join(", \n\t\t", killsByPlayer.entrySet().stream() // get key and values formatted as
                        .sorted(Entry.<MeansOfDeath, Integer>comparingByValue().reversed()) // JSON alike and list them
                        .map(k -> "\"" + k.getKey() + "\": " + k.getValue()).collect(toList())) // ordered by their values
        );
    }

    /**
     * Writes content to a file.
     *
     * @param content a String
     */
    public void write(String content) {
        try (BufferedWriter writer = getWriter()) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats and then writes the game to a file.
     *
     * @param game instance of evaluated game
     */
    public void formatAndWrite(Game game) {
        write(format(game));
    }

    /**
     * Formats and then writes the kills by means to a file.
     *
     * @param killByMeans instance of Map<MeansOfDeath, Integer> already evaludated by Evaluator
     */
    public void formatAndWrite(Map<MeansOfDeath, Integer> killByMeans) {
        write(format(killByMeans));
    }
}
