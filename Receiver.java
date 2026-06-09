import com.sun.net.httpserver.Request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

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
            System.out.println("2-Make a donation request: ");
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
        try {
            FileReader fr = new FileReader("All active donations.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null)
            {
                String split[] = line.split(",");
                System.out.print("Donation ID: " + split[0]);
                System.out.print(", Item name: " + split[3]);
                System.out.print(", Location: " + split[4]);
                System.out.print(", Weight (kg): " + split[6]);
                System.out.print(", Quantity: " + split[7]);
                System.out.println(", Expiry date: " + split[8]);
                System.out.println(", Uploaded by: " + split[1]);
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
    }

    /*For making a request, receiver must enter only the donation id which exists. To check this, we will
    store all ids and check the entered ID against that*/
    private DoublyLinkedList storeAllDonationIDs()
    {
        DoublyLinkedList allIds = new DoublyLinkedList();
        try
        {
            FileReader fr = new FileReader("All active donations.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line=br.readLine())!=null)
            {
                String[] split = line.split(",");
                allIds.insertAtEnd(new Node<>(split[0]));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error! "+e.getMessage());
        }
        return allIds;
    }

    public void makeDonationRequest()
    {
        DoublyLinkedList allIds = this.storeAllDonationIDs();
        int max = Main.findMaxId("All active donations.txt");
        while(true)
        {
            System.out.print("Enter donation ID: ");
            int id = Main.validateInput(1,max);
            if(allIds.containsID(String.valueOf(id)))
            {
                String[] donations= this.storeAllDonations();
                String[] split = donations[id-1].split(",");
                String donorID = split[1];
                String foodID = split[2];
                String expiryDate = split[8];
                int requestID = Main.findMaxId("All donation requests.txt")+1;
                DonationRequest request = new DonationRequest(String.valueOf(requestID),donorID,
                        this.getUserID(), foodID,expiryDate);
                request.storeRequestToFile();
                break;
            }
            else
            {
                System.out.println("Enter correct id!");
            }
        }
    }
    /*Store all donations in array so that we can take donor id and food id from them,
    to make request.*/
    private String[] storeAllDonations()
    {
        String[] donations = new String[Main.countLines("All active donations.txt")];
        try
        {
            int index=0;
            FileReader fr = new FileReader("All active donations.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                donations[index] = line;
                index++;
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
        return donations;
    }
}