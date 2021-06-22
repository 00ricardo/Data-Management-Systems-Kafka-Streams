package consumer;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Transformation {

    public static void stringToLineorder(String value) {
        String[] splits = value.split("[:,]");
        String p_name = splits[1];               // Table Part 
        String p_color = splits[3];              // Table Part
        String c_name = splits[5];               // Table Customer
        String d_date = splits[7];               // Table Date
        String s_name = splits[9];               // Table Suplier
        String lo_orderpriority = splits[11];    // Table Lineorder
        String lo_shippriority = splits[13];     // Table Lineorder
        int lo_quantity = Integer.parseInt(splits[15]);              // Table Lineorder    
        int lo_extendedprice = Integer.parseInt(splits[17]);         // Table Lineorder
        int lo_ordertotalprice = Integer.parseInt(splits[21]);       // Table Lineorder
        int lo_discount = Integer.parseInt(splits[23]);              // Table Lineorder
        int lo_supplycost = Integer.parseInt(splits[25]);            // Table Lineorder
        int lo_tax = Integer.parseInt(splits[27]);                   // Table Lineorder
        String lo_shipmode = splits[29];                             // Table Lineorder
    }

    /**
     * Transform the received line into a Record
     * @param line to be transformed
     * @return a newrecord
     */
    public Record transformLineToRecord(String line) {
        char[] lineArray = line.toCharArray();
        int counter = 0;
        String field = "";
        ArrayList<String> fields = new ArrayList<>();

        // Split field by field
        for (int i = 0; i < line.length(); i++) {
            // Verify if character is equal to an "
            if (lineArray[i] == '"') {
                counter++;
                // End of a field
                if (counter == 2) {
                    counter = 0;
                    fields.add(field);
                    field = "";
                }
            }
            // Verify if is the priority
            if (lineArray[i] == '0' && counter == 0) {
                String auxStr = "" + lineArray[i];
                fields.add(auxStr);
            }
            // Save the field information character by character
            if (counter > 0 && counter < 2 && lineArray[i] != '"') {
                field = field + lineArray[i];
            }
        } // End For
        // Create new Record and save them
        String[] spliting;
        int aux;
        spliting = fields.get(0).split(" ");
        String p_name = spliting[0] + spliting[1];
        String p_color = spliting[2];
        
        String c_name = fields.get(1).replace(" ", "");
        spliting = c_name.split("#");
        aux = Integer.parseInt(spliting[1]);
        spliting[1] = String.format("%09d", aux);
        c_name = spliting[0] + "#" + spliting[1];
        
        String d_date = fields.get(3) + " " + fields.get(2) + ", " + fields.get(4);
        
        String s_name = fields.get(5).replace(" ", "");
        spliting = s_name.split("#");
        aux = Integer.parseInt(spliting[1]);
        spliting[1] = String.format("%09d", aux);
        s_name = spliting[0] + "#" + spliting[1];
        
        String lo_orderpriority = fields.get(6);
        String lo_shippriority = fields.get(7);
        spliting = fields.get(8).split("[.]");
        int lo_quantity = Integer.parseInt(spliting[0]);
        spliting = fields.get(9).split("[.]");
        int lo_extendedprice = Integer.parseInt(spliting[0]);
        spliting = fields.get(10).split("[.]");
        int lo_ordertotalprice = Integer.parseInt(spliting[0]);
        spliting = fields.get(11).split("[.]");
        int lo_discount = Integer.parseInt(spliting[0]);
        spliting = fields.get(12).split("[.]");
        int lo_supplycost = Integer.parseInt(spliting[0]);
        spliting = fields.get(13).split("[.]");
        int lo_tax = Integer.parseInt(spliting[0]);
        String lo_shipmode = fields.get(14);
        Record newRecord = new Record(p_name, p_color, c_name, d_date, s_name, lo_orderpriority, lo_shippriority,
                lo_quantity, lo_extendedprice, lo_ordertotalprice, lo_discount, lo_supplycost, lo_tax, lo_shipmode);
       
        return newRecord;
    }

}
