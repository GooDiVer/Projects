package e.mi.work3_v2.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {
    private static String[] firstName = {"Dmitry", "Ivan", "Artem", "Vladislav","Alexandr"};
    private static String[] lastName = {"Kotkin", "Pascalev","Cplusplusov", "Javailov","Pythin"};
    private static String[] fatherName = {"Blezovich","Stivinovich", "Linusovich", "Straustrupovich","Jetovich"};

    public static String getStudentRandomRaw() {
        int fn = (int)(Math.random()*5);
        int ln = (int)(Math.random()*5);
        int fatn = (int)(Math.random()*5);
        String FIO =  lastName[ln] + " " + firstName[fn] + " " + fatherName[fatn];
        return FIO;
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }
}
