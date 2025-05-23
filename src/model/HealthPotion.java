package model;

public class HealthPotion extends Item {

    private int healAmount;

    public HealthPotion(String name, String description, int healAmount) {
        super(name, description);
        this.healAmount = healAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public void use(Player player) {
        player.heal(healAmount);
        System.out.println(player.getName() + " trinkt " + name + " und heilt sich um " + healAmount + " Lebenspunkte.");
    }
}
