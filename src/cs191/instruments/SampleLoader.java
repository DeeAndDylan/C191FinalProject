package cs191.instruments;

import cs191.SampleHandler;
import javax.sound.sampled.*;
import java.io.InputStream;

public class SampleLoader {
	// instance final variables that take the path to the instruments folder and get all of the note names.
	private static final String BASE_PATH = "/instruments/";
	private static final String[] NOTE_NAMES = { "C1", "C1s", "D1", "D1s", "E1", "F1", "F1s", "G1", "G1s", "A2", "A2s",
			"B2", "C2", "C2s", "D2", "D2s", "E2", "F2", "F2s", "G2", "G2s", "A3", "A3s", "B3", "C3" };

    /**
    * Constructor that takes in the sample handler, and the instrument type in order to load the instrument into the code. 
    * @param handler, the SampleHandler that will handle the samples of the provided instrument
    * @param type, the type of instrument that will be loaded into the program and the SampleHandler.
    */
	public static void loadInstrument(SampleHandler handler, InstrumentType type) {
		//for loop that loads the samples into the samplehandler
        for (int i = 0; i < NOTE_NAMES.length; i++) {
			String path = BASE_PATH + type.getFolderName() + "/" + NOTE_NAMES[i] + ".wav";
			//trys to load in the samples
            try (InputStream is = SampleLoader.class.getResourceAsStream(path)) {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
				handler.loadSample(i, audioStream);
				System.out.println("Loaded: " + path);
			} catch (Exception e) { //catches if it cannot load in the samples. 
				System.err.println("❌ Failed to load: " + path);
				e.printStackTrace();
			}
		}
	}
}
