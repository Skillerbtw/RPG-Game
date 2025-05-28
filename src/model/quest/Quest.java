package model.quest;

import model.character.Player;
import model.quest.reward.QuestReward;

public class Quest {

    protected String title;
    protected String description;
    protected QuestState state;
    protected QuestReward reward;


    public Quest(String title, String description, QuestReward reward) {
        this.title = title;
        this.description = description;
        this.state = QuestState.NOT_STARTED;
        this.reward = reward;
    }

    public void start() {
        if (state == QuestState.NOT_STARTED) {
            state = QuestState.ACTIVE;
            System.out.println("📜 Quest gestartet: " + title);
        }
    }

    public void complete(Player player) {
        if (state == QuestState.ACTIVE) {
            state = QuestState.COMPLETED;
            System.out.println("✅ Quest abgeschlossen: " + title);

            if (reward != null) {
                reward.giveTo(player);
            }
        }
    }

    public QuestState getState() {
        return state;
    }

    public boolean isCompleted() {
        return state == QuestState.COMPLETED;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String mark = switch (state) {
            case NOT_STARTED -> "[ ]";
            case ACTIVE -> "[~]";
            case COMPLETED -> "[✔]";
        };
        return mark + " " + title + ": " + description;
    }
}









