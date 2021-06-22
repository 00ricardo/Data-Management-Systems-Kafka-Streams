package producer;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Producer {

    public static void main(String[] args) {
        new Producer().run();
    }
    
    
    public void run(){
        
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propsPath = rootPath + "config.properties";
        
        //Get update data from files
        ReadRecordsFromFile obj1 = new ReadRecordsFromFile();
        ReadRecordsFromFile obj2 = new ReadRecordsFromFile();
        ReadRecordsFromFile obj3 = new ReadRecordsFromFile();
        ReadRecordsFromFile obj4 = new ReadRecordsFromFile();
        List<NewLine> rec1 = null;   
        List<NewLine> rec2 = null; 
        List<NewLine> rec3 = null; 
        List<NewLine> rec4 = null; 
        try {
            obj1.readFromFile(ReadRecordsFromFile.PATH_FILE_1);
            obj2.readFromFile(ReadRecordsFromFile.PATH_FILE_2);
            obj3.readFromFile(ReadRecordsFromFile.PATH_FILE_3);
            obj4.readFromFile(ReadRecordsFromFile.PATH_FILE_4);
            
            rec1 =  obj1.getRecords();
            rec2 =  obj2.getRecords();
            rec3 =  obj3.getRecords();
            rec4 =  obj4.getRecords();
        } catch (IOException ex) {
            return;
        }
        
        // Create a kafka producer 1
        ProducerRunnable producerRunnable1 = new ProducerRunnable(propsPath, rec1);
        Thread producerThread1 = new Thread(producerRunnable1);     
        producerThread1.start();
        
        
        // Create a kafka producer 2
        ProducerRunnable producerRunnable2 = new ProducerRunnable(propsPath, rec2);
        Thread producerThread2 = new Thread(producerRunnable2);     
        producerThread2.start();
        
        //Create a kafka producer 3
        ProducerRunnable producerRunnable3 = new ProducerRunnable(propsPath, rec3);
        Thread producerThread3 = new Thread(producerRunnable3);     
        producerThread3.start();
      
        // Create a kafka producer 4
        ProducerRunnable producerRunnable4 = new ProducerRunnable(propsPath, rec4);
        Thread producerThread4 = new Thread(producerRunnable4);     
        producerThread4.start();
        
        
        try {
          producerThread1.join();
          producerThread2.join();
          producerThread3.join();
           producerThread4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
