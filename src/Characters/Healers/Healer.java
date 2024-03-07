package Characters.Healers;
import Characters.Character;
import java.util.*;

public abstract class Healer extends Character {
    protected void heal(List<Character> ownArmy){
        double minHealth = ownArmy.getFirst().getHealth();
        Character characterToHeal = null;
        Character myhealer = null;
        for(Character c: ownArmy){
            if(minHealth >= c.getHealth() && c.getHealth() > 0){
                characterToHeal = c;
            }
            if(c.getClass().getSuperclass().getSimpleName() == "Healer"){
                myhealer = c;
            }
        }
        double healthIncrement = 0.1*myhealer.getAttackPoint();
        characterToHeal.setHealth(characterToHeal.getHealth()+healthIncrement);
    };
}
