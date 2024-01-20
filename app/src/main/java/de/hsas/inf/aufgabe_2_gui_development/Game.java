package de.hsas.inf.aufgabe_2_gui_development;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import java.time.LocalDateTime;
import java.util.Random;

public class Game {
    private ImageButton[] imageButtons = new ImageButton[14];
    private Button playButton;
    private int treasure;
    private int ghost;
    private int gameCounter = 0;
    private  boolean gameEnd = false;
    private LocalDateTime timeStamp;
    private FileIOScores fileIO;

    public Game(Button playButton, ImageButton[] imageButtons, FileIOScores fileIO){
        this.playButton = playButton;
        this.imageButtons = imageButtons;
        this.fileIO = fileIO;
    }

    public void createImageButtonsListeners(){
        int imageButtonID;

        for (int i = 0; i < 14; i++) {
            int finalI = i;
            //disable all image buttons
            imageButtons[i].setEnabled(false);
            //set a listener to every image button
            imageButtons[i].setOnClickListener(view->{
                //increment the game counter if a button was selected
                gameCounter++;
                //if the player selects a image for the fourth time he lost the game and set image
                if(gameCounter == 4){
                    imageButtons[finalI].setImageResource(R.mipmap.ghost_foreground);
                    gameCounter = -1;
                    gameEnd = true;
                }
                //set image and end the game if the treasure was found
                else if(finalI == treasure){
                    imageButtons[finalI].setImageResource(R.mipmap.treasure_foreground);
                    gameEnd = true;
                }
                //set image and end the game if the ghost was found
                else if(finalI == ghost){
                    imageButtons[finalI].setImageResource(R.mipmap.ghost_foreground);
                    gameEnd = true;
                }
                //set image when no treasure was found
                else{
                    imageButtons[finalI].setImageResource(R.mipmap.no_treasure_foreground);
                }

                //When game is ended disable all image buttons and enable the play button
                if(gameEnd == true){
                    for(int e=0; e<14; e++){
                        imageButtons[e].setEnabled(false);
                    }
                    playButton.setEnabled(true);
                    timeStamp = LocalDateTime.now();
                    ScoreItem scoreItem = new ScoreItem(gameCounter, timeStamp);
                    Log.i("date", scoreItem.toString());
                    fileIO.writeFile("File1.txt", scoreItem.toString());
                }
                //if game is not ended yet disable the selected image button, so it cant get pressed a second time
                else{
                    imageButtons[finalI].setEnabled(false);
                }
            });
        }
    }

    public void createPlayButtonListener(){
        //set listener for play button
        playButton.setOnClickListener(view ->{
            //reset game counter if a new game ist started
            gameCounter = 0;
            //game is not ended
            gameEnd = false;
            //set all image buttons with the island image for a new game
            for(int i=0; i<14; i++){
                imageButtons[i].setImageResource(R.mipmap.island_foreground);
            }
            //generate a random int between 0 and 13 for the id which the treasure is hided
            treasure = new Random().nextInt(14);
            //generate a random int between 0 and 13 for the id which the ghost is hidden, neets to be a diffrent int then treasure
            do{
                ghost =  new Random().nextInt(14);
            }
            while(ghost == treasure);
            //disable the play button when pressed
            playButton.setEnabled(false);
            //enable all image buttons
            for(int i=0; i<14; i++){
                imageButtons[i].setEnabled(true);
            }
        });
    }
}
