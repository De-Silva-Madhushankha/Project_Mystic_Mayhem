package Characters.Archers;
import Characters.Character;
import java.util.*;

public class Zing extends Archer{
    private double price = 200;
    private double attackPoint = 16;
    private double defencePoint = 9;
    private double health = 11;
    private double speed = 14;
    private String characterType = "Sunchildren";

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getAttackPoint() {
        return attackPoint;
    }
    public void setAttackPoint(double attackPoint) {
        this.attackPoint = attackPoint;
    }

    public double getDefencePoint() {
        return defencePoint;
    }
    public void setDefencePoint(double defencePoint) {
        this.defencePoint = defencePoint;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setHomeGround(String homeGround) {
    }

    public void attack(List<Character> opponentArmy) {
        PriorityQueue<Character> defenceOrder = new PriorityQueue<>(Comparator.comparing(Character::getDefencePriority));
        for(Character c: opponentArmy){
            defenceOrder.add(c);
        }

        Character opponent = null;
        double minDefencePoint = 50; //temp Value

        for(Character c : defenceOrder) {
            if(minDefencePoint >= c.getDefencePoint() && c.getHealth() > 0) {
                opponent = c;
                minDefencePoint = c.getDefencePoint();
            }
        }

        if (opponent == null){
            System.out.println("All characters are dead!");
        } else {
            double damage = (0.5*attackPoint) - (0.1*opponent.getDefencePoint());
            opponent.setHealth(opponent.getHealth()-damage);
        }
    }

    @Override
    public String toString() {
        return "Character[Name: "+ this.getClass().getSimpleName() +",Type: "+ this.characterType +
                ",Price: "+ this.price + ",Health: "+ this.health +",Armour: "+ this.isArmour +
                ",Artefacts: "+ this.isArtefacts +" ]";
    }
}
