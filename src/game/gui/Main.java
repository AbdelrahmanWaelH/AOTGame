package game.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try{
		primaryStage.setTitle("AOT Game pre-alpha");
		primaryStage.setMaximized(true);
		primaryStage.getIcons().add(new Image("game/gui/assets/icon.png"));
		//primaryStage.setFullScreen(true);
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		launch();
	}

}
