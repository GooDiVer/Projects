package e.mi.work2;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import e.mi.work2.Binder.ViewPagerAdapter;
import e.mi.work2.Net.TechItem;

import static android.os.Build.VERSION_CODES.KITKAT;

public class ViewPagerFragment extends Fragment {
    private static final String EXTRA_ITEMS_POS = "item_pos";
    private static final String EXTRA_TECH_ITEMS = "tech_items";

    public static ViewPagerFragment getInstance(int position, List<TechItem> techItems) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();

        bundle.putInt(EXTRA_ITEMS_POS,position);
        bundle.putParcelableArrayList(EXTRA_TECH_ITEMS, (ArrayList<? extends Parcelable>)techItems);

        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if(Build.VERSION.SDK_INT >= KITKAT) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
        setSharedElementReturnTransition(null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tech_item,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int curentPos = getArguments().getInt(EXTRA_ITEMS_POS);
        ArrayList<TechItem> techItems = getArguments().getParcelableArrayList(EXTRA_TECH_ITEMS);
        ViewPager viewPager = view.findViewById(R.id.techViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), techItems);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(curentPos);
    }
}
