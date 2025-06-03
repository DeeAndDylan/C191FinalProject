package cs191;

import cs191.instruments.*;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 * Responsibilities of Class: To Preload all of the samples tied to the currently enabled instrument in the GUI. 
 */
public class PreLoadedSamples {

	// instance variables
	private final SampleHandler sampleHandler; //PreLoadedSamples has-a SampleHandler
	private InstrumentType currentInstrument; //PreLoadedSamples has-a InstrumentType

	/*
	 * Constructor that knows the instrument that is being loaded into it.
	 * 
	 * @param defaultInstrument, the instrument that will be loaded.
	 */
	public PreLoadedSamples(InstrumentType defaultInstrument) {
		this.sampleHandler = new SampleHandler();
		loadInstrument(defaultInstrument);
	}

	/*
	 * Method that loads the instrument, to get the samples associated to the
	 * instrument.
	 * 
	 * @param type, the type of instrument being loaded.
	 */
	public void loadInstrument(InstrumentType type) {
		SampleLoader.loadInstrument(sampleHandler, type);
		this.currentInstrument = type;
		System.out.println("Instrument set to: " + type.getDisplayName());
	}

	/*
	 * Method that gets the current Sample Handler.
	 * 
	 * @return sampleHandler, the sampleHandler that is being used.
	 */
	public SampleHandler getSampleHandler() {
		return sampleHandler;
	}

	/*
	 * Method that gets the current instrument that is loaded.
	 * 
	 * @return currentInstrument, the current instrument loaded.
	 */
	public InstrumentType getCurrentInstrument() {
		return currentInstrument;
	}
}
