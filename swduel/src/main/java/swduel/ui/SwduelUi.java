package swduel.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import swduel.domain.Gamescreen;
import swduel.domain.Logic;

public class SwduelUi extends Application {

    private TitleMenu titleMenu;
    private Logic logic;
    private Gamescreen gamescreen;
    private SceneHandler sceneHandler;
    
    @Override
    public void init() {
        String arena = "arena1";
        String version = "0.1";
        
        titleMenu = new TitleMenu(version);
        logic = new Logic(arena);
        gamescreen = new Gamescreen(logic);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("SW Duel");
        stage.setWidth(1024);
        stage.setHeight(768);
        sceneHandler = new SceneHandler(stage, titleMenu.getScene(), gamescreen.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(SwduelUi.class);
    }
}
