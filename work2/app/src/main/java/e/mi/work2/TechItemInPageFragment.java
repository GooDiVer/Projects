package e.mi.work2;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import e.mi.work2.Net.TechItem;

public class TechItemInPageFragment extends Fragment {

    private static String BASE_IMAGE_URL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
    private static final String EXTRA_TECH_ITEM = "tech_item";
    private static final String EXTRA_TRANSITION_NAME = "transition_name";

    public static TechItemInPageFragment getInstance(TechItem techItem, String transitionName) {
        TechItemInPageFragment techItemInPageFragment = new TechItemInPageFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_TECH_ITEM,techItem);
        bundle.putString(EXTRA_TRANSITION_NAME,transitionName);

        techItemInPageFragment.setArguments(bundle);

        return techItemInPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tech_in_page,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TechItem techItem = getArguments().getParcelable(EXTRA_TECH_ITEM);
        String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);

        TextView techName = view.findViewById(R.id.techName);
        ImageView techGraph = view.findViewById(R.id.techGraph);

        ///
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            techGraph.setTransitionName(transitionName);
        }
        ///

        Picasso.get()
                .load(BASE_IMAGE_URL + techItem.getGraphic())
                .into(techGraph, new Callback() {
                    @Override
                    public void onSuccess() {
                        startPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        startPostponedEnterTransition();
                    }
                });

        techName.setText(techItem.getName());
    }
}
