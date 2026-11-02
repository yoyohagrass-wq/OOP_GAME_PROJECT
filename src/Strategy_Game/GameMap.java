/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
public class GameMap {

    private char[][] map = {
        {'.', '.', '.'},
        {'1', 'X', '2'},
        {'.', '.', '.'}
    };

    public char getCell(int row, int col) {
        return map[row][col];
    }

    public int getSize() {
        return map.length;
    }
}
