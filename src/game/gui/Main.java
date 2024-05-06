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
		String title = "AOT Game pre-alpha";
		String iconPath = "game/gui/assets/icon.png";
		primaryStage.setTitle(title);
		primaryStage.getIcons().add(new Image(iconPath));

		try{
		Parent diffSelect = FXMLLoader.load(getClass().getResource("diffSelection.fxml"));
		primaryStage.setScene(new Scene(diffSelect));
		primaryStage.setMaximized(true);
		primaryStage.show();
		//primaryStage.setFullScreen(true);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		launch(args);
	}

}
