package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Loads civilization, wins, and gold from a save file.
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

            if (data.length < 4 || !data[0].equals("PLAYER")) {
                System.out.println("Invalid save file.");
                return null;
            }

            String civilizationName = data[1];
            int wins = Integer.parseInt(data[2]);
            int gold = Integer.parseInt(data[3]);

            // ID and name are not saved, so the loaded player receives defaults.
            Player player = new Player(
                    1,
                    "Loaded Player",
                    gold,
                    new Civilization(civilizationName));

            player.restoreCounters(wins, wins, 0);
            System.out.println("Civilization, wins, and gold loaded.");
            return player;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading player: save file was not found.");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Error loading player: wins or gold is invalid.");
            return null;
        }
    }
}
