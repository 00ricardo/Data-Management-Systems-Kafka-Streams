package calendar;

/**
 *
 * @author ricar
 */
public class SsbDate {

    private int d_datekey;                  //id
    private String d_date;                  //format date: January 1, 1992
    private String d_dayofweek;             //day of the week
    private String d_month;                 //month
    private int d_year;                     //year
    private int d_yearmonthnum;             //format year+month: 199201
    private String d_yearmonth;             //format month+year: Jan1992
    private int d_daynuminweek;             //day number in week (if its sunday then its 1)
    private int d_daynuminmonth;            //day number in month (if its January 1 then its 1)
    private int d_daynuminyear;             //day number in year (if its 31 December then its 365)
    private int d_monthnuminyear;           //month number in year
    private int d_weeknuminyear;            //week number in year
    private String d_sellingseason;         //Winter, Spring, etc
    private int d_lastdayinweekfl;          //last day in week (is it saturday? 1= Yes | 0= No)
    private int d_lastdayinmonthfl;         //last day in month?
    private int d_holidayfl;                //é feriado?
    private int d_weekdayfl;                //é dia de semana?

    /**
     *
     * @Constructor
     */
    public SsbDate() {

    }

    public SsbDate(int d_datekey, String d_date, String d_dayofweek, String d_month, int d_year, int d_yearmonthnum, String d_yearmonth, int d_daynuminweek, int d_daynuminmonth, int d_daynuminyear, int d_monthnuminyear, int d_weeknuminyear, String d_sellingseason, int d_lastdayinweekfl, int d_lastdayinmonthfl, int d_holidayfl, int d_weekdayfl) {
        this.d_datekey = d_datekey;
        this.d_date = d_date;
        this.d_dayofweek = d_dayofweek;
        this.d_month = d_month;
        this.d_year = d_year;
        this.d_yearmonthnum = d_yearmonthnum;
        this.d_yearmonth = d_yearmonth;
        this.d_daynuminweek = d_daynuminweek;
        this.d_daynuminmonth = d_daynuminmonth;
        this.d_daynuminyear = d_daynuminyear;
        this.d_monthnuminyear = d_monthnuminyear;
        this.d_weeknuminyear = d_weeknuminyear;
        this.d_sellingseason = d_sellingseason;
        this.d_lastdayinweekfl = d_lastdayinweekfl;
        this.d_lastdayinmonthfl = d_lastdayinmonthfl;
        this.d_holidayfl = d_holidayfl;
        this.d_weekdayfl = d_weekdayfl;

    }

    /**
     *
     * @Getters & Setters
     */
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

    public String getD_dayofweek() {
        return d_dayofweek;
    }

    public void setD_dayofweek(String d_dayofweek) {
        this.d_dayofweek = d_dayofweek;
    }

    public String getD_month() {
        return d_month;
    }

    public void setD_month(String d_month) {
        this.d_month = d_month;
    }

    public int getD_year() {
        return d_year;
    }

    public void setD_year(int d_year) {
        this.d_year = d_year;
    }

    public int getD_yearmonthnum() {
        return d_yearmonthnum;
    }

    public void setD_yearmonthnum(int d_yearmonthnum) {
        this.d_yearmonthnum = d_yearmonthnum;
    }

    public String getD_yearmonth() {
        return d_yearmonth;
    }

    public void setD_yearmonth(String d_yearmonth) {
        this.d_yearmonth = d_yearmonth;
    }

    public int getD_daynuminweek() {
        return d_daynuminweek;
    }

    public void setD_daynuminweek(int d_daynuminweek) {
        this.d_daynuminweek = d_daynuminweek;
    }

    public int getD_daynuminmonth() {
        return d_daynuminmonth;
    }

    public void setD_daynuminmonth(int d_daynuminmonth) {
        this.d_daynuminmonth = d_daynuminmonth;
    }

    public int getD_daynuminyear() {
        return d_daynuminyear;
    }

    public void setD_daynuminyear(int d_daynuminyear) {
        this.d_daynuminyear = d_daynuminyear;
    }

    public int getD_monthnuminyear() {
        return d_monthnuminyear;
    }

    public void setD_monthnuminyear(int d_monthnuminyear) {
        this.d_monthnuminyear = d_monthnuminyear;
    }

    public int getD_weeknuminyear() {
        return d_weeknuminyear;
    }

    public void setD_weeknuminyear(int d_weeknuminyear) {
        this.d_weeknuminyear = d_weeknuminyear;
    }

    public String getD_sellingseason() {
        return d_sellingseason;
    }

    public void setD_sellingseason(String d_sellingseason) {
        this.d_sellingseason = d_sellingseason;
    }

    public int isD_lastdayinweekfl() {
        return d_lastdayinweekfl;
    }

    public void setD_lastdayinweekfl(int d_lastdayinweekfl) {
        this.d_lastdayinweekfl = d_lastdayinweekfl;
    }

    public int isD_lastdayinmonthfl() {
        return d_lastdayinmonthfl;
    }

    public void setD_lastdayinmonthfl(int d_lastdayinmonthfl) {
        this.d_lastdayinmonthfl = d_lastdayinmonthfl;
    }

    public int isD_holidayfl() {
        return d_holidayfl;
    }

    public void setD_holidayfl(int d_holidayfl) {
        this.d_holidayfl = d_holidayfl;
    }

    public int isD_weekdayfl() {
        return d_weekdayfl;
    }

    public void setD_weekdayfl(int d_weekdayfl) {
        this.d_weekdayfl = d_weekdayfl;
    }

    public String sqlInsert() {
        return "INSERT INTO date VALUES ("
                + "'" + d_datekey + "', "
                + "'" + d_date + "', "
                + "'" + d_dayofweek + "', "
                + "'" + d_month + "', "
                + "'" + d_year + "', "
                + "'" + d_yearmonthnum + "', "
                + "'" + d_yearmonth + "', "
                + "'" + d_daynuminweek + "', "
                + "'" + d_daynuminmonth + "', "
                + "'" + d_daynuminyear + "', "
                + "'" + d_monthnuminyear + "', "
                + "'" + d_weeknuminyear + "', "
                + "'" + d_sellingseason + "', "
                + "'" + d_lastdayinweekfl + "', "
                + "'" + d_lastdayinmonthfl + "', "
                + "'" + d_holidayfl + "', "
                + "'" + d_weekdayfl + "');";
    }

}
