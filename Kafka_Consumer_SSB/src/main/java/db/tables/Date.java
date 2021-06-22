package db.tables;

/**
 *
 * @author ricar
 */
public class Date {
    private int d_datekey;
    private String d_date;

    public Date() {
       
    }
    public Date(int d_datekey, String d_date) {
        this.d_datekey = d_datekey;
        this.d_date = d_date;
    }
    
    public int getD_datekey() {
        return d_datekey;
    }

    public void setD_datekey(int d_datekey) {
        this.d_datekey = d_datekey;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }
    
    

}
