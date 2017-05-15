package View;

/**
 * @author Hsienting Chu
 *
 */
import Controller.Driver;
import View.MainScene;
import javafx.application.*;
import javafx.stage.*;

public class Ozlympic extends Application {
	Driver driver;
	Stage window;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		driver = new Driver(primaryStage);
		driver.DBCheck();
		driver.readData();
		driver.initialisation();
		window = primaryStage;

		// Items Adding
		MainScene mainScene = new MainScene(driver);
		window.setTitle(mainScene.getTitle());
		window.setScene(mainScene.getScene());
		window.show();
	}

}