package Equipment.Armour;
import Characters.Character;

public class Chainmail extends Armour {
    private final int price = 70;
    public int getPrice() {
        return price;
    }

    public void equip(Character character) {
        character.isArmour = true;
        character.setDefencePoint(character.getDefencePoint() + 1);
        character.setSpeed(character.getSpeed() - 1);
        character.setPrice(character.getPrice() + 0.2*price);
    }

    public void remove(Character character) {
        character.isArmour = false;
        character.setDefencePoint(character.getDefencePoint() - 1);
        character.setSpeed(character.getSpeed() + 1);
        character.setPrice(character.getPrice() - 0.2*price);
    }
}
