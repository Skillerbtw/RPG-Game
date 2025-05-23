package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int gold;
    private List<Quest> activeQuests = new ArrayList<>();
    private List<Item> inventory;
    private Weapon equippedWeapon;
    private Journal journal = new Journal();


    // Konstruktor
    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.gold = 0;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;
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

    // --- Getter und Setter ---

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

    public void addQuest(Quest quest) {
        activeQuests.add(quest);
        System.out.println("📜 Neue Quest erhalten: " + quest.getTitle());
    }

    public void completeQuest(String title) {
        for (Quest quest : activeQuests) {
            if (quest.getTitle().equalsIgnoreCase(title) && !quest.isCompleted()) {
                quest.complete();
                return;
            }
        }
        System.out.println("⚠️ Quest nicht gefunden oder bereits abgeschlossen.");
    }

    public void showQuests() {
        if (activeQuests.isEmpty()) {
            System.out.println("📭 Du hast keine aktiven Quests.");
            return;
        }
        System.out.println("📋 Deine Quests:");
        for (Quest q : activeQuests) {
            System.out.println(" - " + q);
        }
    }




}
