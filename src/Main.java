import java.util.*;
public class Main {
    static Set<String> usernames = new LinkedHashSet<>();
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
