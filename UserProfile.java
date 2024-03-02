import java.text.SimpleDateFormat;
import java.util.*;


public class UserProfile {
    private String name;
    private String userName;
    private int userID;
    private int goldCoins;
    private int XP ;
    private static int  userCount ;
    private static Set<String> userNames = new LinkedHashSet<String>();
    private List<Character> army;
    private Set<Class<?>> armyClasses;
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

    public int getGoldCoins() {
        return goldCoins;
    }

    public void setArmy(List<Character> army) {
         this.armyClasses = new HashSet<>();
        for (Character c : army) {
            Class<?> unitClass = c.getClass();
            if (!armyClasses.contains(unitClass)) {
                if(this.goldCoins >= c.price){
                    this.army.add(c);
                    armyClasses.add(unitClass);
                }
                else System.out.println("Not enough Gold Coins");

            } else {
                System.out.println("An instance of " + unitClass.getName() + " has already been added. Please Choose a different character from each category");
            }
        }
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setHomeground(String homeground) {
        this.homeground = homeground;
    }

    public void updateStatus(UserProfile opponent, boolean win) {
        int opGoldCoins = opponent.getGoldCoins();
        if(win){
            this.goldCoins += opGoldCoins * 0.1;
        }
        else {
            this.goldCoins -= this.goldCoins * 0.1;
        }
    }

    public void combat(UserProfile opponent) {
        // Combat logic here
    }

    public void buyCharacter(Character newCharacter) {
        if( this.goldCoins >= newCharacter.price){
            if(!armyClasses.contains(newCharacter.getClass()) ){
                army.add(newCharacter);
                armyClasses.add(newCharacter.getClass());
                this.goldCoins -= newCharacter.price;
            }
            else{
                System.out.println("You already have a character from that category. Please choose a character from a different category.");
            }

        }
        else{
            System.out.println("Not enough gold coins to buy the character");
        }
    }

    public void sellCharacter(Character oldCharacter) {
        this.goldCoins += (oldCharacter.price * 0.9);
        armyClasses.remove(oldCharacter.getClass());
        army.remove(oldCharacter);

    }

    public void buyEquipment(){

    }

    public void printUserDetails(){
        String details = String.format("""
                Name: %s
                Username: %s
                XP: %d
                Gold coins: %d
                Homeground: %d
                Archer: %s 
                Knight: %S
                Mage: %s
                Healer: %s
                Mythical Creature: %s
                """);
    }

}
