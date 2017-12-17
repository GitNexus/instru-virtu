package application;


public class Settings {

    public static final int DEFAULT_AMPLITUDE = 1;
    public static final int DEFAULT_WAVEFORM = 0;
    public static final double DEFAULT_ATTACK = 0.01;
    public static final double DEFAULT_DECAY = 0.2;
    public static final double DEFAULT_RELEASE = 1.0;
    public static final double DEFAULT_SUSTAIN = 1.0;
    public static final int DEFAULT_FILTER_TYPE = 1;
    public static final double DEFAULT_FILTER_FREQ = 20000;
    public static final double DEFAULT_FILTER_RES = 0;
    
    private double amplitude;
    private int waveForm;
    
    // Envelope
    private double attack;
    private double decay;
    private double release;
    private double sustain;

    // filter Envelope
    private double filterAttack;
    private double filterDecay;
    private double filterRelease;
    private double filterSustain;
    
    // Filter
    private int filterType;
    private double filterFreq;
    private double filterRes;
    
    
    public Settings() {
    	// osc
        amplitude = DEFAULT_AMPLITUDE;
        waveForm = DEFAULT_WAVEFORM;
        // env
        attack = DEFAULT_ATTACK;
        decay = DEFAULT_DECAY;
        release = DEFAULT_RELEASE;
        sustain = DEFAULT_SUSTAIN;
        // filter env
        filterAttack = DEFAULT_ATTACK;
        filterDecay = DEFAULT_DECAY;
        filterRelease = DEFAULT_RELEASE;
        filterSustain = DEFAULT_SUSTAIN;
        // filter
        filterType = DEFAULT_FILTER_TYPE;
        filterFreq = DEFAULT_FILTER_FREQ;
        filterRes = DEFAULT_FILTER_RES;
    }

    public Settings(double amplitude, int waveForm, double attack, double decay, double release, double sustain, int filterType, double filterFreq, double filterRes, double filterAttack, double filterDecay, double filterRelease, double filterSustain) {
        this.amplitude = amplitude;
        this.waveForm = waveForm;
        this.attack = attack;
        this.decay = decay;
        this.release = release;
        this.sustain = sustain;
        this.filterFreq = filterFreq;
        this.filterRes = filterRes;
        this.filterType = filterType;
        this.filterAttack = filterAttack;
        this.filterDecay = filterDecay;
        this.filterRelease = filterRelease;
        this.filterSustain = filterSustain;
    }

    protected double getFilterAttack() {
		return filterAttack;
	}

	protected void setFilterAttack(double filterAttack) {
		this.filterAttack = filterAttack;
	}

	protected double getFilterDecay() {
		return filterDecay;
	}

	protected void setFilterDecay(double filterDecay) {
		this.filterDecay = filterDecay;
	}

	protected double getFilterRelease() {
		return filterRelease;
	}

	protected void setFilterRelease(double filterRelease) {
		this.filterRelease = filterRelease;
	}

	protected double getFilterSustain() {
		return filterSustain;
	}

	protected void setFilterSustain(double filterSustain) {
		this.filterSustain = filterSustain;
	}

	public double getAmplitude() {
        return amplitude;
    }

    public int getWaveForm() {
        return waveForm;
    }

    public void setWaveForm(int waveForm) {
        this.waveForm = waveForm;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public double getDecay() {
		return decay;
	}

	public void setDecay(double decay) {
		this.decay = decay;
	}

	public double getRelease() {
		return release;
	}

	public void setRelease(double release) {
		this.release = release;
	}

	public double getSustain() {
		return sustain;
	}

	public void setSustain(double sustain) {
		this.sustain = sustain;
	}

	protected int getFilterType() {
		return filterType;
	}

	protected void setFilterType(int filterType) {
		this.filterType = filterType;
	}

	protected double getFilterFreq() {
		return filterFreq;
	}

	protected void setFilterFreq(double filterFreq) {
		this.filterFreq = filterFreq;
	}

	protected double getFilterRes() {
		return filterRes;
	}

	protected void setFilterRes(double filterRes) {
		this.filterRes = filterRes;
	}
	
}

