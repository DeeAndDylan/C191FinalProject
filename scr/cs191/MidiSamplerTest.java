package cs191;

import javax.sound.midi.*;

import cs191.instruments.InstrumentType;

public class MidiSamplerTest {
    public static void main(String[] args) {
        try {
            // 1. Load ALL samples through PreLoadedSamples
            PreLoadedSamples preloader = new PreLoadedSamples(InstrumentType.PIANO);
            
            // 2. Get the pre-configured SampleHandler
            SampleHandler sampleManager = preloader.getSampleHandler();
            
            // 3. MIDI Device Setup
            MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
            
            // Print available devices to help debugging
            System.out.println("Available MIDI Devices:");
            for (int i = 0; i < infos.length; i++) {
                System.out.println(i + ": " + infos[i].getName());
            }
            
            // Select your device (change index as needed)
            int deviceIndex = 5; // Adjust this after seeing the list!
            MidiDevice device = MidiSystem.getMidiDevice(infos[deviceIndex]);
            
            // 4. Setup MIDI Handler
            Receiver receiver = new MidiInputHandler(sampleManager);
            
            // 5. Connect and Start
            device.open();
            Transmitter transmitter = device.getTransmitter();
            transmitter.setReceiver(receiver);
            
            System.out.println("🎹 Connected to: " + device.getDeviceInfo().getName());
            System.out.println("✅ Ready! Press keys to test samples.");
            

            // Keep program running
            while (true) {
                Thread.sleep(1000);
            }
            
        } catch (Exception e) {
            System.err.println("MIDI Error:");
            e.printStackTrace();
            System.out.println("\n💡 Tip: Check if your MIDI device is properly connected.");
        }
    }
}
