/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Strategy_Game;
import java.util.ArrayList;
/**
 *
 * @author yoyoh
 */
public class Practice {
    public static void main(String[] args) {

        // Create a Soldier object
        soldier s1 = new soldier(500, 100, 1, 50);

        // Change values using setters
        s1.setHealth(700);
        s1.setDamage(67);

        // Call Soldier's attack method
        s1.attack();
        
        

        
    
    ArrayList<Unit> army = new ArrayList<>();
    
    army.add(new Archer(100, 20, 1, 50));
    army.add(new Archer(100, 20, 2, 50));
    army.add(new Archer(100, 20, 3, 50));
    army.add(new soldier(120, 15, 5,50));
    army.add(new soldier(120, 15, 5,50));
    
    for (Unit i : army) {
        i.attack();
    }
    System.out.println(army.size());
    
    Army army1 = new Army();
    army1.addUnit(new Archer(100, 20, 1, 50));
    army1.addUnit(new Archer(100, 20, 1, 50));
    army1.addUnit(new soldier(120, 15, 5,50));
    army1.addUnit(new soldier(120, 15, 5,50));
    System.out.println(army1.getArmySize());
    System.out.println(army1.getTotalDamage());
    
    
//    P1.getArmy().addUnit(new Archer(100, 20, 1, 50));
//    System.out.println(P1.getArmy().getArmySize());
//    System.out.println(P1.getArmy().getTotalDamage());

Civilization romans = new Civilization("Romans");
Civilization egyptians = new Civilization("Egyptians");
Civilization vikings = new Civilization("Vikings");


Player player2 = new Player(2, "Ahmed", 500, egyptians);



Archer A1 = new Archer(100, 20, 1, 50);

A1.takeDamage(30);

System.out.println(A1.getHealth());

A1.takeDamage(100);

System.out.println(A1.getHealth());
System.out.println(A1.isDestroyed());



Chariot chariot1 = new Chariot(150, 40, 1, 25);





Player player1 = new Player(1, "Youssef", 500, romans);

player1.getBarracks().recruitUnit(player1, Archer.class, 1);

player1.getBarracks().recruitUnit(player1, Cavalry.class, 2);

System.out.println(player1.getArmy().getArmySize());

System.out.println(player1.getGold());











System.out.println(chariot1.chargeAttack());
System.out.println(chariot1.chargeAttack());
    
    

       
       
    }
}
