import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations
{
    public static void removeDonation(String id)
    {
        FileOperations.transferDonationsToDummy();
        FileOperations.makeFileBlank("All active donations.txt");
        FileOperations.writeBackUpdatedDonations(id);
        FileOperations.makeFileBlank("Dummy.txt");
    }
    private static void transferDonationsToDummy()
    {
        try{
            FileWriter fw = new FileWriter("Dummy.txt",true);
            FileReader fr = new FileReader("All active donations.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null){
                fw.write(line+"\n");
            }
            fw.close();
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error! "+e.getMessage());
        }
    }
    private static void makeFileBlank(String fileName){
        try
        {
            FileWriter fw = new FileWriter(fileName);
            fw.write("");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error! "+e.getMessage());
        }
    }
    private static void writeBackUpdatedDonations(String id){
        try{
            FileWriter fw = new FileWriter("All active donations.txt",true);
            FileReader fr = new FileReader("Dummy.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null)
            {
                String split[] = line.split(",");
                if (split[0].equals(id)){
                    continue;
                }
                fw.write(line+"\n");
            }
            fw.close();
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Error! "+e.getMessage());
        }
    }
}
