package lab5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class BookInfoView implements Stages {
	
	private Scene scene;
	private GridPane gridpane;
	private Text bookInfo;
	private Button okButton;

	public BookInfoView(String info) {
		bookInfoStage.setTitle("Selected Book Info");
		
		gridpane = new GridPane();
		bookInfo = new Text();
		okButton = new Button("OK");
		bookInfo.setWrappingWidth(220);
		bookInfo.setText(info);
		gridpane.add(bookInfo, 0, 0, 2, 2);
		gridpane.add(okButton, 0, 4);
		
		buttonEvents();
		scene = new Scene(gridpane, 360, 240);
		bookInfoStage.setScene(scene);		
		bookInfoStage.show();
	}
	
	public void buttonEvents() {
		okButton.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				stage.show();
				bookInfoStage.close();
			}
		});
	}
}
