package Equipment.Artefacts;
import Characters.Character;

public class Excalibur extends Artefact {
    private final int price = 150;
    public int getPrice() {
        return price;
    }

    public void equip(Character character) {
        character.isArmour = true;
        character.setAttackPoint(character.getAttackPoint()+2);
        character.setPrice(character.getPrice() + 0.2*price);
    }

    public void remove(Character character) {
        character.isArmour = false;
        character.setAttackPoint(character.getAttackPoint()-2);
        character.setPrice(character.getPrice() - 0.2*price);
    }
}
