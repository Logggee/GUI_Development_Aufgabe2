package de.hsas.inf.aufgabe_2_gui_development;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GameViewModel extends ViewModel {
    MutableLiveData<String> image;
    MutableLiveData<Integer> imageButton;
    MutableLiveData<Boolean> endGame;

    public void setImage(String image){this.image.setValue(image);}
    public void setImageButton(Integer imageButton){this.imageButton.setValue(imageButton);}
    public void setEndGame(Boolean endGame){this.endGame.setValue(endGame);}
    public MutableLiveData<String> getImage(){return image;};
    public MutableLiveData<Integer> getImageButton(){return imageButton;};
    public MutableLiveData<Boolean> getEndGame(){return endGame;};
}
