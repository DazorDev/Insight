package dazor.framework.math;

/**
 * 
 * @author Daniel Banic
 *
 */
public class EulerAngle {
	
	/**
	 * The default angle which will be used if no other angle is given inside of the constructor
	 */
	private static final int DEFAULT_ANGLE = 90;
	
	/**
	 * Indentification number for degree unit of mesurement
	 */
	public static final int DEGREE = 0;
	
	/**
	 * Indentification number for radian unit of mesurement
	 */
	public static final int RADIAN = 1;
	
	/**
	 * The conversion rate between a angle from 0 - 360 to a angle from 0 - 2PI
	 * Basically just here for convenience of converting between the angle unit of mesurement
	 */
	private static final float RADIAN_CONVERSION = (float) Math.PI / 180;
	
	/**
	 * This is the biggest angle for the degree unit of mesurement
	 * It is as it큦 names tells used for taking the modulo of the yaw, pitch and roll angles
	 */
	private static final float DEGREE_MODULO = 360;
	
	/**
	 * This is the biggest angle for the radian unit of mesurement
	 * It is as it큦 names states used for taking the modulo of the yaw, pitch and roll angles
	 */
	private static final float RADIAN_MODULO = (float) (Math.PI * 2);
	
	/**
	 * This float defines the yaw of the rotation
	 * This is also a rotation where the z axis is rotated
	 */
	private float yaw;
	
	/**
	 * This float defines the pitch of the rotation
	 * This is also a rotation where the y axis is rotated
	 */
	private float pitch;
	
	/**
	 * This float defines the roll of the rotation
	 * This is also a rotation where the x axis is rotated
	 */
	private float roll;
	
	/**
	 * The current unit of mesurement this euler angle uses to represent it큦 numbers<br>
	 * It uses the {@link #DEGREE} and {@link #RADIAN} integers to store which unit it will use
	 */
	private int currentMode;
	
	/**
	 * Basic constructor without any parameters<br>
	 * this function will call {@link #EulerAngle(float, float, float, int)} with some default values<br>
	 * the default values for rotation are {@link #DEFAULT_ANGLE} for yaw pitch and roll<br>
	 * the default value for the unit of mesurement is {@link #DEGREE}
	 */
	public EulerAngle() {
		this(DEFAULT_ANGLE,DEFAULT_ANGLE,DEFAULT_ANGLE,DEGREE);
	}
	
	/**
	 * A Constructor which will take in the 3 Angles parameters for this rotation
	 * This constructor will assume that the passed in rotations are defined degrees
	 * Then this function will call {@link #EulerAngle(float, float, float, int)} with {@link #DEGREE} as the last Parameter
	 * @param yaw of this rotation
	 * @param pitch of this rotation
	 * @param roll of this rotation
	 */
	public EulerAngle(float yaw, float pitch, float roll) {
		this(yaw,pitch,roll,DEGREE);
	}
	
	/**
	 * This is the normal Constructor which will be called by all other constructors
	 * This constructor will take in the 3 Angles parameters for this rotation
	 * This constructor will also take in a unit of mesurement 
	 * @param yaw of this rotation
	 * @param pitch of this rotation
	 * @param roll of this rotation
	 * @param mode defining the unit of mesurement
	 */
	public EulerAngle(float yaw, float pitch, float roll, int mode) {
		//Sets the three angles of this rotation to the parameter
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;
		//Set the current mode to the unit of mesurment in the parameter
		this.currentMode = mode;
		//scales the yaw pitch and roll down to be less than the maximum angle for the unit of mesurement
		scaleDown();
	}

	/**
	 * This function will "cut" down the angles to be less than the biggest angle of the used unit of mesurment 
	 * This is done by taking each angle and moduloing every single one of them to make sure every single one is below the maximum
	 */
	private void scaleDown() {
		getAbsoluteValue();
		//checks which unit of mesurement is used
		if(currentMode == DEGREE) {
			/** if the unit is degree every value with {@link #DEGREE_MODULO} */
			yaw   %= DEGREE_MODULO;
			pitch %= DEGREE_MODULO;
			roll  %= DEGREE_MODULO;
			//return so that the other calculation is not done with these values
			return;
		}
		/** else modulo every angle with {@link #RADIAN_MODULO} */
		yaw   %= RADIAN_MODULO;
		pitch %= RADIAN_MODULO;
		roll  %= RADIAN_MODULO;
	}
	
	/**
	 * changes the current yaw, pitch and roll values and converts them into the absolute value of these angles
	 * basically just a helper function for {@link #scaleDown()} to make sure all the values are useable for the unit of mesurement
	 * because negative numbers could cause unexpected behaviour
	 */
	private void getAbsoluteValue() {
		//uses the Math.abs() function to take the absolute value for each angle
		yaw   = getValue(yaw);
		pitch = getValue(pitch);
		roll  = getValue(roll);
	}
	
	private float getCurrentModulo() {
		if(currentMode == DEGREE) {
			return DEGREE_MODULO;
		}
		return RADIAN_MODULO;
	}
	
	private float getValue(float value) {
		return (value < 0) ? getCurrentModulo()+value : value;
	}
	
	/**
	 * Converts this euler angle from one unit of mesurement to another given unit
	 * This is done by if it큦 the same mode if not then convert it to the the other mode by calling either {@link #convertToDegree()} or {@link #convertToRadian()}
	 * @param mode the new unit of mesurement which will be used
	 */
	public void convertTo(int mode) {
		//If the current Mode is the same as the mode which it should be converted to return because no further conversions have to be done
		if(mode == currentMode) {
			return;
		}
		//if the mode is equal to degree then convert it to convert it to degree and return
		if(mode == DEGREE) {
			convertToDegree();
			return;
		}
		//convert it to radians
		convertToRadian();
	}
	
	/**
	 * simple wrapper function which divides every single angle by {@link #RADIAN_CONVERSION} 
	 * this is used to convert angles in radians to degrees and basically also just here for convenience
	 * this function is also private in order to not allow accidental conversion from degrees into degrees and to avoid unnecessary checks
	 */
	private void convertToDegree() {
		//Simply divides the yaw, pitch and roll by the conversion rate of degrees to radians to return it to degrees
		yaw   /= RADIAN_CONVERSION;
		pitch /= RADIAN_CONVERSION;
		roll  /= RADIAN_CONVERSION;
	}

	/**
	 * simple wrapper function which multiplies every single angle by {@link #RADIAN_CONVERSION} 
	 * this is used to convert angles in degrees to radians and basically also just here for convenience
	 * this function is also private in order to not allow accidental conversion from radians into radians and to avoid unnecessary checks
	 */
	private void convertToRadian() {
		//Simply multiplies the yaw, pitch and roll by the converion rate of degrees to radians to return it to radians
		yaw   *= RADIAN_CONVERSION;
		pitch *= RADIAN_CONVERSION;
		roll  *= RADIAN_CONVERSION;	
	}
	
	/**
	 * Adds an euler angle to this euler angle
	 * This function will temporarily conver the inputRotation unit of mesurement to this one큦 unit of mesurment and then return it to the previous
	 * This will simply add the yaw, pitch and roll of both euler angles together and then scale it down to the maximum angle
	 * @param inputAngle
	 */
	public void add(EulerAngle inputRotation) {
		//Stores the mode of the inputRotation
		//this will be stored because we want to first convert the input angle큦 format to this format 
		//after calculations are done return them to the previous format 
		int tempMode = inputRotation.getMode();	
		//Converts the input rotation to this rotation
		inputRotation.convertTo(currentMode);
		//Adds every angle of the input euler rotation
		yaw   += inputRotation.getYaw();
		pitch += inputRotation.getPitch();
		roll  += inputRotation.getRoll();
		//convert it to the format the inputRotation had before it was converted
		inputRotation.convertTo(tempMode);
		//scale it down to the biggest angle
		scaleDown();
	}
	
	/**
	 * TEMPORARY
	 * TODO : finish this function and make it stable
	 * @param yaw
	 * @param pitch
	 * @param roll
	 */
	public void add(float yaw, float pitch, float roll) {
		this.yaw += yaw;
		this.pitch += pitch;
		this.roll += roll;
		scaleDown();
	}

	/**
	 * Subtracts an euler angle to this euler angle
	 * This function will temporarily conver the inputRotation unit of mesurement to this one큦 unit of mesurment and then return it to the previous
	 * This will simply subtract the yaw, pitch and roll of both euler angles together and then scale it down to the maximum angle
	 * @param inputAngle
	 */
	public void subtract(EulerAngle inputRotation) {
		//Stores the mode of the inputRotation
		//this will be stored because we want to first convert the input angle큦 format to this format 
		//after calculations are done return them to the previous format 
		int tempMode = inputRotation.getMode();	
		//Converts the input rotation to this rotation
		inputRotation.convertTo(currentMode);
		//Subtract every angle of the input euler rotation
		yaw   -= inputRotation.getYaw();
		pitch -= inputRotation.getPitch();
		roll  -= inputRotation.getRoll();
		//convert it to the format the inputRotation had before it was converted
		inputRotation.convertTo(tempMode);
		//scale it down to the biggest angle
		scaleDown();
	}
	
	/**
	 * Converts this euler angle into a quaternion
	 * This is done by first converting this angle to taking the sinus and the cosin for each {@link #yaw}, {@link #pitch} and {@link #roll}
	 * then using these to complete the calculation to get the quaternion values
	 * @return a Quaternion with the same rotation as this euler angle
	 */
	public Quaternion toQuaternion() {
		//First take the current unit of mesurement so it can be converted back to it
		int tempMode = getMode();
		//then convert it to radian so the calculation can be done without another huge calculation
		convertTo(RADIAN);
		//Calculates the ratios of each angle using sinus and cosin
		float sinYaw   = (float)Math.sin(yaw);
		float cosYaw   = (float)Math.cos(yaw);
		float sinPitch = (float)Math.sin(pitch);
		float cosPitch = (float)Math.cos(pitch);
		float sinRoll  = (float)Math.sin(roll);
		float cosRoll  = (float)Math.cos(roll);
		//convert the euler angle back to the unit of mesurement witch was used before the conversion
		convertTo(tempMode);
		//Long calculation of quaternion values using the before calculated values
		float qr = cosRoll * cosPitch * cosYaw + sinRoll * sinPitch * sinYaw;
		float qi = sinRoll * cosPitch * cosYaw - cosRoll * sinPitch * sinYaw;
		float qj = cosRoll * sinPitch * cosYaw + sinRoll * cosPitch * sinYaw;
		float qk = cosRoll * cosPitch * sinYaw - sinRoll * sinPitch * cosYaw;
		//Return a new Quaternion with the calculated Vaues
	    return new Quaternion(qr, qi, qj, qk);
	}
	
	/**
	 * Converts a Quaternion into an euler angle
	 * @param quaternion
	 * @return
	 */
	public static EulerAngle fromQuaternion(Quaternion quaternion) {
		
		//Check if the input to this function isn큧 null else the function would cause an error in the thread
		if(quaternion == null) {
			quaternion = new Quaternion();
		}
		
		//Convert the quaternion into euler angles using lot큦 of math
		float yaw   = (float) Math.atan2(2 * (quaternion.w * quaternion.x + quaternion.y * quaternion.z), 1 - 2 * (quaternion.x * quaternion.x + quaternion.y * quaternion.y));
		float pitch = (float) Math.asin(Math.max(-1, Math.min(1, 2 * (quaternion.w * quaternion.y - quaternion.z * quaternion.x))));
		float roll  = (float) Math.atan2(2 * (quaternion.w * quaternion.z + quaternion.x * quaternion.y), 1 - 2 * (quaternion.y * quaternion.y + quaternion.z * quaternion.z));
		
		return new EulerAngle(yaw,pitch,roll,RADIAN);
	}
	
	/**
	 * 
	 * @return
	 */
	public float getYaw() {
		return yaw;
	}

	/**
	 * 
	 * @param yaw
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
		scaleDown();
	}

	/**
	 * 
	 * @return
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * 
	 * @param pitch
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
		scaleDown();
	}

	/**
	 * 
	 * @return
	 */
	public float getRoll() {
		return roll;
	}

	/**
	 * 
	 * @param roll
	 */
	public void setRoll(float roll) {
		this.roll = roll;
		scaleDown();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMode() {
		return currentMode;
	}
	
	/**
	 * 
	 * @param mode
	 */
	public void setMode(int mode) {
		this.currentMode = mode;
		convertTo(mode);
	}

	public void set(float yaw, float pitch, int roll) {
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;
	}
	
}
