package lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lab5 extends Application {
	
	private Stage stage;
	private Button checkMemeberIDButton;
	private TextField memberIDField;
	
	

	public Lab5() {
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		stage = new Stage();
		stage.setTitle("Lab 5");
		
		GridPane gridpane = new GridPane();
		
		Scene scene = new Scene(gridpane, 480, 360);
		stage.setScene(scene);
		
		stage.show();
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}


}
