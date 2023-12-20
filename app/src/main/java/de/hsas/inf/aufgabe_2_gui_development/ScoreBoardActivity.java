package de.hsas.inf.aufgabe_2_gui_development;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Iterator;

public class ScoreBoardActivity extends AppCompatActivity {

    private Button buttonDelete;
    private FileIOScores fileIO = new FileIOScores(this);
    private ArrayAdapter<String> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<ScoreItem> scoreItems = new ArrayList<ScoreItem>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);

        //create toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get list view
        ListView listView = findViewById(android.R.id.list);

        //Listener for delete Button, deletes all listet scores in the view and the score file
        buttonDelete = findViewById(R.id.deleteScores);
        buttonDelete.setOnClickListener(view->{
            fileIO.eraseContent("File1.txt");
            model.clear();
            model.notifyDataSetChanged();
        });

        //Read Content of the txt scores file
        scoreItems = readScores();
        //Creat the model and the list view items for score list
        model = createListItems(scoreItems, listView);
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

    private ArrayList<ScoreItem> readScores(){
        fileIO.printFileContent("File1.txt");
        ArrayList<ScoreItem> scoreItems = new ArrayList<ScoreItem>();
        scoreItems = fileIO.readScores("File1.txt");
        return scoreItems;
    }

    private ArrayAdapter<String> createListItems(ArrayList<ScoreItem> scoreItems, ListView listView){
        ArrayList<String> listItems = new ArrayList<String>();
        Iterator<ScoreItem> iterator = scoreItems.iterator();
        while(iterator.hasNext()){
            listItems.add("\n" + iterator.next().toString());
        }
        ArrayAdapter<String> model = new ArrayAdapter<String>(this, R.layout.list_items, listItems);
        listView.setAdapter(model);
        return model;
    }
}