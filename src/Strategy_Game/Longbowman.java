package Strategy_Game;

public class Longbowman extends Unit {

    private int Range;
    private final Bow bow;

    public Longbowman(int Health, int Damage, int id, int Range) {
        super(Health, Damage, id);
        this.Range = Range;
        this.bow = new Bow("Longbow", 20);
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
        System.out.println("Longbowman attacks with " + CalcDamage() + " damage!");
    }
}
