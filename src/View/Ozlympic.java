package View;

/**
 * @author Hsienting Chu
 *
 */
import Controller.Driver;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class Ozlympic extends Application {

	Stage window;
	Scene scene1, scene2, scene3, scene4;

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.dbconnection();
		driver.readFile();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		// layout 1
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(4));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);

		Text title = new Text("Please Select One to start");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		Button btNewGame = new Button("New Game");
		Button btGameHistory = new Button("Game History");
		Button btAthletePoints = new Button("Athlete Points");
		Button btBacktoScene1 = new Button("Main Menu");

		btNewGame.setPrefSize(100, 50);
		btGameHistory.setPrefSize(100, 50);
		btAthletePoints.setPrefSize(100, 50);
		btBacktoScene1.setPrefSize(100, 50);

		vbox.getChildren().add(title);
		vbox.getChildren().addAll(btNewGame, btGameHistory, btAthletePoints);

		btNewGame.setOnAction(e -> window.setScene(scene2));
		btGameHistory.setOnAction(e -> window.setScene(scene3));
		btAthletePoints.setOnAction(e -> window.setScene(scene4));
		btBacktoScene1.setOnAction(e -> window.setScene(scene1));

		scene1 = new Scene(vbox, 300, 250);
		window.setTitle("Ozlympic Game");
		window.setScene(scene1);
		window.show();

		// layout 2
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox();
		VBox vbox3 = new VBox();

		vbox1.getChildren().add(btBacktoScene1);
		scene2 = new Scene(vbox1, 500, 500);
		window.setTitle("Main Screen");

		// layout3

		//vbox2.getChildren().add(btBacktoScene1);
		scene3 = new Scene(vbox2, 500, 500);
		window.setTitle("Game History");

		// layout4

		//vbox3.getChildren().add(btBacktoScene1);
		scene4 = new Scene(vbox3, 500, 500);
		window.setTitle("Athlete Points");

	}

}