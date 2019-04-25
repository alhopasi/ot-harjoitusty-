package swduel.ui;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;
import javazoom.jl.player.Player;

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

    public void playMusic(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            Player playMP3 = new Player(fis);

            new Thread() {
                public void run() {
                    try {
                        playMP3.play();
                        if (playMP3.isComplete()) {
                            playMusic(path);
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();

        } catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("Failed to play the file.");
        }
    }

}
