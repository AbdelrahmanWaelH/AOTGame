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
		try{
		Stage splashStage = new Stage();
		splashStage.setTitle(title);
		splashStage.getIcons().add(new Image(iconPath));
		Parent splashRoot = FXMLLoader.load(getClass().getResource("Splash.fxml"));
		Scene splashScene = new Scene(splashRoot);
		splashStage.setScene(splashScene);
		splashStage.show();
		splashStage.setMaximized(true);
		Thread.sleep(3000);
		splashStage.close();

		
		primaryStage.setTitle(title);
		primaryStage.getIcons().add(new Image(iconPath));
		Parent mainUI = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(mainUI);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setMaximized(true);
		//primaryStage.setFullScreen(true);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		launch();
	}

}
