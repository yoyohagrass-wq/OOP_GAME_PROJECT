/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Axe {
    private String AxeName;
    private int DamageBonus;
    
    public Axe(String AxeName,int DamageBonus){
        this.AxeName = AxeName;
        this.DamageBonus = DamageBonus;
    
    }
    
    public int getDamageBonus(){
         return this.DamageBonus;
    }
    public String getAxeName(){
        return this.AxeName;
    }  
}
