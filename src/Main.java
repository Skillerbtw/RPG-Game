import controller.GameEngine;
import model.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Arin");

        // Startausrüstung
        player.addItem(new Weapon("Eisenschwert", "Ein solides Schwert", 15));
        player.addItem(new HealthPotion("Heiltrank", "Heilt 25 HP", 25));
        player.equipWeapon((Weapon) player.getInventory().get(0));

        GameEngine game = new GameEngine(player);
        game.start();

    }
}
