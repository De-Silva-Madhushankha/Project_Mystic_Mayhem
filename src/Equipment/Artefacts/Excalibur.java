package Equipment.Artefacts;
import Characters.Character;

public class Excalibur extends Artefact {
    private int price = 150;
    public int getPrice() {
        return price;
    }

    public void equipArtefact(Character character) {
        character.isArmour = true;
        character.setAttackPoint(character.getAttackPoint()+2);
    }

    public void removeArtefact(Character character) {
        character.isArmour = false;
        character.setAttackPoint(character.getAttackPoint()-2);
    }
}
