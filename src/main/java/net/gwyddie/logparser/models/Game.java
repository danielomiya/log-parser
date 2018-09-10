package net.gwyddie.logparser.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Representation of a Game
 */
public class Game {
    /**
     * Counter of total kills.
     */
    private int totalKills;

    /**
     * Counter of how many kills a player
     * has performed in this game.
     */
    private final Map<String, Integer> killsByPlayer;

    /**
     * Counter of kills by means.
     */
    private final Map<MeansOfDeath, Integer> killsByMeans;

    /**
     * Constant entity which is not considered
     * as a player, and thus receives a special
     * treatment.
     */
    @SuppressWarnings("WeakerAccess")
    public static final String WORLD = "<world>";

    public Game() {
        killsByPlayer = new HashMap<>();
        killsByMeans = new HashMap<>();
    }

    /**
     * Gets the total of kills of the game.
     *
     * @return total of kills
     */
    public int getTotalKills() {
        return totalKills;
    }

    /**
     * Gets a list of players in the game.
     *
     * @return list of players
     */
    public List<String> getPlayers() {
        return killsByPlayer.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .map(Entry::getKey) // get only key (name)
                .collect(toList()); // collect as List<>
    }

    /**
     * Gets a list of mean of death and how many occurrences they had
     *
     * @return list of kills by means of death
     */
    public Map<MeansOfDeath, Integer> getKillsByMeans() {
        return killsByMeans.entrySet().stream()
                .sorted(Entry.<MeansOfDeath, Integer>comparingByKey().reversed())
                .collect(toMap(Entry::getKey, Entry::getValue, (k, v) -> k));
    }

    /**
     * Gets a list of kills by each player
     *
     * @return list of kills by player
     */
    public Map<String, Integer> getKillsByPlayer() {
        return killsByPlayer
                .entrySet().stream()
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

    /**
     * Adds a player to the game.
     *
     * @param name the name of the player
     */
    @SuppressWarnings("WeakerAccess")
    public void addPlayer(String name) {
        killsByPlayer.putIfAbsent(name, 0);
    }

    /**
     * It adds a kill to the game,
     * and manages the scores.
     *
     * @param kill the performed kill
     */
    public void addKill(Kill kill) {
        addPlayer(kill.getVictim()); // grants that the victim and the mean
        killsByMeans.putIfAbsent(kill.getMeanOfDeath(), 0); // of death are already known

        totalKills++; // adds kill to total, no matter who killed or died

        // add mean of death to counter
        killsByMeans.computeIfPresent(kill.getMeanOfDeath(), (k, v) -> v + 1);

        if (kill.getKiller().equals(WORLD)) {
            // As explained, if the player is killed by <world>,
            // the kill should not be evaluated as a player, and
            // the victim loses a kill, but it is added to the
            // total kills counter
            killsByPlayer.computeIfPresent(kill.getVictim(), (k, v) -> v - 1);
            return;
        }

        // start the killer with 0 kills if does not exist,
        // and then gives him/her the kill score
        addPlayer(kill.getKiller());
        killsByPlayer.computeIfPresent(kill.getKiller(), (k, v) -> v + 1);
    }

    @Override
    public String toString() {
        return "Game{" +
                "totalKills=" + totalKills +
                ", killsByPlayer=" + killsByPlayer +
                ", killsByMeans=" + killsByMeans +
                '}';
    }
}
