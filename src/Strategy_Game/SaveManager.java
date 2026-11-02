package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Saves the selected civilization and player match statistics.
 */
public final class SaveManager {

    private static final char SEPARATOR = '~';

    private SaveManager() {
    }

    public static boolean savePlayer(Player player, String fileName) {
        if (player == null) {
            System.out.println("Cannot save a null player.");
            return false;
        }

        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println("PLAYER" + SEPARATOR
                    + player.getCivilization().getName() + SEPARATOR
                    + player.getGamesPlayed() + SEPARATOR
                    + player.getWins() + SEPARATOR
                    + player.getLosses() + SEPARATOR
                    + player.getGold());

            System.out.println("Player statistics saved.");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error saving player: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteSave(String fileName) {
        try {
            File saveFile = new File(fileName);

            if (!saveFile.exists()) {
                System.out.println("Save file does not exist.");
                return false;
            }

            if (saveFile.delete()) {
                System.out.println("Save file deleted successfully.");
                return true;
            }

            System.out.println("Could not delete the save file.");
            return false;
        } catch (SecurityException e) {
            System.out.println("Error deleting save file: " + e.getMessage());
            return false;
        }
    }
}
