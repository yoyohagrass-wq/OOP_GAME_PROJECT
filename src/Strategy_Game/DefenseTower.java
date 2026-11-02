/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public class DefenseTower extends Buildings {

    private final int attackDamage;

    public DefenseTower(int buildingID) {
        super(buildingID, 400);
        this.attackDamage = 30;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    public void attack(Unit target) {

        if (target == null) {
            return;
        }

        if (isDestroyed() || target.isDestroyed()) {
            return;
        }

        target.takeDamage(this.attackDamage);
    }
}