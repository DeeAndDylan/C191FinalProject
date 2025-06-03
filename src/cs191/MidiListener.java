package cs191;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 * Version Date: 6/2/2025
 * 
 * Purpose: Responsibilities of Interface: Gives the method that allows whatever calls it to listen to MIDI notes.
 */
public interface MidiListener 
{
	/**
	 * Purpose: Called when note-on event is received.
	 * @param midiNote The MIDI note that is triggered.
	 */
	void onNoteOn(int midiNote);
}
