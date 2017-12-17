package application;

import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class myHandler implements EventHandler<MouseEvent>{

	private static Synthesizer synth;
	private static LineOut lineout;
	static{
		lineout = new LineOut();
		synth = JSyn.createSynthesizer();
		synth.add(lineout);
		lineout.start();
		synth.start();
	}

	private Settings settings1, settings2;
	private UnitGate source1, source2;

	private double freq;

	public myHandler(double freq, Settings settings1, Settings settings2) {
		this.freq = freq;
		this.settings1 = settings1;
		this.settings2 = settings2;
	}

	protected double getFreq() {
		return freq;
	}

	protected void setFreq(double freq) {
		this.freq = freq;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			turnOn();
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			turnOff();
		} else {
			throw new IllegalStateException();
		}
	}

	private void turnOn(){
		source1 = createSource(settings1);
		source2 = createSource(settings2);
		source1.input.on();
		source2.input.on();
	}

	private void turnOff(){
		source1.input.off();
		source2.input.off();
	}

	private UnitGate createSource(Settings settings){

		// create oscillator
		UnitOscillator osc;
		switch (settings.getWaveForm()) {
			case 0:
				osc = new SineOscillator();
				break;
			case 1:
				osc = new TriangleOscillator();
				break;
			case 2:
				osc = new SquareOscillator();
				break;
			case 3:
				osc = new SquareOscillator();
				break;
			default:
				throw new IllegalArgumentException("invalid settings");
		}
		synth.add(osc);
		osc.frequency.set(freq);
		
		// create filter
		FilterBiquadCommon filter;
		switch (settings.getFilterType()) {
			case 0:
				filter = new FilterLowPass();
				break;
			case 1:
				filter = new FilterHighPass();
				break;
			default:
				throw new IllegalArgumentException("invalid settings");
		}	
		synth.add(filter);
		filter.frequency.set(settings.getFilterFreq());
		filter.Q.set(settings.getFilterRes());
		
		// create envelope 1
		EnvelopeDAHDSR ampEnv = new EnvelopeDAHDSR();
		synth.add(ampEnv);
		ampEnv.attack.set(settings.getAttack());
		ampEnv.sustain.set(settings.getSustain());
		ampEnv.decay.set(settings.getDecay());
		ampEnv.release.set(settings.getRelease());
		
		// create envelope 2
		EnvelopeDAHDSR filterEnv = new EnvelopeDAHDSR();
		synth.add(filterEnv);
		filterEnv.attack.set(settings.getFilterAttack());
		filterEnv.decay.set(settings.getFilterDecay());
		filterEnv.release.set(settings.getFilterRelease());
		filterEnv.sustain.set(settings.getFilterSustain());
		
		// connect
		filterEnv.output.connect(filter.frequency);
		ampEnv.output.connect(osc.amplitude);
		ampEnv.amplitude.set(settings.getAmplitude());
		osc.output.connect(filter.input);
		filter.output.connect( 0, lineout.input, 0);
		filter.output.connect( 0, lineout.input, 1);
		//osc.output.connect( 0, lineout.input, 0 );   // left channel
		//osc.output.connect( 0, lineout.input, 1 );
		
		return ampEnv;

	}


	// unit test
	//public static void main(String[] args){
	//	Settings settings1 = new Settings();
	//	myHandler handler = new myHandler(440, settings1, settings1);
	//	handler.turnOn();
	//	while(true);
	//}

}
