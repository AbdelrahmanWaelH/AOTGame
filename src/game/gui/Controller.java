package game.gui;
import java.io.IOException;
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
	 private Parent game;
	private Battle battle;
	 
	public void easy(ActionEvent event){
		
		try {
			battle = new Battle(1,0,10,3,250);
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("EasyBattle.fxml"));
			
			scoreLabel.setText("Score: " + battle.getScore());
			turnLabel.setText("turn: " + battle.getNumberOfTurns());
			phaseLabel.setText("phase: " + battle.getBattlePhase());
			resourcesLabel.setText("resources: " + battle.getResourcesGathered());
			// dunno how to make these labels dynamic
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
			System.out.println("You have chosen easy mode, instance created");
			System.out.println("Score: " + battle.getScore());
			System.out.println("turn: " + battle.getNumberOfTurns());
			System.out.println("phase: " + battle.getBattlePhase());
			System.out.println("resources: " + battle.getResourcesGathered());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void hard(ActionEvent event){
		try {
			battle = new Battle(1,0,10,5,125);
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("HardBattle.fxml"));
			
			scoreLabel.setText("Score: " + battle.getScore());
			turnLabel.setText("turn: " + battle.getNumberOfTurns());
			phaseLabel.setText("phase: " + battle.getBattlePhase());
			resourcesLabel.setText("resources: " + battle.getResourcesGathered());
			// dunno how to make these labels dynamic
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
			System.out.println("You have chosen hard mode, instance created");
			System.out.println("Score: " + battle.getScore());
			System.out.println("turn: " + battle.getNumberOfTurns());
			System.out.println("phase: " + battle.getBattlePhase());
			System.out.println("resources: " + battle.getResourcesGathered());
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	public void openShop(ActionEvent event){
		System.out.println("Shop opened!");
		try{
		Parent shop = FXMLLoader.load(getClass().getResource("Shop.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();

		scene = new Scene(shop);
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	public void skipThisTurn(){
		System.out.println("Turn skipped...");
		try{
			battle.passTurn();
		} catch (Exception e){
			e.printStackTrace();
		}
		//something is wrong with this one
	}
	public void buy(){
		//should take parameters to fill the purchase weapon method
		//battle.purchaseWeapon(code, lane);
	}
}
