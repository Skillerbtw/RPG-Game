package model.character;

public class Enemy {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int damage;
    private int goldReward;

    public Enemy(String name, int maxHealth, int damage, int goldReward) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
        this.goldReward = goldReward;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getDamage() {
        return damage;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void takeDamage(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public boolean isDefeated() {
        return currentHealth <= 0;
    }

    public void attack(Player player) {
        player.takeDamage(damage);
        System.out.println(name + " greift an und verursacht " + damage + " Schaden!");
    }
}
