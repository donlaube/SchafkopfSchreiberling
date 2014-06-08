package be.donlau.schafkopfschreiberling.app.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.donlau.schafkopfschreiberling.app.R;
import be.donlau.schafkopfschreiberling.app.main.MainActivity;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link be.donlau.schafkopfschreiberling.app.Fragments.BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_NAME = "name";
    public static final String ARG_NUMBER = "number";

    // TODO: Rename and change types of parameters
    private int stringId;
    private int mNumber;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param stringId Parameter 1.
     * @param mNumber  Parameter 2.
     * @return A new instance of fragment baseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaseFragment newInstance(int stringId, int mNumber) {
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(bundleForNewInstance(stringId, mNumber));
        return fragment;
    }

    public static Bundle bundleForNewInstance(int stringId, int number) {
        Bundle args = new Bundle();
        args.putInt(ARG_NAME, stringId);
        args.putInt(ARG_NUMBER, number);
        return args;
    }

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            stringId = getArguments().getInt(ARG_NAME);
            mNumber = getArguments().getInt(ARG_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_game, container, false);
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getString(
                getArguments().getInt(ARG_NAME)));
    }

}
