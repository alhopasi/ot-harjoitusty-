package swduel.ui;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class SceneHandler {

    private Stage stage;
    private Scene currentScene;
    private Scene titleScene;
    private Scene gameScene;

    public SceneHandler(Stage stage, Scene titleScene, Scene gameScene) {
        this.stage = stage;
        this.titleScene = titleScene;
        this.gameScene = gameScene;
        
        currentScene = titleScene;

        initTitleKeylistener();
        initGameKeylistener();

        stage.setScene(currentScene);
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
