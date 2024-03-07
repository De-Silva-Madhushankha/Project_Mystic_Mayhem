import java.text.SimpleDateFormat;
import java.util.*;
import Characters.Character;
import Equipment.Equipment;

public class UserProfile {
    private String name;
    private String userName;
    private int userID;
    private int goldCoins;
    private int XP ;
    private static int  userCount ;
    private List<Character> guild = new arrayList();
//    private Set<String> armyTypes = new HashSet<>();

    HashMap<String, String> armyDetails = new HashMap<>();

    private String homeground;


    UserProfile(String name, String userName){
        this.name = name;
        this.goldCoins = 500;
        this.XP = 0;
        this.userName = userName;
        this.userID = createUserID();
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
    public int getGoldCoins() {
        return goldCoins;
    }

    private Character trainHero(String heroName){
        Character hero = null;
        switch(heroName){

            case "Ranger":
                hero = new Ranger();
                break;
            case "Saggitarius":
                hero = new Saggitarius();
                break;
            case "Shooter":
                hero = new Shooter();
                break;
            case "Sunfire":
                hero = new Sunfire();
                break;
            case "Zing":
                hero = new Zing();
                break;
            case "Alchemist":
                hero = new Alchemist();
                break;
            case "Lightbringer":
                hero = new Lightbringer();
                break;
            case "Medic":
                hero = new Medic();
                break;
            case "Saint":
                hero = new Saint();
                break;
            case "Soother":
                hero = new Soother();
                break;
            case "Cavalier":
                hero = new Cavalier();
                break;
            case "Squire":
                hero = new Squire();
                break;
            case "Swiftblade":
                hero = new Swiftblade();
                break;
            case "Templar":
                hero = new Templar();
                break;
            case "Zoro":
                hero = new Zoro();
                break;
            case "Conjurer":
                hero = new Conjurer();
                break;
            case "Eldritch":
                hero = new Eldritch();
                break;
            case "Enchanter":
                hero = new Enchanter();
                break;
            case "Illusionist":
                hero = new Illusionist();
                break;
            case "Warlock":
                hero = new Warlock();
                break;
            case "Basilisk":
                hero = new Basilisk();
                break;
            case "Dragon":
                hero = new Dragon();
                break;
            case "Hydra":
                hero = new Hydra();
                break;
            case "Pegasus":
                hero = new Pegasus();
                break;
            case "Phoenix":
                hero = new Phoenix();
                break;
        }

        return hero;

    }

    public int addHero(String characterName) {

        Character hero = trainHero(characterName);
        String heroType = hero.getClass().getSuperClass().getSimpleClass().getSimpleName();
        assert hero != null;
        if( this.getGoldCoins() >= hero.getPrice()){
            if(armyDetails.containsKey(heroType)){
                return 0;
            }else{
                this.goldCoins -= hero.getPrice();
                armyDetails.put(heroType,characterName);
                guild.add(hero);
                return 1;
            }
        }else {
            return -1;
        }
    }

    public void setHomeground(String homeground) {
        this.homeground = homeground;
    }

    public void updateStatus(UserProfile opponent, String battleOutcome) {
        int opGoldCoins = opponent.getGoldCoins();

        switch(battleOutcome){
            case "victory":
                this.goldCoins += opGoldCoins * 0.1;
                break;
            case "defeat":
                this.goldCoins -= this.goldCoins * 0.1;
                break;
            case "draw":
                break;
        }
    }


    public void buyCharacter(String characterName) {
        Scanner scanner = new Scanner(System.in);
        if(army.size() == 5){
            System.out.println("Your Army is already full!");
            System.out.print("Do You wish to sell a hero(Y/N)? ");
            String wannaSell = scanner.nextLine();

            if(wannaSell.compareToIgnoreCase("y")){
                System.out.println(String.format("Your Army :- %s %s %s %s %s",army.get("Archer"), army.get("Healer"), army.get("Knight"), army.get("Mage"), army.get("MythicalCreature")));
                System.out.print("Select a hero to sell : ");
                String heroToBeSell = scanner.nextLine();
                Character  sellHero = trainHero(sellHero);
                sellCharacter(sellHero);
            }

            else{
                System.out.println("Your Army is already full!");
            }
        }
        else
            addHero(characterName);
    }

    public void sellCharacter(String characterName) {
        Character oldCharacter = trainHero(characterName);
        this.goldCoins += (oldCharacter.getPrice() * 0.9);
        String characterType = oldCharacter.getClass().getSuperClass().getSimpleClass().getSimpleName();
        System.out.println(characterType + "was sold.");
        armyTypes.remove(characterType)
        army.remove(characterName);

    }

    public void buyEquipment(String characterName, String equipment){

        Character hero = trainHero(characterName);
        Equipment equipment = null;
        String equipmentType = null;

        switch(equipment){
            case "Chainmail":
                equipment = new Chainmail();
                equipmentType = "Armour";
                break;
            case "Fleece":
                equipment = new Fleece();
                equipmentType = "Armour";
                break;
            case "Regalia":
                equipment = new Regalia();
                equipmentType = "Armour";
                break;
            case "Amulet":
                equipment = new Amulet();
                equipmentType = "Artefacts";
                break;
            case "Crystal":
                equipment = new Crystal();
                equipmentType = "Artefacts";
                break;
            case "Excalibur":
                equipment = new Excalibur();
                equipmentType = "Artefacts";
                break;
        }

        if(this.goldCoins >= equipment.getPrice()){

            if(equipmentType == "Armour" && hero.isArmour()){
                equipment.removeFrom(hero);
                equipment.equipTo(hero);
            }
            else {
                equipment.equipTo(hero);
            }

            if(equipmentType == "Artefact" && hero.isArtefact()){
                equipment.removeFrom(hero);
                equipment.equipTo(hero);
            }
            else {
                equipment.equipTo(hero);
            }

            this.goldCoins -= equipment.getPrice();

        }

    }

    public void printPlayerStatistics() {

        //Hero Name + Equipments is yet to add

            String details = String.format("Name: %s\n" +
                    "Username: %s\n" +
                    "XP: %d\n" +
                    "Gold coins: %d\n" +
                    "Homeground: %d\n" +
                    "Archer: %s\n" +
                    "Knight: %S\n" +
                    "Mage: %s\n" +
                    "Healer: %s\n" +
                    "Mythical Creature: %s\n", name, userName, XP, goldCoins, homeground, );

    }
//    @Override
//    public String toString() {
//        return "User [Name:"+name+" ,Username:"+userName+" ,XP:"+XP+" ,Gold Coin:"+goldCoins+" ,Home ground:"+homeground+" ]";
//    }
}

//    public void combat(UserProfile opponent) {
//        // Combat logic here
//    }
