package model.character.npc;

import model.character.Player;
import model.quest.Quest;
import model.quest.QuestState;

public class QuestGiverNpc extends AiNpc {

    private final String questTitle;

    public QuestGiverNpc(String name, String questTitle) {
        super(name, "Du bist ein NPC, der Quests vergibt. Sei klar und direkt in deinen Anweisungen.");
        this.questTitle = questTitle;
    }

    @Override
    public void interact(Player player) {
        Quest quest = player.getQuestManager().getQuest(questTitle);

        if (quest == null) {
            System.out.println("⚠️ Quest '" + questTitle + "' existiert nicht.");
            return;
        }

        if (quest.getState() == QuestState.NOT_STARTED) {
            System.out.println("🧙 " + getName() + ": Ich habe eine Aufgabe für dich...");
            System.out.println("📜 Quest erhalten: " + quest.getTitle());
            System.out.println("   ➤ " + quest.getDescription());
            quest.start();
        } else if (quest.getState() == QuestState.ACTIVE) {
            System.out.println("🧙 " + getName() + ": Du hast meine Aufgabe noch nicht erfüllt.");
        } else if (quest.getState() == QuestState.COMPLETED) {
            System.out.println("🧙 " + getName() + ": Danke. Du hast deine Aufgabe erfüllt.");
        }

        super.interact(player); // Danach optional KI-Dialog
    }
}
