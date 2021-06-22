package calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ricar
 */
public class DateKafkaInput {
    
    Map<String, String> months = new HashMap<>();
    
    private String year;
    private String month;
    private String day;
    private ArrayList<SsbDate> dates;
    

    
    public DateKafkaInput(String year, String month, String day) {
        initializeMonth();
        this.year = year;
        this.month = months.get(month);
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<SsbDate> getDates() {
        return dates;
    }

    public void setDates(ArrayList<SsbDate> dates) {
        this.dates = dates;
    }
    
    
    public void initializeMonth(){
        months.put("January", "1");
        months.put("March", "2");
        months.put("March", "3");
        months.put("April", "4");
        months.put("May", "5");
        months.put("June", "6");
        months.put("July", "7");
        months.put("August", "8");
        months.put("September", "9");
        months.put("October", "10");
        months.put("November", "11");
        months.put("December", "12");
    }
       
}
