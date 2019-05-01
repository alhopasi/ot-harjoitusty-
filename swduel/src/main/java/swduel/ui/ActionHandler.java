package swduel.ui;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
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
    private ControllerManager controllers;

    public ActionHandler(Stage stage, Scene gameScene, Scene menuScene, Logic logic, Gamescreen gamescreen) {
        this.logic = logic;
        this.pressedKeys = new HashMap<>();
        this.gamescreen = gamescreen;

        controllers = new ControllerManager();
        controllers.initSDLGamepad();

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
                logic.getPlayers().get(0).setUsingJetpack(false);
                logic.getPlayers().get(1).setUsingJetpack(false);
                if (controllers.getNumControllers() > 0) {
                    handleGamepadKeysPlayer1();
                } else {
                    handlePlayer1Keys();
                }
                if (controllers.getNumControllers() > 1) {
                    handleGamepadKeysPlayer2();
                } else {
                    handlePlayer2Keys();
                }

                logic.updateAll(elapsedTime);
            }
        };

        animationTimer.start();
    }

    private void handlePlayer1Keys() {
        if (pressedKeys.getOrDefault(KeyCode.A, false)) {
            handleMovementAndFacing(0, -30, 0, 0);
        }
        if (pressedKeys.getOrDefault(KeyCode.D, false)) {
            handleMovementAndFacing(0, 30, 0, 1);
        }
        if (pressedKeys.getOrDefault(KeyCode.W, false)) {
            handleMovementAndFacing(0, 0, -30, -1);
            logic.getPlayers().get(0).setUsingJetpack(true);
        }
        if (pressedKeys.getOrDefault(KeyCode.TAB, false)) {
            logic.attack(logic.getPlayers().get(0));
        }
    }

    private void handlePlayer2Keys() {
        if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
            handleMovementAndFacing(1, -30, 0, 0);
        }
        if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
            handleMovementAndFacing(1, 30, 0, 1);
        }
        if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
            handleMovementAndFacing(1, 0, -30, -1);
            logic.getPlayers().get(1).setUsingJetpack(true);
        }
        if (pressedKeys.getOrDefault(KeyCode.CONTROL, false)) {
            logic.attack(logic.getPlayers().get(1));
        }
    }

    private void handleGamepadKeysPlayer1() {
        ControllerState currState = controllers.getState(0);
        if (currState.leftTrigger > 0.2 || currState.rightTrigger > 0.2
                || currState.lb || currState.rb || currState.dpadUp
                || currState.leftStickY > 0.5 || currState.rightStickY > 0.5) {
            handleMovementAndFacing(0, 0, -30, -1);
            logic.getPlayers().get(0).setUsingJetpack(true);
        }
        if (currState.a || currState.b || currState.x || currState.y) {
            logic.attack(logic.getPlayers().get(0));
        }
        if (currState.dpadLeft || currState.leftStickX < -0.2
                || currState.rightStickX < -0.2) {
            handleMovementAndFacing(0, -30, 0, 0);
        }
        if (currState.dpadRight || currState.leftStickX > 0.2
                || currState.rightStickX > 0.2) {
            handleMovementAndFacing(0, 30, 0, 1);
        }
    }
        private void handleGamepadKeysPlayer2() {
        ControllerState currState = controllers.getState(1);
        if (currState.leftTrigger > 0.2 || currState.rightTrigger > 0.2
                || currState.lb || currState.rb || currState.dpadUp
                || currState.leftStickY > 0.5 || currState.rightStickY > 0.5) {
            handleMovementAndFacing(1, 0, -30, -1);
            logic.getPlayers().get(1).setUsingJetpack(true);
        }
        if (currState.a || currState.b || currState.x || currState.y) {
            logic.attack(logic.getPlayers().get(1));
        }
        if (currState.dpadLeft || currState.leftStickX < -0.2
                || currState.rightStickX < -0.2) {
            handleMovementAndFacing(1, -30, 0, 0);
        }
        if (currState.dpadRight || currState.leftStickX > 0.2
                || currState.rightStickX > 0.2) {
            handleMovementAndFacing(1, 30, 0, 1);
        }
        }

    private void handleMovementAndFacing(int player, int velocityX, int velocityY, int facing) {
        logic.getPlayers().get(player).addVelocity(velocityX, velocityY);
        if (facing != -1) {
            logic.getPlayers().get(player).setFacing(facing);
        }
    }
}
