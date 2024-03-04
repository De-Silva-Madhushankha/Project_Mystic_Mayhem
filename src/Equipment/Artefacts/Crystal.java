package Equipment.Artefacts;
import Characters.Character;

public class Crystal extends Artefact {
    private final int price = 210;
    public int getPrice() {
        return price;
    }

    public void equip(Character character) {
        character.isArmour = true;
        character.setAttackPoint(character.getAttackPoint()+2);
        character.setDefencePoint(character.getDefencePoint()+1);
        character.setHealth(character.getHealth()-1);
        character.setSpeed(character.getSpeed()-1);
        character.setPrice(character.getPrice() + 0.2*price);
    }

    public void remove(Character character) {
        character.isArmour = false;
        character.setAttackPoint(character.getAttackPoint()-2);
        character.setDefencePoint(character.getDefencePoint()-1);
        character.setHealth(character.getHealth()+1);
        character.setSpeed(character.getSpeed()+1);
        character.setPrice(character.getPrice() - 0.2*price);
    }
}
