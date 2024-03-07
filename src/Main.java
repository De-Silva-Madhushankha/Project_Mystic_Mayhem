import Characters.Archers.*;
import Characters.Character;
import Equipment.Armour.*;
import Equipment.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("|                              Mystic Mayhem                                          |");
        System.out.println("---------------------------------------------------------------------------------------");

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name =scan.nextLine();

        System.out.print("Enter a username: ");
        String username;
        L1:do{
            username = scan.nextLine();
            if(usernames.contains(username)){
                System.out.println("Username is already taken.");
                System.out.print("Enter a username: ");
            } else {
                usernames.add(username);
                break L1;
                //clearConsole();
            }
        }while (true);

        UserProfile user = new UserProfile(name,username);
    }




}
}
