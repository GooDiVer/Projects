package e.mi.work3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import e.mi.work3.Data.Student;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<Student> data;
    Context m;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student
                ,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.id.setText(String.valueOf(data.get(i).getID()));
        myViewHolder.fio.setText(data.get(i).getFIO());
        myViewHolder.time.setText(data.get(i).getTime());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView fio;
        TextView time;

        public MyViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.idStudent);
            fio = v.findViewById(R.id.fioStudent);
            time = v.findViewById(R.id.timeStudentAdded);
        }
    }

    public MyAdapter(ArrayList<Student> data, Context m) {
        this.data = data;
        this.m = m;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}