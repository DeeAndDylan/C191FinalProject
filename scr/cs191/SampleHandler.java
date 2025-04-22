package cs191;

import java.util.HashMap;
import java.util.Map;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 * Other Contributers: ChatGPT
 * 
 * Responsibilities of Class: Manages the audio samples and plays them
 * given MIDI note number.
 */
public class SampleHandler 
{
	// A map that couples the MIDI note numbers to given AudioPlayer.
	private Map<Integer, AudioPlayer> sampleMap;

	/**
	 * Purpose: Default constructor that initializes the sample map.
	 */
	public SampleHandler() 
	{
		sampleMap = new HashMap<>();
	}

	/**
	 * Purpose: Loads the audio sample and assigns it to specific MIDI note number.
	 * 
	 * @param midiNote The MIDI note number to map the audio sample to.
	 * @param filePath The path of the audio file (.wav) that is being loaded.
	 */
	public void loadSample(int midiNote, String filePath) 
	{
		try 
		{
			// Creates an AudioPlayer for the audio file.
			AudioPlayer player = new AudioPlayer(filePath);

			// Stores the player in the map and with the MIDI note as the key.
			sampleMap.put(midiNote, player);

			System.out.println("Loaded sample for Midi note");
		}

		// Throws exception is sample fails to load.
		catch (Exception e) 
		{
			System.out.print("Failed to load sample");
		}
	}

	/**
	 * Purpose: Plays the sample given MIDI not number.
	 * 
	 * @param midiNote The MIDI not number.
	 */
	public void playSample(int midiNote) 
	{
		// Looks up the AudioPlayer in the map.
		AudioPlayer player = sampleMap.get(midiNote);

		// If the Audio player is not null, the sample is played.
		if (player != null) 
		{
			player.play();
		}

		else 
		{
			// Prints statement if there is no sample assigned to MIDI note.
			System.out.println("No sample assigned to MIDI note");
		}
	}
}
