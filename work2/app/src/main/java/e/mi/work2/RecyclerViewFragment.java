package e.mi.work2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import e.mi.work2.Binder.RecyclerViewAdapter;
import e.mi.work2.Net.TechApi;
import e.mi.work2.Net.TechClient;
import e.mi.work2.Net.TechItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragment extends Fragment implements TechItemClickListener {
    RecyclerViewAdapter adapter;
    RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TechClient.getTechClient().create(TechApi.class).getTech().enqueue(new Callback<List<TechItem>>() {
            @Override
            public void onResponse(Call<List<TechItem>> call, Response<List<TechItem>> response) {
                adapter = new RecyclerViewAdapter(response.body(),RecyclerViewFragment.this);
            }

            @Override
            public void onFailure(Call<List<TechItem>> call, Throwable t) {

            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onTechItemClick(int position, TechItem techItem, ImageView imageView) {

    }
}
