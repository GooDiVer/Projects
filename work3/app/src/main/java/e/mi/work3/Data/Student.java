package e.mi.work3.Data;

import android.provider.BaseColumns;

public class Student {

    private int ID;
    private String FIO;
    private String time;

    public static class StudentEntry implements BaseColumns {
        public final static String TABLE_NAME = "student";
        public final static String COLUMN_ID = "id";
        public final static String COLUMN_FIO = "fio";
        public final static String COLUMN_TIME = "time";

        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " +
                StudentEntry.TABLE_NAME + " (" +
                StudentEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                StudentEntry.COLUMN_FIO + " TEXT, " +
                StudentEntry.COLUMN_TIME + " TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;

        public static String getSqlCreateEntries() {
            return SQL_CREATE_ENTRIES;
        }

        public static String getSqlDeleteEntries() {
            return SQL_DELETE_ENTRIES;
        }
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
