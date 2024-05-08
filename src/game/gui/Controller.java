package game.gui;
import java.io.IOException;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {
	String iconPath = "game/gui/assets/icon.png";
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
	 @FXML
	 private Scene scene;
	 @FXML
	 private Parent game;
	private static Battle battle;
	 
	public void easy(ActionEvent event){
		
		try {
			battle = new Battle(1,0,10,3,250);
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("EasyBattle.fxml"));
			
			// IntegerProperty scoreProperty = new SimpleIntegerProperty();
			// scoreProperty.bindBidirectional(new SimpleIntegerProperty(battle.getScore()));
			// scoreLabel.textProperty().bind(scoreProperty.asString("Score: %d"));
			// dunno how to make these labels dynamic
			

			scoreLabel.setText("Score: " + battle.getScore());
			turnLabel.setText("turn: " + battle.getNumberOfTurns());
			phaseLabel.setText("phase: " + battle.getBattlePhase());
			resourcesLabel.setText("resources: " + battle.getResourcesGathered());
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			System.out.println("You have chosen easy mode, instance created");
			consolePrint();
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
			stage.setFullScreen(true);
			stage.show();
			System.out.println("You have chosen hard mode, instance created");
			consolePrint();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void openShop(ActionEvent event){
		
		try{
		Parent shop = FXMLLoader.load(getClass().getResource("Shop.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		System.out.println("Shop opened!");

		scene = new Scene(shop);
		stage.setScene(scene);
		stage.setFullScreen(true);
		stage.show();
		textRefresh();
		} catch (IOException e){
			e.printStackTrace();
		} 
		
	}
	
	public void skipThisTurn(){
		System.out.println("Turn skipped...");
		textRefresh();
		// 	battle.passTurn();
		// } catch (Exception e){
		// 	e.printStackTrace();
		// }
		//something is wrong with this one
	}
	public void buy(){
		//should take parameters to fill the purchase weapon method

		// try{
		// 	battle.purchaseWeapon(code, lane);
		// }catch (InvalidLaneException ILE){
		// 	displayAlert("The lane you chose is invalid!");
		// } catch (InsufficientResourcesException IRE) {
		// 	displayAlert("You currently don't have enough resources to perform this action");
		// }
	}
	private void displayAlert(String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle("Warning! Invalid Action!");
		alertStage.getIcons().add(new Image(iconPath));
        Label label = new Label(message);
        Button closeButton = new Button("Ok!");
        closeButton.setOnAction(event -> alertStage.close());

        BorderPane pane = new BorderPane();
        pane.setTop(label);
        pane.setCenter(closeButton);

        Scene scene = new Scene(pane, 500, 100);
        alertStage.setScene(scene);
        alertStage.show();
    }
	private void consolePrint(){
		System.out.println(scoreLabel.getText());
		System.out.println(turnLabel.getText());
		System.out.println(phaseLabel.getText());
		System.out.println(resourcesLabel.getText());
	}
	public void textRefresh(){
		scoreLabel.setText("Score: " + battle.getScore());
		turnLabel.setText("Turn: " + battle.getNumberOfTurns());
		phaseLabel.setText("Phase: " + battle.getBattlePhase());
		resourcesLabel.setText("Resources: " + battle.getResourcesGathered());
	}
}
