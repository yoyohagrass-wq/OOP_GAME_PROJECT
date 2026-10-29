/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Knight extends Unit{
    
    private int Armor;
    public Sword SSowrd;
    public Shield Shield;

    // Constructor
    public Knight(int Health, int Damage, int id, int Armor) {
        super(Health, Damage, id);
        this.Armor = Armor;
        this.SSowrd = new Sword("Basic Sword",10);
        this.Shield = new Shield("Wooden Shield",15);
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
    
    public int CalcArmor(){
    
        return getArmor() + Shield.getArmorBonus(); 
    }

    // Armor Getter
    public int getArmor() {
        return this.Armor;
    }
    @Override
    // Attack Method
    public void attack() {
        System.out.println("Knight attacks with " + CalcDamage() + " damage!");
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
