package be.donlau.schafkopfschreiberling.app.bin;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by laubenbacher on 07.06.14.
 */
public class Match {

    private ArrayList<Game> games = new ArrayList<Game>();
    private ArrayList<Game.gameType> allowedTypes = new ArrayList<Game.gameType>();
    private Player[] players = new Player[4];
    private int sauspielTarif;
    private int soloTarif;
    private int laufendeTarif;
    private int maxDoppler = 5;
    private Context context;


    public Match(Context nContext) {
        context = nContext;
    }

    public void setPlayers(Player first, Player second, Player third, Player fourth) {
        players[0] = first;
        players[1] = second;
        players[2] = third;
        players[3] = fourth;
    }

    public void setPlayers(Player[] cPlayers) {
        players = cPlayers;
    }

    public void setTarif(int sauspiel, int solo, int laufende) {
        sauspielTarif = sauspiel;
        soloTarif = solo;
        laufendeTarif = laufende;
    }

    public Player getPlayer(int player) {
        return players[player];
    }

    public Game newGame() {
        return new Game(context, players);
    }

    public void addGame(Game cGame) {
        games.add(cGame);
    }

    public Game getLastGame() {
        return games.get(games.size() - 1);
    }

    public void addAlowedType(Game.gameType type) {
        allowedTypes.add(type);
    }


    public int getMaxDoppler() {
        return maxDoppler;
    }
}
