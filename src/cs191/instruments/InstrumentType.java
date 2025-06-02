package cs191.instruments;

public enum InstrumentType {
	// the types of instruments that can be loaded into the program
	PIANO("piano", "Grand Piano", 0), SYNTH("synth", "Analog Synth", 1);

	// instance final variables
	private final String folderName;
	private final String displayName;
	private final int programNumber;

	/**
	 * Constructor that takes in folderName, displayName, and the programNumber and
	 * sets the variables to them.
	 * 
	 * @param folderName,    the folderName of the provided instrument
	 * @param displayName,   the displayName of the provided instrument
	 * @param programNumber, the programNumber of the provided instrument
	 */
	private InstrumentType(String folderName, String displayName, int programNumber) {
		this.folderName = folderName;
		this.displayName = displayName;
		this.programNumber = programNumber;
	}

	// Getters

	/**
	 * getter that gets the folderName of the current instrument loaded.
	 * 
	 * @return folderName, the folder name of the current instrument loaded.
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * getter that gets the displayName of the current instrument loaded.
	 * 
	 * @return displayName, the display name of the current instrument loaded.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * getter that gets the programNumber of the current instrument loaded.
	 * 
	 * @return programNumber, the program number of the current instrument loaded.
	 */
	public int getProgramNumber() {
		return programNumber;
	}
	
	@Override
	public String toString() {
	    return displayName;
	}
}