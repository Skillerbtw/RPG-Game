package model.quest.reward;

import model.character.Player;
import model.item.Item;

public class ItemReward implements QuestReward {

    private final Item item;

    public ItemReward(Item item) {
        this.item = item;
    }

    @Override
    public void giveTo(Player player) {
        player.addItem(item);
        System.out.println("🎁 Belohnung: " + item.getName() + " wurde deinem Inventar hinzugefügt.");
    }
}
