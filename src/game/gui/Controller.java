package game.gui;
import java.io.IOException;

import javax.swing.Action;

import game.engine.Battle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {
	@FXML
    private Label scoreLabel = new Label();

	@FXML
    private Label turnLabel = new Label();

    @FXML
    private Label phaseLabel = new Label();

    @FXML
    private Label resourcesLabel = new Label();

	@FXML
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	
	 
	public void easy(ActionEvent event){
		
		try {
			Battle battle = new Battle(1,0,10,3,250);
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent game = FXMLLoader.load(getClass().getResource("Battle.fxml"));
			
			scoreLabel.setText("score: " + battle.getScore());
			turnLabel.setText("turn: " + battle.getNumberOfTurns());
			phaseLabel.setText("phase: " + battle.getBattlePhase());
			resourcesLabel.setText("resources: " + battle.getResourcesGathered());
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
			System.out.println("You have chosen easy mode, instance created");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void hard(ActionEvent event){
		try {
			Battle battle = new Battle(1,0,10,5,125);
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent game = FXMLLoader.load(getClass().getResource("Battle.fxml"));
			
			scoreLabel.setText("Score: " + battle.getScore());
			turnLabel.setText("turn: " + battle.getNumberOfTurns());
			phaseLabel.setText("phase: " + battle.getBattlePhase());
			resourcesLabel.setText("resources: " + battle.getResourcesGathered());
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
			System.out.println("You have chosen hard mode, instance created");
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
