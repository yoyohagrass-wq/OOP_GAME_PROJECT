package Strategy_Game;

public class Crossbowman extends Unit {

    private int Range;
    private final Bow bow;

    public Crossbowman(int Health, int Damage, int id, int Range) {
        super(Health, Damage, id);
        this.Range = Range;
        this.bow = new Bow("Heavy Crossbow", 15);
    }

    public void setRange(int Range) {
        if (Range >= 0 && Range <= 100) {
            this.Range = Range;
        }
    }

    public int getRange() {
        return this.Range;
    }

    public Bow getBow() {
        return this.bow;
    }

    @Override
    public int CalcDamage() {
        return getDamage() + bow.getDamageBonus();
    }

    @Override
    public void attack() {
        System.out.println("Crossbowman attacks with " + CalcDamage() + " damage!");
    }
}
