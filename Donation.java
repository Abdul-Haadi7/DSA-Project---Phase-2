import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Donation
{
    private String donationID;
    private Food_Item item;
    private String donorID;
    private Donation next,previous;

    //Constructor to create new donation. Every new donation is written to file also.
    public Donation(Food_Item item, String donorID)
    {
        this.item = item;
        this.donorID = donorID;
        this.writeToFile();
    }
    /*Constructor to load existing donations in donor`s list, inside loadDonations()
    functions in Donor and AllActiveDonations classes.*/
    public Donation(Food_Item item, String donorID,String donationID)
    {
        this.donationID = donationID;
        this.item = item;
        this.donorID = donorID;
    }
    public Donation(String donationID,Food_Item item, String donorID)
    {
        this.donationID = donationID;
        this.item = item;
        this.donorID = donorID;
    }
    public void writeToFile()
    {
        try
        {
//            int donationID = Main.findMaxId("All active donations.txt")+1;
            FileWriter fw = new FileWriter("All active donations.txt",true);
            fw.write(donationID+","+this.donorID+","+item.toString());
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
    }

    public Donation getNext() {
        return next;
    }

    public void setNext(Donation next) {
        this.next = next;
    }

    public Donation getPrevious() {
        return previous;
    }

    public void setPrevious(Donation previous) {
        this.previous = previous;
    }

    @Override
    public String toString()
    {
        return "Donation ID: " + donationID+
                ", Food item: " + item.getItemName() +
                ", Weight(kg): "+item.getWeightInKg()+
                ", Quantity: "+item.getQuantity()+"\n";
    }
}
