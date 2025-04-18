import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class MorseCodeController {
	
	private HashMap<Character,String> morseCodeMap;
	private static SourceDataLine sourceDataLine;
	public  MorseCodeController () {
		 // Letters
        morseCodeMap.put('A', ".-");
        morseCodeMap.put('B', "-...");
        morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-..");
        morseCodeMap.put('E', ".");
        morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--.");
        morseCodeMap.put('H', "....");
        morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---");
        morseCodeMap.put('K', "-.-");
        morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--");
        morseCodeMap.put('N', "-.");
        morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--.");
        morseCodeMap.put('Q', "--.-");
        morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "...");
        morseCodeMap.put('T', "-");
        morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-");
        morseCodeMap.put('W', ".--");
        morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--");
        morseCodeMap.put('Z', "--..");
        
     // Lowercase letters
        morseCodeMap.put('a', ".-");
        morseCodeMap.put('b', "-...");
        morseCodeMap.put('c', "-.-.");
        morseCodeMap.put('d', "-..");
        morseCodeMap.put('e', ".");
        morseCodeMap.put('f', "..-.");
        morseCodeMap.put('g', "--.");
        morseCodeMap.put('h', "....");
        morseCodeMap.put('i', "..");
        morseCodeMap.put('j', ".---");
        morseCodeMap.put('k', "-.-");
        morseCodeMap.put('l', ".-..");
        morseCodeMap.put('m', "--");
        morseCodeMap.put('n', "-.");
        morseCodeMap.put('o', "---");
        morseCodeMap.put('p', ".--.");
        morseCodeMap.put('q', "--.-");
        morseCodeMap.put('r', ".-.");
        morseCodeMap.put('s', "...");
        morseCodeMap.put('t', "-");
        morseCodeMap.put('u', "..-");
        morseCodeMap.put('v', "...-");
        morseCodeMap.put('w', ".--");
        morseCodeMap.put('x', "-..-");
        morseCodeMap.put('y', "-.--");
        morseCodeMap.put('z', "--..");


        // Numbers
        morseCodeMap.put('0', "-----");
        morseCodeMap.put('1', ".----");
        morseCodeMap.put('2', "..---");
        morseCodeMap.put('3', "...--");
        morseCodeMap.put('4', "....-");
        morseCodeMap.put('5', ".....");
        morseCodeMap.put('6', "-....");
        morseCodeMap.put('7', "--...");
        morseCodeMap.put('8', "---..");
        morseCodeMap.put('9', "----.");

        // Punctuation
        morseCodeMap.put('.', ".-.-.-");
        morseCodeMap.put(',', "--..--");
        morseCodeMap.put('?', "..--..");
        morseCodeMap.put('!', "-.-.--");
        morseCodeMap.put('/', "-..-.");
        morseCodeMap.put('(', "-.--.");
        morseCodeMap.put(')', "-.--.-");
        morseCodeMap.put('&', ".-...");
        morseCodeMap.put(':', "---...");
        morseCodeMap.put(';', "-.-.-.");
        morseCodeMap.put('=', "-...-");
        morseCodeMap.put('+', ".-.-.");
        morseCodeMap.put('-', "-....-");
        morseCodeMap.put('_', "..--.-");
        morseCodeMap.put('"', ".-..-.");
        morseCodeMap.put('$', "...-..-");
        morseCodeMap.put('@', ".--.-.");
	}

public  String translateToMorse(String textToTranslate) {
	StringBuilder translatedText=new StringBuilder();
	for(Character letter:textToTranslate.toCharArray()) {
		
		translatedText.append(morseCodeMap.get(letter)+"");
		
		
	}
	return translatedText.toString();
}

public static void playSound(String[] morseMessage) throws Exception {
	AudioFormat audioFormat = new AudioFormat(44100, 16, 1, true, false);
	
	DataLine.Info dataLineInfo =new DataLine.Info(SourceDataLine.class, audioFormat);

	SourceDataLine sourcedateLine=(SourceDataLine)AudioSystem.getLine(dataLineInfo);
	sourcedateLine.open(audioFormat);
	sourcedateLine.start();
	
	int dotDuration=200;
	int dashduration=(int)(105*dotDuration);
	int dlashDuration=2*dotDuration;
	
	for(String pattern:morseMessage) {
		
		for(char c:pattern.toCharArray()) {

		
		if(c=='-') {
			playBeep(sourceDataLine,dotDuration);
			Thread.sleep(dotDuration);
			}
		
			else if(c=='-') {
				
		}else if(c=='/') {
			Thread.sleep(dotDuration);
			
		}
		
		
	}
		Thread.sleep(dotDuration);
	}
	
	
	sourceDataLine.drain();
	sourceDataLine.stop();
	sourceDataLine.close();
}



private static void playBeep(SourceDataLine line,int duration) {
	
	byte[] data=new byte[duration *44100/1000];
	for(int i=0;i<data.length;i++) {
		double angle =i/(44100.0/1000)*2* Math.PI;
		
		data[i]=(byte)(Math.sin(angle)*127);
	}
	
	
	
	line.write(data, 0, data.length);

	
	
}
}
