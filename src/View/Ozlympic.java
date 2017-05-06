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
import javafx.scene.input.*;
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
		// Items Declaration
		VBox Scene1Vbox = new VBox();
		Text title = new Text("Please Select One to start");
		Button btNewGame = new Button("New Game");
		Button btGameHistory = new Button("Game History");
		Button btAthletePoints = new Button("Athlete Points");

		// Items Settings -Size
		btNewGame.setPrefSize(100, 50);
		btGameHistory.setPrefSize(100, 50);
		btAthletePoints.setPrefSize(100, 50);

		// Items Settings -Font
		Scene1Vbox.setPadding(new Insets(4));
		Scene1Vbox.setSpacing(10);
		Scene1Vbox.setAlignment(Pos.CENTER);
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		// Items Adding
		Scene1Vbox.getChildren().addAll(title, btNewGame, btGameHistory, btAthletePoints);

		scene1 = new Scene(Scene1Vbox, 300, 250);
		window.setTitle("Ozlympic Game");
		window.setScene(scene1);
		window.show();
		
// Scene 2
		// Items Declaration
		AnchorPane rootPane = new AnchorPane();
		Pane Scene2HBox1 = new HBox();
		Pane Scene2HBox2 = new HBox();
		Label gameLB = new Label("Select a game type");
		Label athleteLB = new Label("Select 4~8 athletes");
		Label officialLB = new Label("Select one official");
		ListView<String> gameList = new ListView<String>(
				FXCollections.observableArrayList("Swimming", "Cycling", "Running"));
		// ListView<Athlete> AthleteList = new ListView<>();
		// ListView<Official> OfficialList = new ListView<>();
		ListView<Participants> ParticipantsList1 = new ListView<>();
		ListView<Participants> ParticipantsList2 = new ListView<>();
		Button btBacktoScene1 = new Button("Main Menu");
		Button btShowAthletes = new Button("Show Athlete");
		Button btStartGame = new Button("Start Game");
		// ArrayList<Athlete> athletesList = driver.getAthleteList();
		// ArrayList<Official> officialList = driver.getOfficialList();
		ArrayList<Participants> participantslList = driver.getParticipantsList();

		// Items Settings -Size
		Scene2HBox1.setPrefSize(850, 30);
		gameLB.setPrefSize(150, 30);
		athleteLB.setPrefSize(350, 30);
		officialLB.setPrefSize(350, 30);
		gameList.setPrefWidth(150);
		btBacktoScene1.setPrefSize(100, 50);
		btShowAthletes.setPrefSize(100, 50);
		btStartGame.setPrefSize(100, 50);
		// AthleteList.setPrefWidth(350);
		// OfficialList.setPrefWidth(350);
		ParticipantsList1.setPrefWidth(350);
		ParticipantsList2.setPrefWidth(350);
		// Items Settings -Font
		gameLB.setFont(Font.font("Arial", 14));
		athleteLB.setFont(Font.font("Arial", 14));
		officialLB.setFont(Font.font("Arial", 14));

// Items Settings - Layout
		Scene2HBox2.setLayoutY(31);
		btBacktoScene1.setLayoutX(550);	btBacktoScene1.setLayoutY(450);
		btShowAthletes.setLayoutX(650); btShowAthletes.setLayoutY(450);
		btStartGame.setLayoutX(750); 	btStartGame.setLayoutY(450);
// Layout Settings
		// ObservableList<Athlete> AthleteObservableList = FXCollections.observableArrayList(athletesList);
		// ObservableList<Official> OfficialObservableList = FXCollections.observableArrayList(officialList);
		
		ObservableList<Participants> ParticipantsObservableList = FXCollections.observableArrayList(participantslList);
		// AthleteList.setItems(AthleteObservableList);
		// OfficialList.setItems(OfficialObservableList);
		ParticipantsList1.setItems(ParticipantsObservableList);
		ParticipantsList2.setItems(ParticipantsObservableList);
		ParticipantsList1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		MultipleSelectionModel<Participants> selectionModel = ParticipantsList1.getSelectionModel();
		selectionModel.clearSelection();
		
		ParticipantsList1.setCellFactory(lv -> {
			ListCell<Participants> cell = new ListCell<Participants>() {
				@Override
				public void updateItem(Participants item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null) {
						setText(null);
					} else {
						setText(item.toString());
					}
				}
			};
			cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
				ParticipantsList1.requestFocus();
				if (!cell.isEmpty()) {
					int index = cell.getIndex();
					if (selectionModel.getSelectedIndices().contains(index)) {
						selectionModel.clearSelection(index);
					} else {
						selectionModel.select(index);
					}
					event.consume();
				}
			});
			return cell;
		});

		// Items Adding
		Scene2HBox1.getChildren().addAll(gameLB, athleteLB, officialLB);
		Scene2HBox2.getChildren().addAll(gameList, ParticipantsList1, ParticipantsList2);

		rootPane.getChildren().addAll(Scene2HBox1, Scene2HBox2, btShowAthletes, btBacktoScene1);

		// Scene3
		VBox vbox2 = new VBox();

		// vbox2.getChildren().add(btBacktoScene1);
		// scene3 = new Scene(vbox2, 500, 500);
		// window.setTitle("Game History");

		// Scene4
		VBox vbox3 = new VBox();

		// vbox3.getChildren().add(btBacktoScene1);
		// scene4 = new Scene(vbox3, 500, 500);
		// window.setTitle("Athlete Points");

// Action handler
		//Scene1
		btNewGame.setOnAction(e -> {
			scene2 = new Scene(rootPane, 850, 500);
			window.setScene(scene2);
			window.setTitle("Main Screen");
		});
		
		btGameHistory.setOnAction(e -> { 
			window.setScene(scene3);
			
			
		});
		btAthletePoints.setOnAction(e -> {
			window.setScene(scene4);
			
			
			
		});
		btBacktoScene1.setOnAction(e -> {
			window.setScene(scene1);
			
			
		});
		
		//Scene2
		btShowAthletes.setOnAction(e ->{
			ObservableList<Participants> SelectedAthlete = ParticipantsList1.getSelectionModel().getSelectedItems();
			for(Participants a : SelectedAthlete)
			System.out.println(a);
		});
//		ParticipantsList1.setOnMouseClicked(e -> {
//			ObservableList<Participants> SelectedAthlete = ParticipantsList1.getSelectionModel().getSelectedItems();
//
//			for (Participants participants : SelectedAthlete) {
//				System.out.println(SelectedAthlete.size());
//				System.out.println(participants);
//			}
//		});


	}
}