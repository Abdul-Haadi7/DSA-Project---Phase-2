import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Quick_Access
{
    private static HashMap<String,Donation> donationMapByDonationID;
    private static HashMap<String,Donation> donationMapByDonorID;

    private static HashMap<String,DonationRequest> requestMapByRequestID;
    private static HashMap<String,DonationRequest> requestMapByReceiverID;

    private static HashMap<String,User> userMap;

    private static void storeRequestsInMap(){
        try
        {
            String line;
            FileReader fr = new FileReader("All donation requests.txt");
            BufferedReader br = new BufferedReader(fr);
            DonationRequest request;
            while((line=br.readLine())!=null)
            {
                String[] split = line.split(",");
                request = new DonationRequest(split[0],split[1],split[2],split[3],split[4]);
                requestMapByRequestID.put(request.getRequestID(),request);
                requestMapByReceiverID.put(request.getReceiverID(),request);
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
    }

    private static void storeDonationsInMap(){
        try
        {
            String line;
            Food_Item item;
            FileReader fr = new FileReader("All active donations.txt");
            BufferedReader br = new BufferedReader(fr);
            Donation donation;
            while((line=br.readLine())!=null)
            {
                String[] split = line.split(",");
                item = new Food_Item(split[2],split[3],split[4],split[5],Double.parseDouble(split[6])
                        ,Integer.parseInt(split[7]),LocalDate.parse(split[8]));
                donation = new Donation(item,split[1],split[0]);
                donationMapByDonationID.put(donation.getDonationID(),donation);
                donationMapByDonorID.put(donation.getDonorID(),donation);
            }
            fr.close();
            br.close();
        }
        catch (Exception e)
        {
            System.out.println("Error! "+e.getMessage());
        }
    }

    public static boolean donationExists(String id)
    {
        if (donationMapByDonationID==null)
        {
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        if (donationMapByDonationID.containsKey(id)){
            return true;
        }
        return false;
    }
    public static Donation getDonationById(String id)
    {
        if (donationMapByDonationID==null)
        {
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        return donationMapByDonationID.get(id);
    }
    public static void displayDonations()
    {
        if (donationMapByDonationID==null)
        {
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        for (Donation d:donationMapByDonationID.values()){
            System.out.println(d);
        }
    }
    public static void displayRequests()
    {
        if (requestMapByRequestID==null)
        {
            requestMapByRequestID = new HashMap<>();
            requestMapByReceiverID = new HashMap<>();
            storeRequestsInMap();
        }
        for (DonationRequest r:requestMapByRequestID.values()){
            System.out.println(r);
        }
    }
    public static void displayDonationsByUser(String id)
    {
        if (donationMapByDonationID==null)
        {
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        if (!donationMapByDonorID.containsKey(id)){
            System.out.println("No donation found!");
            return;
        }
        for(Donation d:donationMapByDonorID.values()){
            if(d.getDonorID().equals(id)){
                System.out.println(d);
            }
        }
    }
    public static void displayRequestsByReceiver(String id)
    {
        if (requestMapByRequestID==null)
        {
            requestMapByRequestID = new HashMap<>();
            requestMapByReceiverID = new HashMap<>();
            storeRequestsInMap();
        }
        if (!requestMapByReceiverID.containsKey(id)){
            System.out.println("No request found!");
            return;
        }
        for(DonationRequest r:requestMapByReceiverID.values())
        {
            if(r.getReceiverID().equals(id)){
                System.out.println(r);
            }
        }
    }

    public static ArrayList<Donation> sortDonationsByWeight()
    {
        if(donationMapByDonationID==null){
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        ArrayList<Donation> list = sortByWeight(donationMapByDonationID);
        return list;
    }

    private static ArrayList<Donation> sortByWeight(HashMap<String, Donation> map) {
        //Store donations in ArrayList from map to sort them.
        ArrayList<Donation> list = new ArrayList<>(map.values());
        //This is merge sort.
        for (int size = 1; size < list.size(); size *= 2)
        {
            for (int left = 0; left < list.size() - size; left += size * 2) {
                int mid = left + size - 1;
                int right = Math.min(left + size * 2 - 1, list.size() - 1);

                ArrayList<Donation> temp = new ArrayList<>();

                int i = left;
                int j = mid + 1;

                while (i <= mid && j <= right)
                {
                    //Find weight of items to sort on base on weight.
                    double w1 = list.get(i).getItem().getWeightInKg();
                    double w2 = list.get(j).getItem().getWeightInKg();
                    if (w1 >= w2)
                    {
                        temp.add(list.get(i));
                        i++;
                    } else
                    {
                        temp.add(list.get(j));
                        j++;
                    }
                }
                while (i <= mid) temp.add(list.get(i++));
                while (j <= right) temp.add(list.get(j++));
                for (int k = 0; k < temp.size(); k++)
                {
                    list.set(left + k, temp.get(k));
                }
            }
        }
        return list;
    }
    public static void addDonation(Donation d)
    {
        if(donationMapByDonationID==null)
        {
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        if(!donationMapByDonationID.containsKey(d.getDonationID())){
            donationMapByDonationID.put(d.getDonationID(),d);
            donationMapByDonorID.put(d.getDonorID(),d);
        }
    }
    public static void addRequest(DonationRequest r)
    {
        if (requestMapByRequestID==null)
        {
            requestMapByRequestID = new HashMap<>();
            requestMapByReceiverID = new HashMap<>();
            storeRequestsInMap();
        }
        if(!requestMapByRequestID.containsKey(r.getRequestID())){
            requestMapByRequestID.put(r.getRequestID(),r);
            requestMapByReceiverID.put(r.getReceiverID(),r);
        }
    }
    public static void removeDonation(String id)
    {
        if(donationMapByDonationID==null)
        {
            donationMapByDonationID = new HashMap<>();
            donationMapByDonorID = new HashMap<>();
            storeDonationsInMap();
        }
        if(donationMapByDonationID.containsKey(id)){
            donationMapByDonationID.remove(id);
            donationMapByDonorID.remove(id);
        }
    }
    public static void removeRequest(String id)
    {
        if (requestMapByRequestID==null)
        {
            requestMapByRequestID = new HashMap<>();
            requestMapByReceiverID = new HashMap<>();
            storeRequestsInMap();
        }
        if(requestMapByRequestID.containsKey(id)){
            requestMapByRequestID.remove(id);
            requestMapByReceiverID.remove(id);
        }
    }
}