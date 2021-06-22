package consumer;

/**
 *
 * @author david
 */
public class Record {
    
    // Lables to identifie the fields
    public static final String P_NAME = "p_name";
    public static final String P_COLOR = "p_color";
    public static final String C_NAME = "c_name";
    public static final String D_DATE = "d_date";
    public static final String S_NAME = "s_name";
    public static final String LO_ORDERPRIORITY = "lo_orderpriority";
    public static final String LO_SHIPPRIORITY = "lo_shippriority";
    public static final String LO_QUANTITY = "lo_quantity";
    public static final String LO_EXTENDEDPRICE = "lo_extendedprice";
    public static final String LO_ORDERTOTALPRICE = "lo_ordertotalprice";
    public static final String LO_DISCOUNT = "lo_discount";
    public static final String LO_SUPPLYCOST = "lo_supplycost";
    public static final String LO_TAX = "lo_tax";
    public static final String LO_SHIPMODE = "lo_shipmode";
    
    // Fields from the file that matchs with the SSB DB fields
    private String p_name;              // Table Part 
    private String p_color;             // Table Part
    private String c_name;              // Table Customer
    private String d_date;              // Table Date
    private String s_name;              // Table Suplier
    private String lo_orderpriority;    // Table Lineorder
    private String lo_shippriority;     // Table Lineorder
    private int lo_quantity;            // Table Lineorder    
    private int lo_extendedprice;       // Table Lineorder
    private int lo_ordertotalprice;     // Table Lineorder
    private int lo_discount;            // Table Lineorder
    private int lo_supplycost;          // Table Lineorder
    private int lo_tax;                 // Table Lineorder
    private String lo_shipmode;         // Table Lineorder
    
    
    public Record(String p_name, String p_color, String c_name, String d_date,
            String s_name, String lo_orderpriority, String lo_shippriority,
            int lo_quantity, int lo_extendedprice, int lo_ordertotalprice,
            int lo_discount, int lo_supplycost, int lo_tax, String lo_shipmode) {
        
        this.p_name = p_name;
        this.p_color = p_color;
        this.c_name = c_name;
        this.d_date = d_date;
        this.s_name = s_name;
        this.lo_orderpriority = lo_orderpriority;
        this.lo_shippriority = lo_shippriority;
        this.lo_quantity = lo_quantity;
        this.lo_extendedprice = lo_extendedprice;
        this.lo_ordertotalprice = lo_ordertotalprice;
        this.lo_discount = lo_discount;
        this.lo_supplycost = lo_supplycost;
        this.lo_tax = lo_tax;
        this.lo_shipmode = lo_shipmode;
    }
    
    
    /**
     * Returns a String with the an order information's.
     * @return 
     */
    @Override
    public String toString() {
        return "{" +
                "\"" + P_NAME + "\": " + "\"" + p_name + "\", " +   
                "\"" + P_COLOR + "\": " + "\"" + p_color + "\", " +
                "\"" + C_NAME +  "\": " + "\"" + c_name + "\", " +
                "\"" + D_DATE +  "\": " + "\"" + d_date + "\", " +
                "\"" + S_NAME +  "\": " + "\"" + s_name + "\", " +
                "\"" + LO_ORDERPRIORITY + "\": " +  "\"" + lo_orderpriority + "\", " +
                "\"" + LO_SHIPPRIORITY +  "\": " + "\"" + lo_shippriority + "\", " + 
                "\"" + LO_QUANTITY +  "\": " + lo_quantity + ", " +
                "\"" + LO_EXTENDEDPRICE +  "\": " + lo_extendedprice + ", " +
                "\"" + LO_ORDERTOTALPRICE + "\": " + lo_ordertotalprice + ", " +
                "\"" + LO_DISCOUNT + "\": " + lo_discount + ", " +
                "\"" + LO_SUPPLYCOST + "\": " + lo_supplycost + ", " +
                "\"" + LO_TAX + "\": " + lo_tax + ", " +
                "\"" + LO_SHIPMODE + "\": " + "\"" + lo_shipmode + "\"" + 
                "}";
    }
    
    /**
     * 
     * 
     * Getters and Setters
     * 
     * 
     */

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

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getLo_orderpriority() {
        return lo_orderpriority;
    }

    public void setLo_orderpriority(String lo_orderpriority) {
        this.lo_orderpriority = lo_orderpriority;
    }

    public String getLo_shippriority() {
        return lo_shippriority;
    }

    public void setLo_shippriority(String lo_shippriority) {
        this.lo_shippriority = lo_shippriority;
    }

    public int getLo_quantity() {
        return lo_quantity;
    }

    public void setLo_quantity(int lo_quantity) {
        this.lo_quantity = lo_quantity;
    }

    public int getLo_extendedprice() {
        return lo_extendedprice;
    }

    public void setLo_extendedprice(int lo_extendedprice) {
        this.lo_extendedprice = lo_extendedprice;
    }

    public int getLo_ordertotalprice() {
        return lo_ordertotalprice;
    }

    public void setLo_ordertotalprice(int lo_ordertotalprice) {
        this.lo_ordertotalprice = lo_ordertotalprice;
    }

    public int getLo_discount() {
        return lo_discount;
    }

    public void setLo_discount(int lo_discount) {
        this.lo_discount = lo_discount;
    }

    public int getLo_supplycost() {
        return lo_supplycost;
    }

    public void setLo_supplycost(int lo_supplycost) {
        this.lo_supplycost = lo_supplycost;
    }

    public int getLo_tax() {
        return lo_tax;
    }

    public void setLo_tax(int lo_tax) {
        this.lo_tax = lo_tax;
    }

    public String getLo_shipmode() {
        return lo_shipmode;
    }

    public void setLo_shipmode(String lo_shipmode) {
        this.lo_shipmode = lo_shipmode;
    }
   
}
