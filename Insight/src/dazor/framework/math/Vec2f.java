package dazor.framework.math;

public class Vec2f {

	float x;
	float y;
	
	float[] vec = {x,y};
 	
	public Vec2f() {
		this(0,0);
	}
	
	public Vec2f(Vec2f vector) {
		this(vector.x, vector.y);
	}
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getLength() {
		return (float) Math.sqrt(getSquaredLength());
	}
	
	public float getSquaredLength() {
		return x*x+y*y;
	}
	
	public Vec2f add(Vec2f inputVector) {
		return add(inputVector, null);
	}
	
	public Vec2f add(Vec2f inputVector, Vec2f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec2f(this.x, this.y);
		}
		outputVector.addLocal(inputVector);
		return outputVector;
	}
	
	public void addLocal(Vec2f inputVector) {
		this.x += inputVector.x;
		this.y += inputVector.y;
	}
	
	public Vec2f subtract(Vec2f inputVector) {
		return add(inputVector, null);
	}
	
	public Vec2f subtract(Vec2f inputVector, Vec2f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec2f(this.x, this.y);
		}
		outputVector.addLocal(inputVector);
		return outputVector;
	}
	
	public void subtractLocal(Vec2f inputVector) {
		this.x -= inputVector.x;
		this.y -= inputVector.y;
	}
	
	public Vec2f scale(float factor) {
		return scale(factor, null);
	}
	
	public Vec2f scale(float factor, Vec2f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec2f(this.x,this.y);
		}
		outputVector.scaleLocal(factor);
		return outputVector;
	}
	
	public void scaleLocal(float factor) {
		this.x *= factor;
		this.y *= factor;
	}
	
	public Vec2f scale(float factor1, float factor2) {
		return scale(factor1, factor2, null);
	}
	
	public Vec2f scale(float factor1, float factor2, Vec2f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec2f(this.x,this.y);
		}
		outputVector.scaleLocal(factor1,factor2);
		return outputVector;
	}
	
	public void scaleLocal(float factor1, float factor2) {
		this.x *= factor1;
		this.y *= factor2;
	}
	
	public Vec2f normalize() {
		return normalize(null);
	}
	
	public Vec2f normalize(Vec2f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec2f(this.x, this.y);
		}
		outputVector.normalizeLocal();
		return outputVector;
	}
	
	public void normalizeLocal() {
		scaleLocal(1/getLength());
	}

	
	/**
	 * Creates the Crossproduct between this Vector and another Vector
	 * @param inputVector
	 * @return the dot product of the two vectors
	 */
	public float dotProduct(Vec2f inputVector) {
		float dot = 0;		
		dot += this.x * inputVector.getX();
		dot += this.y * inputVector.getY();
		return dot;
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float[] getVector() {
		return this.vec;
	}
	
	/**
	 * This Override is basically a JSON representation
	 */
	@Override
	public String toString() {
		return "\""+this.getClass().getName()+"\""+" : { \n\t\"x\" = "+x+", \n\t\"y\" = "+y+", \n}";
	}
	
}
