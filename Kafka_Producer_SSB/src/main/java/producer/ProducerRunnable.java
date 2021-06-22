package producer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author david
 */
public class ProducerRunnable implements Runnable{

    Logger logger = LoggerFactory.getLogger(producer.ProducerRunnable.class);
    KafkaProducer<String, String> producer;
    String topic;
    List<NewLine> records;
        
    /**
     * Set the properties in order to create the producer
     * @param propsPath - Path to the file that contains the properties
     * @param records 
     */
    public ProducerRunnable(String propsPath, List<NewLine> records) {
        try {
            this.records = records;
            FileInputStream in = new FileInputStream(propsPath);
            Properties properties = new Properties();
            properties.load(in);
            this.topic = properties.getProperty("topic");
            in.close();
            producer = new KafkaProducer<String, String>(properties);
        } catch (IOException e) {
            logger.error("Kafka Producer properties file read failure!");
        }
    }
    
    /**
     * Thread responsible to send the lines to the consumer
     */
    @Override
    public void run() {
        boolean firstTime = true;
        String lastCostumer ="";
        int performanceTest = 0;
        for(NewLine rec : records){
            // If the customer is diferent... wait 1 second until send data related to ohter customer
            if(!lastCostumer.equals(rec.getCostumer_name()) && !firstTime ){
               //try { Thread.sleep(1000); } catch (InterruptedException ex) { }
                lastCostumer = rec.getCostumer_name();
            }
            // Send the data to the Kafka consumer
            producer.send(new ProducerRecord<String, String>(topic, rec.getLine()));
            producer.flush();
            if(firstTime){
                lastCostumer = rec.getCostumer_name();
                firstTime = false;
            }
            
            //Performance
            //if(++performanceTest == 200)
            //    break;
            
        }
        //System.out.println("Count: " + performanceTest);
    }
    
    /**
     * Shutdown producer
     */
    public void shutdown() {
            producer.close();
            logger.info("Kafka Producer has closed.");
        }
    
    
}
