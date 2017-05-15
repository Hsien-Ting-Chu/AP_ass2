package View;

import java.util.List;

import Controller.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class GameHistory {
    private Driver driver;
    private String sceneTitle = "Ozlympic Game";

    AnchorPane rootpane3 = new AnchorPane();
    Button btBacktoMain3 = new Button("Main Screen");
    ListView<String> gameHistroyList = new ListView<>();

    public GameHistory(Driver driver, List<String> gameHistory){
        this.driver = driver;

        ObservableList<String> gameHistroyObservableList = FXCollections.observableArrayList(gameHistory);
        gameHistroyList.setItems(gameHistroyObservableList);

        btBacktoMain3.setPrefSize(100, 50);
		gameHistroyList.setPrefWidth(350);
		btBacktoMain3.setLayoutX(400);
        btBacktoMain3.setLayoutY(450);
        addActions();
		
		rootpane3.getChildren().addAll(gameHistroyList, btBacktoMain3);
    }

    private void addActions(){
        btBacktoMain3.setOnAction(e -> {	
			driver.mainPage();
		});
    }

    public String getTitle(){
        return sceneTitle;
    }

    public Scene getScene(){
        return new Scene(rootpane3, 500, 500);
    }

}
