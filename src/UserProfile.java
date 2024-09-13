import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import Characters.Character;
import Equipment.Equipment;

import Characters.Archers.*;
import Characters.Healers.*;
import Characters.Knights.*;
import Characters.Mages.*;
import Characters.MythicalCreatures.*;

import Equipment.Armour.*;
import Equipment.Artefacts.*;


public class UserProfile implements Serializable {
    private String name;
    private final String userName;
    private final int userID;
    private double goldCoins;
    private int XP ;
    private static int  userCount ;
    public List<Character> guild = new ArrayList<>();
    private HashMap<String, String> armyDetails = new HashMap<>();
    private final String[] equipments ={"Chainmail","Regalia","Fleece","Excalibur", "Amulet","Crystal"};

    private final String[] heroIndex ={"Shooter","Ranger","Sunfire","Zing","Saggitarius","Squire","Cavalier","Templar","Zoro","Swiftblade","Warlock","Illusionist","Enchanter","Conjurer","Eldritch","Soother","Medic","Alchemist","Saint","Lightbringer","Dragon","Basilisk","Hydra","Phoenix","Pegasus"};
    private String homeground;

    private final HashMap<String,Integer> minGoldCoins = new HashMap<>();

    UserProfile(String name, String userName){
        this.name = name;
        this.goldCoins = 500;
        this.XP = 0;
        this.userName = userName;
        this.userID = createUserID();
        userCount++;
        minGoldCoins.put("Archer",80);
        minGoldCoins.put("Knight",85);
        minGoldCoins.put("Mage",100);
        minGoldCoins.put("Healer",95);
        minGoldCoins.put("MythicalCreature",120);


    }
    private int createUserID(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
        String date = ft.format(dNow);
        String ID = date + userCount;
        return Integer.parseInt(ID);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("Name was changed successfully.");
    }

    public String getUserName() {
        return userName;
    }

    public int getXP() {
        return XP;
    }
    public void setXP(int XP) {
        this.XP = XP;
    }
    public double getGoldCoins() {
        return goldCoins;
    }
    public void setGoldCoins(double goldCoins) {
        this.goldCoins = goldCoins;
    }
    public String getHomeground() {
        return homeground;
    }
    public void setHomeground(String homeground) {
        this.homeground = homeground;
    }

    public int getGuildSize(){return guild.size();}

    private Character trainHero(String heroName){
        return switch (heroName) {
            case "Ranger" -> new Ranger();
            case "Saggitarius" -> new Saggitarius();
            case "Shooter" -> new Shooter();
            case "Sunfire" -> new Sunfire();
            case "Zing" -> new Zing();
            case "Alchemist" -> new Alchemist();
            case "Lightbringer" -> new Lightbringer();
            case "Medic" -> new Medic();
            case "Saint" -> new Saint();
            case "Soother" -> new Soother();
            case "Cavalier" -> new Cavalier();
            case "Squire" -> new Squire();
            case "Swiftblade" -> new Swiftblade();
            case "Templar" -> new Templar();
            case "Zoro" -> new Zoro();
            case "Conjurer" -> new Conjurer();
            case "Eldritch" -> new Eldritch();
            case "Enchanter" -> new Enchanter();
            case "Illusionist" -> new Illusionist();
            case "Warlock" -> new Warlock();
            case "Basilisk" -> new Basilisk();
            case "Dragon" -> new Dragon();
            case "Hydra" -> new Hydra();
            case "Pegasus" -> new Pegasus();
            case "Phoenix" -> new Phoenix();
            default -> null;
        };
    }
    private Character trainHero(int heroIndex){
        return switch (heroIndex) {
            case 1 -> new Shooter();
            case 2 -> new Ranger();
            case 3 -> new Sunfire();
            case 4 -> new Zing();
            case 5 -> new Saggitarius();
            case 6 -> new Squire();
            case 7 -> new Cavalier();
            case 8 -> new Templar();
            case 9 -> new Zoro();
            case 10 -> new Swiftblade();
            case 11 -> new Warlock();
            case 12 -> new Illusionist();
            case 13-> new Enchanter();
            case 14 -> new Conjurer();
            case 15 -> new Eldritch();
            case 16 ->new Soother();
            case 17 -> new Medic();
            case 18-> new Alchemist();
            case 19 -> new Saint();
            case 20 -> new Lightbringer();
            case 21 -> new Dragon();
            case 22 -> new Basilisk();
            case 23 -> new Hydra();
            case 24-> new Phoenix();
            case 25-> new Pegasus();
            default -> null;
        };
    }
    public int addHero(int characterNumber) {

        Character hero = trainHero(characterNumber);
        String heroType = hero.getClass().getSuperclass().getSimpleName();
        String heroName = hero.getClass().getSimpleName();
        assert hero != null;
        if( this.getGoldCoins() >= hero.getPrice()){
            if(armyDetails.containsKey(heroType)){
                System.out.printf("You already have a hero of %s type.\nYou can't have heros of same type.\nplease select a different hero.\n",heroType);
                return -1;
            }else{
                this.goldCoins -= hero.getPrice();
                armyDetails.put(heroType,heroName);
                guild.add(hero);
                System.out.printf("%s added to your guild successfully.\n",hero.getClass().getSimpleName());
                return 1;
            }
        }else {
            System.out.println("You don't have enough gold coins to buy this hero.");
            return 0;
        }
    }

    public int addHero(String characterName) {

        Character hero = trainHero(characterName);
        String heroType = hero.getClass().getSuperclass().getSimpleName();
        assert hero != null;
        if( this.getGoldCoins() >= hero.getPrice()){
            if(armyDetails.containsKey(heroType)){
                System.out.printf("You already have a hero of %s type.\n You can't have heros of same type.\nplease select a different hero.",characterName);
                System.out.println("You have to have exactly one character from each of the categories Archer, Knight, Mage, Healer, and Mythical Creature.");
                return 0;
            }else{
                this.goldCoins -= hero.getPrice();
                armyDetails.put(heroType,characterName);
                guild.add(hero);
                System.out.printf("%s added to your guild successfully.\n",characterName);
                return 1;
            }
        }else {
            System.out.println("You don't have enough gold coins to buy this hero.");
            return -1;
        }
    }
    public int buyCharacter() {
        Scanner scanner = new Scanner(System.in);
        if(guild.size() == 5){
            System.out.println("Your Guild is already full!");
            System.out.println("You can't buy a new hero when your army is full. please sell a hero if you want to buy a new hero.");
            System.out.print("Do you wish to sell a hero(Y/N)? ");
            String wannaSell = scanner.nextLine();

            if(wannaSell.equalsIgnoreCase("y")){
                int sellOutcome = sellCharacter();
                if(sellOutcome==0){
                    return 0;
                }
                else if(sellOutcome== -1){
                    return -1;
                }
                return buyCharacter();
            }
            else
                return 0;

        }
        else {
            boolean chance = true;
            while(chance){
                System.out.printf("Gold Coins: %s\n",getGoldCoins());
                System.out.print("Enter the number of the hero you wish to buy : ");
                int newHero = scanner.nextInt();
                if(newHero>0 && newHero<26){
                    chance=false;
                    if(goldCoins>=trainHero(newHero).getPrice())
                        if (addHero(newHero)==-1){
                            System.out.println("Do you want to buy a different hero(Y/N)? ");
                            if(scanner.nextLine().equalsIgnoreCase("Y"))
                                continue;
                            else
                                return 0;
                        }
                        else
                            return 0;
                    else{
                        System.out.println("You don't have enough gold coins to buy this hero yet");
                        System.out.println("Do you want to buy another hero(Y/N)? ");
                        if(scanner.nextLine().equalsIgnoreCase("y"))
                            return buyCharacter();
                        else
                            return 0;
                    }
                }
                else{
                    System.out.println("You have entered a wrong number.");
                    System.out.println("Please enter a valid hero number.");
                }
            }
        }
    return -1;
    }

    public int sellCharacter() {
        Scanner scanner = new Scanner(System.in);
        printArmy();
        System.out.print("Enter the name of the hero you wish to sell : ");
        String heroToBeSell = scanner.nextLine();
        int heroNumber = -1;
        for(int i=0;i<heroIndex.length;i++ ){
            if(heroToBeSell.equalsIgnoreCase(heroIndex[i])){
                heroNumber = i+1;
                break;
            }
        }
        while(heroNumber == -1){
            System.out.println("You have entered a invalid hero.");
            System.out.print("Do you wish to continue selling(Y/N)? ");
            if(scanner.nextLine().equalsIgnoreCase("Y")){
                System.out.print("Enter the name of the hero you wish to sell : ");
                heroToBeSell = scanner.nextLine();
                heroNumber = -1;
                for(int i=0;i<heroIndex.length;i++ ){
                    if(heroToBeSell.equalsIgnoreCase(heroIndex[i])){
                        heroNumber = i+1;
                        break;
                    }
                }
            }
            else
                return 0;

        }
        if(heroNumber!=-1){
            Character hero = trainHero(heroNumber);
            double gcAfterSell = goldCoins+hero.getPrice()*0.9;
            if(gcAfterSell > minGoldCoins.get(hero.getClass().getSuperclass().getSimpleName()) ){

                for (Character c:guild){
                    if(c.getClass().getSimpleName().equalsIgnoreCase(heroToBeSell)){
                        guild.remove(c);
                        armyDetails.remove(c.getClass().getSuperclass().getSimpleName());
                        this.goldCoins += (c.getPrice() * 0.9);
                        System.out.println(c.getClass().getSimpleName()+ " was sold.");
                        System.out.printf("Gold Coins: %s",getGoldCoins());
                        System.out.println();
                        printArmy();
                        return 1;
                        //break;
                    }
                }

            }
            else{
                System.out.printf("If you sell %S hero you won't have enough gold coins to buy a new hero",heroToBeSell);
                System.out.print("Would You like to sell a different hero(Y/N)? ");
                String choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("Y")){
                    return sellCharacter();
                }
                else{
                    return 0;
                }
            }

        }

    return -1;
    }

    private void printArmy(){
        System.out.print("Your Army : [ ");
        for(String character: armyDetails.keySet()){
            System.out.printf("%s:%s ",character,armyDetails.get(character));
        }
        System.out.print("]\n");
    }

    private Equipment craftEquipment(String equipmentName){
        return switch (equipmentName) {
            case "Chainmail" -> new Chainmail();
            case "Fleece" -> new Fleece();
            case "Regalia" -> new Regalia();
            case "Amulet" -> new Amulet();
            case "Crystal" -> new Crystal();
            case "Excalibur" -> new Excalibur();
            default -> null;
        };
    }

    private Equipment craftEquipment(int equipmentNumber){
        return switch (equipmentNumber) {
            case 1 -> new Chainmail();
            case 2 -> new Regalia();
            case 3 -> new Fleece();
            case 4 -> new Excalibur();
            case 5 -> new Amulet();
            case 6 -> new Crystal();
            default -> null;
        };
    }

    public void buyEquipment(String characterName, int equipmentNumber){

        Character hero = null;
        Equipment newEquipment = craftEquipment(equipmentNumber);
        String equipmentType = newEquipment.getClass().getSuperclass().getSimpleName();

        String equipmentName = equipments[equipmentNumber-1];

        for(Character c: guild){
            if(c.getClass().getSimpleName().equalsIgnoreCase(characterName)){
                hero = c;
                break;
            }
        }

        assert hero != null;
        if(this.goldCoins >= newEquipment.getPrice()){
            if(equipmentType.equalsIgnoreCase("Armour")) {
                if (hero.isArmour) {
                    System.out.println(hero.armourType + " is taken off by " + characterName);
                    Equipment oldArmour = craftEquipment(hero.armourType);
                    oldArmour.removeFrom(hero);
                    newEquipment.equipTo(hero);
                    System.out.println(equipmentName + " equipped by " + characterName);
                } else {
                    newEquipment.equipTo(hero);
                    System.out.println(equipmentName + " equipped by " + characterName);
                }
            } else if (equipmentType.equalsIgnoreCase("Artefact")){
                if(hero.isArtefact){
                    System.out.println(hero.artefactType + " is taken off by " + characterName);
                    Equipment oldArtefact = craftEquipment(hero.artefactType);
                    oldArtefact.removeFrom(hero);
                    newEquipment.equipTo(hero);
                    System.out.println(equipmentName + " equipped by " + characterName);
                } else {
                    newEquipment.equipTo(hero);
                    System.out.println(equipmentName + " equipped by " + characterName);
                }
            }
            this.goldCoins -= newEquipment.getPrice();
        }
        else{
            System.out.println("You don't have enough money to buy a "+equipmentName+".");
        }

    }

    public void printPlayerStatistics() {

        String details = String.format("""
                        Name: %s
                        XP: %d
                        Archer: %s
                        Knight: %s
                        Mage: %s
                        Healer: %s
                        Mythical Creature: %s
                        """,
                userName, XP,
                guild.get(0).getClass().getSimpleName(),
                guild.get(1).getClass().getSimpleName(),
                guild.get(2).getClass().getSimpleName(),
                guild.get(3).getClass().getSimpleName(),
                guild.get(4).getClass().getSimpleName()
        );
        System.out.println(details);
    }
}
