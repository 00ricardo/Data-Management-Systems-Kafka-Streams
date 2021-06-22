package consumer;

import db.tables.Lineorder;
import calendar.DateKafkaInput;
import calendar.SsbDate;
import calendar.TransformDate;
import db.comunication.DbComunication;
import db.tables.Customer;
import db.tables.Date;
import db.tables.Part;
import db.tables.Supplier;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author david
 */
public class Consumer {

    public static final String TOPIC_NAME = "ssb-topic";                        // Topic Name

    private final DbComunication ssbLT;                                         // DataBase communication
    private HashMap<String, Supplier> supplierTable;                            // Supplier Table in memory
    private HashMap<String, Customer> customerTable;                            // Customer Table in memory
    private HashMap<String, Part> partTable;                                    // Part Table in memory
    private HashMap<String, Date> dateTable;                                    // Date Table in memory
    private int lo_orderKey;                                                    // Last OrderKey 
    private int p_partkey;                                                      // Last PartKey
    private int c_custkey;                                                      // Last CustKey
    private int s_supkey;                                                       // Last SupKey

    public Consumer() {
        ssbLT = new DbComunication();
        supplierTable = ssbLT.getSupplierTable();
        customerTable = ssbLT.getCustomerTable();
        partTable = ssbLT.getPartTable();
        dateTable = ssbLT.getDateTable();
        lo_orderKey = ssbLT.getLastLineorderKey();
        p_partkey = ssbLT.getLastPartKey();
        
        //Get last Customer ID Value
        final Set<Entry<String, Customer>> cMapValues = customerTable.entrySet();
        final Entry<String,Customer>[] cArray = new Entry[customerTable.size()];
        cMapValues.toArray(cArray);
        c_custkey = cArray[customerTable.size()-1].getValue().getC_custkey();
        
        //Get last Supplier ID Value
        final Set<Entry<String, Supplier>> sMapValues = supplierTable.entrySet();
        final Entry<String,Supplier>[] sArray = new Entry[supplierTable.size()];
        sMapValues.toArray(sArray);
        s_supkey = sArray[supplierTable.size()-1].getValue().getS_suppkey();
    }

    /**
     *
     * @throws SQLException
     */
    public void run() throws SQLException {

        Transformation tf = new Transformation();
        Logger logger = java.util.logging.Logger.getLogger(consumer.Consumer.class.getName());
        String lastCustomer = "";
        boolean firstTime = true;
        int sameCustomerCounter = 0;
        boolean sameCustomerFlag = false;

        // create kafka consumer
        KafkaConsumer<String, String> consumer = null;
        try {
            consumer = createConsumer();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        logger.info("Ready to work;");
        // Receive data from the producer and save into the database
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                // logger.info("Consumer reads: " + record.value());
                logger.info("");
                // Transform data received into fields
                Record r = tf.transformLineToRecord(record.value());
                // Lookup data
                Lineorder newLine = lookupData(r);
                // Feel the other fields
                newLine = finishingLineorderCreation(newLine, r);
                // Verify if the order belongs to the same customer
                if (!r.getC_name().equals(lastCustomer) && !firstTime) {
                    lastCustomer = r.getC_name();
                    sameCustomerCounter = 0;
                    sameCustomerFlag = false;
                }
                // Set field lo_linenumber and lo_orderkey
                newLine.setLo_linenumber(++sameCustomerCounter);
                if (!sameCustomerFlag) {
                    newLine.setLo_orderkey(++lo_orderKey);
                } else {
                    newLine.setLo_orderkey(lo_orderKey);
                }
                if (firstTime) {
                    firstTime = false;
                    lastCustomer = r.getC_name();
                }
                // Write to DataBase
                ssbLT.write(newLine.sqlInsert());
                lastCustomer = r.getC_name();
                sameCustomerFlag = true;
            }

            if (records.count() > 0) {
                consumer.commitSync();
            }
        } // End While     
    }

    /**
     * Lookup for the data in the others tables and create the registers if they
     * don't exists
     *
     * @param r
     * @return
     */
    public Lineorder lookupData(Record r) {
        Lineorder newLine = new Lineorder();
        boolean createPart = true;
        boolean createCostumer = true;
        boolean createSupplier = true;

        // Data related with Part Table
        if (partTable.containsKey(r.getP_name() + r.getP_color())) {
            newLine.setLo_partkey(partTable.get(r.getP_name()+r.getP_color()).getP_partkey());
            createPart = false;
        }
        // Part registry dont exists in the DB. Create a new one
        if (createPart) {
            Part newPart = new Part(++this.p_partkey, r.getP_name(), r.getP_color());
            newPart.generateRandomData();
            ssbLT.write(newPart.toSqlInsert());
            newLine.setLo_partkey(p_partkey);
            this.partTable.put(newPart.getP_name() + newPart.getP_color(), newPart);
        }

        // Data related with Supplier Table
        if (supplierTable.containsKey(r.getS_name())) {
            newLine.setLo_suppkey(supplierTable.get(r.getS_name()).getS_suppkey());
            createSupplier = false;
        }
        // Supplier dont exists in the DB. Create a new one
        if (createSupplier) {
            Supplier newSupp = new Supplier(++this.s_supkey);
            ssbLT.write(newSupp.toSqlInsert());
            newLine.setLo_suppkey(this.s_supkey);
            this.supplierTable.put(newSupp.getS_name(), newSupp);
        }

        // Data related with Costumer Table
        if (customerTable.containsKey(r.getC_name())) {
            newLine.setLo_custkey(customerTable.get(r.getC_name()).getC_custkey());
            createCostumer = false;
        }
        // Customer dont exists in the DB. Create a new one
        if (createCostumer) {
            Customer newCust = new Customer(++this.c_custkey);
            ssbLT.write(newCust.toSqlInsert());
            newLine.setLo_custkey(this.c_custkey);
            this.customerTable.put(newCust.getC_name(), newCust);
        }

        // Data related with Date Table
        if (dateTable.containsKey(r.getD_date())) {
            newLine.setLo_orderdate(dateTable.get(r.getD_date()).getD_datekey());
            return newLine;
        }
        //If the date dont exists
        createDataInTheDataBase(r.getD_date());
        // Data related with Date Table
        if (dateTable.containsKey(r.getD_date())) {
            newLine.setLo_orderdate(dateTable.get(r.getD_date()).getD_datekey());
        }
        return newLine;
    }

    /**
     *
     * @param d_date
     * @return
     */
    public void createDataInTheDataBase(String d_date) {
        String[] spliting = d_date.split(" ");
        TransformDate tDate = new TransformDate();
        ArrayList<SsbDate> dates = tDate.transform(new DateKafkaInput(spliting[2], spliting[0], spliting[1].replace(",", "")));
        dates.forEach(value -> ssbLT.write(value.sqlInsert()));
        dateTable = ssbLT.getDateTable();
    }

    /**
     *
     * @param newLine
     * @param r
     * @return
     */
    public Lineorder finishingLineorderCreation(Lineorder newLine, Record r) {
        newLine.setLo_orderpriority(r.getLo_orderpriority());
        newLine.setLo_shipprioriry(r.getLo_shippriority());
        newLine.setLo_quantity(r.getLo_quantity());
        newLine.setLo_extendedprice(r.getLo_extendedprice());
        newLine.setLo_ordertotalprice(r.getLo_ordertotalprice());
        newLine.setLo_discount(r.getLo_discount());
        newLine.setLo_supplycost(r.getLo_supplycost());
        newLine.setLo_tax(r.getLo_tax());
        newLine.setLo_shipmode(r.getLo_shipmode());
        //lo_revenue = lo_extendedprice*(100-lo_discnt))/100)
        newLine.setLo_revenue((r.getLo_extendedprice() * (100 - r.getLo_discount())) / 100);
        // LO_COMMITDATE = O_ORDERDATE + random value [30 .. 90]
        Random rnd = new Random();
        newLine.setLo_commitdate(newLine.getLo_orderdate() + rnd.nextInt(90 - 30) + 30);
        return newLine;
    }

    /**
     * Create Kafka Consumer
     *
     * @return
     * @throws IOException
     */
    public static KafkaConsumer<String, String> createConsumer() throws IOException {
        Properties properties = setProperties();
        String topic = properties.getProperty("topic");
        // Create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        // Subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topic));
        return consumer;
    }

    /**
     * Set the Kafka Consumer properties in run time
     *
     * @return Properties object configured
     */
    public static Properties setProperties() {
        Properties properties = new Properties();
        properties.setProperty("topic", TOPIC_NAME);
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("group.id", "voltage-reading-elasticsearch");
        properties.setProperty("auto.offset.reset", "latest");
        properties.setProperty("enable.auto.commit", "false");
        return properties;
    }

    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Consumer consumer = new Consumer();
        consumer.run();
    }

}
