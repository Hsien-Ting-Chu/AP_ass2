package View;
/**
 * @author Hsienting Chu
 *
 */
import java.util.List;

import Controller.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class AthletePoints {
    private Driver driver;
    private String sceneTitle = "Athlete Point";

    AnchorPane rootpane4 = new AnchorPane();
    Button btBacktoMain4 = new Button("Main Screen");
    ListView<String> athletePointList = new ListView<>();

    public AthletePoints(Driver driver, List<String> athletePoint){
        this.driver = driver;
        ObservableList<String> athletePointObservableList = FXCollections.observableArrayList(athletePoint);
		athletePointList.setItems(athletePointObservableList);

        btBacktoMain4.setPrefSize(100, 50);
		athletePointList.setPrefWidth(400);
		btBacktoMain4.setLayoutX(400);
        btBacktoMain4.setLayoutY(450);
        addActions();
		
		rootpane4.getChildren().addAll(athletePointList, btBacktoMain4);
    }

    private void addActions(){
        //Back to Main
		btBacktoMain4.setOnAction(e -> {
            driver.mainPage();
		});
    }

    public String getTitle(){
        return sceneTitle;
    }

    public Scene getScene(){
        return new Scene(rootpane4, 500, 500);
    }
}
