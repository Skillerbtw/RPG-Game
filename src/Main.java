import model.character.Player;
import persistence.QuestStorage;
import controller.GameEngine;
import java.util.Scanner;

public class Main {

    private static Player player;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printStartMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> startNewGame();
                case "2" -> loadGame();
                case "3" -> {
                    System.out.println("👋 Spiel wird beendet...");
                    running = false;
                }
                default -> System.out.println("❌ Ungültige Auswahl. Bitte 1, 2 oder 3 eingeben.");
            }
        }
    }

    private static void printStartMenu() {
        System.out.println("""
                ╔═══════════════════════════╗
                ║    🏰 RPG Hauptmenü       ║
                ╠═══════════════════════════╣
                ║ 1. Neues Spiel starten    ║
                ║ 2. Spielstand laden       ║
                ║ 3. Spiel beenden          ║
                ╚═══════════════════════════╝
                ➤ Deine Wahl:""");
    }

    private static void startNewGame() {
        System.out.print("🎮 Name deines Helden: ");
        String name = scanner.nextLine();
        player = new Player(name);
        System.out.println("Willkommen, " + player.getName() + "!");
        GameEngine engine = new GameEngine(player);
        engine.start();

    }

    private static void loadGame() {
        System.out.print("📂 Name des Spielers laden: ");
        String name = scanner.nextLine();
        player = new Player(name); // Basisdaten initialisieren
        QuestStorage.loadQuests(player); // gespeicherte Quests laden
        System.out.println("🔄 Spielstand geladen für " + player.getName());
        ProcessBuilder GameEngine;
        GameEngine engine = new GameEngine(player);
        engine.start();

    }
}
