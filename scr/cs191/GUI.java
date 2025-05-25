package cs191;
import javax.sound.midi.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;

import cs191.instruments.InstrumentType;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI extends JFrame implements MidiListener
{
	//instance variables
	private JLabel noteLabel;
	private SampleHandler sampleHandler;
	private MidiInputHandler midiInputHandler;
	private static final int[] MIDI_NOTES = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57,58, 59,
											60, 61, 62, 63, 64,65, 66, 67,68, 69 ,70 , 71, 72};
    private static final String[] NOTE_NAMES = {"C1", "C1s", "D1", "D1s", "E1", "F1", "F1s", 
    											"G1", "G1s", "A1", "A1s", "B1", "C2", "C2s", 
    											"D2", "D2s", "E2", "F2", "F2s", 
    											"G2", "G2s", "A2", "A2s", "B2", "C3"};

	/**
	 * Constructor that builds the GUI for the game, to be called in the main method that starts the game up.
	 * @param model, the model that will be used when creating the fishing game. 
	 */
	public GUI(SampleHandler sampleHandler)
	{
		this.sampleHandler = sampleHandler;

        setTitle("Virtual MIDI Piano");
        setLayout(new BorderLayout());

        noteLabel = new JLabel("No MIDI input yet", SwingConstants.CENTER);
        noteLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(noteLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, MIDI_NOTES.length, 10, 10));
        for (int i = 0; i < MIDI_NOTES.length; i++) {
            int midiNote = MIDI_NOTES[i];
            String label = NOTE_NAMES[i];
            JButton noteButton = new JButton(label);
            noteButton.setFont(new Font("Arial", Font.PLAIN, 16));
            noteButton.addActionListener(e -> triggerNoteOn(midiNote));
            buttonPanel.add(noteButton);
            noteButton.setBackground(Color.WHITE);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}

	private void triggerNoteOn(int midiNote) {
    if (midiInputHandler != null) {
        try {
            ShortMessage fakeMessage = new ShortMessage();
            fakeMessage.setMessage(ShortMessage.NOTE_ON, 0, midiNote, 100); // Channel 0, velocity 100
            midiInputHandler.send(fakeMessage, -1); // Timestamp can be ignored
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}

	@Override
    public void onNoteOn(int midiNote) {
        SwingUtilities.invokeLater(() -> {
            noteLabel.setText("Triggered Note: " + midiNote);
        });
    }

	public void setMidiInputHandler(MidiInputHandler handler) {
        this.midiInputHandler = handler;
    }

	public static void main(String[] args) {
		try {
			 // 1. Load samples
            PreLoadedSamples preloader = new PreLoadedSamples(InstrumentType.PIANO);
            SampleHandler sampleManager = preloader.getSampleHandler(); // This is local

            // 2. Create GUI using the sample handler
            GUI gui = new GUI(sampleManager);

            // 3. Hook up MIDI input handler
            MidiInputHandler inputHandler = new MidiInputHandler(sampleManager, gui);
            gui.setMidiInputHandler(inputHandler);
        
            // 4. Optional: Connect real MIDI device
            MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
            for (int i = 0; i < infos.length; i++) {
                System.out.println(i + ": " + infos[i].getName());
            }

            int deviceIndex = 5; // <-- change this manually
            if (deviceIndex >= 0 && deviceIndex < infos.length) {
                MidiDevice device = MidiSystem.getMidiDevice(infos[deviceIndex]);
                device.open();
                device.getTransmitter().setReceiver(inputHandler);
                System.out.println("🎹 Connected to: " + device.getDeviceInfo().getName());
            }

        } catch (Exception e) {
            System.err.println("MIDI Error:");
            e.printStackTrace();
        }
    }
}


