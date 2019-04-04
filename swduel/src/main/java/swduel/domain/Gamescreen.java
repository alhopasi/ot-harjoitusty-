package swduel.domain;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Gamescreen {

    private Logic logic;
    private Canvas canvas;
    private GraphicsContext drawingTool;
    private int arenaWidth;
    private int arenaHeight;
    private Map<String, PixelReader> images;
    private AnimationTimer drawingTimer;

    private WritableImage arenaImage;

    public Gamescreen(Logic logic, Canvas canvas) {
        this.logic = logic;
        this.arenaHeight = logic.getArena().getHeight() * 32;
        this.arenaWidth = logic.getArena().getWidth() * 32;
        this.canvas = canvas;
        canvas.setWidth(arenaWidth);
        canvas.setHeight(arenaHeight);
        this.drawingTool = canvas.getGraphicsContext2D();
        this.images = new HashMap<>();

        importGraphics();
        drawArena();

        initDrawing();
    }

    public void initDrawing() {

        drawingTimer = new AnimationTimer() {
            private long before;

            @Override
            public void handle(long present) {
                if (present - before < 1_000_000_000 / 40) {
                    return;
                }
                before = present;

                drawAll();

            }
        };

        drawingTimer.start();
    }

    public void stopDrawing() {
        drawingTimer.stop();
    }

    private void importGraphics() {

        for (File file : new File("images/").listFiles()) {
            String filename = file.getName();
            images.put(filename.split("\\.")[0], new Image("file:images/" + filename).getPixelReader());
        }
    }

    private void drawAll() {
        WritableImage newScreen = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        PixelWriter pixelWriter = newScreen.getPixelWriter();
        
        drawPlayers(pixelWriter);

        drawingTool.drawImage(arenaImage, 0, 0);
        drawingTool.drawImage(newScreen, 0, 0);
    }

    private void drawPlayers(PixelWriter pixelWriter) {
        List<Player> players = logic.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int height = player.getHeight();
            int width = player.getWidth();

            PixelReader file;
            file = images.getOrDefault("players", images.get("0"));

            drawPlayer(pixelWriter, i, player, width, height, file);
        }
    }

    private void drawPlayer(PixelWriter pixelWriter, int i, Player player, int width, int height, PixelReader file) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = file.getArgb(x, y + i * height);

                if ((pixel >> 24) == 0x00) {
                    continue;
                }
                Color color = file.getColor(x, y + i * height);

                int drawX = player.getX() + x;
                if (player.getFacing() == 0) {
                    drawX = player.getX() - x + 32;
                }

                int drawY = player.getY() + y;

                pixelWriter.setColor(drawX, drawY - height, color);
            }
        }
    }

    private void drawBackground(PixelWriter pixelWriter) {
        for (int y = 0; y < canvas.getHeight(); y++) {
            for (int x = 0; x < canvas.getWidth(); x++) {
                pixelWriter.setColor(x, y, Color.LIGHTBLUE);
            }
        }
    }

    private void drawArena() {
        arenaImage = new WritableImage(arenaWidth, arenaHeight);
        PixelWriter pixelWriter = arenaImage.getPixelWriter();
        drawBackground(pixelWriter);
        
        for (int y = 0; y < logic.getArena().getHeight(); y++) {
            for (int x = 0; x < logic.getArena().getWidth(); x++) {
                if (logic.getArena().getTile(y, x) != 0) {
                    drawTile(String.valueOf(logic.getArena().getTile(y, x)), y, x, pixelWriter);
                }
            }
        }
    }

    private void drawTile(String tileNumber, int y, int x, PixelWriter pixelWriter) {
        int tileWidth = 32;
        int tileHeight = 32;

        PixelReader file;
        file = images.getOrDefault(tileNumber, images.get("0"));

        pixelWriter.setPixels(x * tileWidth, y * tileHeight, tileWidth, tileHeight, file, 0, 0);
    }
}
