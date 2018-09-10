package net.gwyddie.logparser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReaderTest {
    @Test
    public void testRead() {
        final List<String> lines = new Reader("games.log").read();

        assertEquals("Reader::read should return 5306, which is the amount of lines on file.", 5306, lines.size());
    }
}
