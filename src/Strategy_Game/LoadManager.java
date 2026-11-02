package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Loads the text format written by SaveManager and rebuilds a Player object.
 */
public final class LoadManager {

    private static final char SEPARATOR = '~';

    private LoadManager() {
    }

    public static Player loadPlayer(String fileName)
            throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("Save file is empty");
            }

            String[] playerData = Helper.Tokenizer(scanner.nextLine(), SEPARATOR);
            requireFields(playerData, 5, "PLAYER");
            if (!playerData[0].equals("PLAYER")) {
                throw new IllegalArgumentException("Save file must start with PLAYER");
            }

            Player player = new Player(
                    Integer.parseInt(playerData[1]),
                    playerData[2],
                    Integer.parseInt(playerData[3]),
                    new Civilization(playerData[4]));

            // Read the current counter format.
            if (playerData.length >= 8) {
                player.restoreCounters(
                        Integer.parseInt(playerData[7]),
                        Integer.parseInt(playerData[5]),
                        Integer.parseInt(playerData[6]));
            // Older save files only contained wins.
            } else if (playerData.length >= 6) {
                int oldWins = Integer.parseInt(playerData[5]);
                player.restoreCounters(oldWins, oldWins, 0);
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }

                String[] unitData = Helper.Tokenizer(line, SEPARATOR);
                requireFields(unitData, 7, "UNIT");
                if (!unitData[0].equals("UNIT")) {
                    throw new IllegalArgumentException("Unknown save record: " + unitData[0]);
                }

                Unit unit = createUnit(unitData);
                player.getArmy().addUnit(unit);
            }

            return player;
        }
    }

    private static Unit createUnit(String[] data) {
        String type = data[1];
        int id = Integer.parseInt(data[2]);
        int maxHealth = Integer.parseInt(data[3]);
        int health = Integer.parseInt(data[4]);
        int damage = Integer.parseInt(data[5]);
        int extra = Integer.parseInt(data[6]);

        Unit unit;

        switch (type) {
            case "Archer" -> unit = new Archer(maxHealth, damage, id, extra);
            case "Knight" -> unit = new Knight(maxHealth, damage, id, extra);
            case "Cavalry" -> unit = new Cavalry(maxHealth, damage, id);
            case "soldier" -> unit = new soldier(maxHealth, damage, id, extra);
            case "Legionary" -> unit = new Legionary(maxHealth, damage, id, extra);
            case "Berserker" -> unit = new Berserker(maxHealth, damage, id);
            case "Chariot" -> unit = new Chariot(maxHealth, damage, id, extra);
            case "Spearman" -> unit = new Spearman(maxHealth, damage, id);
            case "Crossbowman" -> unit = new Crossbowman(maxHealth, damage, id, extra);
            case "Longbowman" -> unit = new Longbowman(maxHealth, damage, id, extra);
            case "Guardian" -> unit = new Guardian(maxHealth, damage, id, extra);
            default -> throw new IllegalArgumentException("Unknown unit type: " + type);
        }

        unit.restoreState(health, damage);
        return unit;
    }

    private static void requireFields(String[] data, int requiredFields, String recordType) {
        if (data.length < requiredFields) {
            throw new IllegalArgumentException(recordType + " record is incomplete");
        }
    }
}
