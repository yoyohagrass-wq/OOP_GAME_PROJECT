/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public class Player {

    private final int playerID;
    private String Name;
    private int Gold;
    private final CounterSystem counterSystem;
    private final Army MyArmy;
    private Civilization civilization;
    private final Barracks barracks;
    private final GoldMine goldMine;

    // Constructor
    public Player(int playerID, String Name, int Gold, Civilization selectedCivilization) {
        this.Name = Name;
        this.Gold = Gold;
        this.playerID = playerID;
        this.MyArmy = new Army();
        this.civilization = selectedCivilization;
        this.barracks = new Barracks(playerID);
        this.goldMine = new GoldMine(playerID);
        this.counterSystem = new CounterSystem();
    }

    // Player ID Getter
    public int getPlayerID() {
        return this.playerID;
    }

    // Name Getter
    public String getName() {
        return this.Name;
    }

    // Name Setter
    public void setName(String Name) {
        if (Name != null && !Name.isBlank()) {
            this.Name = Name;
        }
    }

    // Army Getter
    public Army getArmy() {
        return this.MyArmy;
    }
    
    public Barracks getBarracks() {
        return this.barracks;
    }
    
    public Civilization getCivilization() {
        return this.civilization;
    }

    public GoldMine getGoldMine() {
        return this.goldMine;
    }

    // Gold Getter
    public int getGold() {
        return this.Gold;
    }

    public CounterSystem getCounterSystem() {
        return this.counterSystem;
    }

    public int getWins() {
        return this.counterSystem.getWins();
    }

    public void recordWin() {
        this.counterSystem.recordWin();
    }

    public int getLosses() {
        return this.counterSystem.getLosses();
    }

    public int getGamesPlayed() {
        return this.counterSystem.getGamesPlayed();
    }

    public double getWinRate() {
        return this.counterSystem.getWinRate();
    }

    public void recordLoss() {
        this.counterSystem.recordLoss();
    }

    // Used by LoadManager when restoring a saved player.
    void restoreCounters(int gamesPlayed, int wins, int losses) {
        this.counterSystem.restore(gamesPlayed, wins, losses);
    }

    // Add Gold
    public void addGold(int goldAmount) {
        if (goldAmount > 0) {
            this.Gold += goldAmount;
        }
    }

    // Spend Gold
    public boolean spendGold(int Amount) {
        if (Amount > 0 && Gold >= Amount) {
            Gold -= Amount;
            return true;
        }

        return false;
    }
}
