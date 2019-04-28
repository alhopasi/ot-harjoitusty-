package swduel.ui;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;
import javazoom.jl.player.Player;

public class AudioHandler {

    private Map<String, AudioClip> soundEffects;
    private Player playMP3;
    private boolean isPlayingMusic;
    private String musicPath;

    public AudioHandler() {
        soundEffects = new HashMap<>();
    }

    public void addAudioClip(String name, String path) {
        soundEffects.put(name, new AudioClip("file:" + path));
    }

    public void playSound(String name) {
        soundEffects.get(name).play();
    }

    public void toggleMusic() {
        if (isPlayingMusic) {
            playMP3.close();
            isPlayingMusic = false;
        } else {
            playMusic();
        }
    }

    public void setMusicPath(String path) {
        this.musicPath = path;
    }

    public void playMusic() {
        try {
            FileInputStream fis = new FileInputStream(musicPath);
            playMP3 = new Player(fis);
            isPlayingMusic = true;

            new Thread() {
                public void run() {
                    try {
                        playMP3.play();
                        if (playMP3.isComplete()) {
                            playMusic();
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
