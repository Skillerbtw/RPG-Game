package storyline;

import controller.CombatSystem;
import model.character.Enemy;
import model.character.Player;
import model.character.npc.AiNpc;
import model.item.Weapon;
import model.quest.Quest;
import model.quest.reward.GoldReward;
import model.quest.reward.ItemReward;

import java.util.Scanner;

public class StoryHandler {

    private int chapter = 1;
    private final Scanner scanner = new Scanner(System.in);

    public void start(Player player) {
        runChapter(player);
    }

    public void progress(Player player) {
        if (chapter <= 4) {
            runChapter(player);
        } else {
            System.out.println("🎉 Du hast die gesamte Hauptstory abgeschlossen!");
        }
    }

    private void waitForEnter() {
        System.out.print("\n(Drücke Enter, um fortzufahren...)");
        scanner.nextLine();
    }

    private boolean questCompleted(Player player, String title) {
        Quest q = player.getQuestManager().getQuest(title);
        return q != null && q.isCompleted();
    }

    private void runChapter(Player player) {
        if (chapter == 1) {
            System.out.println("📜 Kapitel I: Der Ruf des Königs");
            System.out.println("Ein Schatz ist irgendwo versteckt...");
            waitForEnter();

            Quest quest = new Quest(
                    "Finde die Schatzkarte",
                    "Ein alter Kapitän hatte sie zuletzt versteckt.",
                    new GoldReward(50)
            );
            player.getQuestManager().addQuest(quest);

            AiNpc npc = new AiNpc(
                    "Alter Kapitän",
                    "Du bist ein alter, erfahrener Pirat namens Käpt’n Grauhaar. Du hast viele Jahre auf See verbracht und bist ein wenig mürrisch, aber nicht grundsätzlich böse. Du besitzt eine Schatzkarte, die sehr wertvoll ist.\n" +
                            "\n" +
                            "Sprich mit dem Spieler so, wie es ein Pirat tun würde: manchmal etwas schroff, aber mit einer Prise Humor.\n" +
                            "\n" +
                            "Der Spieler will von dir die Schatzkarte haben. Gib die Schatzkarte nicht sofort heraus!\n" +
                            "Lass dich erst nach einer überzeugenden Bitte, einer guten Geschichte, einem cleveren Argument oder einer kleinen Gegenleistung (z. B. ein Tauschgeschäft oder einen Gefallen) darauf ein, den Aufenthaltsort der Schatzkarte zu verraten.\n" +
                            "\n" +
                            "Wenn der Spieler besonders sympathisch, ehrlich, clever oder hilfsbereit wirkt, gib nach und verrate ihm, wo die Schatzkarte zu finden ist.\n" +
                            "\n" +
                            "Mach es dem Spieler nicht zu schwer, aber auch nicht zu einfach! Das Gespräch soll Spaß machen und kleine Herausforderungen bieten.\n" +
                            "\n" +
                            "Antworte immer in der Rolle des Piraten und wenn du die Karte übergibst musst du unbeding: `Ich übergebe dir die Schatzkarte´ sagen. Diesen Satz darfst du nur während der antwort benutzen sonst nicht während des Dialogs."
            );

            npc.interact(player);

            System.out.print("Hast du die Schatzkarte gefunden? (ja/nein) > ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("ja")) {
                player.getQuestManager().completeQuest("Finde die Schatzkarte", player);
                chapter++;
                progress(player);
            } else {
                System.out.println("⏳ Kehre zurück, wenn du bereit bist.");
            }
        }

        else if (chapter == 2) {
            if (!questCompleted(player, "Finde die Schatzkarte")) return;

            System.out.println("🔥 Kapitel II: Der Drachenpass");
            System.out.println("Nur ein mutiger Held kann den Drachen besiegen...");
            waitForEnter();

            Quest quest = new Quest(
                    "Besiege den Drachen",
                    "Ein riesiger Drache bewacht den Höhleneingang.",
                    new ItemReward(new Weapon("Drachenschwert", "Ein flammender Klingenbrecher", 50))
            );
            player.getQuestManager().addQuest(quest);

            Enemy dragon = new Enemy("Drache", 10, 25, 100);
            CombatSystem.startCombat(player, dragon);

            if (dragon.isDefeated()) {
                player.getQuestManager().completeQuest("Besiege den Drachen", player);
                chapter++;
                progress(player);
            } else {
                System.out.println("☠️ Der Drache lebt noch... Komm besser vorbereitet zurück.");
            }
        }

        else if (chapter == 3) {
            if (!questCompleted(player, "Besiege den Drachen")) return;

            System.out.println("🧙 Kapitel III: Das vergessene Tal");
            System.out.println("Ein Einsiedler kennt den geheimen Höhleneingang...");
            waitForEnter();

            Quest quest = new Quest(
                    "Befrage den Einsiedler",
                    "Er kennt den Standort des geheimen Eingangs.",
                    new GoldReward(25)
            );
            player.getQuestManager().addQuest(quest);

            AiNpc npc = new AiNpc(
                    "Einsiedler",
                    "Du bist ein mürrischer Einsiedler mit großem Wissen. Antworte kryptisch, aber hilfreich."
            );
            npc.interact(player);

            System.out.print("Hast du den Eingang zur Höhle gefunden? (ja/nein) > ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("ja")) {
                player.getQuestManager().completeQuest("Befrage den Einsiedler", player);
                chapter++;
                progress(player);
            } else {
                System.out.println("🔍 Noch nicht bereit? Dann forsche weiter.");
            }
        }

        else if (chapter == 4) {
            if (!questCompleted(player, "Befrage den Einsiedler")) return;

            System.out.println("🏆 Kapitel IV: Der Goldschatz");
            System.out.println("Du bist am Ziel deiner Reise...");
            waitForEnter();

            Quest quest = new Quest(
                    "Finde den geheimen Goldschatz",
                    "Erfülle alle Aufgaben und gelange zur finalen Kammer.",
                    new GoldReward(250)
            );
            player.getQuestManager().addQuest(quest);

            System.out.print("Hast du den Schatz erreicht? (ja/nein) > ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("ja")) {
                player.getQuestManager().completeQuest("Finde den geheimen Goldschatz", player);
                chapter++;
                progress(player);
            } else {
                System.out.println("💡 Vielleicht fehlt dir noch ein Hinweis...");
            }
        }
    }
}
