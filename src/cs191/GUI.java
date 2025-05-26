package cs191;

import javax.sound.midi.*;
import javax.swing.*;

import cs191.instruments.InstrumentType;

import java.awt.*;

public class GUI extends JFrame implements MidiListener {
	//instance variables
	private JLabel noteLabel;
	private SampleHandler sample;
	private MidiInputHandler midi;

	/**
	* Constructor that creates the GUI to be run in the main method
	* @param sample, the sampleHandler that the GUI is built off of. 
	*/
	public GUI(SampleHandler sample) {
		this.sample = sample;

		setTitle("Virtual MIDI Piano");
		setLayout(new BorderLayout());

		noteLabel = new JLabel("No MIDI input yet", SwingConstants.CENTER);
		add(noteLabel, BorderLayout.NORTH);
		//creates the buttons alongside the fake midi output. 
		PianoButtons buttonPanel = new PianoButtons(e -> {
			int midiNote = Integer.parseInt(e.getActionCommand());
			triggerNoteOn(midiNote);
		});

		add(buttonPanel, BorderLayout.CENTER);

		setSize(600, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	* Method that sends a fake message to the midiSampler in order to simulate a fake keypress
	* @param midiNote, the note that was pressed to send a fake message.
	*/
	private void triggerNoteOn(int midiNote) {
		//only runs if midi is not null
		if (midi != null) {
			//try that tries to send a message of what note is pressed to the midiInputHandler
			try {
				ShortMessage msg = new ShortMessage();
				msg.setMessage(ShortMessage.NOTE_ON, 0, midiNote, 100);
				midi.send(msg, -1);
			} catch (InvalidMidiDataException e) { //catch error if note doesn't work
				e.printStackTrace();
			}
		}
	}

	/**
	* Method that writes in the console what note is pressed.
	* @param midiNote, the note that was pressed in the GUI. 
	*/
	@Override
	public void onNoteOn(int midiNote) {
		SwingUtilities.invokeLater(() -> noteLabel.setText("Triggered Note: " + midiNote));
	}

	/**
	 * Setter that sets the midi to the MidiInputHandler provided
	 * @param midi, the MidiInputHandler to set. 
	 */
	public void setMidiInputHandler(MidiInputHandler midi) {
		this.midi = midi;
	}
	
	/**
	 * Main method that creates the GUI for the Piano when the code is run.
	 */
	public static void main(String[] args) {
		try {
			//loads the samples and puts them in the sampleHandler
			PreLoadedSamples preloader = new PreLoadedSamples(InstrumentType.PIANO);
			SampleHandler sampleManager = preloader.getSampleHandler();
			//creates the GUI
			GUI gui = new GUI(sampleManager);
			//creates the inputHandler to check inputs. 
			MidiInputHandler inputHandler = new MidiInputHandler(sampleManager, gui);
			gui.setMidiInputHandler(inputHandler);
			//creates the midiDevice
			MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
			for (int i = 0; i < infos.length; i++) {
				System.out.println(i + ": " + infos[i].getName());
			}
			//tries to connect to the midiDevice, uses the GUI if no midiDevice
			int deviceIndex = 5;
			if (deviceIndex >= 0 && deviceIndex < infos.length) {
				MidiDevice device = MidiSystem.getMidiDevice(infos[deviceIndex]);
				device.open();
				device.getTransmitter().setReceiver(inputHandler);
				System.out.println("🎹 Connected to: " + device.getDeviceInfo().getName());
			}

		} catch (Exception e) { //catches error if there is a midi Error
			System.err.println("MIDI Error:");
			e.printStackTrace();
		}
	}
}
