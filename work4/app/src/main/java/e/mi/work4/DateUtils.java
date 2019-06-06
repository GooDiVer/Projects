package e.mi.work4;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    String curDateString;
    String setDateString;

    SimpleDateFormat formatter;

    public DateUtils() {

        formatter = new SimpleDateFormat("dd-MM-yyyy");

        Calendar cal = Calendar.getInstance();

        int curYear = cal.get(Calendar.YEAR);
        int curMonth = cal.get(Calendar.MONTH) + 1;
        int curDay = cal.get(Calendar.DAY_OF_MONTH);

        curDateString = curDay + "-" + curMonth + "-" + curYear;
    }

    public Calendar getDateToNotificate(Context context) {
//        Toast.makeText(context, setDateString, Toast.LENGTH_LONG).show();
        Date date = new Date();
        try {
           date = formatter.parse(setDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,9);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
//        Toast.makeText(context, date.getTime() + " " + date.getMonth() + " " + date.getDay(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, calendar.get(Calendar.YEAR) + " " + (calendar.get(Calendar.MONTH ) + 1) + " " + calendar.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT).show();


        return calendar;
    }

    public String getSetDateString() {
        return setDateString;
    }

    public void setSetDateString(String setDateString) {
        this.setDateString = setDateString;
    }

    public int getDaysBetwen() {

        Date setDate = new Date();
        Date curDate = new Date();

        try {
            setDate = formatter.parse(setDateString);
            curDate = formatter.parse(curDateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = setDate.getTime() - curDate.getTime();
        int daysLeft = (int)(diff/(1000*60*60*24));

        return daysLeft;
    }
}
