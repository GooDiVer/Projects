package e.mi.laba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    e.mi.laba1.MyAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<e.mi.laba1.ItemRecycler> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataset = new ArrayList<>();

        adapter = new e.mi.laba1.MyAdapter(dataset,this);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for(int i = 1; i <= 1000000; i++) {
            e.mi.laba1.ItemRecycler item = new e.mi.laba1.ItemRecycler();
            item.setImageView(R.drawable.ar);
            dataset.add(item);

        }
    }
}
