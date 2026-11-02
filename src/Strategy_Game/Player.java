package Strategy_Game;

public class Player {

    private final int playerID;
    private String Name;
    private int Gold;
    private final CounterSystem counterSystem;
    private final Army MyArmy;
    private Civilization civilization;
    private final Barracks barracks;
    private final GoldMine goldMine;
    private final DefenseTower defenseTower;
    private final TownHall townHall;

    public Player(int playerID, String Name, int Gold, Civilization selectedCivilization) {
        this.Name = Name;
        this.Gold = Gold;
        this.playerID = playerID;
        this.MyArmy = new Army();
        this.civilization = selectedCivilization;
        this.barracks = new Barracks(playerID);
        this.goldMine = new GoldMine(playerID);
        this.defenseTower = new DefenseTower(playerID);
        this.townHall = new TownHall(playerID);
        this.counterSystem = new CounterSystem();
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        if (Name != null && !Name.isBlank()) {
            this.Name = Name;
        }
    }

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

    public DefenseTower getDefenseTower() {
        return this.defenseTower;
    }

    public TownHall getTownHall() {
        return this.townHall;
    }

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

    public void recordLoss() {
        this.counterSystem.recordLoss();
    }

    public int getGamesPlayed() {
        return this.counterSystem.getGamesPlayed();
    }

    public double getWinRate() {
        return this.counterSystem.getWinRate();
    }

    void restoreCounters(int gamesPlayed, int wins, int losses) {
        this.counterSystem.restore(gamesPlayed, wins, losses);
    }

    public void addGold(int goldAmount) {
        if (goldAmount > 0) {
            this.Gold += goldAmount;
        }
    }

    public boolean spendGold(int Amount) {
        if (Amount > 0 && Gold >= Amount) {
            Gold -= Amount;
            return true;
        }

        return false;
    }
}
