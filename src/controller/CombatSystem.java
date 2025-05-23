package controller;

import model.Player;
import model.Enemy;
import model.Weapon;
import model.Item;

import java.util.List;
import java.util.Scanner;

public class CombatSystem {

    private static Scanner scanner = new Scanner(System.in);

    public static void startCombat(Player player, Enemy enemy) {
        System.out.println("🛡️ Du triffst auf einen " + enemy.getName() + "!");

        chooseWeapon(player);

        while (player.getCurrentHealth() > 0 && !enemy.isDefeated()) {
            System.out.println("\n--- Deine Runde ---");
            System.out.println("1. Angreifen");
            System.out.println("2. Waffe wechseln");
            System.out.println("3. Fliehen");
            System.out.print("Wähle eine Aktion: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    attackEnemy(player, enemy);
                    break;

                case "2":
                    chooseWeapon(player);
                    continue;

                case "3":
                    if (attemptEscape()) {
                        System.out.println("🚪 Du bist erfolgreich geflohen!");
                        return;
                    } else {
                        System.out.println("❌ Flucht fehlgeschlagen!");
                    }
                    break;

                default:
                    System.out.println("Ungültige Eingabe.");
                    continue;
            }

            if (enemy.isDefeated()) {
                System.out.println("✅ Du hast den " + enemy.getName() + " besiegt!");
                System.out.println("💰 Du erhältst " + enemy.getGoldReward() + " Gold.");
                player.setGold(player.getGold() + enemy.getGoldReward());
                break;
            }

            System.out.println("--- Gegner Runde ---");
            enemy.attack(player);
            System.out.println("🧍 Dein Leben: " + player.getCurrentHealth());

            if (player.getCurrentHealth() <= 0) {
                System.out.println("☠️ Du wurdest vom " + enemy.getName() + " besiegt!");
            }
        }
    }

    private static void chooseWeapon(Player player) {
        List<Item> inventory = player.getInventory();
        System.out.println("🔪 Wähle eine Waffe zum Ausrüsten:");

        int index = 1;
        for (Item item : inventory) {
            if (item instanceof Weapon weapon) {
                System.out.println(index + ". " + weapon.getName() + " (Schaden: " + weapon.getDamage() + ")");
            } else {
                continue;
            }
            index++;
        }

        System.out.print("Nummer eingeben: ");
        String input = scanner.nextLine();

        try {
            int choice = Integer.parseInt(input);
            int weaponCount = 0;
            for (Item item : inventory) {
                if (item instanceof Weapon weapon) {
                    weaponCount++;
                    if (weaponCount == choice) {
                        player.equipWeapon(weapon);
                        System.out.println("✅ " + weapon.getName() + " wurde ausgerüstet.");
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Ungültige Eingabe – keine Waffe ausgerüstet.");
        }

        System.out.println("⚠️ Keine gültige Auswahl getroffen.");
    }

    private static void attackEnemy(Player player, Enemy enemy) {
        Weapon weapon = player.getEquippedWeapon();
        if (weapon == null) {
            System.out.println("⚠️ Du hast keine Waffe ausgerüstet!");
            return;
        }
        int damage = weapon.getDamage();
        System.out.println("Du greifst mit " + weapon.getName() + " an und verursachst " + damage + " Schaden.");
        enemy.takeDamage(damage);
    }

    private static boolean attemptEscape() {
        double chance = Math.random(); // 0.0 - 1.0
        return chance > 0.5; // 50% Erfolgsrate
    }
}
