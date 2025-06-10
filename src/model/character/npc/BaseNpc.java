package model.character.npc;

import model.character.Player;

public abstract class BaseNpc {
    private final String name;

    public BaseNpc(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void interact(Player player);
}
