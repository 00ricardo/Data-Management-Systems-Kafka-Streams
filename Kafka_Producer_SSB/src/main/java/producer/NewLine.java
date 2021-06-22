package producer;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class NewLine {
    private String line;
    private String costumer_name;

    public NewLine(String line) {
        this.line = line;
        this.costumer_name = getCustomerName(line);
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCostumer_name() {
        return costumer_name;
    }

    public void setCostumer_name(String costumer_name) {
        this.costumer_name = costumer_name;
    }
    
    
    
    public String getCustomerName(String line) {
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
        // Get Customer name
        String[] spliting;
        String c_name = fields.get(1).replace(" ", "");      
        return c_name;
    }

}
