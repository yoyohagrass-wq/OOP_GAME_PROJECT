/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Cavalry extends Unit {

    private final Horse horse;

    // Constructor
    public Cavalry(int Health, int Damage, int id) {
        super(Health, Damage, id);
        this.horse = new Horse(50);
    }
    @Override
    public int CalcDamage() {
        return getDamage();
    }
    // Getter
    public Horse getHorse() {
        return this.horse;
    }
    @Override
    public void attack() {
        System.out.println("Cavalry attacks with " + CalcDamage() + " damage!");
    }
}
