/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class soldier extends Unit {

    private int Armor;
    public Sword SSowrd;

    // Constructor
    public soldier(int Health, int Damage, int id, int Armor) {
        super(Health, Damage, id);
        this.Armor = Armor;
        this.SSowrd = new Sword("Basic Sword",10);
    }
   

    // Armor Setter
    public void setArmor(int Armor) {
        if (Armor >= 0 && Armor <= 100) {
            this.Armor = Armor;
        }
    }
    
    @Override
    public int CalcDamage() {
        return getDamage() + SSowrd.getDamageBonus();
    }

    // Armor Getter
    public int getArmor() {
        return this.Armor;
    }
    @Override
    // Attack Method
    public void attack() {
        System.out.println("Soldier attacks with " + CalcDamage() + " damage!");
    }
    

}
