import java.util.PriorityQueue;
//This is a class to store all donation requests made to a specific donor.
public class DonationRequestsOfDonor
{
    //Give priority by expiry date of food. Foods that expire earlier, get priority.
    PriorityQueue<DonationRequest> allRequests = new PriorityQueue<>(
            (f1, f2) -> f1.getExpiryDate().compareTo(f2.getExpiryDate())
    );
    public void addRequest(DonationRequest r)
    {
        this.allRequests.add(r);
    }
    public void removeRequest(DonationRequest r)
    {
        this.allRequests.remove(r);
    }
    public void removeRequest(String id)
    {
        for (DonationRequest req: this.allRequests){
            if (req.getRequestID().equals(id)){
                this.allRequests.remove(req);
            }
        }
    }
    public DonationRequest getRequestByID(String id)
    {
        for (DonationRequest req: this.allRequests){
            if (req.getRequestID().equals(id)){
                return req;
            }
        }
        return new DonationRequest();
    }
    public void display()
    {
        for(DonationRequest r:allRequests){
            System.out.println(r.display());
        }
    }

    public int size()
    {
        return this.allRequests.size();
    }
    //Make a doubly linked list of ids of all requests.
    public DoublyLinkedList storeReqIds()
    {
        DoublyLinkedList list = new DoublyLinkedList();
        for (DonationRequest r:this.allRequests)
        {
            list.insertAtEnd(new Node<>(r.getRequestID()));
        }
        return list;
    }
    public int getMaxID()
    {
        int max=0;
        for (DonationRequest req: this.allRequests)
        {
            if (Integer.parseInt(req.getRequestID()) > max){
                max = Integer.parseInt(req.getRequestID());
            }
        }
        return max;
    }
}
