package Strategy_Game;

/**
 * Stores player statistics and battle summary information.
 */
public class CounterSystem {

    private int gamesPlayed;
    private int wins;
    private int losses;

    private int battlesPlayed;
    private int player1Wins;
    private int player2Wins;
    private int player1Losses;
    private int player2Losses;

    public void recordWin() {
        gamesPlayed++;
        wins++;
    }

    public void recordLoss() {
        gamesPlayed++;
        losses++;
    }

    public void recordBattle(Player winner, Player player1, Player player2) {
        if (winner == player1) {
            battlesPlayed++;
            player1Wins++;
            player2Losses++;
        } else if (winner == player2) {
            battlesPlayed++;
            player2Wins++;
            player1Losses++;
        }
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinRate() {
        if (gamesPlayed == 0) {
            return 0.0;
        }
        return (wins * 100.0) / gamesPlayed;
    }

    public int getSurvivingUnits(Army army) {
        int count = 0;

        if (army == null) {
            return count;
        }

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

    void restore(int gamesPlayed, int wins, int losses) {
        if (gamesPlayed >= 0 && wins >= 0 && losses >= 0
                && wins + losses <= gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
            this.wins = wins;
            this.losses = losses;
        }
    }
}
