package de.hsas.inf.aufgabe_2_gui_development;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private ActivityResultContracts.StartActivityForResult contractScoreBoardActivity;
    private ActivityResultCallback<ActivityResult> resultCallbackScoreBoard;
    private Intent intentScoreBoard;
    private ActivityResultLauncher<Intent> scoreBoardLauncher;
    private ImageButton[] imageButtons = new ImageButton[14];
    private Button playButton;

    //Methode is called on app startup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the Playbutton by ID
        playButton = findViewById(R.id.playButton);
        //Create the launcher for switching to the sub activity
        createScoreBoardLauncher();

        //Create a array of the Imagebuttons by creating the IDs imageButton0 to imageButton13
        int imageButtonID;
        for(int i=0; i<14; i++){
            String buttonIDString = "imageButton" + i;
            imageButtonID = getResources().getIdentifier(buttonIDString, "id", getPackageName());
            imageButtons[i] = findViewById(imageButtonID);
        }

        //Game object for creating the button listeners which are holding the games logic inside of them
        FileIOScores fileIO = new FileIOScores(this);
        Game game = new Game(playButton, imageButtons, fileIO);
        game.createImageButtonsListeners();
        game.createPlayButtonListener();
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
        }
        return false;
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