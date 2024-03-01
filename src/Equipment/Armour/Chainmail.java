package Equipment.Armour;
import Characters.Character;

public class Chainmail extends Armour {
    private int price = 70;
    public int getPrice() {
        return price;
    }

    public void equipArmour(Character character) {
        character.isArmour = true;
        character.setDefencePoint(character.getDefencePoint() + 1);
        character.setSpeed(character.getSpeed() - 1);
    }

    public void removeArmour(Character character) {
        character.isArmour = false;
        character.setDefencePoint(character.getDefencePoint() - 1);
        character.setSpeed(character.getSpeed() + 1);
    }
}
