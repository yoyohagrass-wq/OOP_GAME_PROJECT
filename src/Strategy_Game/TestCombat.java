/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public class TestCombat {

    public static void main(String[] args) {

        Army battleArmy1 = new Army();
        Army battleArmy2 = new Army();

        Chariot chariot = new Chariot(150, 40, 1, 25);
        Berserker berserker = new Berserker(150, 35, 2);

        battleArmy1.addUnit(chariot);
        battleArmy2.addUnit(berserker);

        Battle battle = new Battle(battleArmy1, battleArmy2);

        System.out.println("===== BATTLE STARTED =====");

        boolean invalidAttack = battle.takeTurn(chariot, chariot);

        System.out.println("Attack Own Unit: " + invalidAttack);
        System.out.println("Chariot HP: " + chariot.getHealth());
        System.out.println("Current Turn: " + battle.getCurrentTurn());

        System.out.println("==========================");

        boolean firstCharge = battle.takeTurn(chariot, berserker, true);

        System.out.println("First Charge Successful: " + firstCharge);
        System.out.println("Berserker HP: " + berserker.getHealth());
        System.out.println("Current Turn: " + battle.getCurrentTurn());

        battle.takeTurn(berserker, chariot);

        System.out.println("Chariot HP: " + chariot.getHealth());
        System.out.println("Current Turn: " + battle.getCurrentTurn());

        boolean secondCharge = battle.takeTurn(chariot, berserker, true);

        System.out.println("Second Charge Successful: " + secondCharge);
        System.out.println("Berserker HP: " + berserker.getHealth());
        System.out.println("Current Turn: " + battle.getCurrentTurn());

        boolean normalAttack = battle.takeTurn(chariot, berserker);

        System.out.println("Normal Attack Successful: " + normalAttack);
        System.out.println("Berserker HP: " + berserker.getHealth());

        Army winner = battle.getWinner();

        if (winner == battleArmy1) {
            System.out.println("Army 1 Wins!");
        }
        else if (winner == battleArmy2) {
            System.out.println("Army 2 Wins!");
        }
        else {
            System.out.println("No winner yet!");
        }
        boolean attackAfterBattle = battle.takeTurn(chariot, berserker);

        System.out.println("Attack After Battle: " + attackAfterBattle);
        System.out.println("Current Turn: " + battle.getCurrentTurn());
    }
}
