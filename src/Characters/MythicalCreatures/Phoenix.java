package Characters.MythicalCreatures;

public class Phoenix extends MythicalCreature {
    private int price = 275;
    private int attackPoint = 17;
    private int defencePoint = 13;
    private int health = 17;
    private int speed = 19;

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getAttackPoint() {
        return attackPoint;
    }
    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefencePoint() {
        return defencePoint;
    }
    public void setDefencePoint(int defencePoint) {
        this.defencePoint = defencePoint;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
