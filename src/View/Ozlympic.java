package View;

/**
 * @author Hsienting Chu
 *
 */
import Controller.Driver;
import Model.*;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.ArrayList;

public class Ozlympic extends Application {
	Driver driver;
	Stage window;
	Scene scene1, scene2, scene3, scene4;

	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		driver = new Driver();
		driver.dbconnection();
		driver.readFile();
		
		window = primaryStage;

		// Scene 1
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

		
		//Action handler 

		btGameHistory.setOnAction(e -> window.setScene(scene3));
		btAthletePoints.setOnAction(e -> window.setScene(scene4));
		btBacktoScene1.setOnAction(e -> window.setScene(scene1));

		scene1 = new Scene(vbox, 300, 250);
		window.setTitle("Ozlympic Game");
		window.setScene(scene1);
		window.show();

		// Scene 2
		StackPane rootPane = new StackPane();
		Pane pane1 = new VBox();
		Pane pane2 = new VBox();
		Pane pane3 = new VBox();
		Pane pane4 = new VBox();
		
		
//		ChoiceBox gameType = new ChoiceBox(FXCollections.observableArrayList("Swimming", "Cycling", "Running"));
//
//		
//		pane1.getChildren().addAll(gameType,btBacktoScene1);
		
		
		ListView<Athlete> AthleteListView = new ListView<>();
		ArrayList<Athlete> athletesList = driver.getAthleteList();


		
		ObservableList<Athlete> AthleteObservableList = FXCollections.observableArrayList(athletesList);
		AthleteListView.setItems(AthleteObservableList);
		pane1.getChildren().add(AthleteListView);
		rootPane.getChildren().add(pane1);
		
		btNewGame.setOnAction(e -> {
			scene2 = new Scene(rootPane, 500, 500);
			window.setScene(scene2);
			window.setTitle("Main Screen");
			});
		

		// layout3
		VBox vbox2 = new VBox();

		// vbox2.getChildren().add(btBacktoScene1);
		//scene3 = new Scene(vbox2, 500, 500);
		//window.setTitle("Game History");

		// layout4
		VBox vbox3 = new VBox();

		// vbox3.getChildren().add(btBacktoScene1);
		//scene4 = new Scene(vbox3, 500, 500);
		//window.setTitle("Athlete Points");

	}

}