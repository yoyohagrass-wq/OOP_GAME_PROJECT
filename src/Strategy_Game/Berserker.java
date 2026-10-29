/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Berserker extends Unit{
    private Axe axe;
    
    public Berserker(int Health, int Damage, int id){
        super(Health,Damage,id);
        this.axe = new Axe("Warrior Axe", 30);
    }
    
        public Axe getAxe() {
        return this.axe;
        }

    // Calculate Damage
    @Override
    public int CalcDamage() {
        if (getHealth() < getMaxHealth()/2){
            return (int)(getDamage() * 1.5) + axe.getDamageBonus();
        
        }
        return getDamage() + axe.getDamageBonus();
    }


    // Attack Method
    @Override
    public void attack() {
        System.out.println("Berserker attacks with " + CalcDamage() + " damage!");
    }
    
}
