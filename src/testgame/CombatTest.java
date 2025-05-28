package testgame;

import controller.CombatSystem;
import model.character.Player;
import model.character.Enemy;
import model.item.Weapon;

public class CombatTest {
    public static void main(String[] args) {
        Player player = new Player("Testheld");
        Weapon sword = new Weapon("Testschwert", "Ein scharfes Übungsschwert", 12);
        player.addItem(sword);
        player.equipWeapon(sword);

        Enemy testEnemy = new Enemy("Trainings-Goblin", 35, 6, 15);

        CombatSystem.startCombat(player, testEnemy);
    }
}
