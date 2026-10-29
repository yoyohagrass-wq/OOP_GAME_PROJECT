/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Shield {
    public String ShieldName;
    public int ArmorBonus;
    
    public Shield(String ShieldName,int ArmorBonus){
        this.ArmorBonus = ArmorBonus;
        this.ShieldName = ShieldName;
    }
    
        
    public int getArmorBonus(){
        return this.ArmorBonus;
    }
    public String getShieldName(){
        return this.ShieldName;
    }
}
