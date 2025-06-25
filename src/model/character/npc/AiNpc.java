package model.character.npc;

import ai.StoryBot;
import model.character.Player;

import java.util.Scanner;

public class AiNpc {

    private final String name;
    private final String systemPrompt;

    public AiNpc(String name, String systemPrompt) {
        this.name = name;
        this.systemPrompt = systemPrompt;
    }

    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("💬 Sprich mit " + name + ": ");
        System.out.print("> ");
        String input = scanner.nextLine();

        String response = StoryBot.ask(input, systemPrompt);
        System.out.println(name + ": \"" + response + "\"");
    }
}