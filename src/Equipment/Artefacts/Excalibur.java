package Equipment.Artefacts;
import Characters.Character;

public class Excalibur extends Artefact {
    private final int price = 150;
    public int getPrice() {
        return price;
    }

    public void equipTo(Character character) {
        character.isArmour = true;
        character.artefactType = "Excalibur";
        character.setAttackPoint(character.getAttackPoint()+2);
        character.setPrice(character.getPrice() + 0.2*price);
    }

    public void removeFrom(Character character) {
        character.isArmour = false;
        character.artefactType = null;
        character.setAttackPoint(character.getAttackPoint()-2);
        character.setPrice(character.getPrice() - 0.2*price);
    }
}
