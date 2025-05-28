package model.item;

import model.character.Player;

public abstract class Item {
    protected String name;
    protected String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Optional: für später überschreibbare Aktionen wie benutzen, verkaufen, etc.
    public abstract void use(Player player);
}
