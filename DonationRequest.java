import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class DonationRequest
{
    private String requestID,donorID,receiverID,FoodItemID;
    private LocalDate expiryDate;
    public DonationRequest(String requestID, String donorID, String receiverID, String foodItemID,
                           String expiryDate)
    {
        this.requestID = requestID;
        this.donorID = donorID;
        this.receiverID = receiverID;
        FoodItemID = foodItemID;
        this.expiryDate = LocalDate.parse(expiryDate);
    }
    //Default constructor
    public DonationRequest()
    {
        this.requestID = "";
        this.donorID = "";
        this.receiverID = "";
        this.FoodItemID = "";
        this.expiryDate = LocalDate.parse("1990-10-10");
    }
    public void storeRequestToFile(){
        try{
            FileWriter fw = new FileWriter("All donation requests.txt",true);
            fw.write(this.toString()+"\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error! "+e.getMessage());
        }
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getFoodItemID() {
        return FoodItemID;
    }

    public void setFoodItemID(String foodItemID) {
        FoodItemID = foodItemID;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


    public String toString()
    {
        return requestID +","+ donorID + "," +receiverID + "," + FoodItemID + "," +expiryDate;
    }
    public String display(){
        return "Request id: "+this.requestID+", Made by: "+this.receiverID+", Food expiry date: "+this.expiryDate+"\n";
    }
}
