import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private String userName;
    private int userID;

    private int coins = 500;
    private int XP;

    private int userCount;

    User(){
        userCount++;
        this.userID = createUserID();
    }

    private int createUserID(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
        String date = ft.format(dNow);
        String ID = date + userCount;
        return Integer.parseInt(ID);

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
