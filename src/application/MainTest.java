package application;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;

public class MainTest {

	public static void main(String[] args) throws InterruptedException {
		
		Synthesizer synth = JSyn.createSynthesizer();
		SineOscillator test = new SineOscillator(300, 1);
		LineOut testline = new LineOut();
		test.output.connect( 0, testline.input, 0 );   // connect to left channel
		test.output.connect( 0, testline.input, 1 );   // connect to right channel
		synth.add(testline);
		synth.add(test);
		synth.start();
		testline.start();
		//TimeUnit.SECONDS.sleep(4);
		Thread.sleep(4000);
		testline.stop();
	}

}
