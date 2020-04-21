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
		b3.setOnAction(value -> {
			try {
				analysisMenuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
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

	public void analysisMenuPage(Stage primaryStage) throws Exception {

		//Analysis menu page (#5) by Me
		//jk it's by Akshay
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Button farmReportButton = new Button("Farm Report");
		Button AnnualReportButton = new Button("Annual Report");
		Button MonthlyReportButton = new Button("Monthly Report");
		Button RangeReportButton = new Button("Date Range Report");
		Button backButton = new Button("Back");


		// Add the vertical box to the center of the root pane

		farmReportButton.setLayoutX(WINDOW_WIDTH/2);
		farmReportButton.setLayoutY(WINDOW_HEIGHT/4);
		farmReportButton.setOnAction(value -> {
			try {
				farmAnalysisPage(primaryStage);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});

		AnnualReportButton.setLayoutX(WINDOW_WIDTH/2);
		AnnualReportButton.setLayoutY(WINDOW_HEIGHT/2);
		MonthlyReportButton.setLayoutX(WINDOW_WIDTH/2);
		MonthlyReportButton.setLayoutY((3/4)*WINDOW_HEIGHT);
		RangeReportButton.setLayoutX(WINDOW_WIDTH/2);
		RangeReportButton.setLayoutY(WINDOW_HEIGHT-(WINDOW_HEIGHT/4));
		root.getChildren().add(farmReportButton);
		root.getChildren().add(AnnualReportButton);
		root.getChildren().add(MonthlyReportButton);
		root.getChildren().add(RangeReportButton);
		backButton.setLayoutY(WINDOW_HEIGHT-30);
		backButton.setLayoutX(10);
		root.getChildren().add(backButton);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public void farmAnalysisPage(Stage primaryStage) throws Exception {
		//Farm Analysis Page (#6) by Akshay
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Farm ID: ");
		TextField id = new TextField();
		Label yearLabel = new Label("Year: ");
		TextField year = new TextField();
		Button backButton = new Button("Back");
		Button enterButton = new Button("Enter");



		// Add the vertical box to the center of the root pane

		id.setLayoutX((WINDOW_WIDTH/2)-75);
		id.setLayoutY(WINDOW_HEIGHT/4);
		idLabel.setLayoutX((WINDOW_WIDTH/2)-125);
		idLabel.setLayoutY(WINDOW_HEIGHT/4);
		yearLabel.setLayoutX((WINDOW_WIDTH/2)-125);
		yearLabel.setLayoutY(WINDOW_HEIGHT*(3/4));
		year.setLayoutX((WINDOW_WIDTH/2)-75);
		year.setLayoutY(WINDOW_HEIGHT*(3/4));
		enterButton.setLayoutX(WINDOW_WIDTH/2);
		enterButton.setLayoutY(WINDOW_HEIGHT-150);
		enterButton.setOnAction(value -> {
			try {
				farmOutputPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(id);
		root.getChildren().add(idLabel);
		root.getChildren().add(year);
		root.getChildren().add(yearLabel);
		backButton.setLayoutY(WINDOW_HEIGHT-30);
		backButton.setLayoutX(10);
		backButton.setOnAction(value -> {
			try {
				analysisMenuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(backButton);
		root.getChildren().add(enterButton);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public void farmOutputPage(Stage primaryStage) throws Exception {
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Total Weight: ");
		TextField id = new TextField();
		Label yearLabel = new Label("Percent of Total: ");
		TextField year = new TextField();
		Button backButton = new Button("Back");
		Button enterButton = new Button("Enter");



		// Add the vertical box to the center of the root pane

		id.setLayoutX((WINDOW_WIDTH/2)-75);
		id.setLayoutY(WINDOW_HEIGHT/4);
		idLabel.setLayoutX((WINDOW_WIDTH/2)-175);
		idLabel.setLayoutY(WINDOW_HEIGHT/4);
		yearLabel.setLayoutX((WINDOW_WIDTH/2)-175);
		yearLabel.setLayoutY(WINDOW_HEIGHT*(3/4));
		year.setLayoutX((WINDOW_WIDTH/2)-75);
		year.setLayoutY(WINDOW_HEIGHT*(3/4));
		enterButton.setLayoutX(WINDOW_WIDTH/2);
		enterButton.setLayoutY(WINDOW_HEIGHT-150);
		root.getChildren().add(id);
		root.getChildren().add(idLabel);
		root.getChildren().add(year);
		root.getChildren().add(yearLabel);
		backButton.setLayoutY(WINDOW_HEIGHT-30);
		backButton.setLayoutX(10);
		backButton.setOnAction(value -> {
			try {
				farmAnalysisPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(backButton);
		root.getChildren().add(enterButton);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

}
