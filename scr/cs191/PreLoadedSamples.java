package cs191;

import cs191.instruments.*;

public class PreLoadedSamples {
    
    private final SampleHandler sampleHandler;
    private InstrumentType currentInstrument;

    public PreLoadedSamples(InstrumentType defaultInstrument) 
    {
      this.sampleHandler = new SampleHandler();  
      loadInstrument(defaultInstrument);
    }

   public void loadInstrument(InstrumentType type)
   {
	   SampleLoader.loadInstrument(sampleHandler, type);
	   this.currentInstrument = type;
	   System.out.println("Instrument set to: " + type.getDisplayName());
   }
   
   public SampleHandler getSampleHandler()
   {
	   return sampleHandler;
   }
   
   public InstrumentType getCurrentInstrument()
   {
	   return currentInstrument;
   }
}
