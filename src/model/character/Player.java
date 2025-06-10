package model.character;

import model.quest.Journal;
import model.quest.Quest;
import model.quest.QuestManager;
import model.item.Weapon;
import model.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int gold = 0;
    private List<Quest> activeQuests = new ArrayList<>();
    private List<Item> inventory;
    private Weapon equippedWeapon;
    private Journal journal = new Journal();
    private QuestManager questManager = new QuestManager();


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

    // --- Getter und Setter ---

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

    public void addQuest(Quest quest) {
        activeQuests.add(quest);
        System.out.println("📜 Neue Quest erhalten: " + quest.getTitle());
    }

    public void completeQuest(String title) {
        for (Quest quest : activeQuests) {
            if (quest.getTitle().equalsIgnoreCase(title) && !quest.isCompleted()) {
                quest.complete(this);
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

    public QuestManager getQuestManager() {
        return questManager;
    }

    public void addGold(int amount) {
        gold += amount;
    }
    public boolean hasCompleted(String questTitle) {
        for (Quest quest : activeQuests) {
            if (quest.getTitle().equalsIgnoreCase(questTitle) && quest.isCompleted()) {
                return true;
            }
        }
        return false;
    }



}
