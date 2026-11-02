package Strategy_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Saves a Player and the units in the player's army as readable text.
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
                    + player.getPlayerID() + SEPARATOR
                    + clean(player.getName()) + SEPARATOR
                    + player.getGold() + SEPARATOR
                    + clean(player.getCivilization().getName()) + SEPARATOR
                    + player.getWins() + SEPARATOR
                    + player.getLosses() + SEPARATOR
                    + player.getGamesPlayed());

            for (Unit unit : player.getArmy().getUnits()) {
                writer.println("UNIT" + SEPARATOR
                        + unit.getClass().getSimpleName() + SEPARATOR
                        + unit.getId() + SEPARATOR
                        + unit.getMaxHealth() + SEPARATOR
                        + unit.getHealth() + SEPARATOR
                        + unit.getDamage() + SEPARATOR
                        + getExtraValue(unit));
            }

            System.out.println("Player saved successfully.");
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

    private static String clean(String value) {
        if (value == null) {
            return "";
        }
        return value.replace(SEPARATOR, ' ');
    }

    private static int getExtraValue(Unit unit) {
        if (unit instanceof Archer) {
            return ((Archer) unit).getRange();
        }
        if (unit instanceof Knight) {
            return ((Knight) unit).getArmor();
        }
        if (unit instanceof soldier) {
            return ((soldier) unit).getArmor();
        }
        if (unit instanceof Legionary) {
            return ((Legionary) unit).getArmor();
        }
        if (unit instanceof Chariot) {
            return ((Chariot) unit).CalcChargeDamage() - ((Chariot) unit).CalcDamage();
        }
        if (unit instanceof Crossbowman) {
            return ((Crossbowman) unit).getRange();
        }
        if (unit instanceof Longbowman) {
            return ((Longbowman) unit).getRange();
        }
        if (unit instanceof Guardian) {
            return ((Guardian) unit).getArmor();
        }
        return 0;
    }
}
