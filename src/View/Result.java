package View;

import java.util.List;

import Controller.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class Result {
    private Driver driver;
    private String sceneTitle = "Result";

    AnchorPane rootpane5 = new AnchorPane();
    Button btBacktoMain5 = new Button("Main Screen");
    Button btBacktogame = new Button("New Game");
    ListView<String> resultList = new ListView<>();

    public Result(Driver driver, List<String> result){
        this.driver = driver;
        ObservableList<String> resultObservableList = FXCollections.observableArrayList(result);
        resultList.setItems(resultObservableList);

        btBacktoMain5.setPrefSize(100, 50);
		btBacktogame.setPrefSize(100, 50);
		resultList.setPrefWidth(350);
		
		btBacktoMain5.setLayoutX(300);
        btBacktoMain5.setLayoutY(450);
		btBacktogame.setLayoutX(400);
        btBacktogame.setLayoutY(450);
        addActions();
        
		rootpane5.getChildren().addAll(resultList, btBacktoMain5, btBacktogame);

    }

    private void addActions(){
        //Back to Scene2
		btBacktoMain5.setOnAction(e -> {
            driver.mainPage();
		});		
		btBacktogame.setOnAction(e -> {
            driver.newGame();
		});	
    }

    public String getTitle(){
        return sceneTitle;
    }

    public Scene getScene(){
        return new Scene(rootpane5, 500, 500);
    }

}