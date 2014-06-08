package be.donlau.schafkopfschreiberling.app.main;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashMap;

import be.donlau.schafkopfschreiberling.app.Fragments.BaseFragment;
import be.donlau.schafkopfschreiberling.app.Fragments.CurrentGameFragment;
import be.donlau.schafkopfschreiberling.app.Fragments.NewGameFragment;
import be.donlau.schafkopfschreiberling.app.Fragments.StatsFragment;
import be.donlau.schafkopfschreiberling.app.R;
import be.donlau.schafkopfschreiberling.app.bin.Match;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, NewGameFragment.onMatchCreatedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static final int FRAGMENTNEWGAMEPOSITION = 0;
    public static final int FRAGMENTCURRENTGAMEPOSITION = 1;
    public static final int FRAGMENTSTATSPOSITION = 2;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static HashMap<Integer, BaseFragment> fragments = new HashMap<Integer, BaseFragment>() {{
        put(FRAGMENTNEWGAMEPOSITION, NewGameFragment.newInstance(R.string.title_section1_new_game, FRAGMENTNEWGAMEPOSITION));
        put(FRAGMENTCURRENTGAMEPOSITION, CurrentGameFragment.newInstance(R.string.title_section2_current_game, FRAGMENTCURRENTGAMEPOSITION));
        put(FRAGMENTSTATSPOSITION, StatsFragment.newInstance(R.string.title_section3_stats, FRAGMENTSTATSPOSITION));

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragments.get(position))
                .commit();
    }

    public void onSectionAttached(String name) {
        mTitle = name;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


@Override
public void onMatchCreated(Match newMatch) {
	CurrentGameFragment f = (CurrentGameFragment) fragments.get(FRAGMENTCURRENTGAMEPOSITION);
	//TODO: Abfrage ob bereits ein Spiel läuft und ob es beendet werden soll
	f.setMatch(newMatch);

	onNavigationDrawerItemSelected(1);
	this.restoreActionBar();
}
}
