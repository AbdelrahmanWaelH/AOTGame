package game.gui;
import java.io.IOException;
import game.engine.Battle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToSplash(ActionEvent event) throws IOException{
		Parent splashRoot = FXMLLoader.load(getClass().getResource("Splash.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(splashRoot);
		stage.setScene(scene);
		stage.show();
	}
	public void easy(){
		try {
			Battle battle = new Battle(1,0,10,3,250);
			System.out.println("You have chosen easy mode, instance created");
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void hard(){
		try {
			Battle battle = new Battle(1,0,10,5,125);
			System.out.println("You have chosen hard mode, instance created");
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
