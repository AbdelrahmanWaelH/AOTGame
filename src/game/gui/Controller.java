package game.gui;
import java.io.IOException;
import game.engine.Battle;
import javafx.fxml.FXML;

public class Controller {
	@FXML
	
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
