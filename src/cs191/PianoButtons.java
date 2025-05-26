package cs191;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PianoButtons extends JPanel {
	// the notes that are read on the MIDI
	private static final int[] MIDI_NOTES = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65,
			66, 67, 68, 69, 70, 71, 72 };
	// The actual notes to be played
	private static final String[] NOTES = { "C1", "C1s", "D1", "D1s", "E1", "F1", "F1s", "G1", "G1s", "A1", "A1s", "B1",
			"C2", "C2s", "D2", "D2s", "E2", "F2", "F2s", "G2", "G2s", "A2", "A2s", "B2", "C3" };
	
	// Represents the black keys on piano.
	private static final boolean[] IS_BLACK_KEY = 
	{
		false, true, false, true, false, false, true, false, 
		true, false, true, false, false, true, false, true, 
		false, false, true, false, true, false, true, false, false	
	};
	
	/**
	 * Constructor that creates the keys that are placed inside of the GUI.
	 * @param actionListener, the actionListener that will listen into the buttons in the GUI. 
	 */
	public PianoButtons(ActionListener actionListener) {
		//creates the layout for the keys based off of a FlowLayout
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//for loop that creates all of the key to be set in the GUI
		for (int i = 0; i < MIDI_NOTES.length; i++) {
			JButton keys = new JButton(NOTES[i]);
			keys.setBackground(Color.WHITE);
			if(IS_BLACK_KEY[i] = true)
			{
				keys.setBackground(Color.BLACK);
			}
			keys.setActionCommand(String.valueOf(MIDI_NOTES[i]));
			keys.addActionListener(actionListener);
			keys.setPreferredSize(new Dimension(70, 150));
			add(keys);
		}
	}
}
