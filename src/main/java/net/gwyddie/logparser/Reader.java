package net.gwyddie.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to get contents from a file.
 */
@SuppressWarnings("WeakerAccess")
public class Reader {
    /**
     * The file we're reading.
     */
    private final File file;

    /**
     * Constructor
     *
     * @param path the path to the file
     */
    public Reader(String path) {
        this.file = new File(path);
    }

    /**
     * Checks if the given path represents a file.
     *
     * @return whether it's a file or not
     */
    public boolean isFile() {
        return file.exists() && file.isFile();
    }

    /**
     * Checks if the given file is accessible by the app.
     *
     * @return whether the app can read the file or not
     */
    public boolean isReachable() {
        return isFile() && file.canRead();
    }

    /**
     * Return a List<String> with the lines of the file.
     *
     * @return the contents of file
     */
    public List<String> read() {
        List<String> lines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
