package de.hsas.inf.aufgabe_2_gui_development;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private GameFragment gameFragment;
    private ActivityResultContracts.StartActivityForResult contractScoreBoardActivity;
    private ActivityResultCallback<ActivityResult> resultCallbackScoreBoard;
    private Intent intentScoreBoard;
    private ActivityResultLauncher<Intent> scoreBoardLauncher;

    //Methode is called on app startup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Create the launcher for switching to the sub activity
        createScoreBoardLauncher();
        //Open the game fragment inside the main activity
        openGameFragment();
    }

    //Open the game fragment
    public void openGameFragment(){
        //new instance of GameFragment
        gameFragment = new GameFragment();
        //replace the frame layout with the game fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, gameFragment);
        ft.commit();
    }

    //Methode is called on app startup to inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Methode is called if any item is selected in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.itemScoreBoard){
            Log.i("Menu", "Scores item selected");
            //Intent for scoreboard activity
            intentScoreBoard = new Intent(MainActivity.this, ScoreBoardActivity.class);

            //Launch scoreboard
            scoreBoardLauncher.launch(intentScoreBoard);
        } else if (item.getItemId() == R.id.itemChangeButton) {
            Drawable shapePlayButton = getResources().getDrawable(R.drawable.shape_play_button);
            Button playButton = findViewById(R.id.playButton);
            playButton.setBackground(shapePlayButton);
        }
        return super.onOptionsItemSelected(item);
    }

    private void createScoreBoardLauncher(){
        //Contract for scoreboard activity
        contractScoreBoardActivity = new ActivityResultContracts.StartActivityForResult();

        //Callback for scoreboard activity
        resultCallbackScoreBoard = result -> {
            int resultCode = result.getResultCode();
            if(resultCode == RESULT_OK){
                Log.i("resultCodes", "Result OK from sub activity scores");
            }
            else{
                Log.i("resultCodes", "Result NOT OK from sub activity scores");
            }
        };

        //Launcher for scoreboard activity
        scoreBoardLauncher = registerForActivityResult(contractScoreBoardActivity, resultCallbackScoreBoard);
    }
}