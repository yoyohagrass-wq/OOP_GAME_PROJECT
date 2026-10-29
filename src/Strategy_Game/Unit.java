/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public abstract class Unit implements IMoveSet, Damageable {

    private int Health;
    private int Damage;
    private int id;
    private final int maxHealth;

    // Constructor
    public Unit(int Health, int Damage, int id) {
    if (Health <= 10 || Health >= 1000) {
        throw new IllegalArgumentException("Invalid health");
    }

    this.maxHealth = Health;
    this.Health = Health;
    setDamage(Damage);
    setId(id);
}

    // Health Setter
    public void setHealth(int Health) {
        if (Health < 1000 && Health > 10) {
            this.Health = Health;
        }
    }
    
    public int getMaxHealth() {
    return this.maxHealth;
    }

    // Health Getter
    @Override
    public int getHealth() {
        return this.Health;
    }

    // Calculate Damage
    public int CalcDamage() {
        return getDamage();
    }

    // Damage Setter
    public void setDamage(int Damage) {
        if (Damage > 10 && Damage <= 100) {
            this.Damage = Damage;
        }
    }

    // Damage Getter
    public int getDamage() {
        return this.Damage;
    }

    // ID Setter
    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    // ID Getter
    public int getId() {
        return this.id;
    }

    // Take Damage
    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            if (damage >= this.Health) {
                this.Health = 0;
            } else {
                this.Health -= damage;
            }
        }
    }

    // Check if Unit is Destroyed
    @Override
    public boolean isDestroyed() {
        return this.Health == 0;
    }
}
