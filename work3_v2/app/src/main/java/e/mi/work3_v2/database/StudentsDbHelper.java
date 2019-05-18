package e.mi.work3_v2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import e.mi.work3_v2.Data.Student;

import static e.mi.work3_v2.Data.Student.StudentEntry.COLUMN_FIO;
import static e.mi.work3_v2.Data.Student.StudentEntry.COLUMN_ID;
import static e.mi.work3_v2.Data.Student.StudentEntry.TABLE_NAME;


public class StudentsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "Students.db";

    public StudentsDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.StudentEntry.getSqlCreateEntries_V1());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION) {
            db.execSQL(Student.StudentEntry.getSqlDeleteEntries());
            db.execSQL(Student.StudentEntry.getSqlCreateEntries_V2());
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertStudent(String ID, String family, String name, String father, String time) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Student.StudentEntry.COLUMN_ID,ID);
        values.put(Student.StudentEntry.COLUMN_FAMILY,family);
        values.put(Student.StudentEntry.COLUMN_NAME,name);
        values.put(Student.StudentEntry.COLUMN_FATHERSTVO,father);
        values.put(Student.StudentEntry.COLUMN_TIME,time);

        long rawId = db.insert(TABLE_NAME, null, values);

        return rawId;
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query =
                "SELECT * FROM " + TABLE_NAME +
                " ORDER BY " + Student.StudentEntry.COLUMN_TIME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            do {
                Student student = new Student();
                String family = (cursor.getString(cursor.getColumnIndex(Student.StudentEntry.COLUMN_FAMILY)));
                String name = (cursor.getString(cursor.getColumnIndex(Student.StudentEntry.COLUMN_NAME)));
                String fatherName = (cursor.getString(cursor.getColumnIndex(Student.StudentEntry.COLUMN_FATHERSTVO)));
                String fullName = family + " " + name + " " + fatherName;

                Log.i("info", fullName);

                student.setID(cursor.getInt(cursor.getColumnIndex(Student.StudentEntry.COLUMN_ID)));
                student.setFIO(family + " " + name + " " + fatherName);
                student.setTime(cursor.getString(cursor.getColumnIndex(Student.StudentEntry.COLUMN_TIME)));

                studentList.add(student);
            } while (cursor.moveToNext());
        }
        db.close();
        return studentList;
    }

    public void deleteAllStudents(){
        int rowsNumberDeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        rowsNumberDeleted = db.delete(TABLE_NAME,null,null);
        Log.i("info",String.valueOf(rowsNumberDeleted) + " students is deleted from database while launching");
    }



    public static StudentsDbHelper getInstance(Context context) {
        return new StudentsDbHelper(context);
    }


}
