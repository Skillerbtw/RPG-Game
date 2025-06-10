package storyline;
import model.character.Player;
import model.item.Weapon;
import model.quest.Quest;
import model.quest.reward.GoldReward;
import model.quest.reward.ItemReward;


public class StoryHandler {
    private int chapter = 0;

    public void start(Player player) {
        chapter = 1;
        System.out.println("📜 Kapitel I: Die Legende des Schatzes");
        System.out.println("Der König ruft dich zu sich...");
        // Trigger erste Quest
        player.addQuest(new Quest(
                "Finde die Schatzkarte",
                "Der alte Kapitän versteckte sie in drei Teilen.",
                new GoldReward(50)
        ));
    }

    public void progress(Player player) {
        if (chapter == 1 && player.hasCompleted("Finde die Schatzkarte")) {
            chapter = 2;
            System.out.println("🔥 Kapitel II: Der Drachenpass");
            player.addQuest(new Quest(
                    "Besiege den Drachen",
                    "Er bewacht das Tor zum vergessenen Tal.",
                    new ItemReward(new Weapon("Drachenschwert", "Legendäres Schwert", 50))
            ));
        }
        // usw.
    }
}
