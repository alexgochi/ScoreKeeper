package alexgochi.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int mScore1;
    private int mScore2;
    TextView mScoreText1;
    TextView mScoreText2;

    private static final String STATE_SCORE_1 = "Team 1 Score";
    private static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the correct item was clicked
        if(item.getItemId()== R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu from XML
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //Change the label of the menu based on	the	state of the app
        int	nightMode =	AppCompatDelegate.getDefaultNightMode();
        if(nightMode ==	AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return	true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreText1 = (TextView) findViewById(R.id.score_1);
        mScoreText2 = (TextView) findViewById(R.id.score_2);

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            //Set the score	text views
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Save the scores
        outState.putInt(STATE_SCORE_1,	mScore1);
        outState.putInt(STATE_SCORE_2,	mScore2);
        super.onSaveInstanceState(outState);
    }

    /**
     * Method that handles the onClick of both the increment buttons
     * @param view The button view that was clicked
     */
    public void increaseScore(View view) {
        //Get the ID of the button that was clicked
        int viewID = view.getId();
        switch (viewID){
            //If it was on Team 1
            //Increment the score and update the TextView
            case R.id.increaseTeam1: mScore1++;
            mScoreText1.setText(String.valueOf(mScore1));
            break;

            //If it was Team 2
            // Increment the score and update the TextView
            case R.id.increaseTeam2: mScore2++;
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    /**
     * Method that handles the onClick of both the decrement buttons
     * @param view The button view that was clicked
     */
    public void decreaseScore(View view) {
        //Get the ID of the button that was clicked
        int viewID = view.getId();
        switch (viewID) {
        //If it was on Team 1
        //Decrement the score and update the TextView
        case R.id.decreaseTeam1:
        if (!mScoreText1.getText().equals("0")) {
            mScore1--;
            mScoreText1.setText(String.valueOf(mScore1));
            break;
        } else break;

        //If it was Team 2
        //Decrement the score and update the TextView
        case R.id.decreaseTeam2:
            if (!mScoreText2.getText().equals("0")) {
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
            } else break;
        }
    }
}
