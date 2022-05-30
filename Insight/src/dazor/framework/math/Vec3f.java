package dazor.framework.math;

/**
 * 
 * @author Daniel Banic
 *
 */
public class Vec3f {

	/**
	 * One of the three Position this Vector posses
	 */
	private float x,y,z;
	
	/**
	 * Array representation of the three positions
	 * Basically only used for the dot product and matrix multiplication
	 */
	private float[] vec = new float[3];
	
	/**
	 * Basic Constructor, calls itself with standart arguments to ensure there´s never null
	 */
	public Vec3f() {
		this(0,0,0);
	}

	/**
	 * Basic Constructor, which creates a copy of the Vector
	 * @param inputVector
	 */
	public Vec3f(Vec3f inputVector) {
		this(inputVector.x, inputVector.y, inputVector.z);
	}
	
	/**
	 * Creates a Vec3f with 3 given floats as Positions in 3d space.
	 * This is the final constructor which will be called
	 * @param x the x position of the Vector
	 * @param y the y position of the Vector
	 * @param z the z position of the Vector
	 */
	public Vec3f(float x,float y, float z) {
		vec[0] = this.x = x;
		vec[1] = this.y = y;
		vec[2] = this.z = z;
	}

	/**
	 * Creates a Vec3f with 3 given floats as Positions in 3d space.
	 * This is the final constructor which will be called
	 * @param x the x position of the Vector
	 * @param y the y position of the Vector
	 * @param z the z position of the Vector
	 */
	public Vec3f(float[] floatArray) {
		this(floatArray[0],floatArray[1],floatArray[2]);
	}
	
	/**
	 * Basic Construct which takes in doubles and casts them down to floats
	 * @param x the x position of the Vector
	 * @param y the y position of the Vector
	 * @param z the z position of the Vector
	 */
	public Vec3f(double x, double y, double z) {
		this((float) x,(float) y,(float) z);
	}

	/**
	 * Sets all the Positions of the Vector
	 * @param x the new position of the x vector
	 * @param y the new position of the y vector
	 * @param z the new position of the z vector
	 */
	public void set(float x,float y, float z) {
		this.x = vec[0] = x;
		this.y = vec[1] = y;
		this.z = vec[2] = z;	
	}
	
	/**
	 * Sets all the Positions of the Vector
	 * @param x the new position of the x vector
	 * @param y the new position of the y vector
	 * @param z the new position of the z vector
	 */
	public void set(float[] floatValues) {
		if(floatValues.length != 3) {
			return;
		}
		set(floatValues[0],floatValues[1],floatValues[2]);
	}
	
	/**
	 * Scales the Vector by a given factor
	 * Scaling is basically a simple multiplication with 
	 * the position for example we multiply {@link #x} with 
	 * the given factor to scale it up to the factor
	 * @param factor
	 */
	public void scaleLocal(double factor) {
		this.x = vec[0] *= factor;
		this.y = vec[1] *= factor;
		this.z = vec[2] *= factor;
	}
	
	/**
	 * Scales the Vector by a given factor
	 * Scaling is basically a simple multiplication with 
	 * the position for example we multiply {@link #x} with 
	 * the given factor to scale it up to the factor
	 * @param factor
	 */
	public void scaleLocal(float factor) {
		this.x = vec[0] *= factor;
		this.y = vec[1] *= factor;
		this.z = vec[2] *= factor;
	}
	
	/**
	 * Creates a new Vector which will be scaled to the Factor given in
	 * Scaling is basically a simple multiplication with 
	 * the position for example we multiply {@link #x},{@link #y} and {@link #z} with 
	 * the given factor to scale it up to the factor
	 * @param factor
	 */
	public Vec3f scale(double factor) {
		return scale(factor, null);
	}
	
	/**
	 * Scales the Vector by a given factor
	 * Scaling is basically a simple multiplication with 
	 * the position for example we multiply {@link #x} with 
	 * the given factor to scale it up to the factor
	 * @param factor
	 */
	public Vec3f scale(double factor, Vec3f inputVector) {
		if(inputVector == null) {
			inputVector = new Vec3f(this);
		}
		inputVector.scaleLocal(factor);
		return inputVector;
	}
	
	
	/**
	 * Just the pythagoream theorem but 3d
	 * Returns the Squareroot of the squaredSize to determin the Length of the Vector
	 * @return
	 */
	public double getSize() {
		return Math.sqrt(squaredSize());
	}
	
	/**
	 * Just the pythagoream theorem but 3d
	 * Squares all Position of the Vector
	 * @return
	 */
	public double squaredSize() {
		return x*x+y*y+z*z;
	}

	/**
	 * Scales the Whole Vector down, so only the The Longest Side has its length equal to 1
	 * Achived by dividing 1 by the entire length, because only n * (1/n) == 1
	 */
	public void normalizeLocal() {
		scaleLocal(1 / getSize());
	}
	
	/**
	 * Creates a new Normalized Vector where only the The Longest Side has its length equal to 1
	 * Achived by dividing 1 by the entire length, because only n * (1/n) == 1
	 */
	public Vec3f normalize() {
		return normalize(null);
	}
	
	/**
	 * Noramlizes a Vector given as parameter so only the longest has its length equal to 1
	 * @param outputVector the Vector which will hold the new Values
	 * @return a normalized Vector
	 */
	public Vec3f normalize(Vec3f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec3f(this.x, this.y, this.z);
		}
		outputVector.normalizeLocal();;
		return outputVector;
	}

	/**
	 * Creates a new Vector which is the Crossproduct between this Vector and a nother Vector
	 * @param vec the Vector which will be cross multiplied with the vector
	 * @return the CrossProduct Vector
	 */
	public Vec3f crossProduct(Vec3f inputVector) {
		return crossProduct(inputVector, null);
	}
	
	/**
	 * 
	 * @param inputVector
	 * @return
	 */
	public Vec3f add(Vec3f inputVector) {
		return add(inputVector, null);
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param outputVector
	 * @return
	 */
	public Vec3f add(Vec3f inputVector, Vec3f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec3f(this);
		}
		
		outputVector.addLocal(inputVector);
		return outputVector;
	}
	
	/**
	 * 
	 * @param inputVector
	 */
	public void addLocal(Vec3f inputVector) {
		this.x = vec[0] += inputVector.getX();
		this.y = vec[1] += inputVector.getY();
		this.z = vec[2] += inputVector.getZ();
	}
	
	/**
	 * 
	 * @param inputVector
	 * @return
	 */
	public Vec3f add(float x, float y, float z) {
		return add(x,y,z , null);
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param outputVector
	 * @return
	 */
	public Vec3f add(float x, float y, float z, Vec3f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec3f(this);
		}
		
		outputVector.addLocal(x,y,z);
		return outputVector;
	}
	
	/**
	 * 
	 * @param inputVector
	 */
	public void addLocal(float x, float y, float z) {
		this.x = vec[0] += x;
		this.y = vec[1] += y;
		this.z = vec[2] += z;
	}
	
	/**
	 * 
	 * @param inputVector
	 * @return
	 */
	public Vec3f subtract(Vec3f inputVector) {
		return add(inputVector, null);
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param outputVector
	 * @return
	 */
	public Vec3f subtract(Vec3f inputVector, Vec3f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec3f(this);
		}
		
		outputVector.addLocal(inputVector);
		return outputVector;
	}
	
	/**
	 * 
	 * @param inputVector
	 */
	public void subtractLocal(Vec3f inputVector) {
		this.x = vec[0] -= inputVector.getX();
		this.y = vec[1] -= inputVector.getY();
		this.z = vec[2] -= inputVector.getZ();
	}
	
	/**
	 * Takes the Crossprodukt of the first InputVector and The OutputVector, the outputVector will get the Results as it´s Values
	 * @param inputVector the Vector which will be multiplied with
	 * @param outputVector the Vector which will store the new Values
	 * @return a Vector which is the Crossproduct between the first and the second Vector
	 */
	public Vec3f crossProduct(Vec3f inputVector, Vec3f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec3f(this.x,this.y,this.z);
		}
		outputVector.x = outputVector.y * inputVector.getZ() - outputVector.z * inputVector.getY();
		outputVector.y = outputVector.z * inputVector.getX() - outputVector.x * inputVector.getZ();
		outputVector.z = outputVector.x * inputVector.getY() - outputVector.y * inputVector.getX();
		return outputVector;
	}
	
	/**
	 * Creates the Crossproduct between this Vector and another Vector
	 * @param inputVector
	 * @return the dot product of the two vectors
	 */
	public float dotProduct(Vec3f inputVector) {
		float dot = 0;		
		for(int i= 0; i!= 3; i++) {
			dot += getVector()[i] * inputVector.getVector()[i];
		}
		return dot;
	}
	
	public float[] getVector() {
		return this.vec;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = vec[0] = x;
	}
	
	public void setX(double x) {
		this.x = vec[0]  = (float) x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = vec[1]  = y;
	}
	
	public void setY(double y) {
		this.y = vec[1]  = (float) y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = vec[2]  = z;
	}
	
	public void setZ(double z) {
		this.z = vec[2]  = (float) z;
	}
	
	/**
	 * This Override is basically a JSON representation
	 */
	@Override
	public String toString() {
		return "\""+this.getClass().getName()+"\""+" : { \n\t\"x\" = "+x+", \n\t\"y\" = "+y+", \n\t\"z\" = "+z+" \n}";
	}
	
}
