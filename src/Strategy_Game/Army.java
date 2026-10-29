/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;
import java.util.ArrayList;
/**
 *
 * @author yoyoh
 */



public class Army {

    private ArrayList<Unit> units;
    
    public Army(){
        this.units = new ArrayList<>();
    }
    
    public void addUnit(Unit unit){
        this.units.add(unit);
    }
    public int getArmySize(){
        return this.units.size();
    }
    
    public boolean removeUnit(Class<? extends Unit> unitType) {

    for (Unit unit : units) {
        if (unitType.isInstance(unit)) {
            return units.remove(unit);
        }
    }

    return false;
    }
    
    public int getTotalDamage() {

    int totalDamage = 0;

    for (int i = 0; i < getArmySize(); i++) {

        totalDamage += units.get(i).CalcDamage();

    }

    return totalDamage;
    
    
    }
    
    public boolean isDefeated() {

    for (Unit unit : units) {

        if (!unit.isDestroyed()) {
            return false;
        }
    }

    return true;
    }
    
    public ArrayList<Unit> getUnits() {
    return this.units;
    }
    
    
            
            

}
