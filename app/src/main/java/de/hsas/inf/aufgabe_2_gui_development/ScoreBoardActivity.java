package de.hsas.inf.aufgabe_2_gui_development;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Iterator;

public class ScoreBoardActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.deleteScores);
        button.setOnClickListener(view->{

        });

        //Read Content of the txt scores file
        readData();
    }

    //Methode is called on app startup to inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.itemPlayGame){
            Log.i("Menu", "Play Game item selected");
            Intent answer = new Intent();
            setResult(RESULT_OK, answer);
            finish();
        }
        return false;
    }

    private void readData(){
        FileIOScores fileIO = new FileIOScores(this);
        ArrayList<ScoreItem> scoreItems = fileIO.readScores("File1.txt");
        Iterator<ScoreItem> iterator = scoreItems.iterator();
        Log.i("Results", scoreItems.toString());
        while(iterator.hasNext()){
            Log.i("Results", "Test");
            Log.i("Results", iterator.next().toString());
        }
    }
}