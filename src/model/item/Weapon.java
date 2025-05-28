package model.item;

import model.character.Player;

public class Weapon extends Item {
    private int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void use(Player player) {
        // Beispielverhalten: Waffe ausrüsten
        player.equipWeapon(this);
        System.out.println(player.getName() + " hat " + name + " ausgerüstet.");
    }
}
