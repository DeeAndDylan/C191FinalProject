package cs191.instruments;

import cs191.SampleHandler;
import javax.sound.sampled.*;
import java.io.InputStream;

public class SampleLoader {
    private static final String BASE_PATH = "/instruments/";
    private static final String[] NOTE_NAMES = {
        "A1", "A1s", "B1", "C1", "C1s", 
        "D1", "D1s", "E1", "F1", "F1s", 
        "G1", "G1s"
    };

    public static void loadInstrument(SampleHandler handler, InstrumentType type) {
        for (int i = 0; i < NOTE_NAMES.length; i++) {
            String path = BASE_PATH + type.getFolderName() + "/" + NOTE_NAMES[i] + ".wav";
            try (InputStream is = SampleLoader.class.getResourceAsStream(path)) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
                handler.loadSample(i, audioStream);
                System.out.println("Loaded: " + path);
            } catch (Exception e) {
                System.err.println("❌ Failed to load: " + path);
                e.printStackTrace();
            }
        }
    }
}
