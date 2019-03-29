package swduel.domain;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ActionHandler {

    private Logic logic;
    private Map<KeyCode, Boolean> pressedKeys;

    public ActionHandler(Stage stage, Scene gameScene, Scene menuScene, Logic logic, Gamescreen gamescreen) {
        this.logic = logic;
        this.pressedKeys = new HashMap<>();

        addKeyHandler(stage, gameScene, menuScene, gamescreen);
        handleKeyPresses();
    }

    private void addKeyHandler(Stage stage, Scene gameScene, Scene menuScene, Gamescreen gamescreen) {
        gameScene.setOnKeyPressed((event) -> {

            pressedKeys.put(event.getCode(), true);

            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuScene);
                gamescreen.stopDrawing();
            }
        });

        gameScene.setOnKeyReleased((event) -> {
            pressedKeys.put(event.getCode(), false);
        });
    }

    private void handleKeyPresses() {
        new AnimationTimer() {

            @Override
            public void handle(long present) {

//                if (logic.getGameEnded()) {
//            		return;
//            	}
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    logic.movePlayer(logic.getPlayers().get(1), "left");
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    logic.movePlayer(logic.getPlayers().get(1), "right");
                }
                if (pressedKeys.getOrDefault(KeyCode.A, false)) {
                    logic.movePlayer(logic.getPlayers().get(0), "left");
                }
                if (pressedKeys.getOrDefault(KeyCode.D, false)) {
                    logic.movePlayer(logic.getPlayers().get(0), "right");
                }
                

            }
        }.start();
    }
}
