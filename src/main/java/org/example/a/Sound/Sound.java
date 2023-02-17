package org.example.a.Sound;

import org.example.a.JPanel.GamePanel;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    private Clip clip;

    private GamePanel gamePanel;

    private URL[] soundURL = new URL[20];

    public Sound(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        soundURL[0] = getClass().getResource("/sound/1.wav");
        soundURL[1] = getClass().getResource("/sound/2.wav");
        soundURL[2] = getClass().getResource("/sound/3.wav");
        soundURL[3] = getClass().getResource("/sound/4.wav");
        soundURL[4] = getClass().getResource("/sound/aa.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public void playSoundEffect(int i) {
        setFile(i);
        gamePanel.getSound().play();
    }

    public void stopMusic() {
        gamePanel.getSound().stop();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public URL[] getSoundURL() {
        return soundURL;
    }

    public void setSoundURL(URL[] soundURL) {
        this.soundURL = soundURL;
    }
}
