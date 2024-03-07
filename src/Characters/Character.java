package Characters;
import java.util.List;

public abstract class Character {
    private double price;
    private double attackPoint;
    private double defencePoint;
    private double health;
    private double speed;
    private String characterType;

    public boolean isArmour = false;
    public boolean isArtefacts = false;

    public abstract double getPrice();
    public abstract void setPrice(double price);
    public abstract double getAttackPoint();
    public abstract void setAttackPoint(double attackPoint);
    public abstract double getDefencePoint();
    public abstract void setDefencePoint(double defencePoint);
    public abstract double getHealth();
    public abstract void setHealth(double health);
    public abstract double getSpeed();
    public abstract void setSpeed(double speed);
    public abstract void setHomeGround(String homeGround);
    public abstract String toString();
    public abstract void attack(List<Character> opponentArmy,List<Character> ownArmy);

    public static int getDefencePriority(Character c){
        String characterType = c.getClass().getSuperclass().getSimpleName();
        switch (characterType) {
            case "Healer" :
                return 1;
            case "MythicalCreature":
                return 2;
            case "Archer":
                return 3;
            case "Knight":
                return 4;
            case "Mage":
                return 5;
        }
        return 0;
    }
    public static int getAttackPriority(Character c){
        String characterType = c.getClass().getSuperclass().getSimpleName();
        switch (characterType) {
            case "Archer" :
                return 1;
            case "Knight":
                return 2;
            case "MythicalCreature":
                return 3;
            case "Mage":
                return 4;
            case "Healer":
                return 5;
        }
        return 0;
    }

}
