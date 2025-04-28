package cs191;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 * Other Contributers: ChatGPT
 * 
 * Responsibilities of Class: To receive and process MIDI . Connects
 * with SampleHandler to trigger audio samples when MIDI NOTE_ON
 * messages are received.
 */
public class MidiInputHandler implements Receiver 
{
	// References SampleHandler to play sounds.
	private SampleHandler sampleHandler;
	
	 private static final int OCTAVE_UP_BUTTON = 127; // Highest key
	 private static final int OCTAVE_DOWN_BUTTON = 0; // Lowest key

	/**
	 * Purpose: Default that takes SampleHandler to play sounds when given MIDI
	 * notes.
	 * 
	 * @param sampleHandler Manages and plays samples.
	 */
	public MidiInputHandler(SampleHandler sampleHandler) 
	{
		this.sampleHandler = sampleHandler;
	}

	/**
	 * Purpose: To be called when MIDI messages are received.
	 * 
	 * @param message   The MIDI information that is received.
	 * @param timeStamp The time stamp when a message is received. (can be ignored)
	 */
	@Override
	public void send(MidiMessage message, long timeStamp) 
	{
		// Handles information that are ShortMessages
		if (message instanceof ShortMessage) 
		{
			ShortMessage sm = (ShortMessage) message;

			// Handles information in NOTE_ON events when velocity is greater than 0.
			if (sm.getCommand() == ShortMessage.NOTE_ON && sm.getData2() > 0) 
			{
				// Gets the MIDI note number.
				int midiNote = sm.getData1();
				
				if(midiNote == OCTAVE_UP_BUTTON)
				{
					sampleHandler.octaveUp();
				}
				
				else if(midiNote == OCTAVE_DOWN_BUTTON)
				{
					sampleHandler.octaveDown();
				}
				
				else
				{
					System.out.println("Received MIDI NOTE_ON: " + midiNote);

					// Tells sample handler to play sample for given MIDI note.
					sampleHandler.playSample(midiNote);
				}

				
			}
		}
	}

	/**
	 * Purpose: Closes receiver.
	 */
	@Override
	public void close() 
	{
	}

}
