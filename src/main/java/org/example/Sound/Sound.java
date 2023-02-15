package org.example.Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    public Clip clip;
    URL[] soundURL = new URL[20];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/theme.wav");
        soundURL[1] = getClass().getResource("/sound/drink.wav");
        soundURL[2] = getClass().getResource("/sound/hit.wav");
        soundURL[3] = getClass().getResource("/sound/die.wav");
        soundURL[4] = getClass().getResource("/sound/talk.wav");
        soundURL[5] = getClass().getResource("/sound/player_die.wav");
        soundURL[6] = getClass().getResource("/sound/receiveDMG.wav");
        soundURL[7] = getClass().getResource("/sound/BLANK.wav");
        soundURL[8] = getClass().getResource("/sound/teleport.wav");
        soundURL[9] = getClass().getResource("/sound/tia.wav");
        soundURL[10] = getClass().getResource("/sound/aa.wav");
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


    public void play() {
        clip.start();
    }
    public void stop() {
        clip.stop();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
