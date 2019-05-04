package e.mi.work2.Binder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import e.mi.work2.Net.TechItem;
import e.mi.work2.TechItemClickListener;
import e.mi.work2.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static String BASE_IMAGE_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    private Context context;
    private List<TechItem> techItems;
    private TechItemClickListener ticl;

    public RecyclerViewAdapter(List<TechItem> techItems, TechItemClickListener ticl) {
        this.techItems = techItems;
        this.ticl = ticl;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView helptext;
        TextView name;
        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.imageView);
            name = view.findViewById(R.id.techname);
        }
    }

    public RecyclerViewAdapter(ArrayList<TechItem> techItems) {
        this.techItems = techItems;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final TechItem techItem = techItems.get(i + 1);

        Picasso.get()
                .load(BASE_IMAGE_URL + techItem.getGraphic())
                .into(myViewHolder.image);

        ViewCompat.setTransitionName(myViewHolder.image,techItem.getName());
//        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ticl.onTechItemClick(myViewHolder.getAdapterPosition(), techItem, myViewHolder.image);
//            }
//        });

        myViewHolder.name.setText(techItem.getName());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tech,viewGroup,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return techItems.size();
    }
}
