package controller;

import model.character.Player;
import model.character.Enemy;
import model.character.npc.AiNpc;
import model.character.npc.QuestGiverNpc;
import model.item.Item;
import model.item.Weapon;
import model.quest.Quest;
import model.quest.reward.GoldReward;
import model.quest.reward.ItemReward;
import model.quest.reward.MultiReward;
import storyline.StoryHandler;

import java.util.List;
import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner = new Scanner(System.in);
    private final Player player;
    private final StoryHandler storyHandler = new StoryHandler();

    public GameEngine(Player player) {
        this.player = player;
    }

    public void start() {
        System.out.println("🎮 Willkommen im textbasierten RPG, " + player.getName() + "!");
        System.out.println("Deine Reise beginnt...");

        // Start der Story
        storyHandler.start(player);

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
                default -> System.out.println("❌ Ungültige Auswahl.");
            }
        }
    }

    private void talkToNpc() {
        System.out.println("🧙 Du triffst den Mystischen Wanderer...");

        Quest quest = new Quest(
                "Finde den roten Baum",
                "Gehe in den Wald.",
                new MultiReward(List.of(
                        new GoldReward(50),
                        new ItemReward(new Weapon("Waldbogen", "Ein einfacher, aber robuster Bogen", 15))
                ))
        );

        player.addQuest(quest);
        AiNpc npc1 = new AiNpc("Mystischer Wanderer");
        npc1.interact(player);
        player.completeQuest("Finde den roten Baum");

        System.out.println("\n🧓 Ein weiterer Einsiedler taucht auf...");
        QuestGiverNpc npc2 = new QuestGiverNpc("Einsiedler", "Befrage den Einsiedler");
        npc2.interact(player);

        // Kapitel-Fortschritt prüfen
        storyHandler.progress(player);
    }

    private void startCombat() {
        Enemy goblin = new Enemy("Goblin", 40, 10, 25);
        CombatSystem.startCombat(player, goblin);
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
