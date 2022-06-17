package dazor.framework.util;

/**
 * Basic TimeHandler which will take care of rather annoying Tasks like Calculating deltaTime, getting currentTime,  
 * @author Daniel Banic
 *
 */
public class TimeHandler {
	
	/**
	 * Conversion of milliseconds to seconds
	 */
	private static final float MILLICONVERSION = 1000f;
	
	/**
	 * Conversion of nanoseconds to seconds
	 */
	private static final float NANOCONVERSION = 1000000000f;
	
	/**
	 * Indentification number for millisecond time
	 */
	public static final int MILLI = 0;
	
	/**
	 * Identification number for nanosecond time
	 */
	public static final int NANO = 1;
	
	/**
	 * The current mode the time handler uses to keep track of time
	 */
	private int currentMode;
	
	/**
	 * Time holder values
	 * Both have to be instantiated with the current time or else the calculation of deltaTime will create the unexpected result of 
	 * having negative 2 billion as first deltaTime value
	 */
	private long currentTime, lastTime = getCurrentTime();
	
	/**
	 * The difference of time between now and the Last time {@link #calculateDeltaTime()} was called
	 */
	private long deltaTime;
	
	/**
	 * Call holder values
	 * will hold the amount of times {@link #calculateCallsPerSecond()} was called
	 */
	private int callsPerSecond, lastCallsPerSecond = 0;
	
	/**
	 * Holds the last Second in the currentTime format for updating the calls per second
	 */
	private long lastCallsPerSecondTime = getCurrentTime();
	
	/**
	 * Basic constructor without parameters, will call another constructor in order to set default settings
	 */
	public TimeHandler() {
		this(MILLI);
	}
	
	/**
	 * Sets the current mode of the timeHandler to the mode given as input
	 * @param timeMode
	 */
	public TimeHandler(int timeMode) {
		setMode(timeMode);
	}
	
	/**
	 * Sets the current mode of the timehandler to the specified mode
	 * @param inputMode
	 */
	public void setMode(int inputMode) {
		if(inputMode != MILLI || inputMode != NANO) {
			return;
		}
		currentMode = inputMode;
	}
	
	/**
	 * Sets the current mode of the timehandler to the specified mode
	 * @param inputMode
	 */
	public int getMode() {
		return currentMode;
	}
	
	/**
	 * Return the Conversionrate of the current mode to seconds
	 * @return the Conversion of milliseconds to seconds or nanoseconds to seconds
	 */
	public float getModeConversion() {
		//If the currentMode is Milli
		if(currentMode == MILLI) {
			//return the milliconversionrate
			return MILLICONVERSION;
		}
		//return the nanoconversion
		return NANOCONVERSION;
	}
	
	/**
	 * Returns the delta time 
	 * @return deltaTime
	 */
	public long getDeltaTime() {
		return deltaTime;
	}
	
	/**
	 * Returns the delta time in seconds by dividing the deltaTime by the conversionrate from 
	 * nanoseconds to seconds or milliseconds to seconds
	 * @return deltaTime normalized to seconds
	 */
	public float getDeltaTimeInSeconds() {
		return deltaTime/getModeConversion();
	}
	
	/**
	 * Calculates the difference of time between now and the last time this function was called
	 * It then sets the delta time equal to the currentTime - lastTime to see how many milliseconds have since then passed
	 */
	public void calculateDeltaTime() {
		//Sets the currentTime equal to the currentTime of the machine
		currentTime = getCurrentTime();
		//Calculate the difference between the lastTime this function was called and the currentTime
		deltaTime = currentTime - lastTime;
		//Sets the lastTime equal to the currentTime because it now is the last time this function was called
		lastTime = currentTime;
	}
	
	/**
	 * Returns the current time according to the time system this timehandler is using
	 * @return
	 */
	public long getCurrentTime() {
		//If currentMode is milli then return millitime
		if(currentMode == MILLI) {
			return System.currentTimeMillis();
		}
		//Else return nanotime
		return System.nanoTime();
	}
	
	/**
	 * Calculates the amount of times this function is called in a second
	 * This is achived by counting until the currentTime - the lastTime is greater or equal to the 1 second specified by the conversionrate
	 */
	public void calculateCallsPerSecond() {
		//if the currentTime - the lastTime is greater or equal to 1
		if(getCurrentTime() - lastCallsPerSecondTime >= getModeConversion()) {
			//let the last Calls per second be equal to this calls per second
			lastCallsPerSecond = callsPerSecond;
			//then set calls per second equal to 0
			callsPerSecond = 0;
		}
		//Increase the calls per second by 1
		callsPerSecond++;
	}
	
	/**
	 * Return the amount of times {@link #calculateCallsPerSecond()} was called
	 * This function returns {@link #lastCallsPerSecondTime} and is thus one second behind the actual calls per second
	 * @return {@link #lastCallsPerSecondTime}
	 */
	public int getCallsPerSecond() {
		return lastCallsPerSecond;
	}
}
