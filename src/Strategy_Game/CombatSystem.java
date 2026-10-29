/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class CombatSystem {
    
    public void performAttack(Unit attacker, Damageable target) {
        
        if (attacker == null || target == null) {
            return;
        }
        
        if (target.isDestroyed() || attacker.isDestroyed()){
            return;
        }
        
        target.takeDamage(attacker.CalcDamage());
        
    }
    
    
    public void performChargeAttack(Chariot attacker, Damageable target) {

        if (attacker == null || target == null) {
            return;
        }

        if (attacker.isDestroyed() || target.isDestroyed()) {
            return;
        }
        target.takeDamage(attacker.CalcChargeDamage());
    }
    
    public Army getWinner(Army army1, Army army2) {

        if (army1.isDefeated()) {
            return army2;
        }

        else if (army2.isDefeated()) {
            return army1;
        }
        return null;
}   
}
