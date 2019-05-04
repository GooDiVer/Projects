package e.mi.work2;

import android.widget.ImageView;

import e.mi.work2.Net.TechItem;

public interface TechItemClickListener {
    void onTechItemClick(int position, TechItem techItem, ImageView imageView);
}
