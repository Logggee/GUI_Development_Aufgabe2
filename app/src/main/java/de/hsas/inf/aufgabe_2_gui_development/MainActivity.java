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
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;
import java.util.Random;


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
        openFragment();
    }

    public void openFragment(){
        gameFragment = new GameFragment();
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