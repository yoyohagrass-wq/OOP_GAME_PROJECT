/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author 3beed
 */
public class CounterSystem {

    private int battlesPlayed;

    private int player1Wins;
    private int player2Wins;

    private int player1Losses;
    private int player2Losses;

    public CounterSystem() {

    this.battlesPlayed = 0;

    this.player1Wins = 0;
    this.player2Wins = 0;

    this.player1Losses = 0;
    this.player2Losses = 0;
} 
    
    public void recordBattle(Player winner, Player player1, Player player2) {

    if (winner == player1) {
        battlesPlayed++;
        player1Wins++;
        player2Losses++;
    }
    else if (winner == player2) {
        battlesPlayed++;
        player2Wins++;
        player1Losses++;
    }
}
    
    public int getSurvivingUnits(Army army) {

    int count = 0;

    for (Unit unit : army.getUnits()) {

        if (!unit.isDestroyed()) {
            count++;
        }
    }

    return count;
}
    
    public int getBattlesPlayed() {
    return battlesPlayed;
}

public int getPlayer1Wins() {
    return player1Wins;
}

public int getPlayer2Wins() {
    return player2Wins;
}

public int getPlayer1Losses() {
    return player1Losses;
}

public int getPlayer2Losses() {
    return player2Losses;
}

}


