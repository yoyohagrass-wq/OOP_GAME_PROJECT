/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Barracks extends Buildings {

    public Barracks(int buildingID) {
        super(buildingID, 500);
    }

    public boolean canRecruit() {
        return !isDestroyed();
    }

    public boolean recruitUnit(Player player, Class<? extends Unit> unitType, int unitID) {

        if (player == null || unitType == null || unitID <= 0 || !canRecruit()) {
            return false;
        }

        Unit newUnit;
        int cost;

        if (unitType == Archer.class) {
            cost = 100;
            newUnit = new Archer(100, 20, unitID, 50);
        }

        else if (unitType == Knight.class) {
            cost = 150;
            newUnit = new Knight(150, 30, unitID,20);
        }

        else if (unitType == Cavalry.class) {
            cost = 200;
            newUnit = new Cavalry(200, 40, unitID);
        }

        else if (unitType == Spearman.class) {
            cost = 120;
            newUnit = new Spearman(120, 25, unitID);
        }

        else if (unitType == Crossbowman.class) {
            cost = 140;
            newUnit = new Crossbowman(100, 25, unitID, 60);
        }

        else if (unitType == Longbowman.class) {
            cost = 160;
            newUnit = new Longbowman(100, 30, unitID, 80);
        }

        else if (unitType == Guardian.class) {
            cost = 180;
            newUnit = new Guardian(180, 25, unitID, 25);
        }
        
        else if (unitType == Legionary.class) {

            if (!player.getCivilization().getName().equals("Romans")) {
                return false;
        }

            cost = 175;
            newUnit = new Legionary(150, 35, unitID, 15);
        }
        
        
        else if (unitType == Berserker.class) {

            if (!player.getCivilization().getName().equals("Vikings")) {
                return false;
        }

            cost = 180;
            newUnit = new Berserker(150, 35, unitID);
        }
        
        else if (unitType == Chariot.class) {

            if (!player.getCivilization().getName().equals("Egyptians")) {
                return false;
        }

            cost = 160;
            newUnit = new Chariot(220, 35, unitID,25);
        }

        else {
            return false;
        }
        
        
        

        if (player.spendGold(cost)) {
            player.getArmy().addUnit(newUnit);
            return true;
        }
        
        

        return false;
    }
}
