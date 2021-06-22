package db.tables;

/**
 *
 * @author ricar
 */
public class Supplier {

    public static final String SUPPLIER = "Suppplier";  
    public static final String ND = "ND";
    
    private int s_suppkey;
    private String s_name;
    private String s_address;
    private String s_city;
    private String s_nation;
    private String s_region;
    private String s_phone;
    
        
    
    public Supplier() {
     
    }
    
    public Supplier(int s_suppkey, String s_name) {
        this.s_suppkey = s_suppkey;
        this.s_name = s_name;
    }
    
    public Supplier(int s_suppkey){
        this.s_suppkey = s_suppkey;
        String code = String.format("%09d", s_suppkey);
        this.s_name = SUPPLIER + "#" + code;
        this.s_address = ND;
        this.s_city = ND;
        this.s_nation = ND;
        this.s_region = ND;
        this.s_phone = ND;
    }
    
    
    public int getS_suppkey() {
        return s_suppkey;
    }

    public void setS_suppkey(int s_suppkey) {
        this.s_suppkey = s_suppkey;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }
    
    public String toSqlInsert(){
        return "INSERT INTO supplier VALUES ("
                + "'" + s_suppkey + "', "
                + "'" + s_name + "', "
                + "'" + s_address + "', "
                + "'" + s_city + "', "
                + "'" + s_nation + "', "
                + "'" + s_region + "', "
                + "'" + s_phone + "');";
    }
  
}
    