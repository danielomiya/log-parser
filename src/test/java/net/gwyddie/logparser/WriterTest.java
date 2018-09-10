package net.gwyddie.logparser;

import net.gwyddie.logparser.models.MeansOfDeath;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class WriterTest {
    @Test
    public void testWrite() throws IOException {
        final Writer writer = new Writer("test.txt", 1);

        final Map<MeansOfDeath, Integer> killByMeans = new HashMap<>();
        killByMeans.put(MeansOfDeath.MOD_GRENADE, 23); // just to get a random String

        final String content = writer.format(killByMeans);

        writer.write(content);

        final byte[] file = Files.readAllBytes(Paths.get("reports/test.txt"));

        assertArrayEquals("File written and String bytes must be equal", content.getBytes(), file);
    }
}
