package e.mi.work2.Binder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import e.mi.work2.Net.TechItem;
import e.mi.work2.TechItemInPageFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<TechItem> techItems;
    private int itemPosition;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<TechItem> techItems) {
        super(fm);
        this.techItems = techItems;
    }

    @Override
    public Fragment getItem(int i) {
        TechItem techItem = techItems.get(i);
        TechItemInPageFragment techItemInPageFragment = TechItemInPageFragment.getInstance(techItem, techItem.getName());
        return techItemInPageFragment;
    }

    @Override
    public int getCount() {
        return techItems.size();
    }
}
