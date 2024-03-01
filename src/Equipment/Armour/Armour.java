package Equipment.Armour;
import Characters.Character;
import Equipment.Equipment;

public abstract class Armour implements Equipment {

    public abstract void equipArmour(Character character);
    public abstract void removeArmour(Character character);
}
