/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class Horse {

    private int Speed;

    public Horse(int Speed) {
        this.Speed = Speed;
    }

    public void setSpeed(int Speed) {
        if (Speed >= 0) {
            this.Speed = Speed;
        }
    }

    public int getSpeed() {
        return this.Speed;
    }
}
