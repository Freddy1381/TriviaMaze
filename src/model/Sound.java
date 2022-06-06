package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public static void playSound(final File theChosenFile) {
        try {
            AudioInputStream audioInput =
                    AudioSystem.getAudioInputStream(theChosenFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            Thread.sleep(1600);
            audioInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
