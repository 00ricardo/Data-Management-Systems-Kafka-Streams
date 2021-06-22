package db.tables;

/**
 *
 * @author ricar
 */
public class Customer {    
    
    public static final String CUSTOMER = "Customer";
    public static final String ND = "ND";
    
    
    private int c_custkey;
    private String c_name;
    private String c_address;
    private String c_city;
    private String c_nation;
    private String c_region;
    private String c_phone;
    private String c_mktsegment;  
    
    
    public Customer() {
     
    }
    public Customer(int c_custkey,String c_name) {
        this.c_custkey = c_custkey;
        this.c_name = c_name;
    }
    
    public Customer(int c_custkey){
        this.c_custkey = c_custkey;
        String code = String.format("%09d", c_custkey);
        this.c_name = CUSTOMER + "#" + code;
        this.c_address = ND;
        this.c_city = ND;
        this.c_nation = ND;
        this.c_region = ND;
        this.c_phone = ND;
        this.c_mktsegment = ND;
    }

    public int getC_custkey() {
        return c_custkey;
    }

    public void setC_custkey(int c_custkey) {
        this.c_custkey = c_custkey;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_city() {
        return c_city;
    }

    public void setC_city(String c_city) {
        this.c_city = c_city;
    }

    public String getC_nation() {
        return c_nation;
    }

    public void setC_nation(String c_nation) {
        this.c_nation = c_nation;
    }

    public String getC_region() {
        return c_region;
    }

    public void setC_region(String c_region) {
        this.c_region = c_region;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_mktsegment() {
        return c_mktsegment;
    }

    public void setC_mktsegment(String c_mktsegment) {
        this.c_mktsegment = c_mktsegment;
    }
    
    public String toSqlInsert(){
        return "INSERT INTO customer VALUES ("
                + "'" + c_custkey + "', "
                + "'" + c_name + "', "
                + "'" + c_address + "', "
                + "'" + c_city + "', "
                + "'" + c_nation + "', "
                + "'" + c_region + "', "
                + "'" + c_phone + "', "
                + "'" + c_mktsegment + "');";
    }

    
}
