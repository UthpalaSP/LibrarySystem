package models;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public DateTime() {
        //current date time
        Calendar currentDate = Calendar.getInstance();
        java.util.Date x = currentDate.getTime();
        SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
        this.year = Integer.parseInt(formatyear.format(x));

        SimpleDateFormat formatmonth = new SimpleDateFormat("MM");
        this.month = Integer.parseInt(formatmonth.format(x));

        SimpleDateFormat formatdd = new SimpleDateFormat("dd");
        this.day = Integer.parseInt(formatdd.format(x));

        SimpleDateFormat formathh = new SimpleDateFormat("hh");
        this.hour = Integer.parseInt(formathh.format(x));

        SimpleDateFormat formatmm = new SimpleDateFormat("mm");
        this.minute = Integer.parseInt(formatmm.format(x));
    }

    public DateTime(int day, int month, int year, int hour, int minute) throws IllegalArgumentException {

        if (!isValid(year, month, day, hour, minute)) throw new IllegalArgumentException();

        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public static boolean isValid(int year, int month, int day, int hour, int minute) {
        if (year < 0) return false;
        if ((month < 1) || (month > 12)) return false;
        if ((day < 1) || (day > 31)) return false;
        if ((hour < 0) || (hour > 24)) return false;
        if ((minute < 0) || (minute > 60)) return false;

        switch (month) {
            case 1: return true;
            case 2: return (isLeap(year) ? day <= 29 : day <= 28);
            case 3: return true;
            case 4: return day < 31;
            case 5: return true;
            case 6: return day < 31;
            case 7: return true;
            case 8: return true;
            case 9: return day < 31;
            case 10: return true;
            case 11: return day < 31;
            default: return true;
        }
    }

    public static boolean isLeap(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {

        StringBuilder dateString = new StringBuilder();
        dateString.append(String.valueOf(this.year));
        dateString.append("-");

        if (this.month < 10)
            dateString.append("0");
        dateString.append(String.valueOf(this.month));
        dateString.append("-");

        if (this.day < 10)
            dateString.append("0");
        dateString.append(String.valueOf(this.day));

        dateString.append(" ");

        if (this.hour < 24)
            dateString.append(String.valueOf(this.day));
        dateString.append(":");

        if (this.minute < 60)
            dateString.append(String.valueOf(this.minute));

        return dateString.toString();
    }

    public int substract(DateTime date2){
        //return difference in hrs
        GregorianCalendar newDate1 = new GregorianCalendar(this.year,this.month,this.day);
        GregorianCalendar newDate2 = new GregorianCalendar(date2.year,date2.month,date2.day);
        long m1 = newDate1.getTimeInMillis();
        long m2 = newDate2.getTimeInMillis();
        long dif = (int)(m2-m1);
        int difH = (int) dif/1000/60/60;
        return  difH;
    }

    public DateTime add(int days){
        //return the new date after adding
        GregorianCalendar newDate1 = new GregorianCalendar(this.year,this.month,this.day);
        GregorianCalendar newDate2 = newDate1;
        return  new DateTime(newDate2.get(Calendar.YEAR),newDate2.get(Calendar.MONTH),newDate2.get(Calendar.DATE),0,0);
    }

}
