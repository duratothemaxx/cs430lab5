package lab5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lab5 {

	private QueryDB queryEngine;

	private Stage stage;
	private Scene scene;
	private GridPane gridpane;

	private Button checkMemeberIDButton;
	private TextField memberIDField;

	private Button checkIsbnButton;
	private TextField isbnField;
	private Button checkBookNameButton;
	private TextField bookNameField;
	private Button checkAuthorButton;
	private TextField authorField;

	private Button quitButton;

	public Lab5(QueryDB queryEngine) {
		this.queryEngine = queryEngine;
		stage = new Stage();
		stage.setTitle("Lab 5");

		createNodes();
		buildGridPaneLayout();
		buttonListeners();

		stage.show();
	}

	private void buildGridPaneLayout() {
		gridpane = new GridPane();
		gridpane.setGridLinesVisible(false);
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.add(memberIDField, 0, 0);
		gridpane.add(checkMemeberIDButton, 0, 1);
		gridpane.add(isbnField, 1, 0);
		gridpane.add(checkIsbnButton, 2, 0);
		gridpane.add(bookNameField, 1, 1);
		gridpane.add(checkBookNameButton, 2, 1);
		gridpane.add(authorField, 1, 2);
		gridpane.add(checkAuthorButton, 2, 2);
		gridpane.add(quitButton, 1, 7);

		scene = new Scene(gridpane, 480, 360);
		stage.setScene(scene);
	}

	private void buttonListeners() {
		checkMemeberIDButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				queryEngine.queryMember(memberIDField.getText());
			}
		});
	}

	private void createNodes() {
		checkMemeberIDButton = new Button("submit");
		memberIDField = new TextField();
		memberIDField.setPromptText("MemberID");
		checkIsbnButton = new Button("submit");
		isbnField = new TextField();
		isbnField.setPromptText("isbn");
		checkBookNameButton = new Button("submit");
		bookNameField = new TextField();
		bookNameField.setPromptText("book name");
		checkAuthorButton = new Button("submit");
		authorField = new TextField();
		authorField.setPromptText("author name");
		quitButton = new Button("QUIT");
	}

}
