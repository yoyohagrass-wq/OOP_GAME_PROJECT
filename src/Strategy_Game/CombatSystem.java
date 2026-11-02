package Strategy_Game;

public class CombatSystem {

    public void performAttack(Unit attacker, Damageable target) {

        if (attacker == null || target == null) {
            return;
        }

        if (attacker.isDestroyed() || target.isDestroyed()) {
            return;
        }

        target.takeDamage(attacker.CalcDamage());
    }

    public boolean performChargeAttack(Chariot attacker, Damageable target) {

        if (attacker == null || target == null) {
            return false;
        }

        if (attacker.isDestroyed() || target.isDestroyed()) {
            return false;
        }

        int damage = attacker.chargeAttack();

        if (damage <= 0) {
            return false;
        }

        target.takeDamage(damage);

        return true;
    }

    public void startBattle(Army army1, Army army2) {

        for (Unit unit : army1.getUnits()) {

            if (unit instanceof Chariot) {
                Chariot chariot = (Chariot) unit;
                chariot.resetCharge();
            }
        }

        for (Unit unit : army2.getUnits()) {

            if (unit instanceof Chariot) {
                Chariot chariot = (Chariot) unit;
                chariot.resetCharge();
            }
        }
    }

    public Army getWinner(Army army1, Army army2) {

        if (army1.isDefeated() && !army2.isDefeated()) {
            return army2;
        }

        else if (army2.isDefeated() && !army1.isDefeated()) {
            return army1;
        }

        return null;
    }
}