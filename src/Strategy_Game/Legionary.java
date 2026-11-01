/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public class Legionary extends Unit {

    private int Armor;
    private Sword sword;
    private Shield shield;

    // Constructor
    public Legionary(int Health, int Damage, int id, int Armor) {
        super(Health, Damage, id);
        setArmor(Armor);
        this.sword = new Sword("Great Sword", 20);
        this.shield = new Shield("Heavy Shield", 20);
    }

    // Armor Setter
    public void setArmor(int Armor) {
        if (Armor >= 0 && Armor <= 100) {
            this.Armor = Armor;
        }
    }

    // Armor Getter
    public int getArmor() {
        return this.Armor;
    }

    // Sword Getter
    public Sword getSword() {
        return this.sword;
    }

    // Shield Getter
    public Shield getShield() {
        return this.shield;
    }

    // Calculate Damage
    @Override
    public int CalcDamage() {
        return getDamage() + sword.getDamageBonus();
    }

    // Calculate Armor
    public int CalcArmor() {
        return getArmor() + shield.getArmorBonus();
    }

    // Attack Method
    @Override
    public void attack() {
        System.out.println("Legionary attacks with " + CalcDamage() + " damage!");
    }
    
    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            int actualDamage = damage - CalcArmor();

        if (actualDamage > 0) {
            super.takeDamage(actualDamage);
            }
        }
    }
}
