package cs191.instruments;

public enum InstrumentType {
    PIANO("piano", "Grand Piano", 0),
    SYNTH("synth", "Analog Synth", 1);

    private final String folderName;
    private final String displayName;
    private final int programNumber;

   private InstrumentType(String folderName, String displayName, int programNumber) {
        this.folderName = folderName;
        this.displayName = displayName;
        this.programNumber = programNumber;
    }

    // Getters
    public String getFolderName()
    {
    	return folderName;
    }
   
    public String getDisplayName() 
    {
    	return displayName; 
    }
   
    public int getProgramNumber() 
    {
    	return programNumber; 
    }
}