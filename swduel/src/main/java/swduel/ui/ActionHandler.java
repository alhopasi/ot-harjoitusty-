package swduel.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import swduel.domain.Logic;

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

                if (logic.getGameFinished()) {
                    return;
                }
                handleMovementKeys();
                handleAttacks();
                logic.updateAll(elapsedTime);
            }
        };

        animationTimer.start();
    }

    private void handleMovementKeys() {
        if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
            handleMovementAndFacing(1, -30, 0, 0);
        }
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
            handleMovementAndFacing(1, 30, 0, 1);
        }
        if (pressedKeys.getOrDefault(KeyCode.CONTROL, false)) {
            handleMovementAndFacing(1, 0, -30, -1);
            logic.getPlayers().get(1).setUsingJetpack(true);
        } else {
            logic.getPlayers().get(1).setUsingJetpack(false);
        }
        if (pressedKeys.getOrDefault(KeyCode.A, false)) {
            handleMovementAndFacing(0, -30, 0, 0);
        }
        if (pressedKeys.getOrDefault(KeyCode.D, false)) {
            handleMovementAndFacing(0, 30, 0, 1);
        }
        if (pressedKeys.getOrDefault(KeyCode.TAB, false)) {
            handleMovementAndFacing(0, 0, -30, -1);
            logic.getPlayers().get(0).setUsingJetpack(true);
        } else {
            logic.getPlayers().get(0).setUsingJetpack(false);
        }
    }

    private void handleAttacks() {
        if (pressedKeys.getOrDefault(KeyCode.SHIFT, false)) {
            logic.attack(logic.getPlayers().get(1));
        }
        if (pressedKeys.getOrDefault(KeyCode.Q, false)) {
            logic.attack(logic.getPlayers().get(0));
        }
    }

    private void handleMovementAndFacing(int player, int velocityX, int velocityY, int facing) {
        logic.getPlayers().get(player).addVelocity(velocityX, velocityY);
        if (facing != -1) {
            logic.getPlayers().get(player).setFacing(facing);
        }
    }
}
