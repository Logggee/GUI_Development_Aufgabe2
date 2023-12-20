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

    private Button buttonDelete;
    private FileIOScores fileIO = new FileIOScores(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Listener for delete Button, deletes all listet scores in the view and the score file
        buttonDelete = findViewById(R.id.deleteScores);
        buttonDelete.setOnClickListener(view->{
            fileIO.eraseContent("File1.txt");
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

    private ArrayList<ScoreItem> readData(){
        fileIO.printFileContent("File1.txt");
        ArrayList<ScoreItem> scoreItems = new ArrayList<ScoreItem>();
        scoreItems = fileIO.readScores("File1.txt");
        Iterator<ScoreItem> iterator = scoreItems.iterator();
        Log.i("Results", scoreItems.toString());
        while(iterator.hasNext()){
            Log.i("Results", "Test");
            Log.i("Results", iterator.next().toString());
        }
        return scoreItems;
    }
}