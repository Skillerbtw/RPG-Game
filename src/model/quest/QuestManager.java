package model.quest;

import model.character.Player;
import java.util.HashMap;
import java.util.Map;

public class QuestManager {

    private final Map<String, Quest> questMap = new HashMap<>();

    // Fix: Quest wird direkt aktiviert!
    public void addQuest(Quest quest) {
        questMap.put(quest.getTitle(), quest);
        quest.start(); // <-- AUTOMATISCHER START!
        System.out.println("📜 Neue Quest erhalten: " + quest.getTitle());
    }

    public Quest getQuest(String title) {
        return questMap.get(title);
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

    public Quest[] getAllQuests() {
        return questMap.values().toArray(new Quest[0]);
    }
}
