/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Bow {
    private String Bowname;
    private int DamageBonus;
    
    public Bow(String BowName,int DamageBonus){
        this.Bowname = BowName;
        this.DamageBonus = DamageBonus;
    
    }
    public int getDamageBonus(){
         return this.DamageBonus;
    }
    public String getBowname(){
        return this.Bowname;
    }  
}
