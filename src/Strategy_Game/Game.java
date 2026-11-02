/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public class Game {

    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getWinner() {

        if (player1.getTownHall().isDestroyed() && !player2.getTownHall().isDestroyed()) {
            return player2;
        }

        else if (player2.getTownHall().isDestroyed() && !player1.getTownHall().isDestroyed()) {
            return player1;
        }

        return null;
    }
}