package Characters;
public abstract class Character {
    public boolean isArmour = false;
    public boolean isArtefacts = false;
    public abstract int getPrice();
    public abstract void setPrice(int price);
    public abstract int getAttackPoint();
    public abstract void setAttackPoint(int attackPoint);
    public abstract int getDefencePoint();
    public abstract void setDefencePoint(int defencePoint);
    public abstract int getHealth();
    public abstract void setHealth(int health);
    public abstract int getSpeed();
    public abstract void setSpeed(int speed);
}
