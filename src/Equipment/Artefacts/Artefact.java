package Equipment.Artefacts;
import Characters.Character;
import Equipment.Equipment;

public abstract class Artefact implements Equipment {
    public abstract void equipArtefact(Character character);
    public abstract void removeArtefact(Character character);
}
