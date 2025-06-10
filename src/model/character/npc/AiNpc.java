package model.character.npc;

import ai.StoryBot;
import model.character.Player;

import java.util.Scanner;

public class AiNpc extends BaseNpc {

    private final String systemPrompt;

    public AiNpc(String name, String systemPrompt) {
        super(name);
        this.systemPrompt = systemPrompt;
    }

    @Override
    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("💬 Du sprichst mit " + getName() + ". Sag etwas (\"tschüss\" zum Beenden):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("tschüss") || input.equalsIgnoreCase("bye")) {
                System.out.println(getName() + ": \"Leb wohl, Reisender.\"");
                break;
            }

            String response = StoryBot.ask(input, systemPrompt);
            System.out.println(getName() + ": \"" + response + "\"");
        }
    }
}
