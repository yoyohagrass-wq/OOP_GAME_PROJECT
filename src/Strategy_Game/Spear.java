/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Spear {
    private String SpearName;
    private int DamageBonus;
    
    public Spear(String SpearName, int DamageBonus){
        this.SpearName = SpearName;
        this.DamageBonus = DamageBonus;
    }
    
    public int getDamageBonus(){
        return this.DamageBonus;
    }
    public String getSpearName(){
        return this.SpearName;
    }
    
    
}
