package cs191;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
 * Responsibilities of Class: To create a series of Buttons in a JLayeredPane stylized to a Piano that will go in the
 * GUI. 
 */
public class PianoButtons extends JLayeredPane //PianoButtons is-a JLayeredPane
{ 
		
    // the notes that are read on the MIDI
	private static final int[] MIDI_NOTES = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65,
			66, 67, 68, 69, 70, 71, 72 };

	// The actual notes to be played
	private static final String[] NOTES = { "C1", "C1s", "D1", "D1s", "E1", "F1", "F1s", "G1", "G1s", "A1", "A1s", "B1",
			"C2", "C2s", "D2", "D2s", "E2", "F2", "F2s", "G2", "G2s", "A2", "A2s", "B2", "C3" }; //PianoButtons has-a String[]

	// Represents the black keys on piano.
	private static final boolean[] IS_BLACK_KEY = { false, true, false, true, false, false, true, false, true, false,
			true, false, false, true, false, true, false, false, true, false, true, false, true, false, false };

    //The final variable dimensions for both the black keys and the white keys.
	private static final Dimension WHITE_KEYS = new Dimension(80, 300); //PianoButtons Has-a Dimension
	private static final Dimension BLACK_KEYS = new Dimension(50, 220); //PianoButtons Has-a Dimension
	
	/**
	 * Purpose: Constructor that creates the keys that are placed inside of the GUI.
	 * 
	 * @param actionListener, the actionListener that will listen into the buttons
	 *                        in the GUI.
	 */
	public PianoButtons(ActionListener actionListener) 
	{
		setLayout(null);

        int whiteKeyX = 0;
        int[] whiteKeyXs = new int[MIDI_NOTES.length];

        // Manages and adds all of the white keys to the Panel.
        for (int i = 0; i < MIDI_NOTES.length; i++) 
        {
            if (!IS_BLACK_KEY[i]) 
            {
                JButton whiteKey = new JButton(NOTES[i]);
                whiteKey.setBackground(Color.WHITE);
                whiteKey.setForeground(Color.BLACK);
                whiteKey.setBounds(whiteKeyX, 0, WHITE_KEYS.width, WHITE_KEYS.height);
                whiteKey.setActionCommand(String.valueOf(MIDI_NOTES[i]));
                whiteKey.addActionListener(actionListener);
                whiteKey.setFocusPainted(false);
                whiteKeyXs[i] = whiteKeyX;
                whiteKeyX += WHITE_KEYS.width;
                add(whiteKey, JLayeredPane.DEFAULT_LAYER);
            }
        }

        // Layers the black keys ontop of the white keys in the Palette layer. 
        for (int i = 0; i < MIDI_NOTES.length; i++) 
        {
            if (IS_BLACK_KEY[i]) 
            {
                // Find the closest previous white key
                int leftWhiteIndex = i - 1;
                while (leftWhiteIndex >= 0 && IS_BLACK_KEY[leftWhiteIndex]) 
                {
                    leftWhiteIndex--;
                }
                //if that creates the black keys. 
                if (leftWhiteIndex >= 0) 
                {
                    int x = whiteKeyXs[leftWhiteIndex] + (WHITE_KEYS.width - BLACK_KEYS.width / 2);
                    JButton blackKey = new JButton(NOTES[i]);
                    blackKey.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    blackKey.setBackground(Color.BLACK);
                    blackKey.setForeground(Color.WHITE);
                    blackKey.setBounds(x, 0, BLACK_KEYS.width, BLACK_KEYS.height);
                    blackKey.setActionCommand(String.valueOf(MIDI_NOTES[i]));
                    blackKey.addActionListener(actionListener);
                    blackKey.setFocusPainted(true);
                    blackKey.setBorderPainted(false);
                    add(blackKey, JLayeredPane.PALETTE_LAYER);
                }
            }
        }

        
	    
        setPreferredSize(new Dimension(whiteKeyX, WHITE_KEYS.height));
    }

    /**
     * Method that gets the name of the note that was currently played by the MIDI.
     * @param midiNote
     * @return NOTES[I], the String Name of the note that was just pressed.
     * @return null, if it couldn't find the note.
     */
    public static final String getNote(int midiNote) 
    {
		for (int i = 0; i < MIDI_NOTES.length; i++) 
        {
			if (MIDI_NOTES[i] == midiNote) 
            {
			    return NOTES[i];
			}
		}
	    return null;
	}
}
