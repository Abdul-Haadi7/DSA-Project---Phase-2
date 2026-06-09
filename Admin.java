import java.util.HashMap;

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
            System.out.print("5-Delete user: ");
            choice = Main.validateInput(0,5);
            if (choice==0){
                break;
            }
        }
    }
}
