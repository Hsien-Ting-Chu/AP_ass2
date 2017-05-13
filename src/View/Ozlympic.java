package View;

/**
 * @author Hsienting Chu
 *
 */
import Controller.Driver;
import Model.*;
import javafx.application.*;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.*;

public class Ozlympic extends Application {
	Driver driver;
	Stage window;
	Scene scene1, scene2, scene3, scene4;
	private String gameType;
	final int TooFewAthleteException = 4;
	final int GameFullException = 8;

	
	
	
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
		Text title1 = new Text();
		Button btNewGame = new Button("New Game");
		Button btGameHistory = new Button("Game History");
		Button btAthletePoints = new Button("Athlete Points");
				
		if(!driver.dbconnection()){
			title1.setText("Can not find DataBase" );
			title1.setFill(Color.RED);
			if(!driver.readFile()){
				title1.setText("Can not find Database and participants.txt");
				title1.setFill(Color.RED);
			}
		}
		else { 
			title1.setText("Please select a option to start");
			title1.setFill(Color.BLUE);
		}
		// Items Settings -Size
		btNewGame.setPrefSize(100, 50);
		btGameHistory.setPrefSize(100, 50);
		btAthletePoints.setPrefSize(100, 50);
		// Items Settings -Font
		Scene1Vbox.setPadding(new Insets(4));
		Scene1Vbox.setSpacing(10);
		Scene1Vbox.setAlignment(Pos.CENTER);
		title1.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		// Items Adding
		Scene1Vbox.getChildren().addAll(title1, btNewGame, btGameHistory, btAthletePoints);
		scene1 = new Scene(Scene1Vbox, 300, 250);
		window.setTitle("Ozlympic Game");
		window.setScene(scene1);
		window.show();		
		
		

// Scene 2
		// Items Declaration
		AnchorPane rootPane2 = new AnchorPane();
		Pane Scene2HBox1 = new HBox();
		Pane Scene2HBox2 = new HBox();
		Text title2 = new Text();
		Label gameLB = new Label("Select a game type");
		Label athleteLB = new Label("Select 4~8 athletes");
		Label officialLB = new Label("Select one official");
		ListView<String> gameList = new ListView<String>(FXCollections.observableArrayList(Driver.SWIM, Driver.CYCLE, Driver.RUN));
		ListView<Participants> ParticipantsList1 = new ListView<>();
		ListView<Participants> ParticipantsList2 = new ListView<>();
		Button btBacktoMain2 = new Button("Main Menu");
		Button btShowAthletes = new Button("Show Athlete");
		Button btStartGame = new Button("Start Game");
		ArrayList<Participants> participantsList1 = new ArrayList<>();
		ArrayList<Participants> participantsList2 = new ArrayList<>();
		ArrayList<Athlete> athletesList = driver.getAthleteList();
		ArrayList<Official> officialList = driver.getOfficialList();
		participantsList1.addAll(athletesList); participantsList1.addAll(officialList);
		participantsList2.addAll(officialList); participantsList2.addAll(athletesList);
		

		// Items Settings -Size
		Scene2HBox1.setPrefSize(850, 30);
		gameLB.setPrefSize(150, 30);
		athleteLB.setPrefSize(350, 30);
		officialLB.setPrefSize(350, 30);
		gameList.setPrefWidth(150);
		btBacktoMain2.setPrefSize(100, 50);
		btShowAthletes.setPrefSize(100, 50);
		btStartGame.setPrefSize(100, 50);
		ParticipantsList1.setPrefWidth(350);
		ParticipantsList2.setPrefWidth(350);
		
		// Items Settings -Font
		title2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		title2.setFill(Color.RED);
		gameLB.setFont(Font.font("Arial", 14));
		athleteLB.setFont(Font.font("Arial", 14));
		officialLB.setFont(Font.font("Arial", 14));

// Items Settings - Layout
		Scene2HBox1.setLayoutY(30);
		Scene2HBox2.setLayoutY(55);
		btBacktoMain2.setLayoutX(650);	btBacktoMain2.setLayoutY(450);
		btStartGame.setLayoutX(750); 	btStartGame.setLayoutY(450);
		title2.setLayoutY(25);
// Layout Settings
		// ObservableList<Athlete> AthleteObservableList = FXCollections.observableArrayList(athletesList);
		// ObservableList<Official> OfficialObservableList = FXCollections.observableArrayList(officialList);
		
		ObservableList<Participants> ParticipantsObservableList1 = FXCollections.observableArrayList(participantsList1);
		ObservableList<Participants> ParticipantsObservableList2 = FXCollections.observableArrayList(participantsList2);
		// AthleteList.setItems(AthleteObservableList);
		// OfficialList.setItems(OfficialObservableList);
			ParticipantsList1.setItems(ParticipantsObservableList1);
			ParticipantsList2.setItems(ParticipantsObservableList2);
		ParticipantsList1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		
		
		// Items Adding
		Scene2HBox1.getChildren().addAll(gameLB, athleteLB, officialLB);
		Scene2HBox2.getChildren().addAll(gameList, ParticipantsList1, ParticipantsList2);

		rootPane2.getChildren().addAll(title2, Scene2HBox1, Scene2HBox2, btBacktoMain2 ,btStartGame);

// Scene3
		AnchorPane rootpane3 = new AnchorPane();
		Button btBacktoMain3 = new Button("scene3");

		rootpane3.getChildren().add(btBacktoMain3);
				
// Scene4
		AnchorPane rootpane4 = new AnchorPane();
		Button btBacktoMain4 = new Button("scene4");
		rootpane4.getChildren().add(btBacktoMain4);
				

// Action handler
//Scene1
		scene2 = new Scene(rootPane2, 850, 500);
		scene3 = new Scene(rootpane3, 500, 500);
		scene4 = new Scene(rootpane4, 500, 500);
		
		btNewGame.setOnAction(e -> {
			
			window.setScene(scene2);
			window.setTitle("Main Screen");

		});
		
		btGameHistory.setOnAction(e -> { 
			
			window.setTitle("Game History");
			window.setScene(scene3);

			
		});
		btAthletePoints.setOnAction(e -> {
			
			window.setTitle("Athlete Points");
			window.setScene(scene4);
	
			
			
		});
		
		
//Scene2
		//Multiple select ListView
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
		
		//Back to Main
		btBacktoMain2.setOnAction(e -> {
			window.setTitle("Ozlympic Game");
			window.setScene(scene1);
			
			
		});

		//Start Game 
		gameList.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		            gameType=new_val;
		    });
		
//		ParticipantsList2.getSelectionModel().selectedItemProperty().addListener(
//		        (ObservableValue<? extends Participants> ov, Participants old_val, 
//		        	Participants new_val) -> {
//		            official=(Official)new_val;
//		    });
		
		btStartGame.setOnAction(e -> {
			ArrayList<Athlete> SelectedAthlete = new ArrayList<>();
			Official SelectedOfficial = null;
			ObservableList<Participants> OBSelectedAthlete = ParticipantsList1.getSelectionModel().getSelectedItems();
			ObservableList<Participants> OBSelectedOfficial = ParticipantsList2.getSelectionModel().getSelectedItems();
			try {
				for(Participants athlete : OBSelectedAthlete){
					switch(gameType){
					case Driver.SWIM:
						if(athlete instanceof Swimmer || athlete instanceof SuperAthlete)
							SelectedAthlete.add((Athlete)athlete);
						else
							throw new WrongTypeException();
						break;
					case Driver.CYCLE:
						if(athlete instanceof Cyclist || athlete instanceof SuperAthlete)
							SelectedAthlete.add((Athlete)athlete);
						else
							throw new WrongTypeException();
						break;
					case Driver.RUN:
						if(athlete instanceof Sprinter || athlete instanceof SuperAthlete)
							SelectedAthlete.add((Athlete)athlete);
						else
							throw new WrongTypeException();
						break;
					}	
				}
				for(Participants official : OBSelectedOfficial){
					if(official instanceof Official){
						SelectedOfficial = ((Official)official);
					}
					else 
						throw new WrongTypeException();					
				}
				if (SelectedAthlete.size() <= TooFewAthleteException)
					throw new TooFewAthleteException();
				else if (SelectedAthlete.size() >= GameFullException)
					throw new GameFullException();
				else if (SelectedOfficial == null)
					throw new NoRefereeException();
				driver.startgame(gameType, SelectedAthlete, SelectedOfficial);
				
			} catch (TooFewAthleteException e1) {
				title2.setText("TooFewAthleteException");
			} catch (GameFullException e2) {
				title2.setText("GameFullException");
			} catch (NoRefereeException e3) {
				title2.setText("NoRefereeException");
			} catch (WrongTypeException e1){
				title2.setText("Wrong type exception");
			} 
//			catch (Exception e2){
//				title2.setText("Please select a game type");
//			}	
//			
	
			
		});
//Scene3
		//Back to Main
		btBacktoMain3.setOnAction(e -> {	
			window.setTitle("Ozlympic Game");
			window.setScene(scene1);
			
		});
		
		
		
		
		
		
		
		
		
//Scene4		
		//Back to Main
		btBacktoMain4.setOnAction(e -> {
			window.setTitle("Ozlympic Game");
			window.setScene(scene1);
			
		});
		
		
		


	}

}