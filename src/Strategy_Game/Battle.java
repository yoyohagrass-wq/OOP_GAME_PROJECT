package Strategy_Game;

public class Battle {

    private Army battleArmy1;
    private Army battleArmy2;
    private int currentTurn = 1;
    private final CombatSystem combat;

    public Battle(Army battleArmy1, Army battleArmy2) {
        this.battleArmy1 = battleArmy1;
        this.battleArmy2 = battleArmy2;
        this.combat = new CombatSystem();

        if (battleArmy1 != null && battleArmy2 != null) {
            this.combat.startBattle(battleArmy1, battleArmy2);
        }
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    public CombatSystem getCombatSystem() {
        return this.combat;
    }

    public void executeTurn() {
        Army attackingArmy = currentTurn == 1 ? battleArmy1 : battleArmy2;
        Army defendingArmy = currentTurn == 1 ? battleArmy2 : battleArmy1;

        if (attackingArmy == null || defendingArmy == null) {
            return;
        }

        for (Unit attacker : attackingArmy.getUnits()) {
            if (attacker.isDestroyed()) {
                continue;
            }

            for (Unit target : defendingArmy.getUnits()) {
                if (!target.isDestroyed()) {
                    combat.performAttack(attacker, target);
                    break;
                }
            }
        }

        if (!defendingArmy.isDefeated()) {
            switchTurn();
        }
    }

    public void switchTurn() {
        if (currentTurn == 1) {
            currentTurn = 2;
        } else {
            currentTurn = 1;
        }
    }

    public boolean takeTurn(Unit attacker, Unit target) {
        return takeTurn(attacker, target, false);
    }

    public boolean takeTurn(Chariot attacker, Unit target, boolean useCharge) {
        return takeTurn((Unit) attacker, target, useCharge);
    }

    public boolean takeTurn(Unit attacker, Unit target, boolean useCharge) {
        Army attackingArmy = currentTurn == 1 ? battleArmy1 : battleArmy2;
        Army defendingArmy = currentTurn == 1 ? battleArmy2 : battleArmy1;

        if (attacker == null || target == null
                || attackingArmy == null || defendingArmy == null
                || attackingArmy.isDefeated() || defendingArmy.isDefeated()
                || !attackingArmy.getUnits().contains(attacker)
                || !defendingArmy.getUnits().contains(target)
                || attacker.isDestroyed() || target.isDestroyed()
                || getWinner() != null) {
            return false;
        }

        if (useCharge) {
            if (!(attacker instanceof Chariot)
                    || !combat.performChargeAttack((Chariot) attacker, target)) {
                return false;
            }
        } else {
            combat.performAttack(attacker, target);
        }

        if (!defendingArmy.isDefeated()) {
            switchTurn();
        }

        return true;
    }

    public Army getWinner() {
        return combat.getWinner(battleArmy1, battleArmy2);
    }
}
