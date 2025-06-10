package storyline;

import controller.CombatSystem;
import model.character.Enemy;
import model.character.Player;
import model.item.Weapon;
import model.quest.Quest;
import model.quest.reward.GoldReward;
import model.quest.reward.ItemReward;
import model.quest.reward.MultiReward;

import java.util.List;

public class StoryHandler {

    private int chapter = 0;

    public void start(Player player) {
        chapter = 1;
        System.out.println("📜 Kapitel I: Der Ruf des Königs");
        System.out.println("Der König hat dich beauftragt, einen alten Schatz zu finden...");

        Quest quest = new Quest(
                "Finde die Schatzkarte",
                "Ein alter Kapitän hatte sie zuletzt in drei Teilen versteckt.",
                new GoldReward(50)
        );
        player.addQuest(quest);
    }

    public void progress(Player player) {
        if (chapter == 1 && player.hasCompleted("Finde die Schatzkarte")) {
            chapter = 2;
            System.out.println("🔥 Kapitel II: Der Drachenpass");
            System.out.println("Nur ein mutiger Held kann den Drachen besiegen, der den Zugang zur Höhle bewacht...");

            Quest quest = new Quest(
                    "Besiege den Drachen",
                    "Bezwinge den Drachen im Norden.",
                    new ItemReward(new Weapon("Drachenschwert", "Ein flammender Klingenbrecher", 50))
            );
            player.addQuest(quest);
        }

        if (chapter == 2 && player.hasCompleted("Besiege den Drachen")) {
            chapter = 3;
            System.out.println("💰 Kapitel III: Das vergessene Tal");
            System.out.println("Du hast nun Zugang zur Schatzhöhle... doch ein letzter Hinweis fehlt.");

            Quest quest = new Quest(
                    "Befrage den Einsiedler",
                    "Er kennt den Standort des geheimen Eingangs.",
                    new GoldReward(25)
            );
            player.addQuest(quest);
        }

        if (chapter == 3 && player.hasCompleted("Befrage den Einsiedler")) {
            chapter = 4;
            System.out.println("🏆 Finale Kapitel: Der Goldschatz");
            System.out.println("Der Weg ist frei! Betritt die Höhle und nimm den Schatz an dich...");

            Quest quest = new Quest(
                    "Finde den geheimen Goldschatz",
                    "Erfülle alle Aufgaben und gelange zur finalen Kammer.",
                    new GoldReward(250)
            );
            player.addQuest(quest);
        }

        if (chapter == 4 && player.hasCompleted("Finde den geheimen Goldschatz")) {
            System.out.println("🎉 Du hast die Hauptquest abgeschlossen und den legendären Schatz geborgen!");
            chapter = 5; // Story abgeschlossen
        }
    }

    public void autoPlayChapter(Player player) {
        switch (chapter) {
            case 1 -> {
                System.out.println("🔍 Du findest zufällig ein Stück der Schatzkarte im alten Hafen.");
                player.completeQuest("Finde die Schatzkarte");
            }
            case 2 -> {
                System.out.println("🔥 Der Drache erscheint und greift dich an!");
                Enemy drache = new Enemy("Feuerdrache", 80, 20, 50);
                CombatSystem.startCombat(player, drache);
                player.completeQuest("Besiege den Drachen");
            }
            case 3 -> {
                System.out.println("🧙 Der Einsiedler spricht mit dir und verrät den Ort.");
                player.completeQuest("Befrage den Einsiedler");
            }
            case 4 -> {
                System.out.println("💰 Du erreichst die geheime Kammer und findest den Schatz.");
                player.completeQuest("Finde den geheimen Goldschatz");
            }
        }
    }


    // ✅ Diese Methode prüfen wir im GameEngine-Loop
    public boolean isFinished() {
        return chapter >= 5;
    }
}
