package Equipment;
import Characters.Character;

public interface Equipment {
    public int getPrice();
    public abstract void equip(Character character);
    public abstract void remove(Character character);
}
