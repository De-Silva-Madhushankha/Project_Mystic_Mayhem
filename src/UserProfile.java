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

public class UserProfile {
    private String name;
    private String userName;
    private int userID;
    private int goldCoins;
    private int XP ;
    private static int  userCount ;
    private List<Character> guild = new ArrayList<>();
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
    public int addHero(String characterName) {

        Character hero = trainHero(characterName);
        String heroType = hero.getClass().getSuperclass().getSimpleName();
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
        if(guild.size() == 5){
            System.out.println("Your Army is already full!");
            System.out.print("Do you wish to sell a hero(Y/N)? ");
            String wannaSell = scanner.nextLine();
            if(wannaSell.equalsIgnoreCase("y")){
                System.out.printf("Your Army [ Archer:%s Healer:%s Knight:%s Mage:%s MythicalCreature:%s ]%n",
                        armyDetails.get("Archer"),
                        armyDetails.get("Healer"),
                        armyDetails.get("Knight"),
                        armyDetails.get("Mage"),
                        armyDetails.get("MythicalCreature"));
                System.out.print("Select a hero to sell : ");
                String heroToBeSell = scanner.nextLine();
                sellCharacter(heroToBeSell);
            }
            else{
                System.out.println("Your Army is already full!");
            }
        }
        else
            addHero(characterName);
    }

    public void sellCharacter(String heroToBeSell) {
        for (Character c:guild){
            if(c.getClass().getSimpleName() == heroToBeSell){
                guild.remove(c);
                armyDetails.remove(c.getClass().getSuperclass().getSimpleName());
                this.goldCoins += (c.getPrice() * 0.9);
                System.out.println(c.getClass().getSimpleName()+ " was sold.");
            }
        }
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

    public void buyEquipment(String characterName, String equipmentName){

        Character hero = null;
        Equipment newEquipment = craftEquipment(equipmentName);
        String equipmentType = newEquipment.getClass().getSuperclass().getSimpleName();

        for(Character c: guild){
            if(c.getClass().getSimpleName() == characterName){
                hero = c;
            }
        }

        if(this.goldCoins >= newEquipment.getPrice()){
            if(equipmentType == "Armour") {
                if (hero.isArmour) {
                    //sout
                    Equipment oldArmour = craftEquipment(hero.armourType);
                    oldArmour.removeFrom(hero);
                    newEquipment.equipTo(hero);
                    //sout
                } else {
                    newEquipment.equipTo(hero);
                    //sout
                }
            } else if (equipmentType == "Artefact"){
                if(hero.isArtefact){
                    //sout
                    Equipment oldArtefact = craftEquipment(hero.artefactType);
                    oldArtefact.removeFrom(hero);
                    newEquipment.equipTo(hero);
                    //sout
                } else {
                    newEquipment.equipTo(hero);
                    //sout
                }
            }
            this.goldCoins -= newEquipment.getPrice();
        }
    }

//    public void printPlayerStatistics() {
//
//        //Hero Name + Equipments is yet to add
//
//        String details = String.format("Name: %s\n" +
//                "Username: %s\n" +
//                "XP: %d\n" +
//                "Gold coins: %d\n" +
//                "Homeground: %d\n" +
//                "Archer: %s\n" +
//                "Knight: %S\n" +
//                "Mage: %s\n" +
//                "Healer: %s\n" +
//                "Mythical Creature: %s\n", name, userName, XP, goldCoins, homeground, );
//
//    }
}
