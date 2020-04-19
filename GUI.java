package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * GUI - TODO Describe purpose of this user-defined type
 * 
 */
public class GUI extends Application {
	private static final int WINDOW_WIDTH = 700;
	private static final int WINDOW_HEIGHT = 500;
	private static final String APP_TITLE = "Milk Weights";
	
	private static final HBox idEntry = new HBox(new Label(" ID "), new TextField());
	private static final HBox yearEntry = new HBox(new Label(" Year "), new TextField());
	private static final HBox monthEntry = new HBox(new Label(" Month "), new TextField());
	private static final HBox dateEntry = new HBox(new Label(" Date "), new TextField());
	private static final HBox weightEntry = new HBox(new Label(" Weight "), new TextField());
	

	@Override
	public void start(Stage arg0) throws Exception {
		// dataAddPage (#3) -- by Erin
		
		BorderPane pageThreeRoot = new BorderPane();
		
		VBox pageThreeLeftVbox = new VBox(new Label("Manual Entry"), idEntry, 
				yearEntry, monthEntry, dateEntry, weightEntry, new Button("Enter"));
		HBox fileEntry = new HBox(new Label("File location"), new TextField());
		VBox pageThreeRightVbox = new VBox(new Label("Add from File"), fileEntry,
				new Button("Enter")); 
		
		pageThreeRoot.setLeft(pageThreeLeftVbox);
		pageThreeRoot.setRight(pageThreeRightVbox);

		Scene dataAddPage = new Scene(pageThreeRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		
		
		
		
		
		
		
		
		
		
		// Final step in GUI -- setting app title and setting the scene to the
		//						welcome page
		
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(welcomePage); // not created yet
		primaryStage.show();

	}

}
