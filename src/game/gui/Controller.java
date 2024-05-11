package game.gui;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.PrimitiveIterator.OfDouble;

import game.engine.Battle;
import game.engine.BattlePhase;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import game.gui.assets.*;
import javafx.animation.TranslateTransition;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


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
	private Label laneLabel1 = new Label();

	@FXML
	private Label laneLabel2 = new Label();

	@FXML
	private Label laneLabel3 = new Label();

	@FXML
	private Label laneLabel4 = new Label();

	@FXML
	private Label laneLabel5 = new Label();

	@FXML
	 private ChoiceBox<String> laneChoice = new ChoiceBox<>();

	@FXML
	 private Stage stage;

	@FXML
	 private Scene scene;

	@FXML
	 private Parent game;
	
	@FXML
	 private VBox lane1field= new VBox();
	
	@FXML
	 private VBox lane2field= new VBox();
	
	@FXML
	 private VBox lane3field= new VBox();
	
	@FXML
	 private VBox lane4field= new VBox();
	
	@FXML
	 private VBox lane5field= new VBox();

	private static Battle battle;
	private Lane Lane1;
	private Lane Lane2;
	private Lane Lane3;
	private Lane Lane4;
	private Lane Lane5;
	private Lane purchaseLane;
    private boolean hardDifficulty;
	
	public void easy(ActionEvent event) throws IOException{
		hardDifficulty = false;
		battle = new Battle(1,0,10,3,250);
		switchToEasyBattle(event);
	}
	private void switchToEasyBattle(ActionEvent event){
		try {
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("EasyBattle.fxml"));
			//spawnTitans(hardDifficulty);
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			System.out.println("You have chosen easy mode, instance created");
			consolePrint();
		} catch (IOException e){
			displayAlert("IOException when switching to easy battle","IOException");
		}
	}
	public void hard(ActionEvent event) throws IOException{
		hardDifficulty = true;
		battle = new Battle(1,0,10,5,125);
		switchToHardBattle(event);
	}
	private void switchToHardBattle(ActionEvent event){
		try {
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("HardBattle.fxml"));
			//spawnTitans(hardDifficulty);
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			System.out.println("You have chosen hard mode, instance created");
			consolePrint();
		} catch (IOException e){
			displayAlert("IOException when switching to hard battle", "IOException");
		}
	}
	
	 /* public void spawnTitans(boolean hard){
		ArrayList<Lane> lanes=battle.getOriginalLanes();
		battle.refillApproachingTitans();
		ArrayList<Titan> titans = battle.getApproachingTitans();
		if(!lanes.get(0).isLaneLost()){
			spawnTitansAtLane(lane1field, titans);
		}
		
		if(!lanes.get(1).isLaneLost()){
			spawnTitansAtLane(lane2field, titans);
		}
		
		if(!lanes.get(2).isLaneLost()){
			spawnTitansAtLane(lane3field, titans);
		}
		
		if(hard){
			
			
			if(!lanes.get(3).isLaneLost()){
				spawnTitansAtLane(lane4field, titans);
			}
			
			if(!lanes.get(4).isLaneLost()){
				spawnTitansAtLane(lane5field, titans);
			}
		}
	}
	
	 public void spawnTitansAtLane(VBox theLane, ArrayList<Titan> theTitans){
		int i=0;
		while(i<theTitans.size()){
			Titan currTitan=theTitans.get(i);
			Image currTitanImage;
			ImageView theSprite;
			if(currTitan instanceof PureTitan){
				currTitanImage=new Image("pure_titan.jpeg");
			}else if(currTitan instanceof AbnormalTitan){
				currTitanImage=new Image("abnormal_titan.jpeg");
			}else if(currTitan instanceof ArmoredTitan){
				currTitanImage=new Image("armored_titan.jpeg");
			}else{
				currTitanImage=new Image("colossal_titan.jpeg");
			}
			StackPane fullSprite=new StackPane();
			Rectangle healthBox= new Rectangle();
			healthBox.setSize(10, 50);
			theSprite=new ImageView(currTitanImage);
			theLane.getChildren().add(fullSprite);
			
			TranslateTransition translate = new TranslateTransition(Duration.seconds(3), theSprite);
			translate.setByY(200); 
			translate.setAutoReverse(true);
			translate.setCycleCount(TranslateTransition.INDEFINITE);
			translate.play();
			i++;
		}
	}*/

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
	public void returnToGame(ActionEvent event){
		if (hardDifficulty){
			switchToHardBattle(event);
		} else {
			switchToEasyBattle(event);
		}
	}
	
	public void skipThisTurn(){
		System.out.println("Turn skipped...");
		battle.passTurn();
		textRefresh();
		// if (battle.isGameOver()){
		// 	defeat();
		// }
	}

	public void buy(int code){
		try{
			battle.purchaseWeapon(code, purchaseLane);
		}catch (InvalidLaneException ILE){
			displayAlert("The lane you chose is invalid!", "Invalid Lane!");
		} catch (InsufficientResourcesException IRE) {
			displayAlert("You currently don't have enough resources to perform this action", "Insufficient Resources!");
		} finally {
			textRefresh();
		}
	}
			public void buyButton1(ActionEvent event) {
				buy(1);
				// if (battle.isGameOver()){
				// 	defeat();
				// }
			}

			public void buyButton2(ActionEvent event) {
				buy(2);
				// if (battle.isGameOver()){
				// 	defeat();
				// }
			}

			public void buyButton3(ActionEvent event) {
				buy(3);
				// if (battle.isGameOver()){
				// 	defeat();
				// }
			}

			public void buyButton4(ActionEvent event) {
				buy(4);
				// if (battle.isGameOver()){
				// 	defeat();
				// }
			}

		private void displayAlert(String message, String titleBar) {
        Stage alertStage = new Stage();
        alertStage.setTitle(titleBar);
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
		System.out.println(laneLabel1.getText());
		System.out.println(laneLabel2.getText());
		System.out.println(laneLabel3.getText());
		System.out.println(laneLabel4.getText());
		System.out.println(laneLabel5.getText());
	}

	public void textRefresh(){
		scoreLabel.setText("Score: " + battle.getScore());
		turnLabel.setText("Turn: " + battle.getNumberOfTurns());
		phaseLabel.setText("Phase: " + battle.getBattlePhase());
		resourcesLabel.setText("Resources: " + battle.getResourcesGathered());
		ArrayList<Lane> tempArr= battle.getOriginalLanes();
		Lane1=tempArr.get(0);
		Lane2=tempArr.get(1);
		Lane3=tempArr.get(2);
		laneLabel1.setText("Wall 1 health: " + Lane1.getLaneWall().getCurrentHealth() + " & Danger Level: " + Lane1.getDangerLevel());
		laneLabel2.setText("Wall 2 health: " + Lane2.getLaneWall().getCurrentHealth() + " & Danger Level: " + Lane2.getDangerLevel());
		laneLabel3.setText("Wall 3 health: " + Lane3.getLaneWall().getCurrentHealth() + " & Danger Level: " + Lane3.getDangerLevel());
		if(hardDifficulty){
			Lane4=tempArr.get(3);
			Lane5=tempArr.get(4);
			laneLabel4.setText("Wall 4 health: " + Lane4.getLaneWall().getCurrentHealth() + " & Danger Level: " + Lane4.getDangerLevel());
			laneLabel5.setText("Wall 5 health: " + Lane5.getLaneWall().getCurrentHealth() + " & Danger Level: " + Lane5.getDangerLevel());
		}
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (hardDifficulty){
			laneChoice.getItems().addAll("Lane 1", "Lane 2", "Lane 3", "Lane 4", "Lane 5");
		} else {
			laneChoice.getItems().addAll("Lane 1", "Lane 2", "Lane 3");
		}
		laneChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, laneName) -> {
			switch (laneName) {
				case "Lane 1": purchaseLane = Lane1; break;
				case "Lane 2": purchaseLane = Lane2; break;
				case "Lane 3": purchaseLane = Lane3; break;
				case "Lane 4": purchaseLane = Lane4; break;
				case "Lane 5": purchaseLane = Lane5; break;
				default: purchaseLane = null;
			}
		});
	}
	// private void defeat(){
	// 	displayAlert("You have been defeated! Your score is: " + battle.getScore(), "Game Over!");
	// 	try{
	// 		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("diffSelection.fxml"))));
	// 		stage.show();
	// 		stage.setFullScreen(true);
	// 	} catch (Exception e){
	// 		e.printStackTrace();
	// 	}
	// }
}
