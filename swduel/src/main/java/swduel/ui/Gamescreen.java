package swduel.ui;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import swduel.domain.Logic;
import swduel.domain.Player;
import swduel.domain.Smoke;
import swduel.domain.Sprite;
import swduel.domain.ammunition.Ammunition;

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
        drawAmmunition(pixelWriter);
        drawSmoke(pixelWriter);

        drawingTool.drawImage(arenaImage, 0, 0);
        drawingTool.drawImage(newScreen, 0, 0);
        drawPlayerInfo();
        if (logic.getGameFinished()) {
            drawWinnerAndStop();
        }
    }
    
    private void drawSmoke(PixelWriter pixelWriter) {
        for (Smoke smoke : logic.getSmoke()) {
            PixelReader file;
            file = images.getOrDefault("smoke", images.get("0"));

            drawSprite(pixelWriter, 0, 0, smoke, file);
        }
    }

    private void drawWinnerAndStop() {
        int winner = 1;
        if (logic.getPlayers().get(0).getScore() < logic.getPlayers().get(1).getScore()) {
            winner = 2;
        }
        drawingTool.setFont(Font.font("consolas", FontWeight.BOLD, 64));
        drawingTool.setTextAlign(TextAlignment.CENTER);
        drawingTool.setFill(Color.RED);
        if (winner == 2) {
            drawingTool.setFill(Color.BLUE);
        }
        drawingTool.fillText("Player " + winner + " won!", logic.getArena().getWidth() * 32 / 2, logic.getArena().getHeight() * 32 / 2);
        stopDrawing();
    }

    private void drawPlayerInfo() {
        for (int playerNumber = 0; playerNumber < logic.getPlayers().size(); playerNumber++) {
            Player player = logic.getPlayers().get(playerNumber);
            String text = player.getWeapon().getName();
            String text2 = String.valueOf(player.getScore());

            int textX = 64;
            int textY = 96;
            drawingTool.setFont(Font.font("consolas", FontWeight.BOLD, 32));
            drawingTool.setFill(Color.RED);
            drawingTool.setTextAlign(TextAlignment.LEFT);
            if (playerNumber == 1) {
                drawingTool.setFill(Color.BLUE);
                textX = arenaWidth - 64;
                drawingTool.setTextAlign(TextAlignment.RIGHT);
            }
            drawingTool.fillText(text, textX, textY);
            drawingTool.fillText(text2, textX, textY + 32);
        }
    }

    private void drawPlayers(PixelWriter pixelWriter) {
        List<Player> players = logic.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            PixelReader file;
            file = images.getOrDefault("players", images.get("0"));

            int frame = 0;
            if (player.isRunning()) {
                player.setNextRunningFrame();
                frame = player.getRunningFrame();
            }
            drawSprite(pixelWriter, i, frame, player, file);
        }
    }

    private void drawAmmunition(PixelWriter pixelWriter) {
        List<Ammunition> ammunition = logic.getAmmunition();
        for (int i = 0; i < ammunition.size(); i++) {
            Ammunition ammo = ammunition.get(i);

            PixelReader file;
            file = images.getOrDefault(ammo.getFileName(), images.get("0"));
            drawSprite(pixelWriter, 0, 0, ammo, file);
        }
    }

    private void drawSprite(PixelWriter pixelWriter, int i, int frame, Sprite sprite, PixelReader file) {
        try {
            for (int y = 0; y < sprite.getHeight(); y++) {
                for (int x = 0; x < sprite.getWidth(); x++) {

                    if (pixelIsTransparent(file, x, y, i, frame, sprite.getWidth(), sprite.getHeight())) {
                        continue;
                    }

                    drawPixel(file, x, y, i, frame, sprite, pixelWriter);
                }
            }
        } catch (Exception e) {
            System.out.println("error drawing sprite " + e.getMessage());
        }
    }

    private void drawPixel(PixelReader file, int x, int y, int i, int frame, Sprite sprite, PixelWriter pixelWriter) {
        Color color = file.getColor(x + frame * sprite.getWidth(), y + i * sprite.getHeight());

        int drawX = sprite.getX() + x;
        if (sprite.getFacing() == 0) {
            drawX = sprite.getX() - x + sprite.getWidth();
        }
        int drawY = sprite.getY() + y - sprite.getHeight();

        pixelWriter.setColor(drawX, drawY, color);
    }

    private boolean pixelIsTransparent(PixelReader file, int x, int y, int i, int frame, int width, int height) {
        int pixel = file.getArgb(x + frame * width, y + i * height);
        if ((pixel >> 24) == 0x00) {
            return true;
        }
        return false;
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
