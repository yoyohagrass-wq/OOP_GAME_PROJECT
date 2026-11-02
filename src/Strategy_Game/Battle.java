/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;
/**
 *
 * @author yoyoh
 */


public class Battle {

    private Army battleArmy1;
    private Army battleArmy2;
    private final CombatSystem combatSystem;
    private int currentTurn = 1;

    // Constructor
    public Battle(Army battleArmy1, Army battleArmy2) {
        this.battleArmy1 = battleArmy1;
        this.battleArmy2 = battleArmy2;
        this.combatSystem = new CombatSystem();
    }

    // Current Turn Getter
    public int getCurrentTurn() {
        return this.currentTurn;
    }

    // Combat System Getter
    public CombatSystem getCombatSystem() {
        return this.combatSystem;
    }

    // Execute one army's turn
    public void executeTurn() {
        Army attackingArmy = currentTurn == 1 ? battleArmy1 : battleArmy2;
        Army defendingArmy = currentTurn == 1 ? battleArmy2 : battleArmy1;

        if (attackingArmy == null || defendingArmy == null) {
            return;
        }

        for (Unit attacker : attackingArmy.getUnits()) {
            if (attacker.isDestroyed()) {
                continue;
            }

            for (Unit target : defendingArmy.getUnits()) {
                if (!target.isDestroyed()) {
                    combatSystem.performAttack(attacker, target);
                    break;
                }
            }
        }

        switchTurn();
    }

    // Execute a normal attack for the active side
    public boolean takeTurn(Unit attacker, Unit target) {
        if (!isValidTurn(attacker, target) || getWinner() != null) {
            return false;
        }

        combatSystem.performAttack(attacker, target);
        switchTurn();
        return true;
    }

    // Execute a charge attack for the active side
    public boolean takeTurn(Chariot attacker, Unit target, boolean charge) {
        if (!charge) {
            return takeTurn(attacker, target);
        }

        if (!isValidTurn(attacker, target) || getWinner() != null) {
            return false;
        }

        int chargeDamage = attacker.chargeAttack();
        if (chargeDamage <= 0) {
            return false;
        }

        target.takeDamage(chargeDamage);
        switchTurn();
        return true;
    }

    // Get the winner when one army is defeated
    public Army getWinner() {
        return this.combatSystem.getWinner(this.battleArmy1, this.battleArmy2);
    }

    private boolean isValidTurn(Unit attacker, Unit target) {
        if (attacker == null || target == null
                || attacker.isDestroyed() || target.isDestroyed()) {
            return false;
        }

        Army attackingArmy = currentTurn == 1 ? battleArmy1 : battleArmy2;
        Army defendingArmy = currentTurn == 1 ? battleArmy2 : battleArmy1;

        return attackingArmy != null && defendingArmy != null
                && attackingArmy.getUnits().contains(attacker)
                && defendingArmy.getUnits().contains(target);
    }

    // Switch Turn
    public void switchTurn() {
        if (currentTurn == 1) {
            currentTurn = 2;
        }
        else {
            currentTurn = 1;
        }
    }
}
