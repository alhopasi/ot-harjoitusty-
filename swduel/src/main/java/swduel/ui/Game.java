package swduel.ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import swduel.domain.Gamescreen;
import swduel.domain.Logic;

public class Game {

    Logic logic;
    Gamescreen gamescreen;
    Canvas canvas;
    Stage stage;
    Scene menuScene;

    public Game(Stage stage, Scene menuScene) {
        this.stage = stage;
        this.menuScene = menuScene;
        canvas = new Canvas();
    }

    public Scene getScene() {
        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        window.setCenter(canvas);
        Scene gameScene = new Scene(window);

        addKeyHandler(gameScene);

        return gameScene;
    }

    public void initGame(String arena) {
        logic = new Logic(arena);
        gamescreen = new Gamescreen(logic, canvas);
    }

    private void exitGame() {
        gamescreen.stopDrawing();
        logic = null;
        gamescreen = null;
    }

    private void addKeyHandler(Scene gameScene) {
        gameScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuScene);
                exitGame();
            }
        });
    }
}
