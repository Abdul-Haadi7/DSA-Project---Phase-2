import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations
{
    public static void removeDonation(String id)
    {
        FileOperations.transferDonationsToDummy("All active donations.txt");
        FileOperations.makeFileBlank("All active donations.txt");
        FileOperations.writeBackUpdatedData(id,"All active donations.txt");
        FileOperations.makeFileBlank("Dummy.txt");
    }
    private static void transferDonationsToDummy(String fileNameToTransfer)
    {
        try{
            FileWriter fw = new FileWriter("Dummy.txt",true);
            FileReader fr = new FileReader(fileNameToTransfer);
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
    private static void writeBackUpdatedData(String idToIgnore,String fileToWrite){
        try{
            FileWriter fw = new FileWriter(fileToWrite,true);
            FileReader fr = new FileReader("Dummy.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null)
            {
                String split[] = line.split(",");
                if (split[0].equals(idToIgnore)){
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
    public static void removeRequest(String id)
    {
        transferDonationsToDummy("All donation requests.txt");
        makeFileBlank("All donation requests.txt");
        writeBackUpdatedData(id,"All donation requests.txt");
        makeFileBlank("Dummy.txt");
    }
}
