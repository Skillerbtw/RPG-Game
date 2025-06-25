package model.character;

import model.item.Item;
import model.item.Weapon;
import model.quest.Journal;
import model.quest.QuestManager;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int gold = 0;
    private List<Item> inventory;
    private Weapon equippedWeapon;
    private Journal journal = new Journal();
    private QuestManager questManager = new QuestManager();

    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;


        // Startwaffe!
        Weapon startWeapon = new Weapon("Stock", "Ein einfacher Holzstock", 8);
        inventory.add(startWeapon);
        this.equippedWeapon = startWeapon;
    }

    // Lebenspunkte reduzieren
    public void takeDamage(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    // Heilen
    public void heal(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    // Item hinzufügen
    public void addItem(Item item) {
        inventory.add(item);
    }

    // Item entfernen
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    // Waffe ausrüsten
    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
    }

    // Spielerstatus zurückgeben
    public String getStats() {
        return "Name: " + name +
                "\nHP: " + currentHealth + "/" + maxHealth +
                "\nGold: " + gold +
                "\nWaffe: " + (equippedWeapon != null ? equippedWeapon.getName() : "Keine");
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public Journal getJournal() {
        return journal;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    public void addGold(int amount) {
        gold += amount;
    }
}
