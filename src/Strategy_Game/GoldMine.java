package Strategy_Game;

public class GoldMine extends Buildings {

    private final int goldPerInterval = 50;
    private final long productionInterval = 30000;
    private long lastCollectionTime;

    public GoldMine(int buildingID) {
        super(buildingID, 300);
        this.lastCollectionTime = System.currentTimeMillis();
    }

    public void collectGold(Player player) {
        if (player == null || isDestroyed()) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastCollectionTime;
        long intervals = elapsedTime / productionInterval;

        if (intervals > 0) {
            player.addGold((int) (intervals * goldPerInterval));
            lastCollectionTime += intervals * productionInterval;
        }
    }
}
