package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
	public void start(Stage primaryStage) throws Exception {
		// landingPage (#1) -- by Tushar

		Pane pageOneRoot = new Pane();

		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUEVIOLET);
		gc.setFont(new Font(20));
		gc.fillText("Welcome to the", 275, 70);
		gc.setFill(Color.DARKBLUE);
		gc.setFont(new Font(35));
		gc.fillText("M i l k   A n a l y z e r !", 180, 120);
		Button startButton = new Button("Enter the Analyzer");
		startButton.setPrefSize(200, 50);
		startButton.setLayoutX(250);
		startButton.setLayoutY(300);
		startButton.setOnAction(value -> {
			try {
				dataEntryPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		VBox header = new VBox(canvas);
		pageOneRoot.getChildren().addAll(header, startButton);

		Scene landingPage = new Scene(pageOneRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(landingPage);
		primaryStage.show();
	}

	public void dataEntryPage(Stage primaryStage) throws Exception {
		// dataAddPage (#3) -- by Erin

		BorderPane pageThreeRoot = new BorderPane();

		VBox pageThreeLeftVbox = new VBox(new Label("Manual Entry"), idEntry, yearEntry, monthEntry,
				dateEntry, weightEntry, new Button("Enter"));
		HBox fileEntry = new HBox(new Label("File location"), new TextField());
		VBox pageThreeRightVbox = new VBox(new Label("Add from File"), fileEntry, new Button(
				"Enter"));

		pageThreeRoot.setLeft(pageThreeLeftVbox);
		pageThreeRoot.setRight(pageThreeRightVbox);

		Scene dataAddPage = new Scene(pageThreeRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Final step in GUI -- setting app title and setting the scene to the
		// welcome page

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(dataAddPage); // not created yet
		primaryStage.show();

	}

}
