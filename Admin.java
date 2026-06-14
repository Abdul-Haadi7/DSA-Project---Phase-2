import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//Admin class will be completed by phase 2.

public class Admin
{
    /*Admin can access all donations and requests using the id of donor and receiver.
    String id will be the key and all donations and requests against user`s id will be stored in a doubly
    linked list.*/
    HashMap<String,DoublyLinkedList> allDonations;
    HashMap<String,DoublyLinkedList> allRequests;
    public void menu()
    {
        int choice;
        while(true)
        {
            System.out.println("==================");
            System.out.println("  Select action");
            System.out.println("==================");
            System.out.println("0-Exit: ");
            System.out.println("1-See all donations: ");
            System.out.println("2-See donations of specific donor: ");
            System.out.println("3-See all requests: ");
            System.out.println("4-See requests of specific receiver: ");
            System.out.print("5-Display sorted donations by weight: ");
            choice = Main.validateInput(0,5);
            if (choice==0){
                break;
            }
            else if(choice == 1){
                Quick_Access.displayDonations();
            }
            else if(choice == 2 || choice == 4)
            {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter id: ");
                String id = input.nextLine();
                if (choice==2){
                    Quick_Access.displayDonationsByUser(id);
                }
                else {
                    Quick_Access.displayRequestsByReceiver(id);
                }
            }
            else if(choice == 3)
            {
                Quick_Access.displayRequests();
            }
            else if(choice == 5)
            {
                ArrayList<Donation> list = Quick_Access.sortDonationsByWeight();
                for(Donation d:list){
                    System.out.println(d);
                }
            }
        }
    }
}
