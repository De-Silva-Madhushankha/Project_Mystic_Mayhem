package Equipment;
import Characters.Character;

public interface Equipment {
    public int getPrice();
    public abstract void equipTo(Character character);
    public abstract void removeFrom(Character character);
}
