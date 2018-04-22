package lab5;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddMemeber implements Stages {

	private QueryDB queryEngine;
	
	//private Stage addMemberStage;
	private Scene scene;
	private GridPane gridpane;

	private TextField memberIDField;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField dobField;
	private TextField genderField;

	private Button createMemberButton;
	private Button cancelButton;
	
	private String values;

	public AddMemeber(QueryDB queryEngine) {
		this.queryEngine = queryEngine;

		//addMemberStage = new Stage();
		addMemberStage.setTitle("Add a new Member?");

		createNodes();
		buildGridPaneLayout();
		buttonListeners();

		addMemberStage.show();
	}

	private void createNodes() {
		memberIDField = new TextField();
		memberIDField.setPromptText("MemberID: <####>");
		firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		dobField = new TextField();
		dobField.setPromptText("DOB: <yyyy-mm-dd>");
		genderField = new TextField();
		genderField.setPromptText("gender: <M|F>");
		createMemberButton = new Button("Create Member");
		cancelButton = new Button("Cancel");
	}

	private void buttonListeners() {
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.show();
				addMemberStage.close();
			}
		});
		
		// we want to make sure all the field are filled in when we
		// press the button to create a new member
		createMemberButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(allFilledIn()) {
					System.out.println("All fields filled in");
					createValuesStmt();					
					queryEngine.insertMember(values);
					stage.show();
					addMemberStage.close();
				}
				else {
					System.err.println("Some field not filled in");
				}
				
			}
		});
	}

	private void buildGridPaneLayout() {
		gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.add(memberIDField, 0, 0, 2, 1);
		gridpane.add(firstNameField, 0, 1, 2, 1);
		gridpane.add(lastNameField, 0, 2, 2, 1);
		gridpane.add(dobField, 0, 3, 2, 1);
		gridpane.add(genderField, 0, 4, 2, 1);
		gridpane.add(createMemberButton, 0, 5);
		gridpane.add(cancelButton, 1, 5);
		scene = new Scene(gridpane, 480, 360);
		addMemberStage.setScene(scene);
	}
	
	private boolean allFilledIn() {
		return !memberIDField.getText().isEmpty() && 
				!firstNameField.getText().isEmpty() &&
				!lastNameField.getText().isEmpty() &&
				!dobField.getText().isEmpty() &&
				!genderField.getText().isEmpty();
	}
	
	private void createValuesStmt() {
		values = "('" + memberIDField.getText() + "', '" + lastNameField.getText() +
				"', '" + firstNameField.getText() + "', '" + dobField.getText() + 
				"', '" + genderField.getText() + "');";		
	}

}
