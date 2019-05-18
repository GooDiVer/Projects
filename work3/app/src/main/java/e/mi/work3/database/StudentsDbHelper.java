package e.mi.work3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import e.mi.work3.Data.Student;

import static e.mi.work3.Data.Student.StudentEntry.COLUMN_FIO;
import static e.mi.work3.Data.Student.StudentEntry.COLUMN_ID;
import static e.mi.work3.Data.Student.StudentEntry.TABLE_NAME;
import static e.mi.work3.Data.Student.StudentEntry.getSqlCreateEntries;
import static e.mi.work3.Data.Student.StudentEntry.getSqlDeleteEntries;


public class StudentsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Students.db";

    public StudentsDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Student.StudentEntry.getSqlCreateEntries());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getSqlDeleteEntries());
        onCreate(db);
    }

    public void change(SQLiteDatabase db) {
        String query = "UPDATE " + TABLE_NAME +
                " SET " + COLUMN_FIO +  " = 'Ivanov Ivan Ivanovich' " +
                "WHERE " + COLUMN_ID +
                " = (SELECT max(" + COLUMN_ID + ")" +
                " FROM " + TABLE_NAME + ")";
        db.execSQL(query);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertStudent(String ID, String FIO, String time) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Student.StudentEntry.COLUMN_ID,ID);
        values.put(Student.StudentEntry.COLUMN_FIO,FIO);
        values.put(Student.StudentEntry.COLUMN_TIME,time);

        long rawId = db.insertOrThrow(TABLE_NAME, null, values);

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
                student.setID(cursor.getInt(cursor.getColumnIndex(Student.StudentEntry.COLUMN_ID)));
                student.setFIO(cursor.getString(cursor.getColumnIndex(Student.StudentEntry.COLUMN_FIO)));
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
