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
    private Gamescreen gamescreen;
    private AnimationTimer animationTimer;

    public ActionHandler(Stage stage, Scene gameScene, Scene menuScene, Logic logic, Gamescreen gamescreen) {
        this.logic = logic;
        this.pressedKeys = new HashMap<>();
        this.gamescreen = gamescreen;

        addKeyHandler(stage, gameScene, menuScene);
        handleKeyPresses();
    }

    private void addKeyHandler(Stage stage, Scene gameScene, Scene menuScene) {
        gameScene.setOnKeyPressed((event) -> {

            pressedKeys.put(event.getCode(), true);

            if (event.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuScene);
                gamescreen.stopDrawing();
                gamescreen = null;
                logic = null;
                animationTimer.stop();
                animationTimer = null;
            }
        });

        gameScene.setOnKeyReleased((event) -> {
            pressedKeys.put(event.getCode(), false);
        });
    }

    private void handleKeyPresses() {
        animationTimer = new AnimationTimer() {

            private long before = System.currentTimeMillis();

            @Override
            public void handle(long present) {

                double elapsedTime = (present - before) / 1_000_000_000.0;
                before = present;

//                if (logic.getGameEnded()) {
//            		return;
//            	}
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    logic.getPlayers().get(1).addVelocity(-30, 0);
                    logic.getPlayers().get(1).setFacing(0);
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    logic.getPlayers().get(1).addVelocity(30, 0);
                    logic.getPlayers().get(1).setFacing(1);
                }
                if (pressedKeys.getOrDefault(KeyCode.CONTROL, false)) {
                    logic.getPlayers().get(1).addVelocity(0, -30);
                }
                if (pressedKeys.getOrDefault(KeyCode.A, false)) {
                    logic.getPlayers().get(0).addVelocity(-30, 0);
                    logic.getPlayers().get(0).setFacing(0);
                }
                if (pressedKeys.getOrDefault(KeyCode.D, false)) {
                    logic.getPlayers().get(0).addVelocity(30, 0);
                    logic.getPlayers().get(0).setFacing(1);
                }
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                    logic.getPlayers().get(0).addVelocity(0, -30);
                }

                logic.updateAll(elapsedTime);

            }
        };

        animationTimer.start();
    }

}
