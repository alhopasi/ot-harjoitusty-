package swduel.ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import swduel.domain.Logic;

public class Game {

    private Logic logic;
    private Gamescreen gamescreen;
    private Canvas canvas;
    private Stage stage;
    private Scene menuScene;
    private Scene gameScene;
    private ActionHandler actionHandler;

    public Game(Stage stage, Scene menuScene) {
        this.stage = stage;
        this.menuScene = menuScene;
        canvas = new Canvas();
    }

    public Scene getScene() {
        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        window.setCenter(canvas);
        gameScene = new Scene(window);

        return gameScene;
    }

    public void initGame(String arena) {
        logic = new Logic(arena);
        gamescreen = new Gamescreen(logic, canvas);
        
        actionHandler = null;
        actionHandler = new ActionHandler(stage, gameScene, menuScene, logic, gamescreen);
    }
}
