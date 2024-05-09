package game.gui;
import java.io.IOException;
import java.security.acl.Group;
import java.time.Duration;
import java.util.ArrayList;
import java.util.PriorityQueue;

<<<<<<< HEAD
import javax.swing.RootPaneContainer;

import org.omg.CORBA.PRIVATE_MEMBER;

=======
>>>>>>> efd4347a7be5e357447ada95ea7264de31fe6302
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.exceptions.GameActionException;
import game.engine.lanes.Lane;
<<<<<<< HEAD
import game.engine.titans.Titan;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
=======
>>>>>>> efd4347a7be5e357447ada95ea7264de31fe6302
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Controller {
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
<<<<<<< HEAD
	 private Label lane5=new Label();
    
     private boolean Hard=true;
     
     @FXML
	 VBox lane1field= new VBox(); 
     
     @FXML
	 VBox lane2field= new VBox(); 
     
     @FXML
	 VBox lane3field= new VBox(); 
     
     @FXML
     Rectangle rectangle= new Rectangle();
    
 
=======
	 private Label lane5 = new Label();
>>>>>>> efd4347a7be5e357447ada95ea7264de31fe6302
    
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
			
		
			
				TranslateTransition translate = new TranslateTransition();
				translate.setByY(-100); 
				translate.setDuration(javafx.util.Duration.millis(1000));
				translate.setCycleCount(10);
				translate.setAutoReverse(true);
				translate.setNode(rectangle);
				translate.play();
				
				lane1field.getChildren().add(rectangle);
				
			
			
			
			
			
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
		Lane l1=tempArr.get(0);
		Lane l2=tempArr.get(1);
		Lane l3=tempArr.get(2);
		lane1.setText("Wall 1 health: " + l1.getLaneWall().getCurrentHealth() + " & Danger Level: " + l1.getDangerLevel());
		lane2.setText("Wall 2 health: " + l2.getLaneWall().getCurrentHealth() + " & Danger Level: " + l2.getDangerLevel());
		lane3.setText("Wall 3 health: " + l3.getLaneWall().getCurrentHealth() + " & Danger Level: " + l3.getDangerLevel());
		if(hardDifficulty){
			Lane l4=tempArr.get(3);
			Lane l5=tempArr.get(4);
			lane4.setText("Wall 4 health: " + l4.getLaneWall().getCurrentHealth() + " & Danger Level: " + l4.getDangerLevel());
			lane5.setText("Wall 5 health: " + l5.getLaneWall().getCurrentHealth() + " & Danger Level: " + l5.getDangerLevel());
		}
		
		
	}
	
	
}
