package swduel.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TitleMenu {

    private final String version;

    public TitleMenu(String version) {
        this.version = version;
    }

    public Scene getScene() {
        VBox centerBox = createCenterBox();
        VBox bottomBox = createBottomBox();

        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        window.setBottom(bottomBox);
        window.setCenter(centerBox);

        Scene titleScene = new Scene(window);

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
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(titleLabel, playLabel);

        return centerBox;
    }

    private Label createLabel(String text, int size) {
        Label label = new Label(text);
        label.setPadding(new Insets(20, 20, 20, 20));
        label.setTextFill(Color.LIGHTBLUE);
        label.setFont(new Font("Consolas", size));

        return label;
    }
}
