package e.mi.work3_v2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import e.mi.work3_v2.Data.Student;
import e.mi.work3_v2.Data.Util;
import e.mi.work3_v2.database.StudentsDbHelper;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Student> studentList;
    private static int ID = 1;
    private static int oldVersion = 1;

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

        SQLiteDatabase db = StudentsDbHelper.getInstance(this).getWritableDatabase();
        StudentsDbHelper.getInstance(this).onUpgrade(db,oldVersion++,oldVersion);
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

}
