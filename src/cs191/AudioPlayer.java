package cs191;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 *         Other Contributers: ChatGPT
 * 
 *         Responsibilities of Class: To load and play short audio files.
 */
public class AudioPlayer {
	// The Clip object hold the audio sample.
	private final Clip clip;

	/**
	 * Purpose: Default constructor that loads audio file and prepares for playback.
	 * 
	 * @param filePath The file path of the audio file(.wav) to be loaded.
	 * @throws Exception Throws exception if file isn't found or if an error occurs.
	 */
	public AudioPlayer(Clip clip) throws Exception {
		if (clip == null) {
			throw new Exception("Clip cant be null");
		}

		this.clip = clip;
	}

	/**
	 * Purpose: Plays the audio from the beginning. If there is clip is already
	 * playing, it stops it and then restarts.
	 */
	public void play() throws Exception {
		if (clip == null || !clip.isOpen()) {
			throw new Exception("Audio clip not available");
		}

		if (clip.isRunning()) {
			// Stops clip if its already playing.
			clip.stop();
		}

		// Rewinds to start of clip.
		clip.setFramePosition(0);

		// Starts the playback.
		clip.start();
	}

	/**
	 * Purpose: Stops clip if it is currently playing.
	 */
	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}
}
