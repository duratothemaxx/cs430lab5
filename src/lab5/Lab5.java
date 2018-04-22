package lab5;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Lab5 implements Stages {

	private QueryDB queryEngine;

	private Scene scene;
	private GridPane gridpane;

	private Button checkMemeberIDButton;
	private TextField memberIDField;
	private Text memberIDStatus;

	private Button checkIsbnButton;
	private TextField isbnField;
	private Button checkBookNameButton;
	private TextField bookNameField;
	private Button checkAuthorButton;
	private TextField authorField;
	private Button quitButton;
	private Text statusInfo;
	
	private ListView<Book> bookResultsSelectionList;
	private ArrayList<Book> bookResults;
	private Button getBookInfoButton;
	
	private boolean memberVerified = false;

	public Lab5(QueryDB queryEngine) {
		this.queryEngine = queryEngine;
		stage.setTitle("Lab 5");
		bookResults = new ArrayList<Book>();

		createNodes();
		buildGridPaneLayout();
		buildBookResultsList();
		buttonListeners();

		stage.show();
	}
	
	private void buildBookResultsList() {
		if (!bookResultsSelectionList.getItems().isEmpty())
			bookResultsSelectionList.getItems().clear();

		if (bookResults != null) {
			for (Book b : bookResults) {
				bookResultsSelectionList.getItems().add(b);
			}
		}
		else {
			statusInfo.setFill(Color.FIREBRICK);
			statusInfo.setText("no results");
		}
	}

	private void getSelectedBook() {
		if(!bookResultsSelectionList.getSelectionModel().isEmpty()) {
			new BookInfoView(bookResultsSelectionList.getSelectionModel().getSelectedItem(), queryEngine);
		}
		else {
			statusInfo.setFill(Color.FIREBRICK);
			statusInfo.setText("Please select a book");
		}		
	}

	private void buildGridPaneLayout() {
		gridpane = new GridPane();
		gridpane.setGridLinesVisible(false);
		gridpane.setAlignment(Pos.CENTER);
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		gridpane.add(memberIDField, 0, 0);
		gridpane.add(checkMemeberIDButton, 0, 1);
		gridpane.add(quitButton, 0, 2);
		gridpane.add(memberIDStatus, 0, 3);
		gridpane.add(isbnField, 1, 0);
		gridpane.add(checkIsbnButton, 2, 0);
		gridpane.add(bookNameField, 1, 1);
		gridpane.add(checkBookNameButton, 2, 1);
		gridpane.add(authorField, 1, 2);
		gridpane.add(checkAuthorButton, 2, 2);
		gridpane.add(statusInfo, 0, 4, 3, 2);
		gridpane.add(bookResultsSelectionList, 0, 6, 3, 3);
		gridpane.add(getBookInfoButton, 1, 10);

		scene = new Scene(gridpane, 480, 520);
		stage.setScene(scene);
	}

	private void buttonListeners() {
		checkMemeberIDButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean memberExist = queryEngine.queryMember(memberIDField.getText());
				if(memberExist) {
					memberIDStatus.setText("MemberID is valid");
					memberVerified = true;
				}
				else {
					memberIDStatus.setText("MemberID is not valid");
					stage.hide();
					new AddMemeber();
				}
			}
		});
		
		checkIsbnButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(memberVerified) {
					statusInfo.setText("Checking for book with isbn: " + isbnField.getText());
					bookResults = queryEngine.queryBookByISBN(isbnField.getText());
					buildBookResultsList();
				}
				else {
					statusInfo.setText("member not yet verified");
				}
			}
		});
		
		checkBookNameButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(memberVerified) {
					statusInfo.setText("Checking for book with name: " + bookNameField.getText());
					bookResults = queryEngine.queryBookByName(bookNameField.getText());
					buildBookResultsList();
				}
				else {
					statusInfo.setText("member not yet verified");
				}
			}
		});
		
		checkAuthorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(memberVerified) {
					statusInfo.setText("Checking for book with Author: " + authorField.getText());
					bookResults = queryEngine.queryBookByAuthor(authorField.getText());
					buildBookResultsList();
				}
				else {
					statusInfo.setText("member not yet verified");
				}
			}
		});
		
		getBookInfoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getSelectedBook();
			}
		});
		
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
	}

	private void createNodes() {
		checkMemeberIDButton = new Button("submit");
		memberIDField = new TextField();
		memberIDField.setPromptText("MemberID");
		memberIDStatus = new Text();
		memberIDStatus.setFill(Color.FIREBRICK);
		checkIsbnButton = new Button("submit");
		isbnField = new TextField();
		isbnField.setPromptText("isbn search (Exact)");
		checkBookNameButton = new Button("submit");
		bookNameField = new TextField();
		bookNameField.setPromptText("book name search");
		checkAuthorButton = new Button("submit");
		authorField = new TextField();
		authorField.setPromptText("author name search");
		quitButton = new Button("QUIT");
		statusInfo = new Text();
		statusInfo.setWrappingWidth(340);
		statusInfo.setTextAlignment(TextAlignment.LEFT);
		bookResultsSelectionList = new ListView<>();
		bookResultsSelectionList.setPrefHeight(180);
		bookResultsSelectionList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		getBookInfoButton = new Button("Get info from selected");
	}

}
