package Characters.Mages;

import Characters.Character;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Warlock extends Mage{

    private double defaultHealth = 10;
    private double price = 100;
    private double attackPoint = 12;
    private double defencePoint = 7;
    private double health = 10;
    private double speed = 12;

    public String characterType = "Marshlander";

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
            case "Hillcrest" -> this.speed--;
            case "Marshland" -> this.defencePoint = this.defencePoint + 2;
            case "Desert" -> this.health--;
            case "Arcane" -> {
                this.speed--;
                this.defencePoint--;
            }
        }
    }

    public void resetBattleGround(String homeGround) {
        switch (homeGround) {
            case "Hillcrest" -> this.speed++;
            case "Marshland" -> this.defencePoint = this.defencePoint - 2;
            case "Desert" -> this.health++;
            case "Arcane" -> {
                this.speed++;
                this.defencePoint++;
            }
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
    }

    public void setDefaultHealth(){
        this.health = defaultHealth;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +" + "+ this.armourType +" + "+ this.artefactType;
    }
}
