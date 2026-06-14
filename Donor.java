import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Donor extends User
{
    DoublyLinkedList<Donation> myDonations = new DoublyLinkedList();
    DonationRequestsOfDonor myRequests = new DonationRequestsOfDonor();

    public Donor(String userID, String userName, String userPassword, String phoneNumber, String location) {
        super(userID, userName, userPassword, phoneNumber, location);
        this.loadDonations();
        this.loadRequests();
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
                //If the id of donor is equal to this donor, means that donation is his, so add it.
                if (split[1].equals(this.getUserID()))
                {
                    item = new Food_Item(split[2],split[3],split[4],split[5],
                            Double.parseDouble(split[6]),Integer.parseInt(split[7]),
                            LocalDate.parse(split[8]));
                    //Create donation object.
                    donation = new Donation(item,this.getUserID(),split[0]);
                    //Create node of donation to insert in doubly linked list.
                    donationNode = new Node<Donation>(donation);
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
        Quick_Access.addDonation(donation);
        //Write new donation to file.
        donation.writeToFile();
        System.out.println("Donation uploaded!");
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

    private void viewDonations()
    {
        if (myDonations.getHead()==null)
        {
            System.out.println("No donation uploaded currently!");
            return;
        }
        System.out.println("Your donations: ");
        myDonations.displayList();
    }

    public void requestActions()
    {
        if(this.myRequests.allRequests.isEmpty()){
            System.out.println("No requests made to you currently!");
            return;
        }
         /*For request action, donor must enter only the request id which exists. To check this, we will
         store all ids of his requests and check the entered ID against that*/
        DoublyLinkedList idsList = this.myRequests.storeReqIds();
        //If there is only 1 request, do not ask the id but if more than 1, ask the id to proceed.
        if(this.myRequests.allRequests.size()>1)
        {
            System.out.print("Enter request id to perform action on it: ");
            int id = Main.validateInput(1,this.myRequests.getMaxID());
        }
        //If only 1 request, just take its id to perform actions.
        int id = myRequests.getMaxID();
        //If a request of entered id exists
        if(idsList.containsID(String.valueOf(id)))
        {
            DonationRequest toBeDeleted = this.myRequests.getRequestByID(String.valueOf(id));
            // If request is confirmed, the food item in that request is also deleted.
            //Otherwise, only the request is deleted.
            System.out.print("Enter 1 to confirm request\n2 to delete request: ");
            int choice = Main.validateInput(1,2);
            this.myRequests.allRequests.remove(toBeDeleted);
            FileOperations.removeRequest(toBeDeleted.getRequestID());
            Quick_Access.removeRequest(toBeDeleted.getRequestID());
            this.myRequests.removeRequest(toBeDeleted);
            if (choice==1)
            {
                //Delete the food item in the request
                FileOperations.removeDonation(toBeDeleted.getFoodItemID());
                System.out.println("Request confirmed! Food item also deleted!");
                Quick_Access.removeDonation(toBeDeleted.getFoodItemID());
            }
            else {
                System.out.println("Request rejected!");
            }
        }
        else {
            System.out.println("No request of this ID exists!");
        }
    }
}