package Strategy_Game;

/**
 * Stores simple player game statistics.
 */
public class CounterSystem {

    private int gamesPlayed;
    private int wins;
    private int losses;

    public void recordWin() {
        gamesPlayed++;
        wins++;
    }

    public void recordLoss() {
        gamesPlayed++;
        losses++;
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

    void restore(int gamesPlayed, int wins, int losses) {
        if (gamesPlayed >= 0 && wins >= 0 && losses >= 0
                && wins + losses <= gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
            this.wins = wins;
            this.losses = losses;
        }
    }
}
