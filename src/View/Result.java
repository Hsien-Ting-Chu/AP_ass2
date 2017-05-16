package View;

/**
 * @author Hsienting Chu
 *
 */
import java.util.ArrayList;
import java.util.List;
import Model.*;
import Controller.Driver;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Result {
	private Driver driver;
	private String sceneTitle = "Result";
	AnchorPane rootpane5 = new AnchorPane();
	Button btBacktoMain5 = new Button("Main Screen");
	Button btBacktogame = new Button("New Game");
	ListView<String> resultList = new ListView<>();
	Color[] color = { Color.PURPLE, Color.BLUE, Color.RED, Color.DARKCYAN, Color.SILVER, Color.ORCHID, Color.YELLOW,
			Color.TEAL };
	List<Integer> timeList = new ArrayList<>();

	public Result(Driver driver, List<Athlete> athletes, List<String> result, List<Integer> score) {
		this.driver = driver;
		for (int i = 0; i < athletes.size(); i++) {

			Athlete athlete = athletes.get(i);
			Circle circle = new Circle(15, 15, 15);
			circle.setFill(color[i]);
			Path path = new Path();
			Label label = new Label(athlete.getID());
			path.getElements().add(new MoveTo(50, 40 * (i + 1)));
			path.getElements().add(new LineTo(400.0f, 40 * (i + 1)));
			label.setLayoutX(0);
			label.setLayoutY(40 * (i + 1));
			path.setOpacity(0.5);
			rootpane5.getChildren().addAll(label, path, circle);
			PathTransition pathTransition = new PathTransition();
			int time = score.get(i);
			if (time >= 500)
				time /= 50;
			else if (time < 500 && time >= 100)
				time /= 10;
			pathTransition.setDuration(Duration.seconds(time));
			pathTransition.setPath(path);
			pathTransition.setNode(circle);
			pathTransition.setOrientation(PathTransition.OrientationType.NONE);
			pathTransition.setAutoReverse(false);
			pathTransition.play();
			pathTransition.setOnFinished(t -> {
				ObservableList<String> resultObservableList = FXCollections.observableArrayList(result);
				resultList.setItems(resultObservableList);

			});
		}
		btBacktoMain5.setPrefSize(100, 50);
		btBacktogame.setPrefSize(100, 50);
		resultList.setPrefSize(400, 150);
		btBacktoMain5.setLayoutX(300);
		btBacktoMain5.setLayoutY(500);
		btBacktogame.setLayoutX(400);
		btBacktogame.setLayoutY(500);
		resultList.setLayoutY(350);
		addActions();
		rootpane5.getChildren().addAll(resultList, btBacktoMain5, btBacktogame);

	}

	private void addActions() {
		// Back to Scene2
		btBacktoMain5.setOnAction(e -> {
			driver.mainPage();
		});
		btBacktogame.setOnAction(e -> {
			driver.newGame();
		});
	}

	public String getTitle() {
		return sceneTitle;
	}

	public Scene getScene() {
		return new Scene(rootpane5, 500, 550);
	}

}