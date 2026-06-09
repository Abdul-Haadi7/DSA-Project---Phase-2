import java.util.PriorityQueue;

public class AllRequests
{
    //Donation request are stored in queue. Early expiry dates come first.
    PriorityQueue<DonationRequest> allRequests = new PriorityQueue<>(
            (f1, f2) -> f1.getExpiryDate().compareTo(f2.getExpiryDate())
    );
    public void addRequest(DonationRequest r){
        this.allRequests.add(r);
    }
    public void removeRequest(DonationRequest r){
        this.allRequests.remove(r);
    }
    public void displayAllRequests(){
        for(DonationRequest r:allRequests){
            System.out.println(r);
        }
    }
}
