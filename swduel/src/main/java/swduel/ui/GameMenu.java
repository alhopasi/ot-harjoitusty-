package swduel.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import swduel.domain.Gamescreen;
import swduel.domain.Logic;

public class GameMenu {

    private Stage stage;
    private Logic logic;
    private Gamescreen gamescreen;
    private String version;
    private String arena;

    public GameMenu(Stage stage) {
        this.stage = stage;
        this.arena = "testArena";
        this.version = "0.1";
    }

    public Scene getScene() {
        VBox centerBox = createCenterBox();
        VBox bottomBox = createBottomBox();

        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        window.setBottom(bottomBox);
        window.setCenter(centerBox);

        Scene titleScene = new Scene(window);

        initKeyListener(titleScene);

        return titleScene;
    }

    private VBox createBottomBox() {
        String infoString = "How to play:\n"
                + "eliminate your opponent until you win.\n"
                + "          Player 1 keys:    Player 2 keys:\n"
                + "Movement: A, D              Left Arrow, Right Arrow\n"
                + "Jump:     Left Shift        Right Shift\n"
                + "Attack:   Left Ctrl         Right Ctrl";
        Label infoLabel = createLabel(infoString, 16);

        Label versionLabel = createLabel("Version " + version, 16);
        HBox versionBox = new HBox();
        versionBox.getChildren().add(versionLabel);

        VBox bottomBox = new VBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(infoLabel, versionBox);

        return bottomBox;
    }

    private VBox createCenterBox() {
        Label titleLabel = createLabel("SW Duel", 60);
        titleLabel.setPadding(new Insets(100, 20, 20, 20));
        Label playLabel = createLabel("<< Press Enter >>", 30);
        Label arenaLabel = createLabel(arena, 14);
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(titleLabel, playLabel, arenaLabel);

        return centerBox;
    }

    private Label createLabel(String text, int size) {
        Label label = new Label(text);
        label.setPadding(new Insets(20, 20, 20, 20));
        label.setTextFill(Color.LIGHTBLUE);
        label.setFont(new Font("Consolas", size));

        return label;
    }

    private void initKeyListener(Scene titleScene) {
        titleScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            } else if (event.getCode() == KeyCode.ENTER) {
                logic = new Logic(arena);
                gamescreen = new Gamescreen(logic, stage, titleScene);
                stage.setScene(gamescreen.getScene());
            }
        });
    }
}
