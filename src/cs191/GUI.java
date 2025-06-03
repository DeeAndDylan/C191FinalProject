package cs191;

import javax.sound.midi.*;
import javax.swing.*;

import cs191.instruments.InstrumentType;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
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
 * Responsibilities of Class: To create the GUI that holds the Piano.
 */
public class GUI extends JFrame implements MidiListener // GUI is-a JFrame
{ 
	// instance variables
	private JLabel noteLabel; // GUI has-a JLabel
	private static JComboBox<InstrumentType> instrumentSelector; // GUI has-a JComboBox
	private JTextArea noteQueue; // GUI has-a JTextArea
	private SampleHandler sample; // GUI has-a SampleHandler
	private MidiInputHandler midi; // GUI has-a MidiInputHandler
	private final Queue<String> lastKeys = new LinkedList<>(); // GUI has-a Queue
	private static final int MAX_QUEUE = 10;

	/**
	 * Constructor that creates the GUI to be run in the main method
	 * 
	 * @param sample, the sampleHandler that the GUI is built off of.
	 */
	public GUI(SampleHandler sample) 
	{
		this.sample = sample;

		setTitle("Virtual MIDI Piano");

		// Creates the Background image first
		Image img = new ImageIcon(getClass().getResource("/Pics/background.jpg")).getImage();
		Background background = new Background(img);
		setContentPane(background);

		// Creates the noteQueue for reading last inputted notes
		noteQueue = new JTextArea(5, 20);
		JPanel queuePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		queuePanel.setOpaque(false);
		queuePanel.add(new JScrollPane(noteQueue));
		background.add(queuePanel, BorderLayout.EAST);

		// Creates the PianoButtons from PianoButtons class
		PianoButtons buttonPanel = new PianoButtons(e -> {
			int midiNote = Integer.parseInt(e.getActionCommand());
			triggerNoteOn(midiNote);
		});

		// Creates the PianoContainer to hold PianoButtons in the middle (Chatgpt helped with this)
		JPanel pianoContainer = new JPanel(new GridBagLayout());
		pianoContainer.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(50, 0, 0, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		pianoContainer.add(buttonPanel, gbc);
		background.add(pianoContainer, BorderLayout.CENTER);

		// Creates the noteLabel which updates which key is pressed. 
		noteLabel = new JLabel("No MIDI input yet", SwingConstants.CENTER);
		noteLabel.setOpaque(false);
		
		//Create a JComboBox that allows you to change the instrument type.
		instrumentSelector = new JComboBox<>(InstrumentType.values());
		instrumentSelector.setSelectedItem(InstrumentType.SYNTH);
		instrumentSelector.addActionListener(e -> {
			InstrumentType selected = (InstrumentType) instrumentSelector.getSelectedItem();
			changeInstrument(selected);
		});

		//JPanel that holds the instrumentSelector and note Label and adds it to the Background
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false); 
		topPanel.add(noteLabel, BorderLayout.CENTER);
		topPanel.add(instrumentSelector, BorderLayout.EAST);
		background.add(topPanel, BorderLayout.NORTH);

		// Sets the size of the window and fullscreens
		setSize(600, 200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Method that sends a fake message to the midiSampler in order to simulate a
	 * fake keypress
	 * 
	 * @param midiNote, the note that was pressed to send a fake message.
	 */
	private void triggerNoteOn(int midiNote) 
	{
		// only runs if midi is not null
		if (midi != null) {
			// try that tries to send a message of what note is pressed to the
			// midiInputHandler
			try 
			{
				ShortMessage msg = new ShortMessage();
				msg.setMessage(ShortMessage.NOTE_ON, 0, midiNote, 100);
				midi.send(msg, -1);
			} 
			catch (InvalidMidiDataException e) { //catch error if note doesn't work
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method that writes in the console what note is pressed.
	 * 
	 * @param midiNote, the note that was pressed in the GUI.
	 */
	@Override
	public void onNoteOn(int midiNote) 
	{
		String noteName = PianoButtons.getNote(midiNote);
		noteLabel.setText("Triggered Note: " + noteName);
		lastKeys.add("Triggered Note: " + noteName);
		//pulls from the queue if queue is max
		if (lastKeys.size() > MAX_QUEUE) {
			lastKeys.poll();
		}

		//builds the string of notes last pressed.
		StringBuilder sb = new StringBuilder();
		for (String note : lastKeys) {
			sb.append(note).append("\n");

		}

		noteQueue.setText(sb.toString());

	}

	/**
	 * Setter that sets the midi to the MidiInputHandler provided
	 * 
	 * @param midi, the MidiInputHandler to set.
	 */
	public void setMidiInputHandler(MidiInputHandler midi) 
	{
		this.midi = midi;
	}

	private void changeInstrument(InstrumentType type) 
	{
		PreLoadedSamples preloader = new PreLoadedSamples(type);
		this.sample = preloader.getSampleHandler(); // replace old sampleHandler
		if (midi != null) 
		{
			midi.setSampleHandler(this.sample); // update input handler
		}
		noteLabel.setText("Instrument changed to: " + type.name());
	}

	/**
	 * Purpose: Main method that creates the GUI for the Piano when the code is run.
	 */
	public static void main(String[] args) 
	{
		try 
		{
			// loads the samples and puts them in the sampleHandler
			instrumentSelector = new JComboBox<>(InstrumentType.values());
			PreLoadedSamples preloader = new PreLoadedSamples(InstrumentType.PIANO);
			SampleHandler sampleManager = preloader.getSampleHandler();
			// creates the GUI
			GUI gui = new GUI(sampleManager);
			// creates the inputHandler to check inputs.
			MidiInputHandler inputHandler = new MidiInputHandler(sampleManager, gui);
			gui.setMidiInputHandler(inputHandler);
			// creates the midiDevice
			MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
			for (int i = 0; i < infos.length; i++) 
			{
				System.out.println(i + ": " + infos[i].getName());
			}
			// tries to connect to the midiDevice, uses the GUI if no midiDevice
			int deviceIndex = 1;
			if (deviceIndex >= 0 && deviceIndex < infos.length) 
			{
				MidiDevice device = MidiSystem.getMidiDevice(infos[deviceIndex]);
				device.open();
				device.getTransmitter().setReceiver(inputHandler);
				System.out.println("🎹 Connected to: " + device.getDeviceInfo().getName());
			}
		} 
		catch (Exception e) // catches error if there is a midi Error
		{ 
			System.err.println("MIDI Error:");
			e.printStackTrace();
		}
	}
}
