package swduel.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class SwduelUi extends Application {

    private TitleMenu titleMenu;
//    private Game game;
    private SceneHandler sceneHandler;
    
    @Override
    public void init() {
        String areena = "areena1";
        String version = "0.1";

        titleMenu = new TitleMenu(version);
        

//        logic = new Logic(players, width, height);
//        gamescreen = new Gamescreen(logic);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("SW Duel");
        stage.setWidth(1024);
        stage.setHeight(768);
        sceneHandler = new SceneHandler(stage, titleMenu.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("hello world!");
        launch(SwduelUi.class);
    }
}
