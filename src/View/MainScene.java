package View;

import Controller.Driver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Hsienting Chu
 *
 */

// Scene 1
public class MainScene {
    private Driver driver;
    private String sceneTitle = "Ozlympic Game";

    VBox scene1Vbox = new VBox();
    Text txMainInfo = new Text();
    Button btNewGame = new Button("New Game");
    Button btGameHistory = new Button("Game History");
    Button btAthletePoints = new Button("Athlete Points");
    Button btExit = new Button("Exit");
    public MainScene(Driver driver){
        this.driver = driver;

        if(!driver.DBCheck()){
			txMainInfo.setText("Can not find DataBase" );
			txMainInfo.setFill(Color.RED);
			if(!driver.txtCheck()){
				txMainInfo.setText("Can not find Database and participants.txt");
				txMainInfo.setFill(Color.RED);
			}
		}
		else { 
			txMainInfo.setText("Please select a option to start");
			txMainInfo.setFill(Color.BLUE);
		}
        txMainInfo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        // Items Settings -Size
		btNewGame.setPrefSize(100, 50);
		btGameHistory.setPrefSize(100, 50);
		btAthletePoints.setPrefSize(100, 50);
		btExit.setPrefSize(100, 50);

         // Items Settings -Font
		scene1Vbox.setPadding(new Insets(4));
		scene1Vbox.setSpacing(10);
		scene1Vbox.setAlignment(Pos.CENTER);
        scene1Vbox.getChildren().addAll(txMainInfo, btNewGame, btGameHistory, btAthletePoints, btExit);
        addActions();
    }

    private void addActions(){
        btNewGame.setOnAction(e -> {			
			driver.newGame();
		});
        btGameHistory.setOnAction(e -> {
			driver.gameHistory();
		});
		btAthletePoints.setOnAction(e -> {
			driver.athletePoints();
		});	
		btExit.setOnAction(e -> {			
			driver.exit();	
		});	
    }
    
    public String getTitle(){
        return sceneTitle;
    }

    public Scene getScene(){
        return new Scene(scene1Vbox, 300, 300);
    }
}
