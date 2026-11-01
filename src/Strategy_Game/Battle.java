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
    private int currentTurn = 1;
    private final CombatSystem combat;

    public Battle(Army battleArmy1, Army battleArmy2) {
        this.battleArmy1 = battleArmy1;
        this.battleArmy2 = battleArmy2;
        this.combat = new CombatSystem();

        this.combat.startBattle(battleArmy1, battleArmy2);
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public void switchTurn() {
        if (currentTurn == 1) {
            currentTurn = 2;
        }
        else {
            currentTurn = 1;
        }
    }

    public boolean takeTurn(Unit attacker, Unit target) {
        return takeTurn(attacker, target, false);
    }

    public boolean takeTurn(Unit attacker, Unit target, boolean useCharge) {

        Army attackingArmy;
        Army defendingArmy;

        if (getCurrentTurn() == 1) {
            attackingArmy = battleArmy1;
            defendingArmy = battleArmy2;
        }
        else {
            attackingArmy = battleArmy2;
            defendingArmy = battleArmy1;
        }

        if (attacker == null || target == null) {
            return false;
        }

        if (attackingArmy.isDefeated() || defendingArmy.isDefeated()) {
            return false;
        }

        if (!attackingArmy.getUnits().contains(attacker)) {
            return false;
        }

        if (!defendingArmy.getUnits().contains(target)) {
            return false;
        }

        if (attacker.isDestroyed() || target.isDestroyed()) {
            return false;
        }

        if (useCharge) {

            if (!(attacker instanceof Chariot)) {
                return false;
            }

            Chariot chariot = (Chariot) attacker;

            if (!combat.performChargeAttack(chariot, target)) {
                return false;
            }
        }
        else {
            combat.performAttack(attacker, target);
        }

        if (!defendingArmy.isDefeated()) {
            switchTurn();
        }

        return true;
    }

    public Army getWinner() {
        return combat.getWinner(battleArmy1, battleArmy2);
    }
}