import java.text.SimpleDateFormat;
import java.util.*;


public class UserProfile {
    private String name;
    private String userName;
    private int userID;
    private int goldCoins;
    private int XP ;
    private static int  userCount ;
    private static Set<String> userNames = new LinkedHashSeT<String>();
    private List<Character> army;
    private String homeground;

    UserProfile(String name, String userName){
        this.name = name;
        this.goldCoins = 500;
        this.XP = 0;

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

    public void setArmy(List<Character> army) {
        Set<Class<?>> armyClasses = new HashSet<>();
        for (Character c : army) {
            Class<?> unitClass = c.getClass();
            if (!armyClasses.contains(unitClass)) {
                this.army.add(c);
                armyClasses.add(unitClass);
            } else {
                System.out.println("An instance of " + unitClass.getName() + " has already been added. Please Choose a different character from each category");
            }
        }
    }
    public void setName(String name) {
        this.name = name;
    }

    public void earnCoins(int coins) {
        this.goldCoins += coins;
    }

    public void combat(Player opponent) {
        // Combat logic here
    }

    public void buyCharacter(Character newCharacter) {
        // Buy character logic here
    }

    public void sellCharacter(String category) {
        // Sell character logic here
    }

}
