package model.character.npc;

import model.character.Player;
import model.item.Item;

public class NPC {

    private String name;
    private String dialogue;
    private Item itemToGive; // Optional: kann null sein
    private boolean itemGiven = false;

    public NPC(String name, String dialogue, Item itemToGive) {
        this.name = name;
        this.dialogue = dialogue;
        this.itemToGive = itemToGive;
    }

    public String getName() {
        return name;
    }

    public void interact(Player player) {
        System.out.println("\n🧙 " + name + ": \"" + dialogue + "\"");

        if (itemToGive != null && !itemGiven) {
            System.out.println("🎁 " + name + " überreicht dir: " + itemToGive.getName());
            player.addItem(itemToGive);
            itemGiven = true;
        } else if (itemGiven) {
            System.out.println("📦 " + name + " hat dir bereits ein Item gegeben.");
        }
    }
}
