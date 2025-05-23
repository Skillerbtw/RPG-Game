package controller;

import model.*;
import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner = new Scanner(System.in);
    private final Player player;

    public GameEngine(Player player) {
        this.player = player;
    }

    public void start() {
        System.out.println("🎮 Willkommen im textbasierten RPG, " + player.getName() + "!");
        System.out.println("Deine Reise beginnt...");

        while (true) {
            System.out.println("\n=== 📍 WAS MÖCHTEST DU TUN? ===");
            System.out.println("1. Mit einem NPC sprechen");
            System.out.println("2. In den Kampf ziehen");
            System.out.println("3. Inventar anzeigen");
            System.out.println("4. Journal lesen");
            System.out.println("5. Spiel beenden");
            System.out.println("6. Quests anzeigen");



            System.out.print("> Auswahl: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> talkToNpc();
                case "2" -> startCombat();
                case "3" -> showInventory();
                case "4" -> player.getJournal().printJournal();
                case "5" -> {
                    System.out.println("👋 Auf Wiedersehen, " + player.getName() + "!");
                    return;
                }
                case "6" -> player.showQuests();
                default -> System.out.println("Ungültige Auswahl.");
            }
        }
    }

    private void talkToNpc() {
        System.out.println("Du triffst den Mystischen Wanderer...");
        System.out.println("Er bietet dir eine Aufgabe an:");
        Quest quest = new Quest("Finde den roten Baum", "Suche in den nördlichen Wäldern nach dem legendären Baum.");
        player.addQuest(quest);

        AiNpc npc = new AiNpc("Mystischer Wanderer");
        npc.interact(player);

        // Beispiel: Quest automatisch abschließen nach Gespräch
        player.completeQuest("Finde den roten Baum");
    }



    private void startCombat() {
        Enemy goblin = new Enemy("Goblin", 40, 10, 25);
        controller.CombatSystem.startCombat(player, goblin);
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
