package net.gwyddie.logparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private File file;

    public Reader(String path) {
        this.file = new File(path);
    }

    public boolean isFile() {
        return file.exists() && file.isFile();
    }

    public boolean isReachable() {
        return isFile() && file.canRead();
    }

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
