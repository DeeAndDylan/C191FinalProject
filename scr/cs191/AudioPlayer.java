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
 * Other Contributers: ChatGPT
 * 
 * Responsibilities of Class: To load and play short audio files.
 */
public class AudioPlayer 
{
	// The Clip object hold the audio sample.
	private Clip clip;

	/**
	 * Purpose: Default constructor that loads audio file and prepares for playback.
	 * 
	 * @param filePath The file path of the audio file(.wav) to be loaded.
	 * @throws Exception Throws exception if file isn't found or if an error occurs.
	 */
	public AudioPlayer(String filePath) throws Exception 
	{
		// Creates a file object for the audio file.
		File audioFile = new File(filePath);

		// If file does not exist, an exception is thrown.
		if (!audioFile.exists()) 
		{
			throw new IOException("File notFound " + filePath);
		}

		// Gets an audio input stream from file.
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

		// Creates a Clip
		clip = AudioSystem.getClip();

		// Loads the audio data into the Clip
		clip.open(audioStream);
	}

	/**
	 * Purpose: Plays the audio from the beginning. If there is clip is already
	 * playing, it stops it and then restarts.
	 */
	public void play() 
	{
		if (clip.isRunning()) 
		{
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
	public void stop() 
	{
		if (clip.isRunning()) 
		{
			clip.stop();
		}
	}
}
