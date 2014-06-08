package be.donlau.schafkopfschreiberling.app.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import be.donlau.schafkopfschreiberling.app.R;
import be.donlau.schafkopfschreiberling.app.bin.Game;
import be.donlau.schafkopfschreiberling.app.bin.Match;
import be.donlau.schafkopfschreiberling.app.main.MainActivity;


/**
 * Created by laubenbacher on 05.06.14.
 */
public class CurrentGameFragment extends BaseFragment {

    public static final int TABLEBUTTON = 0;
    public static final int TABLEDOPPLER = 1;
    public static final int TABLELASTGAME = 2;
    public static final int TABLEPOINTS = 3;
    public static final int TABLEPLAYER1 = 0;
    public static final int TABLEPLAYER2 = 1;
    public static final int TABLEPLAYER3 = 2;
    public static final int TABLEPLAYER4 = 3;


    private Match cMatch;
    private Game cGame;
    private View rootView;
    private RadioGroup gameTypeGroup;
    private RadioGroup gameColorGroup;
    private RadioGroup gameResultGroup;
    private RadioButton gameColorGerade;
    private RadioButton gameResultDu;
    private RadioButton gameResultSie;
    private Button laufendePlus;
    private EditText laufendeEditText;
    private Button laufendeMinus;
    private TextView[][] table = new TextView[4][4];


    public static CurrentGameFragment newInstance(int stringId, int mNumber) {
        CurrentGameFragment fragment = new CurrentGameFragment();
        fragment.setArguments(bundleForNewInstance(stringId, mNumber));
        return fragment;
    }

    public void setNames() {
        if (cMatch != null) {
            table[TABLEBUTTON][TABLEPLAYER1].setText(cMatch.getPlayer(TABLEPLAYER1).getUserName());
            table[TABLEBUTTON][TABLEPLAYER2].setText(cMatch.getPlayer(TABLEPLAYER2).getUserName());
            table[TABLEBUTTON][TABLEPLAYER3].setText(cMatch.getPlayer(TABLEPLAYER3).getUserName());
            table[TABLEBUTTON][TABLEPLAYER4].setText(cMatch.getPlayer(TABLEPLAYER4).getUserName());
        } else Log.e("CurrentGameFragment", "setNames: cMatch == null!");
    }

    public void setMatch(Match newMatch) {
        //Match setzen
        cMatch = newMatch;
        cGame = cMatch.newGame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_current_game, container, false);
        MainActivity activity = (MainActivity) this.getActivity();
        //Tabelle zuweisen
        //Name Buttons
        table[TABLEBUTTON][TABLEPLAYER1] = (TextView) rootView.findViewById(R.id.cgPlayer1);
        table[TABLEBUTTON][TABLEPLAYER2] = (TextView) rootView.findViewById(R.id.cgPlayer2);
        table[TABLEBUTTON][TABLEPLAYER3] = (TextView) rootView.findViewById(R.id.cgPlayer3);
        table[TABLEBUTTON][TABLEPLAYER4] = (TextView) rootView.findViewById(R.id.cgPlayer4);
        //Doppler Button
        table[TABLEDOPPLER][TABLEPLAYER1] = (TextView) rootView.findViewById(R.id.cgDouble1);
        table[TABLEDOPPLER][TABLEPLAYER2] = (TextView) rootView.findViewById(R.id.cgDouble2);
        table[TABLEDOPPLER][TABLEPLAYER3] = (TextView) rootView.findViewById(R.id.cgDouble3);
        table[TABLEDOPPLER][TABLEPLAYER4] = (TextView) rootView.findViewById(R.id.cgDouble4);
        //LastGame Text View
        table[TABLELASTGAME][TABLEPLAYER1] = (TextView) rootView.findViewById(R.id.cgLastGame1);
        table[TABLELASTGAME][TABLEPLAYER2] = (TextView) rootView.findViewById(R.id.cgLastGame2);
        table[TABLELASTGAME][TABLEPLAYER3] = (TextView) rootView.findViewById(R.id.cgLastGame3);
        table[TABLELASTGAME][TABLEPLAYER4] = (TextView) rootView.findViewById(R.id.cgLastGame4);
        //Overall point Text Views
        table[TABLEPOINTS][TABLEPLAYER1] = (TextView) rootView.findViewById(R.id.cgPoints1);
        table[TABLEPOINTS][TABLEPLAYER2] = (TextView) rootView.findViewById(R.id.cgPoints2);
        table[TABLEPOINTS][TABLEPLAYER3] = (TextView) rootView.findViewById(R.id.cgPoints3);
        table[TABLEPOINTS][TABLEPLAYER4] = (TextView) rootView.findViewById(R.id.cgPoints4);

        //Laufende Section
        laufendeEditText = (EditText) rootView.findViewById(R.id.cgLaufende);
        laufendeMinus = (Button) rootView.findViewById(R.id.cgMinus);
        laufendePlus = (Button) rootView.findViewById(R.id.cgPlus);

        //Game Attribute zuweisen
        gameColorGerade = (RadioButton) rootView.findViewById(R.id.gameColorNone);
        gameResultDu = (RadioButton) rootView.findViewById(R.id.gameResultDu);
        gameResultSie = (RadioButton) rootView.findViewById(R.id.gameResultSie);
        gameTypeGroup = (RadioGroup) rootView.findViewById(R.id.gameTypeGroup);
        gameColorGroup = (RadioGroup) rootView.findViewById(R.id.gameColorGroup);
        gameResultGroup = (RadioGroup) rootView.findViewById(R.id.gameResultGroup);

        //Listener
        //groups
        gameTypeGroup.setOnCheckedChangeListener(typeListener);
        gameColorGroup.setOnCheckedChangeListener(colorListener);
        gameResultGroup.setOnCheckedChangeListener(resultListener);
        //NameButtons
        table[TABLEBUTTON][TABLEPLAYER1].setOnClickListener(nameButtonListener);
        table[TABLEBUTTON][TABLEPLAYER2].setOnClickListener(nameButtonListener);
        table[TABLEBUTTON][TABLEPLAYER3].setOnClickListener(nameButtonListener);
        table[TABLEBUTTON][TABLEPLAYER4].setOnClickListener(nameButtonListener);
        //DoubleButtons
        table[TABLEDOPPLER][TABLEPLAYER1].setOnClickListener(doubleButtonListener);
        table[TABLEDOPPLER][TABLEPLAYER2].setOnClickListener(doubleButtonListener);
        table[TABLEDOPPLER][TABLEPLAYER3].setOnClickListener(doubleButtonListener);
        table[TABLEDOPPLER][TABLEPLAYER4].setOnClickListener(doubleButtonListener);
        //Laufende
        laufendePlus.setOnClickListener(laufendeListener);
        laufendeMinus.setOnClickListener(laufendeListener);
        laufendeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int current = 0;
                if (s.length() != 0) {
                    current = Integer.parseInt(s.toString());
                    if (current > 14) current = 14;
                    if (current < 0) ;
                    current = 0;
                }
                laufendeEditText.setText("" + current);
            }
        });

        this.setNames();

        return rootView;
    }


    public void updateUI() {
        if (cGame.getType() == Game.gameType.SAUSPIEL) {
            //Sauspiel: also Farben Auswahl ausblenden und die optionen du und sie
            gameColorGroup.setVisibility(View.GONE);
            gameResultDu.setVisibility(View.GONE);
            gameResultSie.setVisibility(View.GONE);
            //Marker neusetzen
            if (gameResultSie.isChecked() || gameResultDu.isChecked()) {
                RadioButton tmp = (RadioButton) gameResultGroup.getChildAt(0);
                tmp.setChecked(true);
            }

        } else if (cGame.getType() == Game.gameType.SOLO) {
            //Solo: Farben Auswahl einblenden, Gerade option ausblenden, du und sie anzeigen
            gameColorGroup.setVisibility(View.VISIBLE);
            gameColorGerade.setVisibility(View.GONE);
            gameResultDu.setVisibility(View.VISIBLE);
            gameResultSie.setVisibility(View.VISIBLE);
            //Marker neusetzen
            if (gameColorGerade.isChecked()) {
                RadioButton tmp = (RadioButton) gameColorGroup.getChildAt(1);
                tmp.setChecked(true);
            }

        } else if (cGame.getType() == Game.gameType.WENZ) {
            //Wenz Farben Auswahl einblenden, Gerade option einblenden, du und sie anzeigen
            gameColorGroup.setVisibility(View.VISIBLE);
            gameColorGerade.setVisibility(View.VISIBLE);
            gameResultDu.setVisibility(View.VISIBLE);
            gameResultSie.setVisibility(View.GONE);
            //Marker neusetzen
            if (gameResultSie.isChecked()) {
                RadioButton tmp = (RadioButton) gameResultGroup.getChildAt(0);
                tmp.setChecked(true);
            }


        } else if (cGame.getType() == Game.gameType.GEIER) {
            //Geier: Farben Auswahl einblenden, Gerade option einblenden, du und sie anzeigen
            gameColorGroup.setVisibility(View.VISIBLE);
            gameColorGerade.setVisibility(View.VISIBLE);
            gameResultDu.setVisibility(View.VISIBLE);
            gameResultSie.setVisibility(View.GONE);
            //Marker neusetzen
            if (gameResultSie.isChecked()) {
                RadioButton tmp = (RadioButton) gameResultGroup.getChildAt(0);
                tmp.setChecked(true);
            }

        }

    }

    public View getViewObject(int rId) {
        return rootView.findViewById(rId);
    }

    RadioGroup.OnCheckedChangeListener typeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton crb = (RadioButton) group.findViewById(checkedId);
            if (crb.getId() == Game.gameType.SAUSPIEL.radioButtonId)
                cGame.setType(Game.gameType.SAUSPIEL);
            if (crb.getId() == Game.gameType.SOLO.radioButtonId) cGame.setType(Game.gameType.SOLO);
            if (crb.getId() == Game.gameType.WENZ.radioButtonId) cGame.setType(Game.gameType.WENZ);
            if (crb.getId() == Game.gameType.GEIER.radioButtonId)
                cGame.setType(Game.gameType.GEIER);
            updateUI();
        }
    };
    RadioGroup.OnCheckedChangeListener colorListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton crb = (RadioButton) group.findViewById(checkedId);
            if (crb.getId() == Game.gameColor.EICHEL.radioButtonId)
                cGame.setColor(Game.gameColor.EICHEL);
            if (crb.getId() == Game.gameColor.GRAS.radioButtonId)
                cGame.setColor(Game.gameColor.GRAS);
            if (crb.getId() == Game.gameColor.HERZ.radioButtonId)
                cGame.setColor(Game.gameColor.HERZ);
            if (crb.getId() == Game.gameColor.SCHELLEN.radioButtonId)
                cGame.setColor(Game.gameColor.SCHELLEN);
            if (crb.getId() == Game.gameColor.GERADE.radioButtonId)
                cGame.setColor(Game.gameColor.GERADE);
            updateUI();
        }
    };
    RadioGroup.OnCheckedChangeListener resultListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton crb = (RadioButton) group.findViewById(checkedId);
            if (crb.getId() == Game.gameResult.FREI.radioButtonId)
                cGame.setResult(Game.gameResult.FREI);
            if (crb.getId() == Game.gameResult.SCHNEIDER.radioButtonId)
                cGame.setResult(Game.gameResult.SCHNEIDER);
            if (crb.getId() == Game.gameResult.SCHWARZ.radioButtonId)
                cGame.setResult(Game.gameResult.SCHWARZ);
            if (crb.getId() == Game.gameResult.DU.radioButtonId)
                cGame.setResult(Game.gameResult.DU);
            if (crb.getId() == Game.gameResult.SIE.radioButtonId)
                cGame.setResult(Game.gameResult.SIE);
            updateUI();
        }
    };

    ToggleButton.OnClickListener nameButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setNames();
        }
    };
    Button.OnClickListener doubleButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button t = (Button) v;
            String s = t.getText().toString();
            Integer count = Integer.parseInt(s);
            if (count == null) count = 0;
            count++;
            if (count > cMatch.getMaxDoppler()) count = 0;
            t.setText(count.toString());
        }
    };

    Button.OnClickListener laufendeListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cgMinus) {
                int current = Integer.parseInt(laufendeEditText.getText().toString());
                current--;
                if (current < 0) current = 0;
                laufendeEditText.setText("" + current);
            } else if (v.getId() == R.id.cgPlus) {
                int current = Integer.parseInt(laufendeEditText.getText().toString());
                current++;
                if (current > 14) current = 14;
                laufendeEditText.setText("" + current);
            }

        }
    };


}
