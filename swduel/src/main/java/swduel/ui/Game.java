package swduel.ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Game {
    
    public Game(String level) {
        
    }
    
    public Scene getScene() {
        BorderPane window = new BorderPane();
        Scene gameScene = new Scene(window);
        
        return gameScene;
    }
}
