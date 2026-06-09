import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class LogInOrSignIn
{
    public static int askChoice() {
        int choice = 0;
        System.out.print("Enter 0 for admin portal\n1 to create new account\n2 to log-in: ");
        choice = Main.validateInput(0, 2);
        return choice;
    }

    public static User createNewAccount()
    {
        boolean created = false;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your complete name: ");
        String name = input.nextLine();

        boolean exists = true;
        String id = "";
        while (exists) {
            System.out.print("Enter your id: ");
            id = input.nextLine();
            exists = checkIfIdIsTaken(id);
            if (exists)
            {
                System.out.println("This id is already taken! Please enter another: ");
            }
        }

        System.out.print("Enter your phone number: ");
        String phone = input.nextLine();

        System.out.print("Enter your location: ");
        String location = input.nextLine();

        System.out.print("Create your password: ");
        String password = input.nextLine();
        try
        {
            FileWriter fw = new FileWriter("Users login details.txt", true);
            fw.write(name + "," + id + "," + phone + "," + location + "," + password + "\n");
            fw.close();
            User u = new User(id,name,password,phone,location);
            System.out.println("Account created!");
            return u;
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
        return null;
    }

    private static boolean checkIfIdIsTaken(String id) {
        boolean exists = false;
        try {
            FileReader fr = new FileReader("Users login details.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                if (split[1].equals(id)) {
                    exists = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
        return exists;
    }

    private static User findUser(String id) {
        User u = null;
        try
        {
            FileReader fr = new FileReader("Users login details.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null)
            {
                String[] split = line.split(",");
                if (split[1].equals(id))
                {
                    u = new User(split[0],split[1],split[2],split[3],split[4]);
                    return u;
                }
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
        return u;
    }

    public static User logIn()
    {
        User u = null;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your id: ");
        String id = input.nextLine();
        while (true)
        {
            if (!checkIfIdIsTaken(id))
            {
                System.out.println("This id does not exist!");
                System.out.print("Enter your id: ");
                id = input.nextLine();
            }
            else{
                break;
            }
        }
        u = findUser(id);
        if (u == null)
        {
            System.out.println("User not found!!!");
            return null;
        }
        String enteredPassword = "";
        while (true)
        {
            enteredPassword = askingPassword();
            if (u.getUserPassword().equals(enteredPassword))
            {
                System.out.println("Welcome back!");
                u = new User(u.getUserID(),u.getUserName(),u.getUserPassword(),
                        u.getPhoneNumber(),u.getLocation());
                return u;
            }
            else
            {
                System.out.println("Wrong password!");
            }
        }
    }

    private static String askingPassword() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        return password;
    }
}
