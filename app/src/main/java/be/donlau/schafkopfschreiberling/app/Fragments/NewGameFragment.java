package be.donlau.schafkopfschreiberling.app.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Switch;

import be.donlau.schafkopfschreiberling.app.R;
import be.donlau.schafkopfschreiberling.app.bin.Game;
import be.donlau.schafkopfschreiberling.app.bin.Match;
import be.donlau.schafkopfschreiberling.app.bin.Player;

/**
 * Created by laubenbacher on 05.06.14.
 */
public class NewGameFragment extends BaseFragment {


    private View rootView;
    private MultiAutoCompleteTextView player1View;
    private MultiAutoCompleteTextView player2View;
    private MultiAutoCompleteTextView player3View;
    private MultiAutoCompleteTextView player4View;
    private Match match;
    private onMatchCreatedListener mCallback;

    public static NewGameFragment newInstance(int stringId, int mNumber) {
        NewGameFragment fragment = new NewGameFragment();
        fragment.setArguments(bundleForNewInstance(stringId, mNumber));
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
        rootView.findViewById(R.id.startGame).setOnClickListener(buttonListener);

        player1View = (MultiAutoCompleteTextView) rootView.findViewById(R.id.player1newGame);
        player2View = (MultiAutoCompleteTextView) rootView.findViewById(R.id.player2newGame);
        player3View = (MultiAutoCompleteTextView) rootView.findViewById(R.id.player3newGame);
        player4View = (MultiAutoCompleteTextView) rootView.findViewById(R.id.player4newGame);
        return rootView;
    }

    private Player[] getPlayers() {
        Player[] players = new Player[]{
                new Player(player1View.getText().toString()),
                new Player(player2View.getText().toString()),
                new Player(player3View.getText().toString()),
                new Player(player4View.getText().toString())};
        return players;
    }

    private int getSauspielTarif() {
        EditText view = (EditText) rootView.findViewById(R.id.tarifSauspiel);
        return Integer.parseInt(view.getText().toString());
    }

    private int getSoloTarif() {
        EditText view = (EditText) rootView.findViewById(R.id.tarifSolo);
        return Integer.parseInt(view.getText().toString());
    }

    private int getLaufendeTarif() {
        EditText view = (EditText) rootView.findViewById(R.id.tarifLaufende);
        return Integer.parseInt(view.getText().toString());
    }

    private Boolean isWenz() {
        Switch s = (Switch) rootView.findViewById(R.id.switchWenz);
        return s.isChecked();
    }

    private Boolean isFarbWenz() {
        Switch s = (Switch) rootView.findViewById(R.id.switchFarbWenz);
        return s.isChecked();
    }

    private Boolean isGeier() {
        Switch s = (Switch) rootView.findViewById(R.id.switchGeier);
        return s.isChecked();
    }

    private Boolean isFarbGeier() {
        Switch s = (Switch) rootView.findViewById(R.id.switchFarbGeier);
        return s.isChecked();
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Boolean allCorrect = true;
            //TODO: Abfrage ob alles richtig eingegeben wurde
            match = new Match(NewGameFragment.this.getActivity());
            match.setPlayers(getPlayers());
            match.setTarif(getSauspielTarif(), getSoloTarif(), getLaufendeTarif());
            //Standard Spiele zulassen
            match.addAlowedType(Game.gameType.SAUSPIEL);
            match.addAlowedType(Game.gameType.SOLO);

            //Abfrage welche Art von Spiele erlaubt ist
            if (isWenz()) {
                match.addAlowedType(Game.gameType.WENZ);
                if (isFarbWenz()) match.addAlowedType(Game.gameType.FARBWENZ);
            }
            if (isGeier()) {
                match.addAlowedType(Game.gameType.GEIER);
                if (isFarbGeier()) match.addAlowedType(Game.gameType.FARBGEIER);
            }

            if (allCorrect) mCallback.onMatchCreated(match);


        }

    };

    public interface onMatchCreatedListener {
        public void onMatchCreated(Match newMatch);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onMatchCreatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
