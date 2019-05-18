package e.mi.work3_v2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import e.mi.work3_v2.Data.Student;
import e.mi.work3_v2.Data.Util;
import e.mi.work3_v2.database.StudentsDbHelper;

import static e.mi.work3_v2.Data.Student.StudentEntry.COLUMN_FIO;
import static e.mi.work3_v2.Data.Student.StudentEntry.COLUMN_ID;
import static e.mi.work3_v2.Data.Student.StudentEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Student> studentList;
    private static int ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentsDbHelper dbHelper = new StudentsDbHelper(this);
        dbHelper.deleteAllStudents();

        dbHelper.onUpgrade(dbHelper.getWritableDatabase(),1,2);

        for(int i = 0; i < 5; i++) {
            String[] splitedFIO = Util.getStudentRandomRaw().split("\\s+");
            dbHelper.insertStudent(String.valueOf(ID++),
                    splitedFIO[0],
                    splitedFIO[1],
                    splitedFIO[2],
                    Util.getCurrentTime());
        }
    }

    public void onButton1(View view) {
        Intent intent = new Intent(this, DatabaseInfoActivity.class);
        startActivity(intent);
    }

    public void onButton2(View view) {
        TextView textView = findViewById(R.id.editText);
        String textFIO = textView.getText().toString();
        String[] splitedFIO = textFIO.split("\\s+");
        if(splitedFIO.length < 3) {
            return;
        }
        StudentsDbHelper.getInstance(this).insertStudent(String.valueOf(ID++),
                splitedFIO[0],
                splitedFIO[1],
                splitedFIO[2],
                Util.getCurrentTime());
    }

    public void onButton3(View view) {
        String query1 = "UPDATE " + TABLE_NAME +
                " SET " + COLUMN_FIO +  " = 'Ivanov Ivan Ivanovich' " +
                "WHERE " + COLUMN_ID +
                " = (SELECT max(" + COLUMN_ID + ")" +
                " FROM " + TABLE_NAME + ")";
        SQLiteDatabase db1 = StudentsDbHelper.getInstance(this).getWritableDatabase();
        db1.execSQL(query1);

    }

}
