package model.quest;

import model.character.Player;

import java.util.HashMap;
import java.util.Map;

public class QuestManager {

    private Map<String, Quest> questMap = new HashMap<>();

    public void addQuest(Quest quest) {
        questMap.put(quest.getTitle(), quest);
    }

    public Quest getQuest(String title) {
        return questMap.get(title);
    }

    public void startQuest(String title) {
        Quest q = questMap.get(title);
        if (q != null && q.getState() == QuestState.NOT_STARTED) {
            q.start();
        }
    }

    public void completeQuest(String title, Player player) {
        Quest q = questMap.get(title);
        if (q != null && q.getState() == QuestState.ACTIVE) {
            q.complete(player);
        }
    }

    public void printAllQuests() {
        if (questMap.isEmpty()) {
            System.out.println("📭 Keine Quests verfügbar.");
            return;
        }

        System.out.println("📋 Deine Quests:");
        for (Quest q : questMap.values()) {
            System.out.println(" - " + q);
        }
    }

    public void checkMainQuests(Player player) {
        for (Quest q : questMap.values()) {
            if (q instanceof MainQuest mq) {
                mq.checkCompletion(player, this);
            }
        }
    }

    public Quest[] getAllQuests() {
        return new Quest[0];
    }
}
