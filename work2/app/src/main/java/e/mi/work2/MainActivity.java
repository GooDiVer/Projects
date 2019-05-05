package e.mi.work2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import e.mi.work2.Binder.RecyclerViewAdapter;
import e.mi.work2.Net.TechApi;
import e.mi.work2.Net.TechClient;
import e.mi.work2.Net.TechItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mycontent, RecyclerViewFragment.getInstance())
                .commit();
        }
    }



}
