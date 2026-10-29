/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Chariot extends Unit{
    private Spear spear;
    private int chargeBonus;
    private boolean chargeAvailable = true;
    
   

    // Constructor
    public Chariot(int Health, int Damage, int id, int chargeBonus) {
        super(Health, Damage, id);
        this.spear = new Spear("Bronze Spear",40);
        this.chargeBonus = chargeBonus;
    }

    // Spear Getter
    public Spear getSpear() {
        return this.spear;
    }
    // Calculate Damage
    @Override
    public int CalcDamage() {
        return getDamage() + spear.getDamageBonus();
    }
    
    public int CalcChargeDamage() {
        return CalcDamage() + this.chargeBonus;
    }
    
    public int chargeAttack() {

        if (chargeAvailable) {
            this.chargeAvailable = false;
            return CalcChargeDamage();
        }   

        return 0;
    }
    
    public void resetCharge() {
        this.chargeAvailable = true;
    }

    // Attack Method
    @Override
    public void attack() {
        System.out.println("Chariot attacks with " + CalcDamage() + " damage!");
    }
    
}
