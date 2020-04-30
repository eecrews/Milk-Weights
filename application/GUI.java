package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
			try {// TODO: change this back
				dataEntryPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Button b2 = new Button("Edit Current Data");
		b2.setPrefSize(150, 50);
		b2.setOnAction(value -> {
			try {
				dataEditPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Button b3 = new Button("View Analysis");
		b3.setPrefSize(150, 50);
		b3.setOnAction(value -> {
			try {
				analysisMenuPage(primaryStage);
			} catch (Exception e) {
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

		Button exitButton = new Button("Exit");
		exitButton.setPrefSize(150, 50);
		exitButton.setLayoutX(550);
		exitButton.setLayoutY(0);
		exitButton.setOnAction(value -> {
			try {
				Platform.exit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		pageTwoRoot.getChildren().addAll(centerBox, b4, exitButton, b5);
		Scene menuPage = new Scene(pageTwoRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(menuPage);
		primaryStage.show();
	}

	public void dataEntryPage(Stage primaryStage) throws Exception {
		// dataAddPage (#3) -- by Erin

		Alert fileNotFound = new Alert(AlertType.ERROR);
		fileNotFound.setContentText("File not found. Please try again.");

		Alert informationOmitted = new Alert(AlertType.ERROR);
		informationOmitted.setContentText("Lines were omitted due to missing information.");

		Pane pageThreeRoot = new Pane();

		TextField fileEntryField = new TextField();

		HBox fileEntry = new HBox(new Label("File location: "), fileEntryField);
		Button enterButton = new Button("Enter");
		VBox pageThreeRightVbox = new VBox(new Label("Add from File "), fileEntry, enterButton);

		enterButton.setOnAction(e -> {
			try {
				Driver.parseFile(fileEntryField.getText());
			} catch (FileNotFoundException e1) {
				fileNotFound.showAndWait();
			} catch (Driver.InformationOmittedException e1) {
				informationOmitted.showAndWait();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		((Labeled) pageThreeRightVbox.getChildren().get(0)).setFont(new Font("Arial", 20));
		pageThreeRightVbox.setLayoutX(270);
		pageThreeRightVbox.setLayoutY(150);
		pageThreeRightVbox.setSpacing(20);

		Button backButton = new Button("Back");
		backButton.setPrefSize(150, 50);
		backButton.setLayoutX(0);
		backButton.setLayoutY(450);
		backButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		pageThreeRoot.getChildren().addAll(pageThreeRightVbox, backButton);

		Scene dataEntryPage = new Scene(pageThreeRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(dataEntryPage);
		primaryStage.show();

	}

	public void dataEditPage(Stage primaryStage) throws Exception {
		// edit data (#4) -- by Erin

		HBox idEntry = new HBox(new Label(" Farm ID "), new TextField());
		HBox yearEntry = new HBox(new Label(" Year "), new TextField());
		HBox monthEntry = new HBox(new Label(" Month "), new TextField());
		HBox dateEntry = new HBox(new Label(" Date "), new TextField());

		Pane pageFourRoot = new Pane();

		VBox pageFourRightVbox = new VBox(new Label(
				"Edit an Entry!\n(Leave field blank if no change)"), idEntry, yearEntry, monthEntry,
				dateEntry, new Button("Enter "));
		((Labeled) pageFourRightVbox.getChildren().get(0)).setFont(new Font("Arial", 20));
		pageFourRightVbox.setLayoutX(240);
		pageFourRightVbox.setLayoutY(100);
		pageFourRightVbox.setSpacing(15);

		Button backButton = new Button("Back");
		backButton.setPrefSize(150, 50);
		backButton.setLayoutX(0);
		backButton.setLayoutY(450);
		backButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		pageFourRoot.getChildren().addAll(pageFourRightVbox, backButton);

		Scene dataEditPage = new Scene(pageFourRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(dataEditPage);
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

	/**
	 * TODO: implement this. I just needed the method to call from my page methods
	 */
	public void analysisMenuPage(Stage primaryStage) {
		// Analysis menu page (#5) by Me
		// jk it's by Akshay
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();

		Button farmReportButton = new Button("Farm Report");
		farmReportButton.setPrefSize(150, 50);
		Button annualReportButton = new Button("Annual Report");
		annualReportButton.setPrefSize(150, 50);
		Button monthlyReportButton = new Button("Monthly Report");
		monthlyReportButton.setPrefSize(150, 50);
		Button rangeReportButton = new Button("Date Range Report");
		rangeReportButton.setPrefSize(150, 50);
		Button backButton = new Button("Back");
		VBox buttonBox = new VBox(farmReportButton, annualReportButton, monthlyReportButton,
				rangeReportButton);
		buttonBox.setLayoutX(270);
		buttonBox.setLayoutY(100);
		buttonBox.setSpacing(10);

		// Add the vertical box to the center of the root pane

		farmReportButton.setOnAction(value -> {
			try {
				farmAnalysisPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		annualReportButton.setOnAction(value -> {
			try {
				annualAnalysisPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		monthlyReportButton.setOnAction(value -> {
			try {
				monthlyAnalysisPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		rangeReportButton.setOnAction(value -> {
			try {
				dateRangeAnalysisPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		backButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		backButton.setPrefSize(150, 50);
		backButton.setLayoutX(0);
		backButton.setLayoutY(450);

		root.getChildren().addAll(buttonBox, backButton);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public void farmAnalysisPage(Stage primaryStage) throws Exception {
		// Farm Analysis Page (#6) by Akshay
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Farm ID: ");
		TextField id = new TextField();
		Label yearLabel = new Label("Year: ");
		TextField year = new TextField();
		Button backButton = new Button("Back");
		backButton.setPrefSize(150, 50);
		Button enterButton = new Button("Enter");
		enterButton.setPrefSize(150, 50);

		// Add the vertical box to the center of the root pane
		
		Alert inputError = new Alert(AlertType.ERROR);
		inputError.setContentText("Please enter valid inputs.\nFarm name format: \"Farm #\" (omit quotes)");
		
		Alert farmDoesntExist = new Alert(AlertType.ERROR);
		farmDoesntExist.setContentText("Farm doesn't exist in the current data set. Please try again.");
		
		Alert yearDoesntExist = new Alert(AlertType.ERROR);
		yearDoesntExist.setContentText("No data currently exists for that year. Please try again.");

		id.setLayoutX((WINDOW_WIDTH / 2) - 75);
		id.setLayoutY(150);
		idLabel.setLayoutX((WINDOW_WIDTH / 2) - 125);
		idLabel.setLayoutY(150);
		yearLabel.setLayoutX((WINDOW_WIDTH / 2) - 125);
		yearLabel.setLayoutY(250);
		year.setLayoutX((WINDOW_WIDTH / 2) - 75);
		year.setLayoutY(250);
		enterButton.setLayoutX(550);
		enterButton.setLayoutY(450);
		enterButton.setOnAction(value -> {
			try {
				farmOutputPage(primaryStage, id.getText(), Integer.parseInt(year.getText()));
			} catch (NumberFormatException e1) {
				inputError.showAndWait();
			} catch(IndexOutOfBoundsException e1) {
				yearDoesntExist.showAndWait();
			} catch(Driver.FarmDoesNotExistException e1) {
				farmDoesntExist.showAndWait();
			} catch(Driver.YearDoesNotExistException e1) {
				yearDoesntExist.showAndWait();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		root.getChildren().add(id);
		root.getChildren().add(idLabel);
		root.getChildren().add(year);
		root.getChildren().add(yearLabel);
		backButton.setLayoutX(0);
		backButton.setLayoutY(450);
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

	/**
	 * Annual Analysis Page (7)
	 * 
	 * @author Taidgh
	 */
	public void annualAnalysisPage(Stage primaryStage) throws Exception {
		Pane pageSevenRoot = new Pane();
		TextField yearEntry = new TextField();

		HBox yearInput = new HBox(new Label("Enter the year: "), yearEntry);
		((Labeled) yearInput.getChildren().get(0)).setFont(new Font("Arial", 20));
		yearInput.setLayoutX(180);
		yearInput.setLayoutY(220);
		// Sends user back to analysis menu page
		Button back = new Button("Back");
		back.setPrefSize(150, 50);
		back.setLayoutX(0);
		back.setLayoutY(450);
		back.setOnAction(value -> {
			try {
				analysisMenuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// Sends user to annual analysis output page
		
		Alert inputError = new Alert(AlertType.ERROR);
		inputError.setContentText("Please enter valid input.");
		
		Alert yearDoesntExist = new Alert(AlertType.ERROR);
		yearDoesntExist.setContentText("No data currently exists for that year. Please try again.");
		
		Button enter = new Button("Enter");
		enter.setPrefSize(150, 50);
		enter.setLayoutX(550);
		enter.setLayoutY(450);
		enter.setOnAction(value -> {
			try {
				annualOutputPage(primaryStage, Integer.parseInt(yearEntry.getText()));
			} catch (NumberFormatException e1) {
				inputError.showAndWait();
			} catch(IndexOutOfBoundsException e1) {
				yearDoesntExist.showAndWait();
			} catch(Driver.YearDoesNotExistException e1) {
				yearDoesntExist.showAndWait();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		pageSevenRoot.getChildren().addAll(back, enter, yearInput);

		Scene annualAnalysisPage = new Scene(pageSevenRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(annualAnalysisPage);
		primaryStage.show();
	}

	/**
	 * Monthly Analysis Page (8)
	 * 
	 * @author Taidgh
	 */
	public void monthlyAnalysisPage(Stage primaryStage) throws Exception {
		Pane pageEightRoot = new Pane();
		TextField yearEntry = new TextField();
		TextField monthEntry = new TextField();

		HBox yearInput = new HBox(new Label("Year:     "), yearEntry);
		HBox monthInput = new HBox(new Label("Month: "), monthEntry);

		VBox inputs = new VBox(yearInput, monthInput);
		inputs.setLayoutX(250);
		inputs.setLayoutY(150);
		inputs.setSpacing(10);

		// Sends user back to analysis menu page
		Button back = new Button("Back");
		back.setPrefSize(150, 50);
		back.setLayoutX(0);
		back.setLayoutY(450);
		back.setOnAction(value -> {
			try {
				analysisMenuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// Sends user to monthly analysis output page
		
		Alert inputError = new Alert(AlertType.ERROR);
		inputError.setContentText("Please enter valid inputs.)");
		
		Alert yearDoesntExist = new Alert(AlertType.ERROR);
		yearDoesntExist.setContentText("No data currently exists for that year. Please try again.");
		
		Alert monthDoesntExist = new Alert(AlertType.ERROR);
		monthDoesntExist.setContentText("No data currently exists for that month. Please try again.");
		
		Button enter = new Button("Enter");
		enter.setPrefSize(150, 50);
		enter.setLayoutX(550);
		enter.setLayoutY(450);
		enter.setOnAction(value -> {
			try {
				monthlyOutputPage(primaryStage, Integer.parseInt(yearEntry.getText()), Integer
						.parseInt(monthEntry.getText()));
			} catch (NumberFormatException e1) {
				inputError.showAndWait();
			} catch(IndexOutOfBoundsException e1) {
				yearDoesntExist.showAndWait();
			} catch(Driver.MonthDoesNotExistException e1) {
				monthDoesntExist.showAndWait();
			} catch(Driver.YearDoesNotExistException e1) {
				yearDoesntExist.showAndWait();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		pageEightRoot.getChildren().addAll(back, enter, inputs);

		Scene annualAnalysisPage = new Scene(pageEightRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(annualAnalysisPage);
		primaryStage.show();

	}

	/**
	 * Data Range Analysis Page (9)
	 * 
	 * @author Taidgh
	 */
	public void dateRangeAnalysisPage(Stage primaryStage) throws Exception {
		Pane pageNineRoot = new Pane();
		// Initialize and combine fields and labels
		Label start = new Label("Start");
		start.setFont(new Font("Times New Roman", 20));
		Label end = new Label("End");
		end.setFont(new Font("Times New Roman", 20));
		TextField yearEntry = new TextField();
		TextField monthEntry1 = new TextField();
		TextField dayEntry1 = new TextField();
		TextField monthEntry2 = new TextField();
		TextField dayEntry2 = new TextField();
		HBox yearInput = new HBox(new Label("Year     "), yearEntry);
		HBox monthInput = new HBox(new Label("Month "), monthEntry1);
		HBox dayInput = new HBox(new Label("Day      "), dayEntry1);
		HBox monthInput2 = new HBox(new Label("Month "), monthEntry2);
		HBox dayInput2 = new HBox(new Label("Day      "), dayEntry2);

		VBox startInputs = new VBox(start, yearInput, monthInput, dayInput);
		startInputs.setLayoutX(150);
		startInputs.setLayoutY(150);
		VBox endInputs = new VBox(end, monthInput2, dayInput2);
		endInputs.setLayoutX(350);
		endInputs.setLayoutY(150);

		// Sends user back to analysis menu page
		Button back = new Button("Back");
		back.setPrefSize(150, 50);
		back.setLayoutX(0);
		back.setLayoutY(450);
		back.setOnAction(value -> {
			try {
				analysisMenuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		// Sends user to data range analysis output page
		
		Alert inputError = new Alert(AlertType.ERROR);
		inputError.setContentText("Please enter valid inputs.\nFarm name format: \"Farm #\" (omit quotes)");
		
		Alert farmDoesntExist = new Alert(AlertType.ERROR);
		farmDoesntExist.setContentText("Farm doesn't exist in the current data set. Please try again.");
		
		Alert yearDoesntExist = new Alert(AlertType.ERROR);
		yearDoesntExist.setContentText("No data currently exists for that year. Please try again.");
		
		Alert monthDoesntExist = new Alert(AlertType.ERROR);
		monthDoesntExist.setContentText("No data currently exists for that month. Please try again.");
		
		Button enter = new Button("Enter");
		enter.setPrefSize(150, 50);
		enter.setLayoutX(550);
		enter.setLayoutY(450);

		enter.setOnAction(value -> {
			try {
				dateRangeOutputPage(primaryStage, Integer.parseInt(yearEntry.getText()), Integer
						.parseInt(monthEntry1.getText()), Integer.parseInt(dayEntry1.getText()),
						Integer.parseInt(monthEntry2.getText()), Integer.parseInt(dayEntry2
								.getText()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		pageNineRoot.getChildren().addAll(enter, back, startInputs, endInputs);

		Scene annualAnalysisPage = new Scene(pageNineRoot, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(annualAnalysisPage);
		primaryStage.show();

	}

	public void farmOutputPage(Stage primaryStage, String farmId, int outputYear) throws Exception {
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Total Weight: ");
		ListView id = new ListView();
		id.setPrefSize(300, 75);
		Label yearLabel = new Label("Percent of Total: ");
		ListView year = new ListView();
		year.setPrefSize(300, 75);
		Button backButton = new Button("Back");
		backButton.setPrefSize(150, 50);
		Button enterButton = new Button("Main Menu");
		enterButton.setLayoutX(550);
		enterButton.setLayoutY(450);
		enterButton.setPrefSize(150, 50);
		enterButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(enterButton);

		// Add the vertical box to the center of the root pane

		VBox lab = new VBox();	
		ArrayList<Label> nodes = getOutput(Driver.printFarmReport(farmId, outputYear));	
		for(Label node: nodes) {	
			lab.getChildren().add(node);	
		}	
		ScrollPane sp = new ScrollPane();	
		sp.setContent(lab);	
        sp.setPrefHeight(WINDOW_HEIGHT);	
        sp.setPrefWidth(WINDOW_WIDTH/1.9);	
        sp.setLayoutX((WINDOW_WIDTH / 2) - (WINDOW_WIDTH / 3.8));	
        sp.setPannable(true);	
        sp.setVisible(true);		
        root.getChildren().add(sp);

		backButton.setLayoutY(450);
		backButton.setLayoutX(0);

		backButton.setOnAction(value -> {
			try {
				farmAnalysisPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(backButton);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * Annual output Page (11)
	 *
	 * @author Richard
	 */

	public void annualOutputPage(Stage primaryStage, int yearInput) throws Exception {
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Annual Total Weight: ");
		ListView id = new ListView();
		Label yearLabel = new Label("Annual Percent of Total: ");
		ListView year = new ListView();
		Button backButton = new Button("Back");
		backButton.setPrefSize(150, 50);
		Button enterButton = new Button("Main Menu");
		enterButton.setLayoutX(550);
		enterButton.setLayoutY(450);
		enterButton.setPrefSize(150, 50);
		enterButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(enterButton);

		// Add the vertical box to the center of the root pane

		VBox lab = new VBox();	
		ArrayList<Label> nodes = getOutput(Driver.printAnnualReport(yearInput));	
		for(Label node: nodes) {	
			lab.getChildren().add(node);	
		}	
		ScrollPane sp = new ScrollPane();	
		sp.setContent(lab);	
        sp.setPrefHeight(WINDOW_HEIGHT);	
        sp.setPrefWidth(WINDOW_WIDTH/1.9);	
        sp.setLayoutX((WINDOW_WIDTH / 2) - (WINDOW_WIDTH / 3.8));	
        sp.setPannable(true);	
        sp.setVisible(true);
        root.getChildren().add(sp);
        
		backButton.setLayoutY(450);
		backButton.setLayoutX(0);
		backButton.setOnAction(value -> {
			try {
				annualAnalysisPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		root.getChildren().add(backButton);
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

	/**
	 * Monthly Output Page (12)
	 *
	 * @author Richard
	 */
	public void monthlyOutputPage(Stage primaryStage, int yearInput, int monthInput) throws Exception {

		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Monthly Total Weight: ");
		ListView id = new ListView();
		Label yearLabel = new Label("Monthly Percent of Total: ");
		ListView year = new ListView();
		Button backButton = new Button("Back");
		Button enterButton = new Button("Main Menu");
		enterButton.setLayoutX(550);
		enterButton.setLayoutY(450);
		enterButton.setPrefSize(150, 50);
		enterButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
			
		VBox lab = new VBox();	
		ArrayList<Label> nodes = getOutput(Driver.printMonthlyReport(yearInput, monthInput));	
		for(Label node: nodes) {	
			lab.getChildren().add(node);	
		}	
		ScrollPane sp = new ScrollPane();	
		sp.setContent(lab);	
        sp.setPrefHeight(WINDOW_HEIGHT);	
        sp.setPrefWidth(WINDOW_WIDTH/1.9);	
        sp.setLayoutX((WINDOW_WIDTH / 2) - (WINDOW_WIDTH / 3.8));	
        sp.setPannable(true);	
        sp.setVisible(true);
        root.getChildren().add(sp);
		backButton.setLayoutY(WINDOW_HEIGHT - 30);
		backButton.setLayoutX(10);
		backButton.setOnAction(value -> {
			try {
				monthlyAnalysisPage(primaryStage);
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

	/**
	 * Data Range Analysis Page (13)
	 *
	 * @author Richard
	 */
	public void dateRangeOutputPage(Stage primaryStage, int year1, int month1, int day1, int month2,
			int day2) {

		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		Label idLabel = new Label("Total Weight: ");
		ListView id = new ListView();
		Label yearLabel = new Label("Percent of Total: ");
		ListView year = new ListView();
		Button backButton = new Button("Back");
		Button enterButton = new Button("Main Menu");
		enterButton.setLayoutX(550);
		enterButton.setLayoutY(450);
		enterButton.setPrefSize(150, 50);
		enterButton.setOnAction(value -> {
			try {
				menuPage(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		VBox lab = new VBox();	
		ArrayList<Label> nodes = getOutput(Driver.printDateRangeReport(year1, month1, day1, month2, day2));	
		for(Label node: nodes) {	
			lab.getChildren().add(node);	
		}	
		ScrollPane sp = new ScrollPane();	
		sp.setContent(lab);	
        sp.setPrefHeight(WINDOW_HEIGHT);	
        sp.setPrefWidth(WINDOW_WIDTH/1.9);	
        sp.setLayoutX((WINDOW_WIDTH / 2) - (WINDOW_WIDTH / 3.8));	
        sp.setPannable(true);	
        sp.setVisible(true);
        root.getChildren().add(sp);

		backButton.setLayoutX(0);
		backButton.setLayoutY(450);
		backButton.setPrefSize(150, 50);

		backButton.setOnAction(value -> {
			try {
				dateRangeAnalysisPage(primaryStage);
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
    
    private ArrayList<Label> getOutput(String input) {	
		ArrayList<Label> nodes = new ArrayList<Label>();	
		String[] lines = input.split("\\r?\\n");	
		for(String line: lines) {	
			nodes.add(new Label(line));	
		}	
		return nodes;	
	}
}