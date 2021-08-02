/**
 * This class represents a Date object
 * 
 * @author course staff
 * @version (2020a)
 */

public class DateSolution {

    private int _day;
    private int _month;
    private int _year;

    private final int DEFAULT_DAY = 1;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_YEAR = 2000;

    private final int JAN = 1;
    private final int FEB = 2;
    private final int MAR = 3;
    private final int APR = 4;
    private final int MAY = 5;
    private final int JUN = 6;
    private final int JUL = 7;
    private final int AUG = 8;
    private final int SEP = 9;
    private final int OCT = 10;
    private final int NOV = 11;
    private final int DEC = 12;
    private final int DAYS_IN_LEAP = 29;
    private final int DAYS_IN_NON_LEAP = 28;
    private final int MAX_DAYS_IN_MONTH = 31;
    //constructors:
    /**
     * creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */

    public DateSolution(int day, int month, int year) {
        if(isValid(day,month,year)){
            _day = day;
            _month = month;
            _year = year;
        }
        else{
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }
    }

    /**
     * copy constructor
     * @param other the date to be copied
     */
    public DateSolution(DateSolution other){       
        _day = other._day;
        _month = other._month;
        _year = other._year;              
    }

    /** 
     * gets the year 
     * @return the year
     */
    public int getYear(){
        return _year;
    }

    /** 
     * gets the month 
     * @return the month
     */
    public int getMonth(){
        return _month;
    }

    /** 
     * gets the day
     * @return the day
     */
    public int getDay(){
        return _day;
    }

    /** 
     * sets the year (only if date remains valid)
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet){

        if(isValid(_day, _month, yearToSet))
            _year = yearToSet;
    }

    /** 
     * set the month (only if date remains valid)
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet){

        if(isValid(_day, monthToSet, _year))
            _month = monthToSet;
    }

    /** 
     * sets the day (only if date remains valid)
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet){

        if(isValid(dayToSet, _month, _year))
            _day = dayToSet;
    }

    /** 
     *  check if 2 dates are the same
     *  @param other the date to compare this date to
     *  @return true if the dates are the same
     */
    public boolean equals(DateSolution other){
        if(_day == other._day && _month == other._month && _year == other._year)
            return true;
        return false;
    }

    /** 
     *  check if this date is before other date
     *  @param other date to compare this date to
     *  @return true if this date is before other date
     */
    public boolean before(DateSolution other){

        if ((_year < other._year || (_year == other._year && _month < other._month) || 
            (_year == other._year && _month == other._month && _day < other._day)))
            return true;
        return false;
    }

    /** 
     *  check if this date is after other date
     *  @param other date to compare this date to
     *  @return true if this date is after other date
     */

    public boolean after(DateSolution other){
        return (other.before(this));
    }

    /** 
     * calculates the difference in days between two dates
     * @param other the date to calculate the difference between
     * @return the number of days between the dates
     */ 
    public int difference(DateSolution other){                   
//        return Math.abs( calculateDate(getDay(),getMonth(),getYear()) - calculateDate(other.getDay(),other.getMonth(),other.getYear())) ;        
        return Math.abs( calculateDate(_day,_month,_year) - calculateDate(other._day,other._month,other._year)) ;        
  
    }            

    /**
     * returns a  String that represents this date
     *
     * @return String that represents this date
     * in the following format:
     * day / month / year for example: 02/03/1998
     */
   public String toString() {
        String s="";
        if(_day<10)
            s=s+"0"+_day+"/";
        else 
            s=s+_day+"/";
        if(_month<10)
            s=s+"0"+_month+"/";
        else 
            s=s+_month+"/";
        return s+_year;
    }

    private boolean isValid(int d,int m,int y){
        if(!dayInRange(d) || !monthInRange(m) || !yearInRange(y))
            return false;
        switch(m){
            case JAN:case MAR: case MAY:case JUL:case AUG:case OCT:case DEC:
            return (d <= MAX_DAYS_IN_MONTH);
            case FEB: 
            return (leap(y) && d <= DAYS_IN_LEAP) || (!leap(y) && d <= DAYS_IN_NON_LEAP);
            case APR:case JUN:case SEP:case NOV: 
            return (d <= MAX_DAYS_IN_MONTH - 1);
            default: return false;
        }
    }

    private boolean dayInRange(int d){
        return (1 <= d && d <= MAX_DAYS_IN_MONTH);
    }

    private boolean monthInRange(int m){
        return (JAN <= m && m <= DEC);
    }

    private boolean yearInRange(int y){
        return (1000 <= y && y < 10000);
    }
    //check if leap year
    private boolean leap(int y){
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    // The following method returns the number of days passed since 0 January 0 CE - use it to calculate trip duration
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        } 
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
    }    

    private int calculateDateOption2(int day, int month,int year){ 
        // computes the day number since 0 January 0 CE (Gregorian) 
        int y=year; 
        int m=month; 
        if(month < 3) y=y-1; 
        if(month < 3) m=m+12; 
        return (int)(Math.floor(365.25*y)-Math.floor(y/100)+Math.floor(y/400)+Math.floor(30.6*(m+1))+day-62); 
    }

    //New method. According to maman 12 semester 2012b
    /** 
     *  calculate the day of the week that this date occurs on 
     *  0-Saturday
     *  1-Sunday
     *  2-Monday
     *  etc.
     *  @return the day of the week that this date occurs on
     */
    public int dayInWeek()
    {
        int  D, M, y, Y, C, whichDayInWeek;

        D = _day;
        M = _month;
        y = _year;
        if (M < 3)
        {
            M += 12;
            y--;
        }
        C = y / 100;
        Y = y % 100;
                
        whichDayInWeek = Math.floorMod((D + (26*(M+1))/10 + Y + Y/4 + C/4 - 2*C), 7);

        return whichDayInWeek;
    }
    // new method according to maman 12 semester 2020a
    /** 
     *  calculate the date of tomorrow 
     *  @return the date of tomorrow
     */
    public DateSolution tomorrow()
    {
     if(isValid(_day+1,_month,_year))return new DateSolution(_day+1,_month,_year);
     if(isValid(1,_month+1,_year))return new DateSolution(1,_month+1, _year);
     if(isValid(1,1,_year+1))return new DateSolution(1,1,_year+1);
     return null; // tomorrow for 31.12.9999
    }
    
}