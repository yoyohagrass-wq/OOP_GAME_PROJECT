/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Sword {
    private String SwordName;
    private int DamageBonus;
    
    public Sword(String SwordName,int DamageBonus){
        this.SwordName = SwordName;
        this.DamageBonus = DamageBonus;
    
    }
    public int getDamageBonus(){
         return this.DamageBonus;
    }
    public String getSwordName(){
        return this.SwordName;
    }  
    
}
