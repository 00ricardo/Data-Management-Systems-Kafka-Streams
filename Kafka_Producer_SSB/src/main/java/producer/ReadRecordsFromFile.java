package producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class ReadRecordsFromFile {
    
    public final static String PATH_FILE_1 = "ssb-update/updateSetfromSources_1001_2000_3500.txt";
    public final static String PATH_FILE_2 = "ssb-update/updateSetfromSources_2001_2100_350.txt";
    public final static String PATH_FILE_3 = "ssb-update/updateSetfromSources_2101_2200_350.txt";
    public final static String PATH_FILE_4 = "ssb-update/updateSetfromSources_3500.txt";
    
    public List<NewLine> records;

    public ReadRecordsFromFile() {
        records = new ArrayList<>();
    }
    
    /**
     * Reads the file with the data and arrange the data in order to be used
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readFromFile(String filePath) throws FileNotFoundException, IOException{
        File file = new File(filePath);    
        FileReader fr;   
        try { fr = new FileReader(file); } catch (FileNotFoundException ex) { throw ex; }
        BufferedReader br = new BufferedReader(fr);
        String line;
       // int n=0;
        // Read until the EOF
        while((line=br.readLine())!=null){
            records.add(new NewLine(line));
          //  n++;
        }
       // System.out.println("Count: " + n);
        br.close();
        fr.close();
    }
    
    
    /**
     * Return the records List
     * @return {records}
     */
    public  List<NewLine> getRecords(){
        return records;
    }
}
