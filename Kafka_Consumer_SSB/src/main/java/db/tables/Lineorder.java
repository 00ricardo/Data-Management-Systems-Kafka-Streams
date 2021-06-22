package db.tables;

/**
 *
 * @author david
 */
public class Lineorder {
    private int lo_orderkey;
    private int lo_linenumber;
    private int lo_custkey;
    private int lo_partkey;
    private int lo_suppkey;
    private int lo_orderdate;
    private String lo_orderpriority;
    private String lo_shipprioriry;
    private int lo_quantity;
    private int lo_extendedprice;
    private int lo_ordertotalprice;
    private int lo_discount;
    private int lo_revenue;
    private int lo_supplycost;
    private int lo_tax;
    private int lo_commitdate;
    private String lo_shipmode;
    

    public Lineorder() {
        
    }

    public Lineorder(int lo_orderkey, int lo_linenumber, int lo_custkey,
            int lo_partkey, int lo_suppkey, int lo_orderdate, String lo_orderpriority,
            String lo_shipprioriry, int lo_quantity, int lo_extendedprice,
            int lo_ordertotalprice, int lo_discount, int lo_revenue, int lo_supplycost,
            int lo_tax, int lo_commitdate, String lo_shipmode) {
        
        this.lo_orderkey = lo_orderkey;
        this.lo_linenumber = lo_linenumber;
        this.lo_custkey = lo_custkey;
        this.lo_partkey = lo_partkey;
        this.lo_suppkey = lo_suppkey;
        this.lo_orderdate = lo_orderdate;
        this.lo_orderpriority = lo_orderpriority;
        this.lo_shipprioriry = lo_shipprioriry;
        this.lo_quantity = lo_quantity;
        this.lo_extendedprice = lo_extendedprice;
        this.lo_ordertotalprice = lo_ordertotalprice;
        this.lo_discount = lo_discount;
        this.lo_revenue = lo_revenue;
        this.lo_supplycost = lo_supplycost;
        this.lo_tax = lo_tax;
        this.lo_commitdate = lo_commitdate;
        this.lo_shipmode = lo_shipmode;
    }

    public int getLo_orderkey() {
        return lo_orderkey;
    }

    public void setLo_orderkey(int lo_orderkey) {
        this.lo_orderkey = lo_orderkey;
    }

    public int getLo_linenumber() {
        return lo_linenumber;
    }

    public void setLo_linenumber(int lo_linenumber) {
        this.lo_linenumber = lo_linenumber;
    }

    public int getLo_custkey() {
        return lo_custkey;
    }

    public void setLo_custkey(int lo_custkey) {
        this.lo_custkey = lo_custkey;
    }

    public int getLo_partkey() {
        return lo_partkey;
    }

    public void setLo_partkey(int lo_partkey) {
        this.lo_partkey = lo_partkey;
    }

    public int getLo_suppkey() {
        return lo_suppkey;
    }

    public void setLo_suppkey(int lo_suppkey) {
        this.lo_suppkey = lo_suppkey;
    }

    public int getLo_orderdate() {
        return lo_orderdate;
    }

    public void setLo_orderdate(int lo_orderdate) {
        this.lo_orderdate = lo_orderdate;
    }

    public String getLo_orderpriority() {
        return lo_orderpriority;
    }

    public void setLo_orderpriority(String lo_orderpriority) {
        this.lo_orderpriority = lo_orderpriority;
    }

    public String getLo_shipprioriry() {
        return lo_shipprioriry;
    }

    public void setLo_shipprioriry(String lo_shipprioriry) {
        this.lo_shipprioriry = lo_shipprioriry;
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

    public int getLo_revenue() {
        return lo_revenue;
    }

    public void setLo_revenue(int lo_revenue) {
        this.lo_revenue = lo_revenue;
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

    public int getLo_commitdate() {
        return lo_commitdate;
    }

    public void setLo_commitdate(int lo_commitdate) {
        this.lo_commitdate = lo_commitdate;
    }

    public String getLo_shipmode() {
        return lo_shipmode;
    }

    public void setLo_shipmode(String lo_shipmode) {
        this.lo_shipmode = lo_shipmode;
    }
    
    
    
    
    public String sqlInsert() {
        return "INSERT INTO lineorder VALUES ("
                + "'" + lo_orderkey + "', "
                + "'" + lo_linenumber + "', "
                + "'" + lo_custkey + "', "
                + "'" + lo_partkey + "', "
                + "'" + lo_suppkey + "', "
                + "'" + lo_orderdate + "', "
                + "'" + lo_orderpriority + "', "
                + "'" + lo_shipprioriry + "', "
                + "'" + lo_quantity + "', "
                + "'" + lo_extendedprice + "', "
                + "'" + lo_ordertotalprice + "', "
                + "'" + lo_discount + "', "
                + "'" + lo_revenue + "', "
                + "'" + lo_supplycost + "', "
                + "'" + lo_tax + "', "
                + "'" + lo_commitdate + "', "
                + "'" + lo_shipmode + "');";
    }
    
    
    
}
