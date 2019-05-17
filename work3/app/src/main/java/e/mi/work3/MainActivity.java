package e.mi.work3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import e.mi.work3.Data.Student;
import e.mi.work3.Data.Util;
import e.mi.work3.database.StudentsDbHelper;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Student> studentList;
    private static int ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentsDbHelper dbHelper = new StudentsDbHelper(this);
        dbHelper.deleteAllStudents();
        for(int i = 0; i < 5; i++) {
            dbHelper.insertStudent(String.valueOf(ID++),
                    Util.getStudentRandomRaw(),
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
        StudentsDbHelper.getInstance(this).insertStudent(String.valueOf(ID++),
                textFIO,
                Util.getCurrentTime());
    }

    public void onButton3(View view) {
        SQLiteDatabase db1 = StudentsDbHelper.getInstance(this).getWritableDatabase();
        StudentsDbHelper.getInstance(this).onUpgrade(db1,1,2);

    }

}
