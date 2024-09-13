package Equipment.Artefacts;
import Characters.Character;

public class Amulet extends Artefact {
    private final int price = 200;
    public int getPrice() {
        return price;
    }

    public void equipTo(Character character) {
        character.isArmour = true;
        character.artefactType = "Amulet";
        character.setAttackPoint(character.getAttackPoint()+1);
        character.setDefencePoint(character.getDefencePoint()-1);
        character.setHealth(character.getHealth()+1);
        character.setSpeed(character.getSpeed()+1);
        character.setPrice(character.getPrice() + 0.2*price);
    }

    public void removeFrom(Character character) {
        character.isArmour = false;
        character.artefactType = null;
        character.setAttackPoint(character.getAttackPoint()-1);
        character.setDefencePoint(character.getDefencePoint()+1);
        character.setHealth(character.getHealth()-1);
        character.setSpeed(character.getSpeed()-1);
        character.setPrice(character.getPrice() - 0.2*price);
    }
}
