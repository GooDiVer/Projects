package e.mi.work2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.List;

import e.mi.work2.Binder.RecyclerViewAdapter;
import e.mi.work2.Net.TechApi;
import e.mi.work2.Net.TechClient;
import e.mi.work2.Net.TechItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity implements TechItemClickListener{

    RecyclerView rv;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TechClient.getTechClient().create(TechApi.class).getTech().enqueue(new Callback<List<TechItem>>() {
            @Override
            public void onResponse(Call<List<TechItem>> call, Response<List<TechItem>> response) {
                adapter = new RecyclerViewAdapter(response.body(),MainActivity.this);
            }

            @Override
            public void onFailure(Call<List<TechItem>> call, Throwable t) {

            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onTechItemClick(int position, TechItem techItem, ImageView imageView) {

    }
}
