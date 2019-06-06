package e.mi.work1;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import e.mi.laba1.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<ItemRecycler> data;
    Context m;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_element
                ,viewGroup,false);
        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        View v;

        public MyViewHolder(View v) {
            super(v);
            this.v = v;
            textView = (TextView)v.findViewById(R.id.textView3);
            imageView = (ImageView)v.findViewById(R.id.imageView);
        }
    }

    public MyAdapter(ArrayList<ItemRecycler> data, Context m) {
        this.data = data;
        this.m = m;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position, payloads);
        ItemRecycler itemRecycler = data.get(position);
        String color = (position % 2 == 0)? "#FFFFFF" : "#CCCCCC";
        holder.v.setBackgroundColor(Color.parseColor(color));
        holder.imageView.setImageResource(itemRecycler.imageView);
        holder.textView.setText(IntToWords.num2words(position + 1,1));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
