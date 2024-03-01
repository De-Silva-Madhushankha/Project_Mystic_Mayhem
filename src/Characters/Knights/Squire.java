package Characters.Knights;

public class Squire extends Knight {
    private int price = 85;
    private int attackPoint = 8;
    private int defencePoint = 9;
    private int health = 7;
    private int speed = 8;

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

    public int getAttackPoint() {return attackPoint;}
    public void setAttackPoint(int attackPoint) {this.attackPoint = attackPoint;}

    public int getDefencePoint() {return defencePoint;}
    public void setDefencePoint(int defencePoint) {this.defencePoint = defencePoint;}

    public int getHealth() {return health;}
    public void setHealth(int health) {this.health = health;}

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}
}
