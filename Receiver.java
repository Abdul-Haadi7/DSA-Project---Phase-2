import java.util.Scanner;

public class Receiver extends User
{
    public Receiver(String userID, String userName, String userPassword, String phoneNumber, String location) {
        super(userID, userName, userPassword, phoneNumber, location);
    }

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
            System.out.print("2-Make a donation request: ");
            choice = Main.validateInput(0,2);
            if (choice==0)
            {
                break;
            }
            else if(choice==1){
                displayAllDonations();
            }
            else if(choice==2)
            {
                displayAllDonations();
                makeDonationRequest();
            }
        }
    }
    public void displayAllDonations()
    {
        Quick_Access.displayDonations();
    }

    public void makeDonationRequest()
    {
        Scanner input = new Scanner(System.in);
        String id="";
        //Keep taking input until a valid id is entered.
        while(!Quick_Access.donationExists(id))
        {
            System.out.print("Enter donation id: ");
            id = input.nextLine();
        }
        Donation donation = Quick_Access.getDonationById(id);
        if(donation.getDonorID().equals(this.getUserID())){
            System.out.println("This donation was uploaded by you, you cannot request for it!");
            return;
        }
        int requestID = Main.findMaxId("All donation requests.txt")+1;
        Food_Item food = donation.getItem();
        DonationRequest request = new DonationRequest(String.valueOf(requestID),donation.getDonorID(),
                this.getUserID(),food.getItemID(), String.valueOf(food.getExpiryDate()));
        request.storeRequestToFile();
        Quick_Access.addRequest(request);
        System.out.println("Request sent to concerned donor!");
    }
}