package be.donlau.schafkopfschreiberling.app.bin;

import android.content.Context;
import android.util.Log;

import be.donlau.schafkopfschreiberling.app.R;

import static be.donlau.schafkopfschreiberling.app.R.string.game;

/**
 * Created by laubenbacher on 07.06.14.
 */
public class Game {
    public static enum gameType {
        SAUSPIEL(R.string.sauspiel, R.id.gameTypeSauspiel, false),
        SOLO(R.string.solo, R.id.gameTypeSolo, true),
        WENZ(R.string.wenz, R.id.gameTypeWenz, false),
        FARBWENZ(R.string.wenz, R.id.gameTypeWenz, true),
        GEIER(R.string.geier, R.id.gameTypeGeier, false),
        FARBGEIER(R.string.geier, R.id.gameTypeGeier, true);

        public final int stringId;
        public final int radioButtonId;
        public final boolean colored;

        private gameType(final int nStringId, final int id, boolean colored) {
            this.stringId = nStringId;
            this.radioButtonId = id;
            this.colored = colored;
        }

    }

    public static enum gameColor {
        EICHEL(R.string.eichel, R.id.gameColorEichel),
        GRAS(R.string.gras, R.id.gameColorGras),
        HERZ(R.string.herz, R.id.gameColorHerz),
        SCHELLEN(R.string.schellen, R.id.gameColorSchellen),
        GERADE(R.string.gerade, R.id.gameColorNone);

        public final int stringId;
        public final int radioButtonId;


        private gameColor(final int nStringId, final int nRadioButtonId) {
            this.stringId = nStringId;
            this.radioButtonId = nRadioButtonId;
        }
    }

    public static enum gameResult {
        FREI(R.string.frei, R.id.gameResultSchneiderfrei),
        SCHNEIDER(R.string.schneider, R.id.gameResultSchneider),
        SCHWARZ(R.string.schneiderschwarz, R.id.gameResultSchwarz),
        DU(R.string.du, R.id.gameResultDu),
        SIE(R.string.sie, R.id.gameResultSie);
        public final int stringId;
        public final int radioButtonId;

        private gameResult(final int cStringId, final int cRadioButtonId) {
            this.stringId = cStringId;
            this.radioButtonId = cRadioButtonId;
        }
    }

    private gameType type;
    private gameColor color;
    private gameResult result;
    private final Player[] players;
    private Player[] winners;
    private final Context context;


    public Game(Context nContext, Player[] cPlayers) {
        this.context = nContext;
        this.players = cPlayers;
    }

    public Player[] getWinners() {
        return winners;
    }

    public void setWinners(Player[] winners) {
        if (winners.length < players.length) {
            this.winners = winners;
        } else Log.e("Game", "zu viele Gewinner");

    }

    public gameType getType() {
        return type;
    }

    public void setType(gameType type) {
        this.type = type;
    }

    public gameResult getResult() {
        return result;
    }

    public void setResult(gameResult result) {
        this.result = result;
    }

    public gameColor getColor() {
        return color;
    }

    public void setColor(gameColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        String tmp = context.getString(game) + ":";
        if (type != null) tmp += " " + context.getString(type.stringId);
        if (color != null) tmp += " " + context.getString(color.stringId);
        if (result != null) tmp += " " + context.getString(result.stringId);

        return tmp;
    }
}
