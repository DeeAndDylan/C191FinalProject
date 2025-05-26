package cs191;

public interface MidiListener 
{
	/**
	 * Purpose: Called when note-on event is received.
	 * @param midiNote The MIDI note that is triggered.
	 */
	void onNoteOn(int midiNote);
}
