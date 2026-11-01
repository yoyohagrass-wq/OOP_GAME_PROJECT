package Strategy_Game;

public class Spearman extends Unit {

    private final Spear spear;

    public Spearman(int Health, int Damage, int id) {
        super(Health, Damage, id);
        this.spear = new Spear("Iron Spear", 20);
    }

    public Spear getSpear() {
        return this.spear;
    }

    @Override
    public int CalcDamage() {
        return getDamage() + spear.getDamageBonus();
    }

    @Override
    public void attack() {
        System.out.println("Spearman attacks with " + CalcDamage() + " damage!");
    }
}
