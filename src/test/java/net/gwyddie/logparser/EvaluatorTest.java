package net.gwyddie.logparser;

import net.gwyddie.logparser.models.Game;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static java.util.Map.Entry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EvaluatorTest {
    @Test
    public void testEvaluate() {
        final Evaluator evaluator = new Evaluator(new Parser(), Arrays.asList(
                " 11:11 InitGame: payload does not matter",
                " 22:22 Kill: 1 2 3: <world> killed himself by MOD_FALLING"
        ));

        evaluator.evaluate();
        final List<Game> games = evaluator.getGames();

        assertEquals("List<Game>::size must be equal to 1", 1, games.size());

        final Game game = games.get(0);

        assertEquals("Game::getPlayers::size must return 1", 1, game.getPlayers().size());
        assertEquals("Game::getTotalKills must return 1", 1, game.getTotalKills());
        assertEquals("Game::getKillsByPlayer must return 0", 1, game.getKillsByPlayer().size());

        final Entry<String, Integer> himself = game.getKillsByPlayer().entrySet().stream()
                .filter(p -> p.getKey().equals("himself")).findFirst().orElse(null);

        assertNotNull(himself);
        assertEquals("Entry<String, Integer>::getValue must return -1, 'cause killed by <world>", -1, (int) himself.getValue());

        // I think for now it's enough...
    }
}
