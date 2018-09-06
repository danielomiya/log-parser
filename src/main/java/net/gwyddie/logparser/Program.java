package net.gwyddie.logparser;

import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {
        List<String> lines = new Reader("games.log").read();

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }
}
