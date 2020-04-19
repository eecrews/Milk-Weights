package application;


// test
import javafx.application.Application;
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
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
