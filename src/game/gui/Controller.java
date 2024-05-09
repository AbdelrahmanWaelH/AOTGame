package game.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.exceptions.GameActionException;
import game.engine.lanes.Lane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Controller implements Initializable{
	public static String iconPath = "game/gui/assets/icon.png";
	@FXML
     private Label scoreLabel = new Label();

	@FXML
     private Label turnLabel = new Label();

    @FXML
     private Label phaseLabel = new Label();

    @FXML
     private Label resourcesLabel = new Label();
    
    @FXML
     private Label lane1 = new Label();
    
    @FXML
     private Label lane2 = new Label();
    
    @FXML
     private Label lane3 = new Label();
    
    @FXML
	 private Label lane4 = new Label();
    
    @FXML
	 private Label lane5 = new Label();

	 private Lane l1;
	 private Lane l2;
	 private Lane l3;
	 private Lane l4;
	 private Lane l5;
	@FXML
	 private ChoiceBox<String> laneChoice = new ChoiceBox<>();
	private Lane purchaseLane;
    
    private boolean hardDifficulty = true;

	@FXML
	 private Stage stage;
	@FXML
	 private Scene scene;
	@FXML
	 private Parent game;
	private static Battle battle;
	


	public void easy(ActionEvent event){
		hardDifficulty = false;
		try {
			battle = new Battle(1,0,10,3,250);
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("EasyBattle.fxml"));
			
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
		battle.passTurn();
		textRefresh();
	}
	public void buy(){
		//should take parameters to fill the purchase weapon method
		//use easyDiff and a helper that takes two general parameters
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
		System.out.println(lane1.getText());
		System.out.println(lane2.getText());
		System.out.println(lane3.getText());
		System.out.println(lane4.getText());
		System.out.println(lane5.getText());

	}

	public void textRefresh(){
		scoreLabel.setText("Score: " + battle.getScore());
		turnLabel.setText("Turn: " + battle.getNumberOfTurns());
		phaseLabel.setText("Phase: " + battle.getBattlePhase());
		resourcesLabel.setText("Resources: " + battle.getResourcesGathered());
		ArrayList<Lane> tempArr= battle.getOriginalLanes();
		l1=tempArr.get(0);
		l2=tempArr.get(1);
		l3=tempArr.get(2);
		lane1.setText("Wall 1 health: " + l1.getLaneWall().getCurrentHealth() + " & Danger Level: " + l1.getDangerLevel());
		lane2.setText("Wall 2 health: " + l2.getLaneWall().getCurrentHealth() + " & Danger Level: " + l2.getDangerLevel());
		lane3.setText("Wall 3 health: " + l3.getLaneWall().getCurrentHealth() + " & Danger Level: " + l3.getDangerLevel());
		if(hardDifficulty){
			l4=tempArr.get(3);
			l5=tempArr.get(4);
			lane4.setText("Wall 4 health: " + l4.getLaneWall().getCurrentHealth() + " & Danger Level: " + l4.getDangerLevel());
			lane5.setText("Wall 5 health: " + l5.getLaneWall().getCurrentHealth() + " & Danger Level: " + l5.getDangerLevel());
		}
	}
	

	public void getLaneChoiceBox(ActionEvent event){
		String laneName = (String) laneChoice.getValue();
		System.out.println("Lane: " + laneName + " chosen");
		switch (laneName) {
		case "Lane 1": purchaseLane = l1; break;
		case "Lane 2": purchaseLane = l2; break;
		case "Lane 3": purchaseLane = l3; break;
		case "Lane 4": purchaseLane = l4; break;
		case "Lane 5": purchaseLane = l5; break;
		default: purchaseLane = null;
		scoreLabel.setText("laneName");
		}		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		laneChoice.getItems().addAll("Lane 1", "Lane 2", "Lane 3", "Lane 4", "Lane 5");
		//laneChoice.setOnAction(this::getLaneChoiceBox);
	}
}
