package controller;

import model.character.Player;
import model.item.Item;
import storyline.StoryHandler;

import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner = new Scanner(System.in);
    private final Player player;
    private final StoryHandler storyHandler = new StoryHandler();

    public GameEngine(Player player) {
        this.player = player;
    }

    public void start() {
        System.out.println("🎮 Willkommen, " + player.getName() + "! Deine Reise beginnt jetzt...");
        storyHandler.start(player);
        while (true) {
            System.out.println("\n=== Menü ===");
            System.out.println("1. 📘 Story fortsetzen");
            System.out.println("2. 🎒 Inventar");
            System.out.println("3. 📜 Journal");
            System.out.println("4. ❌ Spiel beenden");
            System.out.print("> ");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> storyHandler.progress(player);
                case "2" -> showInventory();
                case "3" -> player.getJournal().printJournal();
                case "4" -> {
                    System.out.println("👋 Auf Wiedersehen!");
                    return;
                }
                default -> System.out.println("❌ Ungültige Eingabe.");
            }
        }
    }

    private void showInventory() {
        System.out.println("\n🎒 INVENTAR:");
        if (player.getInventory().isEmpty()) {
            System.out.println("Du hast keine Items.");
        } else {
            for (Item item : player.getInventory()) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}
