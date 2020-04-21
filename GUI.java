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

		// Canvas for title Graphics
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUEVIOLET);
		gc.setFont(new Font(20));
		gc.fillText("Welcome to the", 275, 70);
		gc.setFill(Color.DARKBLUE);
		gc.setFont(new Font(35));
		gc.fillText("M i l k   A n a l y z e r !", 180, 120);

		// Start button, links to second page method
		Button startButton = new Button("Enter the Analyzer");
		startButton.setPrefSize(200, 50);
		startButton.setLayoutX(250);
		startButton.setLayoutY(300);
		startButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		VBox header = new VBox(canvas);
		pageOneRoot.getChildren().addAll(header, startButton);

		Scene landingPage = new Scene(pageOneRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
		// Scene for landing Page
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(landingPage);
		primaryStage.show();
	}

	public void menuPage(Stage primaryStage) throws Exception {
		// menuPage (#2) -- by Tushar

		Pane pageTwoRoot = new Pane();

		// Buttons for checking data
		Button b1 = new Button("Add New Data");
		b1.setPrefSize(150, 50);
		b1.setOnAction(value -> {
			try {
				dataEntryPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Button b2 = new Button("Edit Current Data");
		b2.setPrefSize(150, 50);

		Button b3 = new Button("View Analysis");
		b3.setPrefSize(150, 50);

		// Vbox for 3 main buttons
		VBox centerBox = new VBox(b1, b2, b3);
		centerBox.setLayoutX(270);
		centerBox.setLayoutY(150);
		centerBox.setSpacing(10);

		// Sends user back to landing page
		Button b4 = new Button("Back");
		b4.setPrefSize(150, 50);
		b4.setLayoutX(0);
		b4.setLayoutY(450);
		b4.setOnAction(value -> {
			try {
				start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		// Sends user to feedback page
		Button b5 = new Button("Feedback");
		b5.setPrefSize(150, 50);
		b5.setLayoutX(550);
		b5.setLayoutY(450);
		b5.setOnAction(value -> {
			try {
				feedbackPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		pageTwoRoot.getChildren().addAll(centerBox, b4, b5);
		Scene menuPage = new Scene(pageTwoRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(menuPage);
		primaryStage.show();
	}

	public void dataEntryPage(Stage primaryStage) throws Exception {
		// dataAddPage (#3) -- by Erin

		BorderPane pageThreeRoot = new BorderPane();

		VBox pageThreeLeftVbox = new VBox(new Label("Manual Entry "), idEntry, yearEntry,
				monthEntry, dateEntry, weightEntry, new Button("Enter "));
		HBox fileEntry = new HBox(new Label("File location "), new TextField());
		VBox pageThreeRightVbox = new VBox(new Label("Add from File "), fileEntry, new Button(
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

	public void feedbackPage(Stage primaryStage) throws Exception {
		// feedbackPage (#14) -- by Tushar

		BorderPane pageFourteenRoot = new BorderPane();

		HBox nameLine = new HBox(new Label("Name: "), new TextField());
		HBox emailLine = new HBox(new Label("Email: "), new TextField());
		TextField fbField = new TextField();
		fbField.setPrefSize(300, 300);
		HBox feedbackLine = new HBox(new Label("Feedback: "), fbField);

		// Sends user back to menu page
		Button back = new Button("Back");
		back.setPrefSize(150, 50);
		back.setLayoutX(0);
		back.setLayoutY(450);
		back.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		// Sends user to feedback page
		Button enter = new Button("Enter");
		enter.setPrefSize(150, 50);
		enter.setLayoutX(550);
		enter.setLayoutY(450);
		HBox buttons = new HBox(back, enter);
		buttons.setSpacing(400);
		VBox lines = new VBox(nameLine, emailLine, feedbackLine);

		pageFourteenRoot.setTop(lines);
		pageFourteenRoot.setBottom(buttons);

		Scene feedbackPage = new Scene(pageFourteenRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(feedbackPage);
		primaryStage.show();
	}

}
