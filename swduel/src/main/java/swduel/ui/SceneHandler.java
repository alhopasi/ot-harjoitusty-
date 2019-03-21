package swduel.ui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SceneHandler {

    private Stage stage;
    private Scene currentScene;
    private Scene titleScene;
    private Scene gameScene;

    public SceneHandler(Stage stage, Scene titleScene) {
        this.stage = stage;
        this.titleScene = titleScene;
        currentScene = titleScene;

        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.gameScene = new Scene(window);

        initTitleKeylistener();
        initGameKeylistener();
        stage.setScene(titleScene);
    }

    private void initTitleKeylistener() {
        titleScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            } else if (event.getCode() == KeyCode.ENTER) {
                stage.setScene(gameScene);
            }
        });
    }

    private void initGameKeylistener() {
        gameScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(titleScene);
            }
        });
    }
}
