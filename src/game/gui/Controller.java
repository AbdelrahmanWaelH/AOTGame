package game.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	
	@FXML
	private Label lane1trapcount=new Label();
	
	@FXML
	private Label lane2trapcount=new Label();
	
	@FXML
	private Label lane3trapcount=new Label();
	
	@FXML
	private Label lane4trapcount=new Label();
	
	@FXML
	private Label lane5trapcount=new Label();
	
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
	 private ImageView wall1ImageView;
	@FXML
	 private ImageView wall2ImageView;
	@FXML
	 private ImageView wall3ImageView;
	@FXML
	 private ImageView wall4ImageView;
	@FXML
	 private ImageView wall5ImageView;
	@FXML
	 private ProgressBar wall1HealthBar;
	@FXML
	 private ProgressBar wall2HealthBar;
	@FXML
	 private ProgressBar wall3HealthBar;
	@FXML
	 private ProgressBar wall4HealthBar;
	@FXML
	 private ProgressBar wall5HealthBar;
	@FXML
	 private GridPane laneGrid1;
	@FXML
	 private GridPane laneGrid2;
	@FXML
	 private GridPane laneGrid3;
	@FXML
	 private GridPane laneGrid4;
	@FXML
	 private GridPane laneGrid5;

	private static Battle battle;
	private Lane Lane1;
	private Lane Lane2;
	private Lane Lane3;
	private Lane Lane4;
	private Lane Lane5;
	private Lane purchaseLane;
	private String purchaseLaneName;
    private boolean hardDifficulty = true;
	private ArrayList<TitanView> allSpawnedTitans = new ArrayList<TitanView>();

	
	
	public void easy(ActionEvent event) throws IOException{
		hardDifficulty = false;
		battle = new Battle(1,0,100,3,250);
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
		battle = new Battle(1,0,100,5,125);
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
		} catch (IOException e){
			displayAlert("IOException when switching to hard battle", "IOException");
		}
	}
	public void switchToInstructions(ActionEvent event){
		try {
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("instructions.fxml"));
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
		} catch (IOException e){
			displayAlert("IOException when switching to instructions", "IOException");
		}
	}
	public void switchToDiffSelection(ActionEvent event){
		try {
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			game = FXMLLoader.load(getClass().getResource("diffSelection.fxml"));
			
			scene = new Scene(game);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.show();
		} catch (IOException e){
			displayAlert("IOException when switching to difficulty selection", "IOException");
		}
	}
	
	public void skipThisTurn(ActionEvent event){
		try{
			System.out.println("Turn skipped...");
			battle.passTurn();
			spawnAndMoveTitans();
		} catch (Exception e){
			System.out.println("Exception with spawn and move titans in pass turn");
		}
		textRefresh();
		if (battle.isGameOver()){
			defeat(event);
		}
	}
	

	private void buy(int code){
		try{
			battle.purchaseWeapon(code, purchaseLane);
			spawnAndMoveTitans();
		}catch (InvalidLaneException ILE){
			displayAlert("The lane you chose is invalid!", "Invalid Lane!");
		} catch (InsufficientResourcesException IRE) {
			displayAlert(IRE.getMessage(), "Insufficient Resources!");
		} catch(Exception e){
			System.out.println("exception with spawn and move titans in buy");
		}finally {
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
	
	@SuppressWarnings("unused")
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

	public void spawnAndMoveTitans(){
		if(!Lane1.isLaneLost() && laneGrid1 != null){ 
			try {
			spawnTitansAtLane(laneGrid1, Lane1,1);
			} catch (Exception e){
				System.out.println("Exception in lane 1");
			}
		}
		if(!Lane2.isLaneLost() && laneGrid2 != null){
		try{
			spawnTitansAtLane(laneGrid2, Lane2,2);
		} catch (Exception e){
			System.out.println("Exception in lane 1");
		}
		}
		if(!Lane3.isLaneLost() && laneGrid3 != null) {
			try{
			spawnTitansAtLane(laneGrid3, Lane3,3);
		} catch (Exception e){
			System.out.println("Exception in lane 1");
		}
		}
		if(hardDifficulty){
			if(!Lane4.isLaneLost() && laneGrid4 != null){ 
				try {
				spawnTitansAtLane(laneGrid4, Lane4,4);
				} catch (Exception e){
					System.out.println("Exception in lane 4");
				}
			}
			if(!Lane5.isLaneLost() && laneGrid5 != null){ 
				try {
				spawnTitansAtLane(laneGrid5, Lane5,5);
				} catch (Exception e){
					System.out.println("Exception in lane 5");
				}
			}
		}
	}
	private void spawnTitansAtLane(GridPane laneGrid, Lane lane, int laneNum){
		//Iterator<Titan> iterator = lane.getTitans().iterator();
		for (Titan titan: lane.getTitans()){
			TitanView titanView = new TitanView(titan,laneNum);
			allSpawnedTitans.add(titanView);
			laneGrid.add(titanView, 0 , 0);
		}
		
		moveAndDespawn();
	}
	private void moveAndDespawn(){
		for (TitanView titanView: allSpawnedTitans){
			if (titanView.getTitan().isDefeated()){
				//find which grid to remove from then remove
				switch(titanView.getLaneNum()){
					case 1: laneGrid1.getChildren().remove(titanView); break;
					case 2: laneGrid2.getChildren().remove(titanView) ; break;
					case 3: laneGrid3.getChildren().remove(titanView); break;
					case 4: laneGrid4.getChildren().remove(titanView) ; break;
					case 5: laneGrid5.getChildren().remove(titanView) ; break;
				}
				allSpawnedTitans.remove(titanView);
			} else {
				//insert into new lane grid cell
				setTitanCell(titanView);
			}
		}
	}
	
	private void setTitanCell(TitanView titanView){
		Titan titan = titanView.getTitan();
		GridPane titanLane;
		switch (titanView.getLaneNum()) {
			case 1: titanLane = laneGrid1; break;
			case 2: titanLane = laneGrid2; break;
			case 3: titanLane = laneGrid3; break;
			case 4: titanLane = laneGrid4; break;
			case 5: titanLane = laneGrid5; break;
			default: titanLane = laneGrid1;
		} 
		int distance = titan.getDistance();
		
		titanLane.getChildren().remove(titanView);
		if (distance>=0 && distance <= 10)
			titanLane.add(titanView, 0, 9);
		else if (distance > 10 && distance <= 20)
			titanLane.add(titanView, 0, 8);
		else if (distance > 20 && distance <= 30)
			titanLane.add(titanView, 0, 7);
		else if (distance > 30 && distance <= 40)
			titanLane.add(titanView, 0, 6);
		else if (distance > 40 && distance <= 50)
			titanLane.add(titanView, 0, 5);
		else if (distance > 50 && distance <= 60)
			titanLane.add(titanView, 0, 4);
		else if (distance > 60 && distance <= 70)
			titanLane.add(titanView, 0, 3);
		else if (distance > 70 && distance <= 80)
			titanLane.add(titanView, 0, 2);
		else if (distance > 80 && distance <= 90)
			titanLane.add(titanView, 0, 1);
		else
			titanLane.add(titanView, 0, 0);
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
		laneLabel1.setText("Danger Level: " + Lane1.getDangerLevel());
		laneLabel2.setText("Danger Level: " + Lane2.getDangerLevel());
		laneLabel3.setText("Danger Level: " + Lane3.getDangerLevel());
		lane1weaponcount.setText(piercingCount[0] + space + sniperCount[0] + space + volleyCount[0]);
		lane2weaponcount.setText(piercingCount[1] + space + sniperCount[1] + space + volleyCount[1]);
		lane3weaponcount.setText(piercingCount[2] + space + sniperCount[2] + space + volleyCount[2]);
		lane1trapcount.setText(space+space+trapCount[0] + "");
		lane2trapcount.setText(space+space+trapCount[1] + "");
		lane3trapcount.setText(space+space+trapCount[2] + "");
		if (Lane1.isLaneLost())
			wall1ImageView.setImage(new Image("game/gui/assets/wall_destroyed.jpeg"));
		if (Lane2.isLaneLost())
			wall2ImageView.setImage(new Image("game/gui/assets/wall_destroyed.jpeg"));
		if (Lane3.isLaneLost())
			wall3ImageView.setImage(new Image("game/gui/assets/wall_destroyed.jpeg"));
		wall1HealthBar.setProgress((double) Lane1.getLaneWall().getCurrentHealth() / Lane1.getLaneWall().getBaseHealth());
		wall2HealthBar.setProgress((double) Lane2.getLaneWall().getCurrentHealth() / Lane2.getLaneWall().getBaseHealth());
		wall3HealthBar.setProgress((double) Lane3.getLaneWall().getCurrentHealth() / Lane3.getLaneWall().getBaseHealth());
	
		if(hardDifficulty){
			try {
			Lane4 = tempArr.get(3);
			Lane5 = tempArr.get(4);
			laneLabel4.setText("Danger Level: " + Lane4.getDangerLevel());
			laneLabel5.setText("Danger Level: " + Lane5.getDangerLevel());
			lane4weaponcount.setText(piercingCount[3] + space + sniperCount[3] + space + volleyCount[3]);
			lane5weaponcount.setText(piercingCount[4] + space + sniperCount[4] + space + volleyCount[4]);
			lane4trapcount.setText(space+ space+ trapCount[3] + "");
			lane5trapcount.setText(space+space+trapCount[4] + "");
			wall4HealthBar.setProgress((double) Lane4.getLaneWall().getCurrentHealth() / Lane4.getLaneWall().getBaseHealth());
			wall5HealthBar.setProgress((double) Lane5.getLaneWall().getCurrentHealth() / Lane5.getLaneWall().getBaseHealth());
			
			if (Lane4.isLaneLost())
				wall4ImageView.setImage(new Image("game/gui/assets/wall_destroyed.jpeg"));
			if (Lane5.isLaneLost())
				wall5ImageView.setImage(new Image("game/gui/assets/wall_destroyed.jpeg"));
			} catch(IndexOutOfBoundsException e){
				System.out.println("did not change lane4 & lane5 labels, mode: " + hardDifficulty);
			} //handles easy mode 
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

class TitanView extends Pane{
	private ImageView icon;
	private ProgressBar healthBar;
	private Titan titan;
	private int laneNum= 0;
	

	public TitanView(Titan titan, int laneNum) {
		this.titan = titan;
		this.laneNum = laneNum;
		icon = new ImageView();
		healthBar = new ProgressBar();
		healthBar.setPrefWidth(50);
		healthBar.setPrefHeight(10);
		healthBar.setLayoutX(0);
		healthBar.setLayoutY(0);
		healthBar.setProgress((double)titan.getCurrentHealth() / titan.getBaseHealth());
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
			case 1: icon.setImage(new Image("game/gui/assets/pure_titan.png")); break;
			case 2: icon.setImage(new Image("game/gui/assets/abnormal_titan.png")); break;
			case 3: icon.setImage(new Image("game/gui/assets/armored_titan.png")); break;
			case 4: icon.setImage(new Image("game/gui/assets/colossal_titan.png")); break;
		}
		refreshHealthBar();
		icon.setPreserveRatio(false);
		icon.setFitHeight(titan.getHeightInMeters()*5);
		//icon.maxHeight(120);
		this.getChildren().add(icon);
		this.getChildren().add(healthBar);
	}
	
	public Titan getTitan(){
		return this.titan;
	}
	
	public int getLaneNum(){
		return this.laneNum;
	}

	private void refreshHealthBar(){
		this.healthBar.setProgress((double)titan.getCurrentHealth() / titan.getBaseHealth());
	}
}