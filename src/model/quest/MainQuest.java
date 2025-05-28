package model.quest;

import model.character.Player;
import model.quest.reward.QuestReward;

import java.util.List;

public class MainQuest extends Quest {

    private List<String> requiredQuestTitles;

    public MainQuest(String title, String description, List<String> requiredQuestTitles, QuestReward reward) {
        super(title, description, reward); // ✔️ zuerst Superklasse aufrufen
        this.requiredQuestTitles = requiredQuestTitles;
    }

    public void checkCompletion(Player player, QuestManager manager) {
        for (String req : requiredQuestTitles) {
            Quest q = manager.getQuest(req);
            if (q == null || !q.isCompleted()) return;
        }

        if (!isCompleted()) {
            complete(player); // ✔️ ruft Belohnung korrekt über Basisklasse ab
        }
    }
}

