package View;
/**
 * @author Hsienting Chu
 *
 */
import java.util.ArrayList;

import Controller.Driver;
import Model.Athlete;
import Model.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NewGame {
    private String title = "Main Screen";
    private Driver driver;
    private String gameType;
    private Official SelectedOfficial = null;

	final int TooFewAthleteException = 4;
	final int GameFullException = 8;

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
    Button btStartGame = new Button("Start Game");
    ArrayList<Participants> participants1 = new ArrayList<>();
    ArrayList<Participants> participants2 = new ArrayList<>();
    ArrayList<Athlete> athletesList = new ArrayList<>();
    
    ArrayList<Official> officialList =  new ArrayList<>();
    ArrayList<Athlete> SelectedAthlete = new ArrayList<>();
	

    public NewGame(Driver driver){
        this.driver = driver;
        athletesList = driver.getAthleteList();
        officialList = driver.getOfficialList();
        participants1.addAll(athletesList); participants1.addAll(officialList);
		participants2.addAll(officialList); participants2.addAll(athletesList);

        // Items Settings -Size
		Scene2HBox1.setPrefSize(850, 30);
		gameLB.setPrefSize(150, 30);
		athleteLB.setPrefSize(350, 30);
		officialLB.setPrefSize(350, 30);
		gameList.setPrefWidth(150);
		btBacktoMain2.setPrefSize(100, 50);
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
		ObservableList<Participants> ParticipantsObservableList1 = FXCollections.observableArrayList(participants1);
		ObservableList<Participants> ParticipantsObservableList2 = FXCollections.observableArrayList(participants2);

		ParticipantsList1.setItems(ParticipantsObservableList1);
		ParticipantsList2.setItems(ParticipantsObservableList2);
		ParticipantsList1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Items Adding
		Scene2HBox1.getChildren().addAll(gameLB, athleteLB, officialLB);
		Scene2HBox2.getChildren().addAll(gameList, ParticipantsList1, ParticipantsList2);

		rootPane2.getChildren().addAll(title2, Scene2HBox1, Scene2HBox2, btBacktoMain2 ,btStartGame);
        addActions();
    }

    public void addActions(){
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
            driver.mainPage();
		});

		//Start Game 
		gameList.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		            gameType=new_val;
		    });
			
		btStartGame.setOnAction(e -> {
			
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
				else if (SelectedAthlete.size() > GameFullException)
					throw new GameFullException();
				else if (SelectedOfficial == null)
					throw new NoRefereeException();
				
				driver.result(gameType, SelectedAthlete, SelectedOfficial);
				
			} catch (TooFewAthleteException e1) {
				title2.setText("TooFewAthleteException");
			} catch (GameFullException e2) {
				title2.setText("GameFullException");
			} catch (NoRefereeException e3) {
				title2.setText("NoRefereeException");
			} catch (WrongTypeException e1){
				title2.setText("Wrong type exception");
			} catch (Exception e2){
				title2.setText("Please select a game type");
			}				
			
			
		});
    }

    public String getTitle(){
        return title;
    }

    public Scene getScene(){
        return new Scene(rootPane2, 850, 500);
    }
}
