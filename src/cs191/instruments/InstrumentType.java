package cs191.instruments;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 * 
 * Version Date: 6/2/2025
 * 
 * Responsibilities of Class: To hold all the InstrumentTypes and how to access their files. 
 */
public enum InstrumentType 
{
	// the types of instruments that can be loaded into the program
	PIANO("piano", "Grand Piano", 0), SYNTH("synth", "Analog Synth", 1);

	// instance final variables
	private final String folderName; //InstrumentType has-a String
	private final String displayName; //InstrumentType has-a String
	private final int programNumber; // InstrumentType has-a int

	/**
	 * Purpose: Constructor that takes in folderName, displayName, and the programNumber and
	 * sets the variables to them.
	 * 
	 * @param folderName,    the folderName of the provided instrument
	 * @param displayName,   the displayName of the provided instrument
	 * @param programNumber, the programNumber of the provided instrument
	 */
	private InstrumentType(String folderName, String displayName, int programNumber) 
	{
		this.folderName = folderName;
		this.displayName = displayName;
		this.programNumber = programNumber;
	}

	// Getters

	/**
	 * Purpose: Getter that gets the folderName of the current instrument loaded.
	 * 
	 * @return folderName, the folder name of the current instrument loaded.
	 */
	public String getFolderName() 
	{
		return folderName;
	}

	/**
	 * Purpose: Getter that gets the displayName of the current instrument loaded.
	 * 
	 * @return displayName, the display name of the current instrument loaded.
	 */
	public String getDisplayName() 
	{
		return displayName;
	}

	/**
	 * Purpose: Getter that gets the programNumber of the current instrument loaded.
	 * 
	 * @return programNumber, the program number of the current instrument loaded.
	 */
	public int getProgramNumber() 
	{
		return programNumber;
	}
	
	/**
	 * Purpose: Prints diplayName to String
	 */
	@Override
	public String toString() 
	{
	    return displayName;
	}
}