package testgame;

import model.character.Player;
import model.item.Item;
import model.item.Weapon;

public class InventoryTest {
    public static void main(String[] args) {
        Player p = new Player("Inventarheld");
        Weapon w = new Weapon("Testbogen", "Leichter Bogen", 9);
        p.addItem(w);
        p.equipWeapon(w);
        System.out.println(p.getStats());
    }
}
