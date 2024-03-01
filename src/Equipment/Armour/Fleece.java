package Equipment.Armour;
import Characters.Character;

public class Fleece extends Armour {
    private int price = 150;
    public int getPrice() {
        return price;
    }

    public void equipArmour(Character character) {
        character.isArmour = true;
        character.setDefencePoint(character.getDefencePoint()+2);
        character.setHealth(character.getHealth()+1);
        character.setSpeed(character.getSpeed()-1);
    }

    public void removeArmour(Character character) {
        character.isArmour = false;
        character.setDefencePoint(character.getDefencePoint()-2);
        character.setHealth(character.getHealth()-1);
        character.setSpeed(character.getSpeed()+1);
    }
}
