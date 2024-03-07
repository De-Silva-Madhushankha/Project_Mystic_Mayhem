import Characters.Archers.*;
import Characters.Character;
import Equipment.Armour.*;
import Equipment.*;

public class Main {
    public static void main(String[] args) {
        UserProfile user01 = new UserProfile("Razor","Razor_0.1");
        user01.setHomeground("Arcane");

        Character shooter = new Shooter();
        Character ranger = new Ranger();

        System.out.println(shooter.getClass().getSuperclass().getSimpleName());

        Equipment fleece = new Fleece();
        System.out.println(fleece.getClass().getSuperclass().getInterfaces()[0].getSimpleName());

    }
}
