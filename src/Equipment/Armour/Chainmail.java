package Equipment.Armour;
import Equipment.Equipment;

public class Chainmail implements Armour, Equipment {
    private int price = 70;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
