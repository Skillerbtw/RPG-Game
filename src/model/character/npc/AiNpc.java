package model.character.npc;

import ai.StoryBot;
import model.character.Player;

import java.util.Scanner;

public class AiNpc extends NPC {

    public AiNpc(String name) {
        super(name, null, null); // Kein fixer Dialog, KI übernimmt
    }

    @Override
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n🤖 " + getName() + " ist bereit, mit dir zu sprechen.");
        System.out.println("(Tipp: 'exit' zum Beenden, 'journal' zum Anzeigen deines Journals)");

        while (true) {
            System.out.print("🗨️  Du: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("👋 " + getName() + ": Möge dein Weg sicher sein, Reisender.");
                break;
            }

            if (input.equalsIgnoreCase("journal")) {
                player.getJournal().printJournal();
                continue;
            }

            String response = StoryBot.ask(input);
            System.out.println("🧙 " + getName() + ": \"" + response + "\"");

            // Eintrag ins Journal
            player.getJournal().addEntry(getName() + ": " + response);
        }
    }

}

