package controller;

import model.character.Enemy;
import model.character.Player;
import model.item.Item;
import model.item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CombatSystem {

    public static void startCombat(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("⚔️ Du kämpfst gegen: " + enemy.getName());

        while (enemy.getCurrentHealth() > 0 && player.getCurrentHealth() > 0) {
            System.out.println("\n" + player.getStats());
            System.out.println("🧟 Gegner: " + enemy.getName() + " | ❤️ " + enemy.getCurrentHealth());

            System.out.println("1. Angreifen");
            System.out.println("2. Waffe wechseln");
            System.out.println("3. Fliehen");

            System.out.print("> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    Weapon weapon = player.getEquippedWeapon();
                    int damage = (weapon != null) ? weapon.getDamage() : 5;
                    System.out.println("💥 Du greifst mit " + (weapon != null ? weapon.getName() : "Fäusten") + " an!");
                    enemy.takeDamage(damage);
                    System.out.println("🧟 " + enemy.getName() + " verliert " + damage + " HP!");

                    if (enemy.getCurrentHealth() > 0) {
                        int retaliation = enemy.getDamage();
                        System.out.println("😠 " + enemy.getName() + " schlägt zurück!");
                        player.takeDamage(retaliation);
                        System.out.println("💔 Du verlierst " + retaliation + " HP!");
                    }
                }
                case "2" -> {
                    // Sammle alle Waffen im Inventar
                    List<Weapon> weapons = new ArrayList<>();
                    for (Item item : player.getInventory()) {
                        if (item instanceof Weapon w) {
                            weapons.add(w);
                        }
                    }
                    if (weapons.isEmpty()) {
                        System.out.println("⚠️ Du hast keine Waffen im Inventar!");
                        break;
                    }
                    System.out.println("⚙️ Waffenwechsel:");
                    for (int i = 0; i < weapons.size(); i++) {
                        System.out.println((i + 1) + ". " + weapons.get(i).getName());
                    }
                    int choiceIndex;
                    try {
                        choiceIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (choiceIndex < 0 || choiceIndex >= weapons.size()) {
                            System.out.println("❌ Ungültige Auswahl.");
                            break;
                        }
                        player.equipWeapon(weapons.get(choiceIndex));
                        System.out.println("✅ Ausgerüstet: " + weapons.get(choiceIndex).getName());
                    } catch (Exception e) {
                        System.out.println("❌ Ungültige Eingabe.");
                    }
                    break;
                }

                case "3" -> {
                    System.out.println("🏃‍♂️ Du bist geflohen.");
                    return;
                }
                default -> System.out.println("❌ Ungültig.");
            }
        }

        if (player.getCurrentHealth() <= 0) {
            System.out.println("☠️ Du wurdest besiegt...");
        } else if (enemy.getCurrentHealth() <= 0) {
            System.out.println("🎉 Du hast den Gegner besiegt!");
            player.addGold(enemy.getGoldReward());
        }
    }
}
