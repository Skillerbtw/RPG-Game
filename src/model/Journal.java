package model;

import java.util.ArrayList;
import java.util.List;

public class Journal {

    private final List<String> entries = new ArrayList<>();

    public void addEntry(String entry) {
        entries.add(entry);
    }

    public void printJournal() {
        if (entries.isEmpty()) {
            System.out.println("📖 Dein Journal ist noch leer.");
            return;
        }

        System.out.println("\n📜 Dein Hinweis-Journal:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i));
        }
    }
}
