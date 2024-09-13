package Characters.Healers;

import Characters.Character;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Saint extends Healer{

    private double defaultHealth = 17;
    private double price = 200;
    private double attackPoint = 16;
    private double defencePoint = 14;
    private double health = 17;
    private double speed = 9;

    private String characterType = "Mystic";

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

    public String getCharacterType() {
        return characterType;
    }

    public void setBattleGround(String homeGround) {
        switch (homeGround) {
            case "Hillcrest":
                break;
            case "Marshland":
                this.speed--;
                break;
            case "Desert":
                break;
            case "Arcane":
                this.attackPoint = this.attackPoint+2;
                break;
        }
    }

    public void resetBattleGround(String homeGround) {
        switch (homeGround) {
            case "Hillcrest":
                break;
            case "Marshland":
                this.speed++;
                break;
            case "Desert":
                break;
            case "Arcane":
                this.attackPoint = this.attackPoint-2;
                break;
        }
    }

    public void attack(List<Character> opponentArmy,List<Character> ownArmy) {
        opponentArmy = getDefencePriority(opponentArmy);

        Character opponent = null;
        double minDefencePoint = 50; //temp Value

        for(Character c : opponentArmy) {
            if(minDefencePoint >= c.getDefencePoint() && c.getHealth() > 0) {
                opponent = c;
                minDefencePoint = c.getDefencePoint();
            }
        }

        assert opponent != null;
        System.out.printf("%s%n",opponent.getClass().getSimpleName());

        if (opponent == null){
            System.out.println("All characters are dead!");
        } else {
            double damage = (0.5*this.attackPoint) - (0.1*opponent.getDefencePoint());
            opponent.setHealth(opponent.getHealth()-damage);
            if(opponent.getHealth() <= 0){
                System.out.println("->"+opponent.getClass().getSimpleName() + "'s health: 0");
                System.out.printf("->"+opponent.getClass().getSimpleName() + " died!%n");
            }else{
                System.out.printf("->"+opponent.getClass().getSimpleName()+"'s health: %.1f%n",opponent.getHealth());
            }
        }
        heal(ownArmy);
    }

    public void setDefaultHealth(){
        this.health = defaultHealth;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +" + "+ this.armourType +" + "+ this.artefactType;
    }
}
