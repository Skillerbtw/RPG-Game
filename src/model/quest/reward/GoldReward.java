package model.quest.reward;

import model.character.Player;

public class GoldReward implements QuestReward {

    private final int amount;

    public GoldReward(int amount) {
        this.amount = amount;
    }

    @Override
    public void giveTo(Player player) {
        player.addGold(amount);
        System.out.println("💰 Du hast " + amount + " Gold erhalten!");
    }
}
