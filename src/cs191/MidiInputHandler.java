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
 * Version Date: 6/2/2025
 * 
 * Responsibilities of Class: To receive and process MIDI . Connects
 * with SampleHandler to trigger audio samples when MIDI NOTE_ON
 * messages are received.
 */
public class MidiInputHandler implements Receiver 
{
	// References SampleHandler to play sounds.
	private SampleHandler sampleHandler; //MidiInputHandler Has-a SampleHandler
	private MidiListener listener; //MidiInputHandler Has-a MidiListener

	/**
	 * Purpose: Default that takes SampleHandler to play sounds when given MIDI
	 * notes.
	 * 
	 * @param sampleHandler Manages and plays samples.
	 */
	public MidiInputHandler(SampleHandler sampleHandler, MidiListener listener) 
	{
		this.sampleHandler = sampleHandler;
		this.listener = listener;
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
				System.out.println("Received MIDI NOTE_ON: " + midiNote);

				// Tells sample handler to play sample for given MIDI note.
				try {
					sampleHandler.playSample(midiNote);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (listener != null) {
					listener.onNoteOn(midiNote); //Plays midinote if listener in not null.
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

	/**
	 * Purpose: To set the SampleHandler that will connect with the MidiInputHandler.
	 * @param sample The SampleHandler that is being set.
	 */
	public void setSampleHandler(SampleHandler sample) 
	{
		this.sampleHandler = sample;
	}
}
