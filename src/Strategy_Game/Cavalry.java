package Strategy_Game;

public class Cavalry extends Unit {

    private final Horse horse;

    public Cavalry(int Health, int Damage, int id) {
        super(Health, Damage, id);
        this.horse = new Horse(50);
    }

    @Override
    public int CalcDamage() {
        return getDamage();
    }

    public Horse getHorse() {
        return this.horse;
    }

    @Override
    public void attack() {
        System.out.println("Cavalry attacks with " + CalcDamage() + " damage!");
    }
}
