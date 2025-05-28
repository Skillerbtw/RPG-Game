package model.quest.reward;

import model.character.Player;

import java.util.List;

public class MultiReward implements QuestReward {

    private List<QuestReward> rewards;

    public MultiReward(List<QuestReward> rewards) {
        this.rewards = rewards;
    }

    @Override
    public void giveTo(Player player) {
        for (QuestReward r : rewards) {
            r.giveTo(player);
        }
    }
}
