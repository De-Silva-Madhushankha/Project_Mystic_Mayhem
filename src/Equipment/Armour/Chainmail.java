package Equipment.Armour;
import Characters.Character;

public class Chainmail extends Armour {
    private final int price = 70;
    public int getPrice() {
        return price;
    }

    public void equipTo(Character character) {
        character.isArmour = true;
        character.armourType = "Chainmail";
        character.setDefencePoint(character.getDefencePoint() + 1);
        character.setSpeed(character.getSpeed() - 1);
        character.setPrice(character.getPrice() + 0.2*price);
    }

    public void removeFrom(Character character) {
        character.isArmour = false;
        character.armourType = null;
        character.setDefencePoint(character.getDefencePoint() - 1);
        character.setSpeed(character.getSpeed() + 1);
        character.setPrice(character.getPrice() - 0.2*price);
    }
}
