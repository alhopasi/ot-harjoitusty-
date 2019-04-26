package swduel.ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import swduel.domain.Logic;

/**
 * Luokka rakentaa uuden pelin.
 */
public class Game {

    private Logic logic;
    private Gamescreen gamescreen;
    private Canvas canvas;
    private Stage stage;
    private Scene menuScene;
    private Scene gameScene;
    private ActionHandler actionHandler;

    /**
     * Luokka huolehtii näppäinpainalluksista sekä luo pelin logiikan ja piirturin.
     * @param stage näytettävä stage
     * @param menuScene scene johon palataan escistä tai pelin päätyttyä
     */
    public Game(Stage stage, Scene menuScene) {
        this.stage = stage;
        this.menuScene = menuScene;
        canvas = new Canvas();
    }

    /**
     * Näyttää mustan taustan ja sen keskelle piirrettävän pelin.
     * @return Pelin scene
     */
    public Scene getScene() {
        BorderPane window = new BorderPane();
        window.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        window.setCenter(canvas);
        gameScene = new Scene(window);
        return gameScene;
    }

    /**
     * Luo logiikan, yhdistää siihen areenan GameScreenin
     * sekä luo uuden ActionHandlerin, joka käsittelee kaikki
     * pelin näppäinpainallukset.
     * @param arena pelattava areena
     */
    public void initGame(String arena) {
        logic = new Logic(arena);
        gamescreen = new Gamescreen(logic, canvas);
        
        actionHandler = null;
        actionHandler = new ActionHandler(stage, gameScene, menuScene, logic, gamescreen);
    }
}
