package swduel.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import swduel.utils.Utils;

public class Gamescreen {

    private Logic logic;
    private Canvas canvas;
    private GraphicsContext drawingTool;
    private int width;
    private int height;
    private Map<Integer, PixelReader> images;

    public Gamescreen(Logic logic) {
        this.logic = logic;
        this.height = logic.getArena().getHeight() * 16;
        this.width = logic.getArena().getWidth() * 16;
        this.canvas = new Canvas(width, height);
        this.drawingTool = canvas.getGraphicsContext2D();
        this.images = new HashMap<>();

        importGraphics();
    }

    public Scene getScene() {
        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        window.setCenter(canvas);
        Scene gameScene = new Scene(window);

        new AnimationTimer() {
            private long before;

            @Override
            public void handle(long present) {
                if (present - before < 1_000_000_000 / 40) {
                    return;
                }
                before = present;

                drawAll();

            }
        }.start();

        return gameScene;
    }

    private void importGraphics() {
        List<String> filenames = Utils.readFile("images/imageList");
        for (int i = 0; i < filenames.size(); i++) {
            String filename = filenames.get(i);
            images.put(i, new Image("file:images/" + filename).getPixelReader());

        }
    }

    private void drawAll() {
        WritableImage newScreen = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        PixelWriter pixelWriter = newScreen.getPixelWriter();

        drawBackground(pixelWriter);
        drawArena(pixelWriter);

        drawingTool.drawImage(newScreen, 0, 0);
    }

    private void drawBackground(PixelWriter pixelWriter) {
        for (int y = 0; y < canvas.getHeight(); y++) {
            for (int x = 0; x < canvas.getWidth(); x++) {
                pixelWriter.setColor(x, y, Color.LIGHTBLUE);
            }
        }
    }

    private void drawArena(PixelWriter pixelWriter) {
        for (int y = 0; y < logic.getArena().getHeight(); y++) {
            for (int x = 0; x < logic.getArena().getWidth(); x++) {
                if (logic.getArena().getTile(y, x) != 0) {
                    drawTile(logic.getArena().getTile(y, x), y, x, pixelWriter);
                }
            }
        }
    }

    private void drawTile(int tileNumber, int y, int x, PixelWriter pixelWriter) {
        int tileWidth = 16;
        int tileHeight = 16;

        PixelReader file;
        file = images.getOrDefault(tileNumber, images.get(0));

        pixelWriter.setPixels(x * tileWidth, y * tileHeight, tileWidth, tileHeight, file, 0, 0);
    }
}
