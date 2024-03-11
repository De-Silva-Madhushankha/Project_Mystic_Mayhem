package Equipment;
import Characters.Character;
import java.io.Serializable;

public interface Equipment extends Serializable {
    public int getPrice();
    public abstract void equipTo(Character character);
    public abstract void removeFrom(Character character);
}
