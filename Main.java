import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int choice = LogInOrSignIn.askChoice();
        User u;
        if (choice == 0)
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter password: ");
            String password = input.nextLine();
            if(password.equals("admin@67")){
                System.out.println("Welcome admin!");
                Admin admin = new Admin();
                admin.menu();
                return;
            }
        }
        if (choice == 1)
        {
            u = LogInOrSignIn.createNewAccount();
        }
        else
        {
            u = LogInOrSignIn.logIn();
        }
        if (u==null)
        {
            return;
        }
        System.out.print("Enter 1 to login as Donor, 2 for receiver: ");
        choice = validateInput(1,2);
        if (choice == 1)
        {
            Donor d = new Donor(u.getUserID(),u.getUserName(),u.getUserPassword()
            ,u.getPhoneNumber(),u.getLocation());
            d.menu();
        }
        else{
            Receiver r = new Receiver(u.getUserID(),u.getUserName(),u.getUserPassword()
                    ,u.getPhoneNumber(),u.getLocation());
            r.menu();
        }
    }
    public static int validateInput(int min,int max)
    {
        int choice;
        Scanner input = new Scanner(System.in);
        try
        {
            choice = input.nextInt();
            if (choice >= min && choice <= max)
            {
                return choice;
            }
            System.out.print("Please enter correct choice: ");
            return validateInput(min,max);
        }
        catch (InputMismatchException e)
        {
            System.out.print("Please enter correct choice: ");
            return validateInput(min,max);
        }
    }
    public static void printSpaces(int n){
        for(int i=0;i<1;i++)
        {
            System.out.println();
        }
    }

    public static int countLines(String fileName)
    {
        int count =0;
        try
        {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while(br.readLine()!=null)
            {
                count++;
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
        return count;
    }
    public static int findMaxId(String fileName){
        int max=0,temp=0;
        try
        {
            String current,line;
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while((line=br.readLine())!=null)
            {
                String[] split = line.split(",");
                current=split[0];
                temp = Integer.parseInt(current);
                if(temp>max){
                    max=temp;
                }
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
        return max;
    }

}