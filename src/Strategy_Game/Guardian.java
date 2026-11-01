package Strategy_Game;

public class Guardian extends Unit {

    private int Armor;
    private final Sword sword;
    private final Shield shield;

    public Guardian(int Health, int Damage, int id, int Armor) {
        super(Health, Damage, id);
        setArmor(Armor);
        this.sword = new Sword("Guardian Sword", 10);
        this.shield = new Shield("Guardian Shield", 25);
    }

    public void setArmor(int Armor) {
        if (Armor >= 0 && Armor <= 100) {
            this.Armor = Armor;
        }
    }

    public int getArmor() {
        return this.Armor;
    }

    public Sword getSword() {
        return this.sword;
    }

    public Shield getShield() {
        return this.shield;
    }

    public int CalcArmor() {
        return getArmor() + shield.getArmorBonus();
    }

    @Override
    public int CalcDamage() {
        return getDamage() + sword.getDamageBonus();
    }

    @Override
    public void takeDamage(int damage) {
        if (damage > 0) {
            int actualDamage = damage - CalcArmor();

            if (actualDamage > 0) {
                super.takeDamage(actualDamage);
            }
        }
    }

    @Override
    public void attack() {
        System.out.println("Guardian attacks with " + CalcDamage() + " damage!");
    }
}
