/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public abstract class Buildings implements Damageable {

    private final int buildingID;
    private int health;
    private final int maxHealth;

    // Constructor
    public Buildings(int buildingID, int maxHealth) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Max health must be positive");
        }

        this.buildingID = buildingID;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    // Getters
    public int getBuildingID() {
        return this.buildingID;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    // Take Damage
    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            this.health -= damage;

            if (this.health < 0) {
                this.health = 0;
            }
        }
    }

    // Check if destroyed
    @Override
    public boolean isDestroyed() {
        return this.health == 0;
    }
}