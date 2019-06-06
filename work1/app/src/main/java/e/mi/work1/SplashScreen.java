package e.mi.work1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import e.mi.laba1.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (savedInstanceState == null) {
            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(2000);
                        if(SplashScreen.this.hasWindowFocus()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

            };
            myThread.start();
        }
    }
}
