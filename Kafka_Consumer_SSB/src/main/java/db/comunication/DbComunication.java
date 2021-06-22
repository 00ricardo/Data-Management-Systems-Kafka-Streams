package db.comunication;

import db.tables.Supplier;
import db.tables.Date;
import db.tables.Customer;
import db.tables.Part;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ricar
 */
public class DbComunication {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres";
    
    HashMap<String, Supplier> supplierTable = new HashMap<>();  
    HashMap<String, Customer> customerTable = new HashMap<>();
    HashMap<String, Part> partTable = new HashMap<>();
    HashMap<String, Date> dateTable = new HashMap<>();  
    
    Connection conn;

    public DbComunication() {
        try {
            this.connect(user, password, url);
        } catch (SQLException ex) {
            Logger.getLogger(DbComunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void connect(String username,String password,String url) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user",username);
        props.setProperty("password",password);
        conn = DriverManager.getConnection(url, props);
    }
    

    /**
     * 
     * @return 
     */
    public HashMap<String, Supplier> getSupplierTable(){
        try{
            supplierTable.clear();
            Statement statement = conn.createStatement();
            String query = "SELECT s_suppkey,s_name from supplier";
            ResultSet queryResult = statement.executeQuery(query);
                    
            while(queryResult.next()) {   // getting all the supplier data
                Supplier supplier = new Supplier(queryResult.getInt("s_suppkey"),queryResult.getString("s_name")); 
                supplierTable.put(supplier.getS_name(), supplier);
            }          
        }
        catch (SQLException e){
            e.printStackTrace();
        }    
        return supplierTable;
    }
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Customer> getCustomerTable(){
        try{
            customerTable.clear();
            Statement statement = conn.createStatement();
            String query = "SELECT c_custkey,c_name from customer";
            ResultSet queryResult = statement.executeQuery(query);
                     
            while(queryResult.next()) {   // getting all the customer data
                Customer customer = new Customer(queryResult.getInt("c_custkey"),queryResult.getString("c_name")); 
                customerTable.put(customer.getC_name(), customer);
            }          
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return customerTable;
    }
    
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Part> getPartTable(){
        try{
            partTable.clear();
            Statement statement = conn.createStatement();
            String query = "SELECT p_partkey,p_name,p_color from part";
            ResultSet queryResult = statement.executeQuery(query);
                      
            while(queryResult.next()) {   // getting all the part data
                Part part = new Part(queryResult.getInt("p_partkey"),queryResult.getString("p_name"),queryResult.getString("p_color")); 
                partTable.put(part.getP_name()+part.getP_color(), part);
            }     
        }
        catch (SQLException e){
            e.printStackTrace();
        } 
        return partTable;
    }
    
    /**
     * 
     * @return 
     */
    public HashMap<String, Date> getDateTable(){
        try{
            dateTable.clear();
            Statement statement = conn.createStatement();
            String query = "SELECT d_datekey,d_date from date";
            ResultSet queryResult = statement.executeQuery(query);
                    
            while(queryResult.next()) {   // getting all the Date data
                Date date = new Date(queryResult.getInt("d_datekey"), queryResult.getString("d_date")); 
                dateTable.put(date.getD_date(), date);
            }          
        }
        catch (SQLException e){
            e.printStackTrace();
        } 
        return dateTable;
    }
    
    
    /**
     * 
     * @return 
     */
    public int getLastLineorderKey(){
        int lastKey = -1;
        try{
            Statement statement = conn.createStatement();
            String query = "SELECT lo_orderkey FROM lineorder ORDER BY lo_orderkey DESC LIMIT 1";
            ResultSet queryResult = statement.executeQuery(query);
                    
            while(queryResult.next())  
                lastKey = queryResult.getInt("lo_orderkey");
        }
        catch (SQLException e){
            e.printStackTrace();
        } 
        return lastKey;
    }
    
    /**
     * 
     * @return 
     */
    public int getLastPartKey(){
        int lastKey = -1;
        try{
            Statement statement = conn.createStatement();
            String query = "SELECT p_partkey FROM part ORDER BY p_partkey DESC LIMIT 1";
            ResultSet queryResult = statement.executeQuery(query);
                    
            while(queryResult.next())  
                lastKey = queryResult.getInt("p_partkey");
        }
        catch (SQLException e){
            e.printStackTrace();
        } 
        return lastKey;
    }
    

    public boolean write(String query)  {
        try {
            Statement statement = conn.createStatement();
            boolean ret = statement.execute(query);
            return ret;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }       
}

