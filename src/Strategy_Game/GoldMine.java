/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class GoldMine extends Buildings {

    private final int goldPerInterval = 50;
    private final long productionInterval = 30000;
    private long lastCollectionTime;

    public GoldMine(int buildingID) {
        super(buildingID, 300);
        this.lastCollectionTime = System.currentTimeMillis();
    }
    
    public void collectGold(Player player) {
    }
}