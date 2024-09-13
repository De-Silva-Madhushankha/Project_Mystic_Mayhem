
import java.io.*;
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

import static java.lang.System.exit;

public class Main {
    static Set<String> usernames = new LinkedHashSet<>();
    static List<UserProfile> users = new ArrayList<>();
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Character> getAttackPriority(List<Character> guild){

        List<Character> newGuild = new ArrayList<>(Collections.nCopies(5, null));
        for (Character c : guild){
            switch (c.getClass().getSuperclass().getSimpleName()){
                case "Healer" -> newGuild.set(0,c);
                case "Mage" -> newGuild.set(1,c);
                case "MythicalCreature" -> newGuild.set(2,c);
                case "Knight" -> newGuild.set(3,c);
                case "Archer" -> newGuild.set(4,c);
            }
        }
        return newGuild;
    }

    public static void selectHomeground(UserProfile user){
        Scanner scan = new Scanner(System.in);
        System.out.println("Home Ground");
        String homeGrounds = """
                        1 Hillcrest
                        2 Marshland
                        3 Desert
                        4 Arcane""";
        System.out.println(homeGrounds);
        System.out.print("Select your Home ground(1/2/3/4): ");
        int userHomeGround = scan.nextInt();
        boolean choice2 = true;
        while(choice2){
            if(userHomeGround<5 && userHomeGround>0){
                choice2 = false;
                switch (userHomeGround) {
                    case 1 -> user.setHomeground("Hillcrest");
                    case 2 -> user.setHomeground("Marshland");
                    case 3 -> user.setHomeground("Desert");
                    case 4 -> user.setHomeground("Arcane");
                }
            }
            else{
                System.out.println("You have entered an invalid number. Please enter a valid number");
                System.out.println("Home Ground");
                homeGrounds = """
                        1 Hillcrest
                        2 Marshland
                        3 Desert
                        4 Arcane""";
                System.out.println(homeGrounds);
                System.out.print("Select your Home ground(1/2/3/4): ");
                userHomeGround = scan.nextInt();
            }
        }
    }

    public static void combat(UserProfile player1, UserProfile player2){
        // player1 -> User
        // player2 -> Opponent

        String battleLocation = player1.getHomeground();
        String p1Name = player1.getUserName();
        String p2Name = player2.getUserName();

        // Set player1's Home ground for all characters
        for (Character c : player1.guild){
            c.setBattleGround(battleLocation);
        }
        for (Character c: player2.guild){
            c.setBattleGround(battleLocation);
        }


        // Battle
        for(int i =1; i<11;i++){

            // List attack priority order
            player1.guild = getAttackPriority(player1.guild);
            player2.guild = getAttackPriority(player2.guild);

            // check survivors
            int tempSurvivors1 = 0;
            int tempSurvivors2 = 0;
            for(Character c: player1.guild){
                if(c.getHealth() > 0){
                    tempSurvivors1++;
                }
            }
            for(Character c: player2.guild){
                if(c.getHealth() > 0){
                    tempSurvivors2++;
                }
            }
            if(tempSurvivors1 == 0 || tempSurvivors2 ==0){
                break;
            }

            Character p1Hero = null;
            Character p2Hero = null;

            // find and select characters for this round
            double maxSpeed = 0; // tempValue
            for(Character c: player1.guild){
                if(maxSpeed <= c.getSpeed() && c.getHealth() > 0){
                    p1Hero = c;
                    maxSpeed = c.getSpeed();
                }
            }
            maxSpeed = 0;
            for(Character c: player2.guild){
                if(maxSpeed <= c.getSpeed() && c.getHealth() > 0){
                    p2Hero = c;
                    maxSpeed = c.getSpeed();
                }
            }

            assert p1Hero != null;
            assert p2Hero != null;

            String p1HeroName = p1Hero.getClass().getSimpleName();
            String p2HeroName = p2Hero.getClass().getSimpleName();

            //Battle status
            System.out.println("Turn["+i+"] +- "+p1Name+" -+");
            System.out.print(p1Name+"'s "+p1HeroName + " attacks " +p2Name+"'s ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // player1 attack
            switch (battleLocation) {
                case "Hillcrest" -> {
                    if (p1Hero.getCharacterType() == "Highlander") {

                        //First attack
                        p1Hero.attack(player2.guild, player1.guild);

                        double tempAttackPoint = p1Hero.getAttackPoint();
                        p1Hero.setAttackPoint(tempAttackPoint*0.2);

                        //Bonus attack
                        System.out.println("+--> Highlander's bonus turn");
                        System.out.print(p1Name+"'s "+p1HeroName + " attacks " +p2Name+"'s ");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        p1Hero.attack(player2.guild, player1.guild);
                        p1Hero.setAttackPoint(tempAttackPoint);

                    } else {
                        p1Hero.attack(player2.guild, player1.guild);
                    }
                }
                case "Arcane" -> {
                    if (p1Hero.getCharacterType() == "Mystic") {

                        p1Hero.attack(player2.guild, player1.guild);

                        //Heal
                        //System.out.println("By Arcane command, the Mystic mends their own wounds!!");
                        double healthIncrement = p1Hero.getHealth()*0.1;
                        p1Hero.setHealth(p1Hero.getHealth() + healthIncrement);

                    } else {
                        p1Hero.attack(player2.guild, player1.guild);
                    }
                }
                default -> {
                    p1Hero.attack(player2.guild, player1.guild);
                }
            }
            System.out.println("");

            // Battle status
            System.out.println("Turn["+i+"] +- "+p2Name+" -+");
            System.out.print(p2Name+"'s "+p2HeroName+" attacks "+p1Name+"'s ");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // player2 attack
            switch (battleLocation) {
                case "Hillcrest" -> {
                    if (p2Hero.getCharacterType() == "Highlander") {

                        //First attack
                        p2Hero.attack(player1.guild, player2.guild);

                        double tempAttackPoint = p2Hero.getAttackPoint();
                        p2Hero.setAttackPoint(tempAttackPoint*0.2);

                        //Bonus attack
                        System.out.println("+--> Highlander's bonus turn");
                        System.out.print(p1Name+"'s "+p1HeroName+" attacks " + p2Name+"'s ");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        p2Hero.attack(player1.guild, player2.guild);
                        p2Hero.setAttackPoint(tempAttackPoint);

                    } else {
                        p2Hero.attack(player1.guild, player2.guild);
                    }
                }
                case "Arcane" -> {
                    if (p2Hero.getCharacterType() == "Mystic") {

                        p2Hero.attack(player1.guild, player2.guild);
                        //Heal
                        //System.out.println("By Arcane command, the Mystic mends their own wounds!!");
                        double healthIncrement = p2Hero.getHealth()*0.1;
                        p2Hero.setHealth(p2Hero.getHealth() + healthIncrement);

                    } else {
                        p2Hero.attack(player1.guild, player2.guild);
                    }
                }
                default -> {
                    p2Hero.attack(player1.guild, player2.guild);
                }
            }
            System.out.println("");
        }

        // Battle outcome
        int p1Survivors = 0;
        int p2Survivors = 0;
        for(Character c: player1.guild){
            if(c.getHealth() > 0){
                p1Survivors++;
            }
        }
        for(Character c: player2.guild){
            if(c.getHealth() > 0){
                p2Survivors++;
            }
        }

        if (p1Survivors > 0 && p2Survivors == 0){
            updateStatus(player1,player2);
        } else if(p1Survivors == 0 && p2Survivors > 0){
            updateStatus(player2,player1);
        } else {
            System.out.println("Draw");
            System.out.printf("%s XP: %d , Gold Coin: %.2f%n",player1.getName(),player1.getXP(),player1.getGoldCoins());
            System.out.printf("%s XP: %d , Gold Coin: %.2f%n",player2.getName(),player2.getXP(),player2.getGoldCoins());
            System.out.println("");
        }

        // Restore Values
        for (Character c : player1.guild){
            c.resetBattleGround(battleLocation);
            c.setDefaultHealth();
        }
        for (Character c: player2.guild){
            c.resetBattleGround(battleLocation);
            c.setDefaultHealth();
        }

    }
    public static void updateStatus(UserProfile winner,UserProfile looser) {
        System.out.println(winner.getName() + " Won!!!");

        double reward = looser.getGoldCoins()*0.1;
        winner.setGoldCoins(winner.getGoldCoins() + reward);
        looser.setGoldCoins(looser.getGoldCoins() - reward);

        winner.setXP(winner.getXP() + 1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("%s XP: %d , Gold Coin: %.2f%n",winner.getName(),winner.getXP(),winner.getGoldCoins());
        System.out.printf("%s XP: %d , Gold Coin: %.2f%n",looser.getName(),looser.getXP(),looser.getGoldCoins());
    }
    public static void printHeros(){
        System.out.println("                           Mystic Mayhem Heroes                                          ");
        // Archers
        System.out.printf("---Archers---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("1  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Shooter","80gc",11,4,6,9);
        System.out.printf("2  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Ranger","115gc",14,5,8,10);
        System.out.printf("3  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Sunfire","160gc",15,5,7,14);
        System.out.printf("4  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Zing","200gc",16,9,11,14);
        System.out.printf("5  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Saggitarius","230gc",18,7,12,17);

        // Knights
        System.out.printf("---Knights---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("6  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Squire","85gc",8,9,7,8);
        System.out.printf("7  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Cavalier","115gc",10,12,7,10);
        System.out.printf("8  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Templar","155gc",14,16,12,12);
        System.out.printf("9  %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Zoro","180gc",17,16,13,14);
        System.out.printf("10 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Swiftblade","250gc",18,20,17,13);

        // Mages
        System.out.printf("---Mages---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("11 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Warlock","100gc",12,7,10,12);
        System.out.printf("12 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Illusionist","120gc",13,8,12,14);
        System.out.printf("13 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Enchanter","160gc",16,10,13,16);
        System.out.printf("14 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Conjurer","195gc",18,15,14,12);
        System.out.printf("15 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Eldritch","270gc",19,17,18,14);

        // Healers
        System.out.printf("---Healers---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("16 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Soother","95gc",10,8,9,6);
        System.out.printf("17 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Medic","125gc",12,9,10,7);
        System.out.printf("18 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Alchemist","150gc",13,13,13,13);
        System.out.printf("19 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Saint","200gc",16,14,17,9);
        System.out.printf("20 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Lightbringer","260gc",17,15,19,12);

        // Mythical Creatures
        System.out.printf("---Mythical Creatures---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("21 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Dragon","120gc",12,14,15,8);
        System.out.printf("22 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Basilisk","165gc",15,11,10,12);
        System.out.printf("23 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Hydra","205gc",12,16,15,11);
        System.out.printf("24 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Phoenix","275gc",17,13,17,19);
        System.out.printf("25 %-20s | %-5s | %6d | %7d | %6d | %5d |%n","Pegasus","340gc",14,18,20,20);
    }
    public static void printEquipment(){

        System.out.println("                          Mystic Mayhem Hero's Equipments                                         ");
        System.out.printf("---Armour---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("1  %-20s | %-5s | %6s | %7s | %6s | %5s |%n","Chainmail","70gc","-","+1","-","-1");
        System.out.printf("2  %-20s | %-5s | %6s | %7s | %6s | %5s |%n","Regalia","105gc","-","+1","-","-");
        System.out.printf("3  %-20s | %-5s | %6s | %7s | %6s | %5s |%n","Fleece","150gc","-","+2","+1","-1");

        System.out.printf("---Artefacts---%n");
        System.out.printf("   %-20s | %-5s | %6s | %7s | %6s | %5s |%n","      ","Price","Attack","Defence","Health","Speed");
        System.out.printf("4  %-20s | %-5s | %6s | %7s | %6s | %5s |%n","Excalibur","150gc","+2","-","-","-");
        System.out.printf("5  %-20s | %-5s | %6s | %7s | %6s | %5s |%n","Amulet","200gc","+1","-1","+1","+1");
        System.out.printf("6  %-20s | %-5s | %6s | %7s | %6s | %5s |%n","Crystal","210gc","+2","+1","-1","-1");
    }

    // Build online Players and store them
    public static void buildOnlineUsers() {

        //player 1
        UserProfile user01 = new UserProfile("James","DarkShark");
        user01.setGoldCoins(3000);
        user01.setXP(21);
        user01.setHomeground("Arcane");
        user01.addHero("Ranger");
        user01.addHero("Zoro");
        user01.addHero("Warlock");
        user01.addHero("Saint");
        user01.addHero("Hydra");
        user01.buyEquipment("Ranger",1);
        user01.buyEquipment("Hydra",6);
        user01.setGoldCoins(102);
        usernames.add("DarkShark");
        users.add(user01);

        //player 2
        UserProfile user02 = new UserProfile("GeraltofRivia","Whitewolf");
        user02.setGoldCoins(3000);
        user02.setXP(32);
        user02.setHomeground("Marshland");
        user02.addHero("Ranger");
        user02.addHero("Squire");
        user02.addHero("Warlock");
        user02.addHero("Medic");
        user02.addHero("Dragon");
        user02.buyEquipment("Ranger",1);
        user02.buyEquipment("Medic",5);
        user02.setGoldCoins(215);
        usernames.add("Whitewolf");
        users.add(user02);

        //player 3
        UserProfile user03 = new UserProfile("Matthew","StormSlicer");
        user03.setGoldCoins(3000);
        user03.setXP(10);
        user03.setHomeground("Desert");
        user03.addHero("Sunfire");
        user03.addHero("Squire");
        user03.addHero("Illusionist");
        user03.addHero("Soother");
        user03.addHero("Dragon");
        user03.buyEquipment("Sunfire",2);
        user03.setGoldCoins(54);
        usernames.add("StormSlicer");
        users.add(user03);

        //player 4
        UserProfile user04 = new UserProfile("Emily","NeonNebula");
        user04.setGoldCoins(3000);
        user04.setXP(8);
        user04.setHomeground("Hillcrest");
        user04.addHero("Zing");
        user04.addHero("Templar");
        user04.addHero("Warlock");
        user04.addHero("Medic");
        user04.addHero("Basilisk");
        user04.setGoldCoins(67);
        usernames.add("NeonNebula");
        users.add(user04);

        //player 5
        UserProfile user05 = new UserProfile("Oliver","NovaKnight");
        user05.setGoldCoins(3000);
        user05.setXP(66);
        user05.setHomeground("Hillcrest");
        user05.addHero("Zing");
        user05.addHero("Zoro");
        user05.addHero("Conjurer");
        user05.addHero("Saint");
        user05.addHero("Phoenix");
        user05.setGoldCoins(311);
        user05.buyEquipment("Saint",6);
        user05.buyEquipment("Zing",3);
        user05.buyEquipment("Zing",4);
        usernames.add("NovaKnight");
        users.add(user05);

        clearConsole();
    }

    public static UserProfile searchOpponent(){
        while (true){
            for(int i =0; i <users.size()-1; i++){
                Scanner scan = new Scanner(System.in);
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("|                          Select Opponent                            |");
                System.out.println("-----------------------------------------------------------------------");

                users.get(i).printPlayerStatistics();
                System.out.println("[1]Battle          [2]Skip");
                System.out.print("Select[1/2]:");
                int choice = scan.nextInt();
                clearConsole();
                if (choice == 1) {
                    return users.get(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        buildOnlineUsers();

        HashMap<Integer, String> heroIndex = new HashMap<>();
        heroIndex.put(1,"Shooter");

        Scanner scan = new Scanner(System.in);

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("|                              Mystic Mayhem                                          |");
        System.out.println("---------------------------------------------------------------------------------------");

        System.out.print("Enter your name: ");
        String name =scan.nextLine();

        System.out.print("Enter a username: ");
        String username;
        do{
            username = scan.nextLine();
            if(usernames.contains(username)){
                System.out.println("Username is already taken.");
                System.out.print("Enter a username: ");
            } else {
                usernames.add(username);
                break;
            }
        }while (true);

        clearConsole();
        UserProfile user = new UserProfile(name,username);
        users.add(user);

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("|                              Mystic Mayhem                                          |");
        System.out.println("---------------------------------------------------------------------------------------");

        printHeros();
        System.out.println("");

        System.out.println("          ------{Guilds}------              ");
        // set default Army
        System.out.println("Guild[1]-> [Archer:Shooter, Knight:Squire, Mage:Warlock, Healer:Soother, Mythical Creature:Dragon]");
        System.out.println("Cost: 480gc");
        System.out.println("Guild[2]-> [Archer:Shooter, Knight:Squire, Mage:Illusionist, Healer:Soother, Mythical Creature:Dragon]");
        System.out.println("Cost: 500gc");

        System.out.print("Select your guild(1/2): ");
        int guildNumber = scan.nextInt();
        boolean choice1 = true;
        System.out.println("");

        while(choice1){
            if(guildNumber<3 && guildNumber>0){
                choice1=false;
            }
            else{
                System.out.println("You have entered an invalid number. Please enter a valid number.");
                System.out.println("                                  Guilds              ");
                // set default Army
                System.out.println("Guild[1]-> [Archer:Shooter, Knight:Squire, Mage:Warlock, Healer:Soother, Mythical Creature:Dragon]");
                System.out.println("Cost: 480gc");
                System.out.println("Guild[2]-> [Archer:Shooter, Knight:Squire, Mage:Illusionist, Healer:Soother, Mythical Creature:Dragon]");
                System.out.println("Cost: 500gc");

                System.out.print("Select your guild[1/2]: ");
                guildNumber = scan.nextInt();
            }
        }

        switch (guildNumber) {
            case 1 -> {
                user.addHero("Shooter");
                user.addHero("Squire");
                user.addHero("Warlock");
                user.addHero("Soother");
                user.addHero("Dragon");
                user.setGoldCoins(20);
            }
            case 2 -> {
                user.addHero("Shooter");
                user.addHero("Squire");
                user.addHero("Illusionist");
                user.addHero("Soother");
                user.addHero("Dragon");
                user.setGoldCoins(0);
            }
        }
        System.out.println("Your guild is Ready!");
        System.out.println();

        selectHomeground(user);
        System.out.printf("Your Home Ground is %s.",user.getHomeground());
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clearConsole();

//        File file = new File("userInfo.txt");
//        FileOutputStream fout = new FileOutputStream(file,true);
//        ObjectOutputStream objout = new ObjectOutputStream(fout);
//
//        for (UserProfile u: users){
//            objout.writeObject(u);
//        }

        boolean choice3 = true;
        game: while(choice3){
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("|                              Mystic Mayhem                                          |");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("""
                What's Next %s?
                1. Battle
                2. Buy Hero
                3. Sell Hero
                4. Buy Equipment
                5. Rename
                6. Show Heroes
                7. Show Equipments
                8. Show Gold Coins
                9. Exit\n""",user.getUserName());
            System.out.print("[1/2/3/4/5/6/7/8/9] : ");
            int option = scan.nextInt();
            int userChoice=1;
            switch(option){
                case 1:
                    clearConsole();
                    if(user.getGuildSize() == 5){
                        UserProfile opponent = searchOpponent();
                        System.out.println("---------------------------------------------------------------------------------------");
                        System.out.println("|                              Battle Begins                                          |");
                        System.out.println("---------------------------------------------------------------------------------------");
                        combat(user,opponent);
                    }
                    else{
                        System.out.println("Your guild is not full.");
                        System.out.println("To battle your guild must be full.");
                        System.out.println("Please buy remaining heroes.");
                        continue game;
                    }
                    break;
                case 2:
                    userChoice = user.buyCharacter();
                    if(userChoice == -1){
                        System.out.println("Something went wrong!!");
                    }
                    else if(userChoice == 0){
                        continue;
                    }
                    break;
                case 3:
                    userChoice = user.sellCharacter();
                    if(userChoice == -1){
                        System.out.println("Something went wrong!!");
                    }
                    else if(userChoice == 0){
                        continue;
                    }
                    break;
                case 4:
                    if(user.guild.size()==5){
                        Scanner scan2 = new Scanner(System.in);
                        System.out.println("Warning : Once you have buy new equipment previous ones will be discarded!!");

                        System.out.print("Do you wish to continue(Y/N)? ");
                        String choice4 = scan2.nextLine();


                        if(choice4.equalsIgnoreCase("Y")){
                            System.out.println(user.guild);
                            System.out.print("Enter the name of your hero to equip equipments : ");
                            String heroName = scan2.nextLine();
                            boolean correct = false;
                            for(Character c : user.guild){
                                if(heroName.equalsIgnoreCase(c.getClass().getSimpleName())){
                                    correct = true;
                                    break;
                                }
                            }
                            while(!correct){
                                System.out.println("You have entered an invalid hero name.");
                                System.out.print("Enter a valid hero name : ");
                                heroName = scan2.nextLine();
                                correct = false;
                                for(Character c : user.guild){
                                    if(heroName.equalsIgnoreCase(c.getClass().getSimpleName()))
                                        correct = true;
                                }
                            }
                            printEquipment();
                            System.out.print("Enter the number of the Equipment : ");
                            int equipmentNumber = scan2.nextInt();
                            while(equipmentNumber<1 && equipmentNumber>6){
                                System.out.println("You have entered a invalid number.");
                                System.out.print("Do you wish to continue buying a new equipment(Y/N) ? ");
                                String choice5 = scan2.nextLine();
                                if(!choice5.equalsIgnoreCase("Y")){
                                    break;
                                }
                                printEquipment();
                                System.out.print("Please enter a valid equipment number : ");
                                equipmentNumber = scan2.nextInt();
                            }
                            user.buyEquipment(heroName,equipmentNumber);
                        }
                    }
                    else
                        System.out.println("Your guild is not complete. Please complete your guild before buying equipments.");

                    break;
                case 5:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println();
                    System.out.print("Enter the new name : ");
                    String newName = scanner.nextLine();
                    user.setName(newName);
                    break;
                case 6:
                    printHeros();
                    break;
                case 7:
                    printEquipment();
                    break;
                case 8:
                    System.out.println("Gold Coins : "+user.getGoldCoins());
                    break;
                case 9:
                    System.exit(0);
            }
        }
    }
}
