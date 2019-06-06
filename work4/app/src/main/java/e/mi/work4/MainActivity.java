package e.mi.work4;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView mDisplayDate;
    DatePickerDialog.OnDateSetListener mCallback;

    public static final String APP_PREFERENCES = "mSettings";
    public static final String APP_PREFERENCES_DATE = "set date";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(APP_PREFERENCES,this.MODE_PRIVATE);

        mDisplayDate = findViewById(R.id.atv);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        mCallback,
                        year, month, day);

                dialog.show();
            }
        });

        mCallback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Intent intent = new Intent(MainActivity.this,MyAppWidget.class);
                intent.setAction(MyAppWidget.ACTION_DATE_SET);

                String setDate = dayOfMonth + "-" + (month + 1) + "-" + year;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putString(APP_PREFERENCES_DATE,setDate);
                editor.apply();

                intent.putExtra("NewDate",setDate);

                sendBroadcast(intent);
            }
        };

    }
}
