package Characters;
import java.io.Serializable;
import java.util.*;

public abstract class Character implements Serializable {

    private double defaultHealth;
    private double price;
    private double attackPoint;
    private double defencePoint;
    private double health;
    private double speed;
    private String characterType;
    public String armourType = "-";
    public String artefactType = "-";
    public boolean isArmour = false;
    public boolean isArtefact = false;

    protected Character() {
    }

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
    public abstract String getCharacterType();
    public abstract void setBattleGround(String homeGround);
    public abstract void resetBattleGround(String homeGround);

    public abstract void setDefaultHealth();
    public abstract String toString();
    public abstract void attack(List<Character> opponentArmy, List<Character> ownArmy);

    public static List<Character> getDefencePriority(List<Character> guild){
        List<Character> newGuild = new ArrayList<>(Collections.nCopies(5, null));
        for (Character c : guild) {
            switch (c.getClass().getSuperclass().getSimpleName()){
                case "Mage" -> newGuild.set(0,c);
                case "Knight" -> newGuild.set(1,c);
                case "Archer" -> newGuild.set(2,c);
                case "MythicalCreature" -> newGuild.set(3,c);
                case "Healer" -> newGuild.set(4,c);
            }
        }
        return newGuild;
    }
}
