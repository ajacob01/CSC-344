import java.io.*;
import java.util.Random;

public class Assign1 {

	public static void main(String[] args) {
		
		File inFile = new File(args[0]);
		WavFile inWav, outWav;
		int sampleRate = 44100;
		int clipLength = 30;
		int numFrames = sampleRate*clipLength;
		Random random = new Random();

		double[][] inBuffer = new double[2][60*sampleRate];
		double[][] outBuffer = new double[2][numFrames];
	
		try {
			inWav = WavFile.openWavFile(inFile);
			inWav.readFrames(inBuffer, 60*sampleRate);
			outWav = WavFile.newWavFile(new File("out.wav"), 2, numFrames, 16, sampleRate);

			for (int i = 0; i < numFrames;) {
				int nextRand = random.nextInt(60*sampleRate);
				
				for (int j = nextRand; j < nextRand + sampleRate; j++) {
					outBuffer[0][i] = inBuffer[0][j];
					outBuffer[1][i++] = inBuffer[1][j];
				}
			}			
	
			outWav.writeFrames(outBuffer, numFrames);

		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
} 
