package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Loads civilization and player match statistics from a save file.
 */
public final class LoadManager {

    private static final char SEPARATOR = '~';

    private LoadManager() {
    }

    public static Player loadPlayer(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (!scanner.hasNextLine()) {
                System.out.println("Save file is empty.");
                return null;
            }

            String[] data = Helper.Tokenizer(scanner.nextLine(), SEPARATOR);

            if (data.length < 6 || !data[0].equals("PLAYER")) {
                System.out.println("Invalid save file.");
                return null;
            }

            String civilizationName = data[1];
            int gamesPlayed = Integer.parseInt(data[2]);
            int wins = Integer.parseInt(data[3]);
            int losses = Integer.parseInt(data[4]);
            int gold = Integer.parseInt(data[5]);

            // ID and name are not saved, so the loaded player receives defaults.
            Player player = new Player(
                    1,
                    "Loaded Player",
                    gold,
                    new Civilization(civilizationName));

            player.restoreCounters(gamesPlayed, wins, losses);
            System.out.println("Player statistics loaded.");
            return player;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading player: save file was not found.");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Error loading player: statistics or gold is invalid.");
            return null;
        }
    }
}
