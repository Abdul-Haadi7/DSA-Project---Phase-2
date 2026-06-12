import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Donor extends User
{
//    AllDonationsOfDonor myDonations = new AllDonationsOfDonor();
    //A doubly linked list to store all uploaded donations of a donor.
    DoublyLinkedList myDonations = new DoublyLinkedList();
    DonationRequestsOfDonor myRequests = new DonationRequestsOfDonor();
    public Donor(String userID, String userName, String userPassword, String phoneNumber, String location) {
        super(userID, userName, userPassword, phoneNumber, location);
        this.loadDonations();
        this.loadRequests();
    }

    //Read all donations from file and add particular donor`s donations to his list.
    private void loadDonations()
    {
        try
        {
            String line;
            Food_Item item;
            FileReader fr = new FileReader("All active donations.txt");
            BufferedReader br = new BufferedReader(fr);
            Donation donation;
            Node<Donation> donationNode;
            while((line=br.readLine())!=null)
            {
                String[] split = line.split(",");
                //If the id of donor is equal to this donor, means that donation is hid, so add it.
                if (split[1].equals(this.getUserID()))
                {
                    item = new Food_Item(split[2],split[3],split[4],split[5],
                            Double.parseDouble(split[6]),Integer.parseInt(split[7]),
                            LocalDate.parse(split[8]));
                    //Create donation object.
                    donation = new Donation(item,this.getUserID(),split[0]);
                    //Create node of donation to insert in doubly linked list.
                    donationNode = new Node<>(donation);
                    //Insert in list
                    myDonations.insertAtEnd(donationNode);
                }
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
    }

    private void loadRequests(){
        try
        {
            String line;
            Food_Item item;
            FileReader fr = new FileReader("All donation requests.txt");
            BufferedReader br = new BufferedReader(fr);
            while((line=br.readLine())!=null)
            {
                String[] split = line.split(",");
                if (split[1].equals(this.getUserID()))
                {
                    myRequests.addRequest(new DonationRequest(split[0],split[1],split[2],split[3],split[4]));
                }
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
    }

    public void uploadDonation()
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Item Name: ");
        String itemName = input.nextLine();

        System.out.print("Enter Location: ");
        String location = input.nextLine();

        System.out.print("Enter Category (cooked/raw/packaged etc): ");
        String category = input.nextLine();

        System.out.print("Enter Weight (kg): ");
        double weightInKg = input.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        LocalDate expiryDate = inputDate();

        int id = Main.findMaxId("All active donations.txt")+1;
        String itemID = Integer.toString(id);

        Food_Item itemToDonate = new Food_Item(itemID, itemName, location, category,
                weightInKg, quantity, expiryDate);
        String donationId = itemID;
        //After taking input of donation information, create donation object.
        Donation donation = new Donation(itemToDonate,this.getUserID(),donationId);
        //Create node to insert in list.
        Node<Donation> donationNode = new Node<>(donation);
        this.myDonations.insertAtEnd(donationNode);
        //Write new donation to file.
        donation.writeToFile();
    }
    //Separate function to input date in correct format.
    private LocalDate inputDate()
    {
        Scanner input = new Scanner(System.in);
        while (true)
        {
            try
            {
                System.out.print("Enter Expiry Date (yyyy-mm-dd): ");
                return LocalDate.parse(input.nextLine());
            }
            catch (Exception e)
            {
                System.out.println("Error! Please enter date in correct format!");
            }
        }
    }

    public void menu()
    {
        int choice=0;
        while(true)
        {
            Main.printSpaces(1);
            System.out.println("==================");
            System.out.println("  Select action");
            System.out.println("==================");
            System.out.println("0-Exit");
            System.out.println("1-Upload donation");
            System.out.println("2-View your active donations: ");
            System.out.print("3-Check requests made to me: ");
            choice = Main.validateInput(0,3);
            if (choice == 0)
            {
                break;
            }
            else if (choice == 1)
            {
                this.uploadDonation();
            }
            else if (choice == 2)
            {
                this.viewDonations();
            }
            else if (choice == 3)
            {
                myRequests.display();
                this.requestActions();
            }
        }
    }
    private void viewDonations()
    {
        if (myDonations.getHead()==null)
        {
            System.out.println("No donation uploaded currently!");
            return;
        }
        System.out.println("Your donations: ");
        myDonations.displayDonations();
    }

    public void requestActions()
    {
         /*For request action, donor must enter only the request id which exists. To check this, we will
         store all ids and check the entered ID against that*/
        DoublyLinkedList idsList = this.myRequests.storeReqIds();
        System.out.println("Enter request id to perform action on it: ");
        int id = Main.validateInput(1,this.myRequests.size());
        if(idsList.containsID(String.valueOf(id)))
        {
            // If request is confirmed, the food item in that request is also deleted.
            //Otherwise only the request is deleted.
            System.out.print("Enter 1 to confirm request\n2 to delete request: ");
            int choice = Main.validateInput(1,2);
            if (choice==1)
            {

            }
        }
    }
    /*Boolean is used to check that if donation is also to be deleted. If its given true, means request
    is confirmed by donor so delete the donation from donation list also. If it`s false, request has
    been canceled so only delete the request, not the donation*/
    private void deleteDonation(String s,boolean b)
    {
        //This function will be complete by phase 2.
        try
        {

        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
    }
}
