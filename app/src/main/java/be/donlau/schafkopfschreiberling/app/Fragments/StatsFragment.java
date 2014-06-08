package be.donlau.schafkopfschreiberling.app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.donlau.schafkopfschreiberling.app.R;

/**
 * Created by laubenbacher on 05.06.14.
 */
public class StatsFragment extends BaseFragment {


    public static StatsFragment newInstance(int stringId, int mNumber) {
        StatsFragment fragment = new StatsFragment();
        fragment.setArguments(bundleForNewInstance(stringId, mNumber));
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
        return rootView;
    }
}
