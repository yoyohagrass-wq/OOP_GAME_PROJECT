/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public class Archer extends Unit{
    private int Range;
    public Bow BowNArrow;

    public Archer(int Health, int Damage, int id, int Range) {
        super(Health, Damage, id);
        this.Range = Range;
        this.BowNArrow = new Bow("Wooden Bow", 10);
    
    }
    
        public void setBow(Bow NewBow){
        this.BowNArrow = NewBow;
    }

    // Range Setter
    public void setRange(int Range) {
        if (Range >= 0 && Range <= 100) {
            this.Range = Range;
        }
    }

    // Range Getter
    public int getRange() {
        return this.Range;
    }

    
    @Override
    public int CalcDamage() {
        return getDamage() + BowNArrow.getDamageBonus();
    }
    public void attack() {
        System.out.println("Archer attacks with " + CalcDamage() + " damage!");
    }

    public void useBow(Bow x) {
        System.out.println("Archer is using bow" + x.getBowname());
    }
}