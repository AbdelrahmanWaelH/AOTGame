package game.gui;

import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TitanView extends AnchorPane{
    @FXML
    private ImageView icon;
    @FXML
    private ProgressBar healthBar;

    private Titan titan;

    public TitanView(Titan titan){
        int code = 0;
        if (titan instanceof PureTitan) {
            code = 1;
        } else if (titan instanceof AbnormalTitan) {
            code = 2;
        } else if (titan instanceof ArmoredTitan) {
            code = 3;
        } else if (titan instanceof ColossalTitan) {
            code = 4;
        } 
        switch (code) {
            case 1: icon.setImage(new Image("game/gui/assets/pure_titan.jpeg")); break;
            case 2: icon.setImage(new Image("game/gui/assets/abnormal_titan.jpeg")); break;
            case 3: icon.setImage(new Image("game/gui/assets/armored_titan.jpeg")); break;
            case 4: icon.setImage(new Image("game/gui/assets/colossal_titan.jpeg")); break;
        }
        
        refreshHealthBar();
    }
    private void refreshHealthBar(){
        healthBar.setProgress((double)titan.getCurrentHealth() / titan.getBaseHealth());
    }
}
