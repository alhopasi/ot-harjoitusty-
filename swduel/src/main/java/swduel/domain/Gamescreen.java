package swduel.domain;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Gamescreen {

    private Logic logic;
    private Canvas canvas;
    private GraphicsContext drawingTool;

    public Gamescreen(Logic logic) {
        this.logic = logic;
        this.canvas = new Canvas(100, 100);
        this.drawingTool = canvas.getGraphicsContext2D();
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
    
    private void drawAll() {
        WritableImage newScreen = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        PixelWriter screenWriter = newScreen.getPixelWriter();
        
        drawBackground(screenWriter);
        
        drawingTool.drawImage(newScreen, 0, 0);
    }
    
    private void drawBackground(PixelWriter screenWriter) {
        for (int y = 0; y < canvas.getHeight(); y++) {
            for (int x = 0; x < canvas.getWidth(); x++) {
                screenWriter.setColor(x, y, Color.LIGHTBLUE);
            }
        }
    }
}
