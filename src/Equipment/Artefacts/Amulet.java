package Equipment.Artefacts;
import Characters.Character;

public class Amulet extends Artefact {
    private int price = 200;
    public int getPrice() {
        return price;
    }

    public void equipArtefact(Character character) {
        character.isArmour = true;
        character.setAttackPoint(character.getAttackPoint()+1);
        character.setDefencePoint(character.getDefencePoint()-1);
        character.setHealth(character.getHealth()+1);
        character.setSpeed(character.getSpeed()+1);
    }

    public void removeArtefact(Character character) {
        character.isArmour = false;
        character.setAttackPoint(character.getAttackPoint()-1);
        character.setDefencePoint(character.getDefencePoint()+1);
        character.setHealth(character.getHealth()-1);
        character.setSpeed(character.getSpeed()-1);
    }
}
