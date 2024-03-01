package Characters.Archers;

public class Shooter extends Archer{
     private int price = 80;
     private int attackPoint = 11;
     private int defencePoint = 4;
     private int health = 6;
     private int speed = 9;

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
