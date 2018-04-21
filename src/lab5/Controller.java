package lab5;

import javafx.application.Application;
import javafx.stage.Stage;

public class Controller extends Application {
	
	private QueryDB queryEngine = new QueryDB();
	

	public Controller() {
	}
	
	public static void main(String[] args) {
		 launch(args);				
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Lab5(queryEngine);
	}

}
