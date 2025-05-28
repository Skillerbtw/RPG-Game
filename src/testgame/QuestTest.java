package testgame;

import model.quest.Quest;
import model.character.Player;
import model.quest.reward.GoldReward;

public class QuestTest {
    public static void main(String[] args) {
        Player p = new Player("Tester");
        Quest quest = new Quest("Testquest", "Erledige diesen Test", new GoldReward(50));
        p.addQuest(quest);
        p.completeQuest("Testquest");
    }
}
