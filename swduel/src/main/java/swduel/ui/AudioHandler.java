package swduel.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

public class AudioHandler {

    Map<String, AudioClip> soundEffects;

    public AudioHandler() {
        soundEffects = new HashMap<>();
    }
    
    public void addAudioClip(String name, String path) {
        soundEffects.put(name, new AudioClip("file:" + path));
    }

    public void playSound(String name) {
        soundEffects.get(name).play();
    }
}
