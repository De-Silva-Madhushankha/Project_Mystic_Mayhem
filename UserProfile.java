import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.LinkedHashSet;


public class UserProfile {
    private String name;
    private String userName;
    private int userID;
    private int goldCoins = 500;
    private int XP = 0;
    private static int  userCount ;

    private static Set<String> userNames = new LinkedHashSet<String>();

    UserProfile(String name, String userName){
        this.name = name;

        if(userNames.contains(userName)){
            System.out.println("Username is already taken. Please choose a different username. ");
        }
        else{
            this.userName = userName;
            this.userID = createUserID();
            userNames.add(userName);
        }
        userCount++;
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

}
