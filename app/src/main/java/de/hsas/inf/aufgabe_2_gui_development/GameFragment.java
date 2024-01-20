package de.hsas.inf.aufgabe_2_gui_development;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {
    private final ImageButton[] imageButtons = new ImageButton[14];
    private Button playButton;
    public GameFragment(){

    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    //inflate the fragment and the UI inside it
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        return rootView;
    }

    //After the fragment is inflated you can use it
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        //Get the Playbutton by ID
        playButton = view.findViewById(R.id.playButton);

        //Create a array of the image buttons by creating the IDs imageButton0 to imageButton13
        int imageButtonID;
        for(int i=0; i<14; i++){
            String buttonIDString = "imageButton" + i;
            imageButtonID = getResources().getIdentifier(buttonIDString, "id", requireContext().getPackageName());
            imageButtons[i] = view.findViewById(imageButtonID);
        }

        //Game object for creating the button listeners which are holding the games logic inside of them
        FileIOScores fileIO = new FileIOScores((AppCompatActivity) requireContext());
        Game game = new Game(playButton, imageButtons, fileIO);
        game.createImageButtonsListeners();
        game.createPlayButtonListener();
    }
}