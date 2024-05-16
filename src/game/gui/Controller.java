package game.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.WeaponFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;   //THISSSS SHITTTTT SUCKSSSS //you're kinda right ngl
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	 private Label lane1weaponcount=new Label();
	
	@FXML
	 private Label lane2weaponcount=new Label();
	
	@FXML
	 private Label lane3weaponcount=new Label();
	
	@FXML
	 private Label lane4weaponcount=new Label();
	
	@FXML
	 private Label lane5weaponcount=new Label();
	
	private int[] piercingCount={0,0,0,0,0};
	private int[] sniperCount={0,0,0,0,0};
	private int[] volleyCount={0,0,0,0,0};
	private int[] trapCount={0,0,0,0,0};

	@FXML
	 private Label pcLabel = new Label();

	@FXML
	 private Label scLabel = new Label();

	@FXML
	 private Label vcLabel = new Label();

	@FXML
	 private Label wtLabel = new Label();

	@FXML
	 private ChoiceBox<String> laneChoice = new ChoiceBox<>();

	@FXML
	 private Stage stage;

	@FXML
	 private Scene scene;

	@FXML
	 private Parent game;
	@FXML
	 private GridPane motherGrid;

	private static Battle battle;
	private Lane Lane1;
	private Lane Lane2;
	private Lane Lane3;
	private Lane Lane4;
	private Lane Lane5;
	private Lane purchaseLane;
	private String purchaseLaneName;
    private boolean hardDifficulty = true;
	
	
	public void easy(ActionEvent event) throws IOException{
		hardDifficulty = false;
		battle = new Battle(1,0,10,3,250);
		switchToEasyBattle(event);
	}
	private void switchToEasyBattle(ActionEvent event){
		try {
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("easyBattleV2.fxml"));
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
			System.out.println("You have chosen easy mode, instance created");
			//consolePrint();
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
			game = FXMLLoader.load(getClass().getResource("battleV2.fxml"));
			
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
	
	public void skipThisTurn(ActionEvent event){
		System.out.println("Turn skipped...");
		battle.passTurn();
		System.out.println(battle.isGameOver() + " is the defeated state-------------------------------------------");
		textRefresh();
		if (battle.isGameOver()){
			//is game over? using method
			defeat(event);
		}
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
				incrementCountAtLane(1, purchaseLaneName);
				buy(1);
				if (battle.isGameOver()){
					defeat(event);
				}
			}

			public void buyButton2(ActionEvent event) {
				incrementCountAtLane(2, purchaseLaneName);
				buy(2);
				if (battle.isGameOver()){
					defeat(event);
				}
			}

			public void buyButton3(ActionEvent event) {
				incrementCountAtLane(3, purchaseLaneName);
				buy(3);
				if (battle.isGameOver()){
					defeat(event);
				}
			}

			public void buyButton4(ActionEvent event) {
				incrementCountAtLane(4, purchaseLaneName);
				buy(4);
				if (battle.isGameOver()){
					defeat(event);
				}
			}
			
			
		private void incrementCountAtLane(int weapon, String laneName){
			if(purchaseLaneName.equals("Lane 1")){
				if(weapon==1){
					piercingCount[0]++;
				}else if(weapon==2){
					sniperCount[0]++;
				}else if(weapon==3){
					volleyCount[0]++;
				}else{
					trapCount[0]++;
				}
			}else if(purchaseLaneName.equals("Lane 2")){
				if(weapon==1){
					piercingCount[1]++;
				}else if(weapon==2){
					sniperCount[1]++;
				}else if(weapon==3){
					volleyCount[1]++;
				}else{
					trapCount[1]++;
				}
			}else if(purchaseLaneName.equals("Lane 3")){
				if(weapon==1){
					piercingCount[2]++;
				}else if(weapon==2){
					sniperCount[2]++;
				}else if(weapon==3){
					volleyCount[2]++;
				}else{
					trapCount[2]++;
				}
			}else if(purchaseLaneName.equals("Lane 4")){
				if(weapon==1){
					piercingCount[3]++;
				}else if(weapon==2){
					sniperCount[3]++;
				}else if(weapon==3){
					volleyCount[3]++;
				}else{
					trapCount[3]++;
				}
			}else if(purchaseLaneName.equals("Lane 5")){
				if(weapon==1){
					piercingCount[4]++;
				}else if(weapon==2){
					sniperCount[4]++;
				}else if(weapon==3){
					volleyCount[4]++;
				}else{
					trapCount[4]++;
				}
			}
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
		String space="             ";

		Lane1=tempArr.get(0);
		Lane2=tempArr.get(1);
		Lane3=tempArr.get(2);
		laneLabel1.setText("Wall 1 health: " + Lane1.getLaneWall().getCurrentHealth() + "\nDanger Level: " + Lane1.getDangerLevel());
		laneLabel2.setText("Wall 2 health: " + Lane2.getLaneWall().getCurrentHealth() + "\nDanger Level: " + Lane2.getDangerLevel());
		laneLabel3.setText("Wall 3 health: " + Lane3.getLaneWall().getCurrentHealth() + "\nDanger Level: " + Lane3.getDangerLevel());
		lane1weaponcount.setText(piercingCount[0] + space + sniperCount[0] + space + volleyCount[0]);
		lane2weaponcount.setText(piercingCount[1] + space + sniperCount[1] + space + volleyCount[1]);
		lane3weaponcount.setText(piercingCount[2] + space + sniperCount[2] + space + volleyCount[2]);
		if(hardDifficulty){
			try {
			Lane4 = tempArr.get(3);
			Lane5 = tempArr.get(4);
			laneLabel4.setText("Wall 4 health: " + Lane4.getLaneWall().getCurrentHealth() + "\nDanger Level: " + Lane4.getDangerLevel());
			laneLabel5.setText("Wall 5 health: " + Lane5.getLaneWall().getCurrentHealth() + "\nDanger Level: " + Lane5.getDangerLevel());
			lane4weaponcount.setText(piercingCount[3] + space + sniperCount[3] + space + volleyCount[3]);
			lane5weaponcount.setText(piercingCount[4] + space + sniperCount[4] + space + volleyCount[4]);
			} catch(IndexOutOfBoundsException e){
				System.out.println("did not change lane4 & lane5 labels, mode: " + hardDifficulty);
			} //handles easy mode lane labels
		}
	}
	private void defeat(ActionEvent event){
		
		try{
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			Parent diff = FXMLLoader.load(getClass().getResource("diffSelection.fxml"));
			scene = new Scene(diff);

			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			displayAlert("You have been defeated! Your score is: " + battle.getScore(), "Game Over!");
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
			purchaseLaneName=laneName;
			switch (laneName) {
				case "Lane 1": purchaseLane = Lane1;; break;
				case "Lane 2": purchaseLane = Lane2;; break;
				case "Lane 3": purchaseLane = Lane3;; break;
				case "Lane 4": purchaseLane = Lane4;; break;
				case "Lane 5": purchaseLane = Lane5;; break;
				default: purchaseLane = null;
			}
		});

		try{
			WeaponFactory wF = new WeaponFactory();
			WeaponRegistry wReg1 = wF.getWeaponShop().get(1);
			WeaponRegistry wReg2 = wF.getWeaponShop().get(2);
			WeaponRegistry wReg3 = wF.getWeaponShop().get(3);
			WeaponRegistry wReg4 = wF.getWeaponShop().get(4);
			pcLabel.setText("Price: " + wReg1.getPrice() + "\nName: " + wReg1.getName() + "\nDamage: " + wReg1.getDamage() + "\nType: Piercing Cannon");
			scLabel.setText("Price: " + wReg2.getPrice() + "\nName: " + wReg2.getName() + "\nDamage: " + wReg2.getDamage() + "\nType: Sniper Cannon");
			vcLabel.setText("Price: " + wReg3.getPrice() + "\nName: " + wReg3.getName() + "\nDamage: " + wReg3.getDamage() + "\nType: Volley Cannon");
			wtLabel.setText("Price: " + wReg4.getPrice() + "\nName: " + wReg4.getName() + "\nDamage: " + wReg4.getDamage() + "\nType: Wall Trap");
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	

	
}
