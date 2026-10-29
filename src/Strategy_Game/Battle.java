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

    // Constructor
    public Battle(Army battleArmy1, Army battleArmy2) {
        this.battleArmy1 = battleArmy1;
        this.battleArmy2 = battleArmy2;
    }

    // Current Turn Getter
    public int getCurrentTurn() {
        return this.currentTurn;
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
