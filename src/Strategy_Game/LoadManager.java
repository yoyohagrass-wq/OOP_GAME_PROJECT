package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Loads civilization and player match statistics from a save file.
 */

public final class LoadManager {

    private LoadManager() {
    }

    public static Player loadPlayer(String fileName) {

        try (Scanner scanner = new Scanner(new File(fileName))) {

            if (!scanner.hasNextLine()) {
                return null;
            }

            String[] data = Helper.Tokenizer(scanner.nextLine(), '~');

            if (data.length != 8 || !data[0].equals("PLAYER")) {
                return null;
            }

            int id = Integer.parseInt(data[1]);
            String name = data[2];
            String civilizationName = data[3];
            int gamesPlayed = Integer.parseInt(data[4]);
            int wins = Integer.parseInt(data[5]);
            int losses = Integer.parseInt(data[6]);
            int gold = Integer.parseInt(data[7]);

            if (id <= 0 || name.isEmpty() || civilizationName.isEmpty()
                    || gold < 0 || gamesPlayed < 0 || wins < 0 || losses < 0
                    || wins + losses > gamesPlayed) {
                return null;
            }

            Player player = new Player(
                    id,
                    name,
                    gold,
                    new Civilization(civilizationName)
            );

            player.restoreCounters(gamesPlayed, wins, losses);

            return player;

        } catch (FileNotFoundException | NumberFormatException e) {
            return null;
        }
    }
}
