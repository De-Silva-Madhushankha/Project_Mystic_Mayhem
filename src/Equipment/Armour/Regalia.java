package Equipment.Armour;
import Characters.Character;

public class Regalia extends Armour {
    private int price = 105;
    public int getPrice() {
        return price;
    }

    public void equipArmour(Character character) {
        character.isArmour = true;
        character.setDefencePoint(character.getDefencePoint()+1);
    }

    public void removeArmour(Character character) {
        character.isArmour = false;
        character.setDefencePoint(character.getDefencePoint()-1);
    }
}
