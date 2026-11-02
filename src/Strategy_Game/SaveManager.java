package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Saves the selected civilization and player match statistics.
 */
public final class SaveManager {

    private SaveManager() {
    }

    public static boolean savePlayer(Player player, String fileName) {

        if (player == null) {
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new File(fileName))) {

            writer.println("PLAYER~" + player.getPlayerID() + "~"
                    + player.getName() + "~"
                    + player.getCivilization().getName() + "~"
                    + player.getGamesPlayed() + "~"
                    + player.getWins() + "~"
                    + player.getLosses() + "~"
                    + player.getGold());

            return !writer.checkError();

        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static boolean deleteSave(String fileName) {
        return new File(fileName).delete();
    }
}