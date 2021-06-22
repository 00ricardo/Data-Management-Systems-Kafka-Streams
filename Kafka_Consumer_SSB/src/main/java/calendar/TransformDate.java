package calendar;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author ricar
 */
public class TransformDate {

    
    public TransformDate(){
        //
    }
    public ArrayList<SsbDate> transform(DateKafkaInput date) {         
        int month_aux,daysInMonth,kafka_year_int,kafka_month_int,kafka_day_int, d_datekey,d_year,d_yearmonthnum,d_daynuminyear,d_daynuminmonth,d_lastdayinweekfl,d_weeknuminyear,d_monthnuminyear,d_lastdayinmothfl,d_daynuminweek,d_holidayfl,d_weekdayfl;        
        String season, d_datekey_string, d_date,d_month,d_yearmonthnum_string,d_yearmonth; 
        String d_dayofweek = "";
        ArrayList<SsbDate> result = new ArrayList<>();
        ArrayList<Integer> months = new ArrayList<>();
        ArrayList<String> months_name = new ArrayList<>();
        ArrayList<String> months_prefix = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        
        
        String kafka_year = date.getYear(), kafka_month = date.getMonth(), kafka_day = date.getDay();
        
        SsbDate record;
          
        kafka_year_int = Integer.parseInt(kafka_year);                  //parsing int
        kafka_month_int = Integer.parseInt(kafka_month);                //parsing int
        kafka_day_int = Integer.parseInt(kafka_day);                    //parsing int                                            
        month_aux = kafka_month_int+1;
        YearMonth yearMonthObject = YearMonth.of(kafka_year_int, (month_aux));                 
        daysInMonth  = yearMonthObject.lengthOfMonth();
                
        generateArrayMonths(months_name,months_prefix);
        
        for(int i= 0; i<12; i++){  //for each month of the year         
            months.add(daysInMonth);            
            if(month_aux == 13){
                month_aux = 12;                
            }           
            kafka_month_int = i;
            yearMonthObject = YearMonth.of(kafka_year_int, (month_aux));            
            daysInMonth  = yearMonthObject.lengthOfMonth();             
            month_aux++;
            for(int j=0; j<daysInMonth; j++){ //for each day of the month
                kafka_day_int = (j+1);
                kafka_day = String.valueOf(kafka_day_int);
                kafka_month = String.valueOf(kafka_month_int);
                        
                calendar.set(Calendar.YEAR, kafka_year_int);
                calendar.set(Calendar.MONTH, kafka_month_int); //21 Fev 1992 - Sabado (Start in 0)
                calendar.set(Calendar.DAY_OF_MONTH,kafka_day_int);
                
                if(kafka_day_int >= 0 && kafka_day_int <10){
                    d_datekey_string = kafka_year + kafka_month + "1" + "0" + kafka_day;       //transforming d_datekey  
                }else{
                    d_datekey_string = kafka_year + kafka_month + "1" + kafka_day;       //transforming d_datekey
                }
                d_yearmonthnum_string = kafka_year + kafka_month + kafka_day;
                d_datekey = Integer.parseInt(d_datekey_string);                     //parse int
                d_date = months_name.get(i) + kafka_day + ", " + kafka_year;                
                d_month = months_name.get(i);
                d_year = Integer.parseInt(kafka_year);
                d_yearmonthnum = Integer.parseInt(d_yearmonthnum_string);
                d_yearmonth = months_prefix.get(i) + kafka_year;

                d_daynuminyear = calendar.get((Calendar.DAY_OF_YEAR));
                d_daynuminmonth = calendar.get(Calendar.DAY_OF_MONTH);
                d_monthnuminyear = Integer.sum(calendar.get(Calendar.MONTH),1);
                d_weeknuminyear = calendar.get(Calendar.WEEK_OF_YEAR);     

                //check if the the first day is monday (DAY_OF_WEEK == 7+1)  
                if(Integer.sum(calendar.get(Calendar.DAY_OF_WEEK),1) == 8){
                    d_daynuminweek = 1;
                }else{
                    d_daynuminweek = Integer.sum(calendar.get(Calendar.DAY_OF_WEEK),1);  
                }

                //check if its Sunday
                if(calendar.getActualMaximum(Calendar.DAY_OF_WEEK) == calendar.get(Calendar.DAY_OF_WEEK)){
                    d_lastdayinweekfl = 1;   //Yes   
                }else{
                    d_lastdayinweekfl = 0;   //No 
                }

                //check if its the last day of the month
                if(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)){
                    d_lastdayinmothfl = 1;   //Yes       
                }else{
                    d_lastdayinmothfl = 0;   //No
                }

                // holidays months: 1,2,4,5,7,8,9,10,11,12
                //check if its christmas night
                if(calendar.get(Calendar.DAY_OF_MONTH) == 24 && d_monthnuminyear == 12){
                    d_holidayfl = 1;
                }
                else if(calendar.get(Calendar.DAY_OF_MONTH) == 20 && (d_monthnuminyear == 1 || d_monthnuminyear == 2 || d_monthnuminyear == 4 ||d_monthnuminyear == 5 || d_monthnuminyear == 7 || d_monthnuminyear == 8 || d_monthnuminyear == 9 ||d_monthnuminyear == 10 ||d_monthnuminyear == 11)){
                    d_holidayfl = 1;   
                }else if(calendar.get(Calendar.DAY_OF_MONTH) == 1 && d_monthnuminyear == 1){
                    d_holidayfl = 1;
                }
                else{
                    d_holidayfl = 0;
                }


                //check the season of the month
                if(d_monthnuminyear >= 1 && d_monthnuminyear < 4){
                    season = "Winter";
                }
                else if(d_monthnuminyear == 11 || d_monthnuminyear == 12){
                    season = "Christmas";
                }
                else if(d_monthnuminyear == 4){
                    season = "Spring";
                } 
                else if(d_monthnuminyear >= 5 && d_monthnuminyear <= 8){
                    season = "Summer";
                }else{
                   season = "Fall";
                }

                //check if the the current day is a week day
                if(d_daynuminweek != 1 && d_daynuminweek != 7){
                    d_weekdayfl = 1;  
                }else{
                    d_weekdayfl = 0;
                }

                switch (d_daynuminweek) {
                    case 1:
                        d_dayofweek = "Sunday";
                        break;
                    case 2:
                        d_dayofweek = "Monday";
                        break;
                    case 3:
                         d_dayofweek = "Tuesday";
                        break;
                    case 4:
                         d_dayofweek = "Wednesday";
                        break;
                    case 5:
                         d_dayofweek = "Thursday";
                        break;
                    case 6:
                         d_dayofweek = "Friday";
                        break;
                    case 7:
                         d_dayofweek = "Saturday";
                         break;
                }
                record = new SsbDate(d_datekey,d_date,d_dayofweek,d_month,d_year,d_yearmonthnum,d_yearmonth,d_daynuminweek,d_daynuminmonth,d_daynuminyear,d_monthnuminyear,d_weeknuminyear,season,d_lastdayinweekfl,d_lastdayinmothfl,d_holidayfl,d_weekdayfl);
                result.add(record); 
            } 
        }
        return result;
    }
    
    public void generateArrayMonths(ArrayList<String> months_name, ArrayList<String> months_prefix){
     
        months_name.add("January ");
        months_name.add("February ");
        months_name.add("March ");
        months_name.add("April ");
        months_name.add("May ");
        months_name.add("June ");
        months_name.add("July ");
        months_name.add("August ");
        months_name.add("September ");
        months_name.add("October ");
        months_name.add("November ");
        months_name.add("December ");
        
        months_prefix.add("Jan");
        months_prefix.add("Feb");
        months_prefix.add("Mar");
        months_prefix.add("Apr");
        months_prefix.add("May");
        months_prefix.add("Jun");
        months_prefix.add("Jul");
        months_prefix.add("Aug");
        months_prefix.add("Sep");
        months_prefix.add("Oct");
        months_prefix.add("Nov");
        months_prefix.add("Dec");   
    }

}
