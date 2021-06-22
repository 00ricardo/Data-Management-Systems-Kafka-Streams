package db.tables;

import java.util.Random;

/**
 *
 * @author ricar
 */
public class Part {
    
    public static final String MFGR = "MFGR#";
    
    private int p_partkey;
    private String p_name;
    private String p_mfgr;
    private String p_category;
    private String p_brand1;
    private String p_color;
    private String p_type;
    private int p_size;
    private String p_container;
 
    public Part() {
        
    }
    public Part(int p_partkey, String p_name, String p_color) {
        this.p_partkey = p_partkey;
        this.p_name = p_name;
        this.p_color = p_color;
    }
    public int getP_partkey() {
        return p_partkey;
    }

    public void setP_partkey(int p_partkey) {
        this.p_partkey = p_partkey;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_color() {
        return p_color;
    }

    public void setP_color(String p_color) {
        this.p_color = p_color;
    }
    
    public void generateRandomData(){
        this.p_mfgr = MFGR + generateRandomInt(1, 5);
        this.p_category = MFGR + generateRandomInt(1, 5) + generateRandomInt(1, 5);
        this.p_brand1 = this.p_category + generateRandomInt(1, 40);
        this.p_type = "ND";
        this.p_size = generateRandomInt(1, 50);
        this.p_container = "ND";
    }

    public String toSqlInsert(){
        return "INSERT INTO part VALUES ("
                + "'" + p_partkey + "', "
                + "'" + p_name + "', "
                + "'" + p_mfgr + "', "
                + "'" + p_category + "', "
                + "'" + p_brand1 + "', "
                + "'" + p_color + "', "
                + "'" + p_type + "', "
                + "'" + p_size + "', "
                + "'" + p_container + "');";
    }
    
    /**
     * Generate a random int value between low and high value
     * @param low
     * @param high
     * @return 
     */
    public int generateRandomInt(int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

}
