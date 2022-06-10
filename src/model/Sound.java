/*
 * TCSS 360 Trivia Maze
 */

package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A sound class with a method to play sound.
 * 
 * @author Zhaoyu Xu
 * @version 1.0
 */
public class Sound {

	/**
	 * Called when action requires sound effect. 
	 * 
	 * @param theChosenFile .wav sound file
	 */
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
