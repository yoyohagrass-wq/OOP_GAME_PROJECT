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

    public static void savePlayer(Player player, String fileName)
            throws FileNotFoundException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println("PLAYER" + SEPARATOR
                    + player.getPlayerID() + SEPARATOR
                    + clean(player.getName()) + SEPARATOR
                    + player.getGold() + SEPARATOR
                    + clean(player.getCivilization().getName()) + SEPARATOR
                    + player.getWins());

            for (Unit unit : player.getArmy().getUnits()) {
                writer.println("UNIT" + SEPARATOR
                        + unit.getClass().getSimpleName() + SEPARATOR
                        + unit.getId() + SEPARATOR
                        + unit.getMaxHealth() + SEPARATOR
                        + unit.getHealth() + SEPARATOR
                        + unit.getDamage() + SEPARATOR
                        + getExtraValue(unit));
            }
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
