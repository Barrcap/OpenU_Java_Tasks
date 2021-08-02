
/**
 * This class represents a Date object.
 *
 * @author Barr Caplan
 * @version (2020a 14/12/19)
 */
public class Date
{
    private int _day;
    private int _month;
    private int _year;

    final int LEAPDAYS = 29;
    final int NOTLEAPDAYS = 28;
    final int LONGMONTHMAX = 31;
    final int SHORTMONTHMAX = 30;
    final int YEARMIN = 1000;
    final int YEARMAX = 9999;
    final int ZERO = 0;
    final int MONTHMAX = 12;
    final int DEFAULTDAY = 1;
    final int DEFAULTMONTH = 1;
    final int DEFAULTYEAR = 2000;
    final int JAN = 1;
    final int FEB = 2;
    final int MAR = 3;
    final int APR = 4;
    final int MAY = 5;
    final int JUN = 6;
    final int JUL = 7;
    final int AUG = 8;
    final int SEP = 9;
    final int OCT = 10;
    final int NOV = 11;
    final int DEC = 12;
    final int ONEDIGMAX = 9;
    
    /**
     * creates a new Date object if the date is valid, otherwise creates the date 1/1/2000
     * 
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */
    public Date(int day, int month, int year)
    {
        if (dateValidation(day,month,year))
        {
            _day =  day;
            _month = month;
            _year = year;
        }
        else
        {
            _day = DEFAULTDAY;
            _month = DEFAULTMONTH;
            _year = DEFAULTYEAR;
        }
    }
    
    /**
     * copy constructor
     * 
     * @param other the date to be copied
     */
    public Date(Date other)
    {
        _day = other.getDay();
        _month = other.getMonth();
        _year = other.getYear();
    }
    
    /**
     *  gets the day
     *  
     *  @return the day
     */
    public int getDay()
    {
        return _day;
    }
    
    /**
     *  gets the month
     *  
     *  @return the month
     */
    public int getMonth()
    {
        return _month;
    }
    
    /**
     *  gets the year
     *  
     *  @return the year
     */
    public int getYear()
    {
        return _year;
    }
    
    /**
     *  sets the day (only if date remains valid)
     *  
     *  @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet)
    {
        if (dateValidation(dayToSet,_month,_year))
            _day = dayToSet;
    }

    /**
     * set the month (only if date remains valid)
     * 
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet)
    {
        if (dateValidation(_day,monthToSet,_year))
            _month = monthToSet;
    }
    
    /**
     * sets the year (only if date remains valid)
     * 
     * @param yearToSet  the year value to be set
     */
    public void setYear(int yearToSet)
    {
        if(dateValidation(_day,_month,yearToSet))
            _year = yearToSet;
    }
    
    /**
     * check if 2 dates are the same
     * 
     * @parram other the date to compare this date to
     * 
     * @return true if the dates are the same
     */
    public boolean equals(Date other)
    {
        if (_day == other.getDay() && _month == other.getMonth() && _year == other.getYear())
            return true;
        else
            return false;
    }
    
    /**
     * check if this date is before other date
     * 
     * @param other date to compare this date to
     * 
     * @return true if this date is before other 
     */
    public boolean before(Date other)
    {
        return calculateDate(_day,_month,_year) < calculateDate(other.getDay(),other.getMonth(),other.getYear());
    }
    
    /**
     * check if this date is after other date
     * 
     * @param other date to compare this date to
     * 
     * @return true if this date is after other date
     */
    public boolean after(Date other)
    {
        return other.before(this);
    }
    
    /**
     * calculates the difference in days between two dates
     * 
     * @param other the date to calculate the difference between
     * 
     * @return the number of days between the dates
     */
    public int difference(Date other)
    {
        return Math.abs(calculateDate(_day,_month,_year) - calculateDate(other.getDay(),other.getMonth(),other.getYear()) );
    }
    
    /**
     * returns a String that represents this date
     * 
     * @return String that represents this date in the following format: day/month/year for example: 02/03/1998
     */
    public String toString()
    {
        String day,month,year;
        day = (_day <= ONEDIGMAX)? ("0"+String.valueOf(_day)): String.valueOf(_day);
        month = (_month <= ONEDIGMAX)? ("0"+String.valueOf(_month)): String.valueOf(_month);
        year = String.valueOf(_year);

        return (day+"/"+month+"/"+year);
    }
    
    /**
     * calculate the date of tomorrow
     * 
     * @return the date of tomorrow
     */
    public Date tomorrow()
    {
        int day = _day;
        int month = _month;
        int year = _year;

        if (dateValidation(day+1,month,year))
            day ++;
        else
        {
            day = 1;
            if (dateValidation(day,month+1,year))
                month ++;
            else
            {
                month = 1;
                if (dateValidation(day,month,year+1))
                    year ++;
                else
                {
                    day = DEFAULTDAY;
                    month = DEFAULTMONTH;
                    year = DEFAULTYEAR;
                }
            }
        }

        
        return new Date(day,month,year);
    }
    
    /**
     * calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc.
     * 
     * @return the day of the week that this date occurs on
     */
    public int dayInWeek()
    {
        int d = _day;
        int m = _month;
        int y = _year;
        int c = y/100;
        return (d + (26*(m+1))/10 + y + y/4 + c/4 - c*2) % 7;
    }

    // check if a given year is a leap year
    private boolean isLeap(int year) 
    {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
            return true;
        return false;
    }
    
    //validates if a given date
    private boolean dateValidation(int day, int month, int year) 
    {
        if (year >= YEARMIN && year <= YEARMAX)
            if (month > ZERO && month <= MONTHMAX)
                if (day > ZERO)
                    switch (month)
                    {
                        case JAN:
                        case MAR:
                        case MAY:
                        case JUL:
                        case AUG:
                        case OCT:
                        case DEC:
                        if (day <= LONGMONTHMAX)
                            return true;
                        else
                            return false;
                        case APR:
                        case JUN:
                        case SEP:
                        case NOV:
                        if (day <= SHORTMONTHMAX)
                            return true;
                        else
                            return false;
                        case FEB:
                        if (isLeap(year))
                            if (day <= LEAPDAYS)
                                return true;
                            else if (day <= NOTLEAPDAYS)
                                return true;
                    }
        return false;
    }

    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate(int day,int month, int year)
    {
        if (month < 3) 
        {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }
} //end of class
// Comments -  please see the correct solution is attached     -8 points
// ********** Test Date - Started **********

 // 1. Testing Constructors:
	 // Passed!

 // 2. Testing accessors and mutators:

	 // ERROR in setDay - if d0 is: 01/02/2013 and if day to set is: 29 then the result should be 01/02/2013 but student's result is 29/01/2000

	 // ERROR in setYear - if d0 is: 28,2,2016 and if year to set is: 2013 then the result should be 28/02/2013 but student's result is 28/02/2016

 // 3. Testing equals method:
	 // Passed!

 // 4. Testing before method:

	 // ERROR in d0.before(d1) - if d0 is: 01/01/2013 and d1 is: 01/02/2013 then the expected result is: true but the student result is: false

 // 5. Testing after method:

	 // ERROR in d0.after(d1) - if d0 is: 01/01/2013 and d1 is: 01/02/2013 then the expected result is: false but the student result is: true

 // 6. Testing difference method:
	 // Passed!

 // 7. Testing toString method:
	 // Passed!

 // 8. Testing dayInWeek method:

	 // ERROR in d0.dayInWeek() 6,11,2016 method the expected print is: 1 but student prints: 2

	 // ERROR in d0.dayInWeek() 2,2,2017 method the expected print is: 5 but student prints: 0

	// ERROR in d0.dayInWeek() 2,2,2017 method you shouldn't change the original Date object

	 // ERROR in d0.dayInWeek() 1,3,2018 method the expected print is: 5 but student prints: 6

 // 9. Testing tomorrow method:

	 // ERROR in d0.tomorrow() 28,2,2019 method the expected print is: 01/03/2019 but student prints: 02/01/2000

	// ERROR in d0.tomorrow() 28,2,2019 method you shouldn't change the original Date object

// ********** Test Date - Finished **********