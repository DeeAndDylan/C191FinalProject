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
	
	// TODO: ADD Comments
	private int currentOctave = 0;
	
	// TODO: ADD Comments
	private static final int MAX_OCTAVE_SHIFT = 2;
	
	// TODO: ADD Comments
	private static final int SAMPLE_START_NOTE = 0;

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
	public void loadSample(int baseNote, String filePath) 
	{
		try 
		{
			// Creates an AudioPlayer for the audio file.
			AudioPlayer player = new AudioPlayer(filePath);

			// Stores the player in the map and with the MIDI note as the key.
			sampleMap.put(baseNote, player);

			System.out.println("Loaded sample for Midi note" + baseNote);
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
		int shiftedNote = midiNote +(currentOctave * 12);
		
		
		if(shiftedNote < 0 || shiftedNote > 127)
		{
			System.out.println("Note out of range!!");
			
			return;
		}
		
		int baseNote = (shiftedNote - SAMPLE_START_NOTE) % 12;
		
		if(baseNote < 0) baseNote += 12;
		
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
	
	public void octaveUp()
	{
		if(currentOctave < MAX_OCTAVE_SHIFT)
		{
			if(currentOctave < 127){
				currentOctave++;	
				System.out.println("Octave shifted up to: " + currentOctave);	
			}
			else{
				System.out.println("Cannot Shift the Octive any higher.");
			}
			
		}
	}
	
	public void octaveDown()
	{
		if(currentOctave > MAX_OCTAVE_SHIFT)
		{
			if(currentOctave > 0){
				currentOctave--;
				System.out.println("Octave shifted down to: " + currentOctave);
			}
			else{
				System.out.println("Cannot Shift the Octive any lower.");
			}
			
		}
	}
	
	public void resetOctave()
	{
		currentOctave = 0;
		System.out.println("Octave reset to default");
		
	}
	
	public int getCurrentOctave()
	{
		return currentOctave;
	}
}
