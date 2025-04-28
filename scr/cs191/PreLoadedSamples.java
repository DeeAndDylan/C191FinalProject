package cs191;

public class PreLoadedSamples {
    
    private SampleHandler sampleHandler;

    public PreLoadedSamples() {
        sampleHandler = new SampleHandler();
        preloadSamples();
    }

    private void preloadSamples() {
        int startNote = 0; // Middle C (C4)
        String basePath = "C:/Users//dawit/Music/Samples Java Final Project/";

        // You could name your samples like C4.wav, C#4.wav, D4.wav, etc
        String[] grandPiano = {
            "A1.wav", "A#.wav", "B1.wav", "C1.wav", "C#.wav",
            "D1.wav", "D#.wav", "E1.wav", "F1.wav", "F#.wav", "G1.wav","G#.wav"
        };

        for (int i = 0; i < grandPiano.length; i++) {
            int midiNote = startNote + i;
            String filePath = basePath + grandPiano[i];

            sampleHandler.loadSample(midiNote, filePath);
        }
    }

    public SampleHandler getSampleHandler() {
        return sampleHandler;
    }
}
