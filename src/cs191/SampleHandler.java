package cs191;

import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 * Version Date: 6/2/2025
 * 
 * Responsibilities of Class: Manages the audio samples and plays them
 * given MIDI note number.
 */
public class SampleHandler 
{
	private Map<Integer, AudioPlayer> sampleMap; //SampleHandler has-a Map
	private static final int SAMPLE_START_NOTE = 48;
	
	

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
	 * @param baseNote The MIDI note number to map the audio sample to.
	 * @param filePath The path of the audio file (.wav) that is being loaded.
	 */
	public void loadSample(int semitone, AudioInputStream stream) throws Exception
	{
		Clip clip = AudioSystem.getClip();
		clip.open(stream);
		int midiNote = SAMPLE_START_NOTE + semitone;
		sampleMap.put(midiNote, new AudioPlayer(clip));
		System.out.println("Loaded sample for MIDI note: " + midiNote);
		 
	}

	/**
	 * Purpose: Plays the sample given MIDI not number.
	 * 
	 * @param midiNote The MIDI not number.
	 * @throws Exception 
	 */
	public void playSample(int midiNote) throws Exception 
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
