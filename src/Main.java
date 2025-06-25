import controller.GameEngine;
import model.character.Player;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Drachenbezwinger");
        GameEngine engine = new GameEngine(player);
        engine.start(); // Story beginnt direkt
    }
}
