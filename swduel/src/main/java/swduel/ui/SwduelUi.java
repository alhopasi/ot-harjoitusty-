package swduel.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * SwduelUi on pääluokka, joka käynnistää SW Duel pelin.
 */
public class SwduelUi extends Application {

    private GameMenu gameMenu;
    /**
     * Metodi käynnistää pelin ja asettaa ikkunan leveys- ja pituusarvot
     *
     * @param stage Näytettävä stage
     */
    @Override
    public void start(Stage stage) {
        gameMenu = new GameMenu(stage);
        stage.setTitle("SW Duel");
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setScene(gameMenu.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(SwduelUi.class);
    }
}
