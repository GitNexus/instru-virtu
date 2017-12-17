package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class SimpleViewCtrl {

    // piano keyboard
    private Rectangle[] keys;
    private Map<Rectangle,Double> freqMap;
    private Map<Rectangle,myHandler> handlerMap;

    // wave setting
    private Settings settings1;
    private Settings settings2;
    public int waveForm ;
    private int amplitude;

    // FXML interface

    // piano keys
    @FXML
    private Rectangle
            C1, CSharp1, D1, EFlat1, E1, F1, FSharp1, G1, Gharp1,
            A1, BFlat1, B1, C2, CSharp2, D2, EFlat2, E2,
            F2, FSharp2, G2, GSharp2, A2, BFlat2, B2, C3,
            CSharp3, D3, EFlat3, E3, F3, FSharp3, G3, GSharp3,
            A3, BFlat3, B3;

    // sliders    
    @FXML
    private Slider amp1, co1, fi1, pha1,
    		amp2, co2, fi2, pha2,
    		att1, sus1, dec1, rel1,
    		att2, sus2, dec2, rel2,
    		filterFreq, filterRes;
    
    @FXML
    private Button lowPass, highPass;

    @FXML
    private Button Triangle;

    @FXML
    private Button Rectangle;

    @FXML
    private Button Sinus;

    @FXML
    private Button Scie;

    // Wave form
    // getters/setters

    protected void setWaveForm1(int waveForm) {
        settings1.setWaveForm(waveForm);
        System.out.println("HERE1");
    }
    
    protected void setWaveForm2(int waveForm) {
        settings2.setWaveForm(waveForm);
        System.out.println("HERE2");
    }
    
    protected void setFilterType(int filterType) {
    	settings1.setFilterType(filterType);
    	settings2.setFilterType(filterType);
    	System.out.println("HERE");
    }
    
    @FXML
    public void setLowPass() {
    	setFilterType(0);
    }
    
    @FXML
    public void setHighPass() {
    	setFilterType(1);
    }
    
    @FXML
    public void setSinus() {
        setWaveForm1(0);
    }

    @FXML
    public void setTriangle() {
        setWaveForm1(1);
    }

    @FXML
    public void setSquare(){
        setWaveForm1(2);
    }

    @FXML
    public void setSaw() {
        setWaveForm1(3);
    }
    
    @FXML
    public void setSinus2() {
    	setWaveForm2(0);
    }
    
    @FXML
    public void setTriangle2() {
    	setWaveForm2(1);
    }
    
    @FXML
    public void setRectangle2() {
    	setWaveForm2(2);
    }
    
    @FXML
    public void setSaw2() {
    	setWaveForm2(3);
    }
    
    @FXML
    void initialize() {

        settings1 = new Settings();
        settings2 = new Settings();

        // init piano
        keys = new Rectangle[]{
                C1, CSharp1, D1, EFlat1, E1, F1, FSharp1, G1,
                Gharp1,A1, BFlat1, B1, C2, CSharp2, D2, EFlat2,
                E2, F2, FSharp2, G2, GSharp2, A2, BFlat2, B2,
                C3, CSharp3, D3, EFlat3, E3, F3, FSharp3, G3,
                GSharp3, A3, BFlat3, B3};

        String[] notes = {
                "C1","C1s","D1","D1s","E1","F1","F1s","G1",
                "G1s", "A1","A1s","B1","C2","C2s","D2","D2s",
                "E2", "F2","F2s","G2","G2s","A2","A2s","B2",
                "C3","C3s","D3","D3s","E3","F3","F3s","G3",
                "G3s","A3","A3s","B3"};

        double[] freqs = {
                130.81,138.59,146.83,155.56,164.81,174.61,185,196,
                207.65,220,233.08,246.94,261.63,277.18,293.66,311.13,
                329.63,349.23,370,392,415.30,440,466.16,493.88,
                523.25,554.37,587.33,622.25,659.26,698.46,739.99,783.99,
                830.61,880,932.33,987.77};

        handlerMap = new HashMap<>();
        freqMap = new HashMap<>();

        for(int i=0; i<keys.length; i++){
            Rectangle key = keys[i];
            double freq = freqs[i];
            freqMap.put(key,freq);
            myHandler pianoHandler = new myHandler(freq,settings1, settings2);
            handlerMap.put(key,pianoHandler);
            key.setOnMousePressed(pianoHandler);
            key.setOnMouseReleased(pianoHandler);
        }
        
        
        // OSC
        
        // Osc1
        ChangeListener<Number> ampli1 = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                settings1.setAmplitude(newValue.doubleValue()/100);
                System.out.println("called with "+settings1.getAmplitude());
            }
        };
        amp1.valueProperty().addListener(ampli1);
        
        
        // Osc2
        ChangeListener<Number> ampli2 = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                settings2.setAmplitude(newValue.doubleValue()/100);
                System.out.println("called with "+settings2.getAmplitude());
            }
        };
        amp2.valueProperty().addListener(ampli2);
        
        
        // ENV
        
        // Env1
        ChangeListener<Number> attack1 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setAttack(newValue.doubleValue()/100);
        		settings2.setAttack(newValue.doubleValue()/100);
        		System.out.println("called with "+settings1.getAttack());
        	}
        };
        att1.valueProperty().addListener(attack1);

        ChangeListener<Number> decay1 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setDecay(newValue.doubleValue()/100);
        		settings2.setDecay(newValue.doubleValue()/100);
        		System.out.println("called with "+settings1.getDecay());
        	}
        };
        dec1.valueProperty().addListener(decay1);

        ChangeListener<Number> release1 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setRelease(newValue.doubleValue()/100);
        		settings2.setRelease(newValue.doubleValue()/100);
        		System.out.println("called with "+settings1.getRelease());
        	}
        };
        rel1.valueProperty().addListener(release1);

        ChangeListener<Number> sustain1 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setSustain(newValue.doubleValue()/100);
        		settings2.setSustain(newValue.doubleValue()/100);
        		System.out.println("called with "+settings1.getSustain());
        	}
        };
        sus1.valueProperty().addListener(sustain1);
        
        
        // Env2
        ChangeListener<Number> attack2 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setFilterAttack(newValue.doubleValue()/100);
        		settings2.setFilterAttack(newValue.doubleValue()/100);
        		System.out.println("called with "+settings2.getFilterAttack());
        	}
        };
        att2.valueProperty().addListener(attack2);

        ChangeListener<Number> decay2 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setFilterDecay(newValue.doubleValue()/100);
        		settings2.setFilterDecay(newValue.doubleValue()/100);
        		System.out.println("called with "+settings2.getFilterDecay());
        	}
        };
        dec2.valueProperty().addListener(decay2);

        ChangeListener<Number> release2 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setFilterRelease(newValue.doubleValue()/100);
        		settings2.setFilterRelease(newValue.doubleValue()/100);
        		System.out.println("called with "+settings2.getFilterRelease());
        	}
        };
        rel2.valueProperty().addListener(release2);

        ChangeListener<Number> sustain2 = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setFilterSustain(newValue.doubleValue()/100);
        		settings2.setFilterSustain(newValue.doubleValue()/100);
        		System.out.println("called with "+settings2.getFilterSustain());
        	}
        };
        sus2.valueProperty().addListener(sustain2);
        
        // Filter
        ChangeListener<Number> filterCutOff = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setFilterFreq(newValue.doubleValue());
        		settings2.setFilterFreq(newValue.doubleValue());
        		System.out.println("called with "+settings1.getFilterFreq());
        	}
        };
        filterFreq.valueProperty().addListener(filterCutOff);

        ChangeListener<Number> filterQ = new ChangeListener<Number>() {
        	@Override
        	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        		settings1.setFilterRes(newValue.doubleValue());
        		settings2.setFilterRes(newValue.doubleValue());
        		System.out.println("called with "+settings2.getFilterRes());
        	}
        };
        filterRes.valueProperty().addListener(filterQ);

        // init other buttons
        // TODO
    }

}


