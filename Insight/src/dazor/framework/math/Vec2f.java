package dazor.framework.math;

public class Vec2f {

	float x;
	float y;
	
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
	
}
