/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */

public interface Damageable {

    void takeDamage(int damage);

    int getHealth();

    boolean isDestroyed();
}
