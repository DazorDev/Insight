package dazor.framework.math;

public class Quaternion {

	/**
	 * 
	 */
    public float x,y,z,w;
     
    /**
     * 
     */
    public Quaternion() {
    	this(1,0,0,0);
    }

    /**
     * 
     * @param rotationAxis
     */
    public Quaternion(Vec3f rotationAxis) {
        x = rotationAxis.getX();
        y = rotationAxis.getY();
        z = rotationAxis.getZ();
        w = 0;       
    }
    
    /**
     * 
     * @param angle
     * @param rotationAxis
     */
    public Quaternion(Vec2f rotationAxis) {
        x = rotationAxis.getX();
        y = rotationAxis.getY();
        z = 0;
        w = 0;       
    }
    
    /**
     * 
     * @param angle
     * @param rotationAxis
     */
    public Quaternion(Vec3f rotationAxis, float angle) {
        x = (float) (rotationAxis.getX() * Math.sin(angle)); 
        y = (float) (rotationAxis.getY() * Math.sin(angle));
        z = (float) (rotationAxis.getZ() * Math.sin(angle)); 
        w = (float) Math.cos(angle);
    }
    
    public Quaternion(Quaternion inputQuaternion) {
        this.x = inputQuaternion.x;
        this.y = inputQuaternion.y;
        this.z = inputQuaternion.z;
        this.w = inputQuaternion.w;
    }
    
    public Quaternion(float w, float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public Quaternion(double w, double x, double y, double z) {
        this((float)w,(float)x,(float)y,(float)z);
    }

    public void set (float w, float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public void set (Quaternion inputQuaternion) {
        this.x = inputQuaternion.x;
        this.y = inputQuaternion.y;
        this.z = inputQuaternion.z;
        this.w = inputQuaternion.w;
    }
    
    public double getSize() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }
    
    public void scaleLocal(double factor) {
    	scaleLocal((float) factor);
    }
    
    public void scaleLocal(float factor) {
    	this.x *= factor;
    	this.y *= factor;
    	this.z *= factor;
    	this.w *= factor;
    }
    
    public Quaternion scale(double factor) {
    	return scale((float) factor, null);
    }
    
    public Quaternion scale(float factor, Quaternion outputQuaternion) {
    	if(outputQuaternion == null) {
    		outputQuaternion = new Quaternion(this);
    	}
    	outputQuaternion.scaleLocal(factor);
    	return outputQuaternion;
    }
    
    public void normalizeLocal() {
    	if(x == 0 && y == 0 && z == 0 && w == 0) {
    		set(1, 0, 0, 0);
    	}
    	scaleLocal(1 / getSize());
    }
    
    public Quaternion normalize() {
    	return normalize(null);
    }
    
    public Quaternion normalize(Quaternion outputQuaternion) {
    	if(outputQuaternion == null) {
    		outputQuaternion = new Quaternion(this);
    	}
    	
    	outputQuaternion.normalizeLocal();
    	return outputQuaternion;
    }
    
    /**
     * Conjugates the Imaginary parts of the Quaternion, the Real part stays the same
     */
    public void conjugateLocal() {
        x = -x;
        y = -y;
        z = -z;
    }
    
    /**
     * 
     * @return a new Quaternion which will be the Conjugated
     */
    public Quaternion conjugate() {
        return conjugate(null);
    }
    
    /**
     * 
     * @param outputQuaternion
     * @return
     */
    public Quaternion conjugate(Quaternion outputQuaternion) {
    	if(outputQuaternion == null) {
    		outputQuaternion = new Quaternion(this);
    	}
    	outputQuaternion.conjugateLocal();
    	return outputQuaternion;
    }
    
    public Vec2f rotate(Vec2f inputVector) {
    	return rotate(inputVector, null);
    }
    
    public Vec2f rotate(Vec2f inputVector, Vec2f outputVector) {
    	if(outputVector == null) {
    		outputVector = new Vec2f(inputVector);
    	}
    	rotateLocal(outputVector);
    	return outputVector;
    }
    
    public Vec2f rotateLocal(Vec2f inputVector) {
    	Quaternion thisQuaternion = this.normalize();
    	
    	Quaternion tempQuaternion = thisQuaternion.multiply(new Quaternion(inputVector));
    	Quaternion conQuaternion = thisQuaternion.conjugate();
    	
    	Quaternion result = tempQuaternion.multiply(conQuaternion);
    	
    	inputVector.setX(result.getX());
    	inputVector.setY(result.getY());
    	return inputVector;
    }
    
    public Vec3f rotate(Vec3f inputVector) {
    	return rotate(inputVector, null);
    }
    
    public Vec3f rotate(Vec3f inputVector, Vec3f outputVector) {
    	if(outputVector == null) {
    		outputVector = new Vec3f(inputVector);
    	}
    	
    	Quaternion thisQuaternion = this.normalize();
    	
    	Quaternion tempQuaternion = thisQuaternion.multiply(new Quaternion(inputVector));
    	Quaternion conQuaternion = thisQuaternion.conjugate();
    	
    	Quaternion result = tempQuaternion.multiply(conQuaternion);
    	
    	outputVector.setX(result.getX());
    	outputVector.setY(result.getY());
    	outputVector.setZ(result.getZ());
    	return outputVector;
    }
    
    public Vec3f rotateLocal(Vec3f inputVector) {
    	Quaternion thisQuaternion = this.normalize();
    	
    	Quaternion tempQuaternion = thisQuaternion.multiply(new Quaternion(inputVector));
    	Quaternion conQuaternion = thisQuaternion.conjugate();
    	
    	Quaternion result = tempQuaternion.multiply(conQuaternion);
    	
    	inputVector.setX(result.getX());
    	inputVector.setY(result.getY());
    	inputVector.setZ(result.getZ());
    	return inputVector;
    }
    
    /**
     * 
     * @param inputVector
     * @return
     */
    public Quaternion multiply(Quaternion inputQuaternion) {
    	return multiply(inputQuaternion, null);
    }
    
    /**
     * Shorter Version of the Multiply function using only a Vector not a Pure Quaternion
     * @param inputVector
     * @return
     */
    public Quaternion multiply(Vec3f inputVector) {
    	
		float w = -this.x * inputVector.getX() - this.y * inputVector.getY() - this.z * inputVector.getZ();
		float x =  this.w * inputVector.getX() + this.y * inputVector.getZ() - this.z * inputVector.getY();
		float y =  this.w * inputVector.getY() + this.z * inputVector.getX() - this.x * inputVector.getZ();
		float z =  this.w * inputVector.getZ() + this.x * inputVector.getY() - this.y * inputVector.getX();
		return new Quaternion(w,x,y,z);
    }
    
    /**
     * Makes use of the standart formular of Quaternion and Vector multiplication : " Q * P * Q` "
     * Q representing this Quaternion, Q` representing the conjungated Quaternion and P representing the Vector as a pure Quaternion with only imaginary parts
     * Both of the Quaternions will be unitvectors, which means only the longest side == 1
     * but I know I could cast if down to 3 vectors, just not how :(
     * @param inputVector
     * @param outputVector
     * @return
     */
    public Quaternion multiply(Quaternion inputQuaternion, Quaternion outputQuaternion) {
    	
    	//Check if the outputQuaternion is null
        if(outputQuaternion == null) {
        	//If it is, create a new Quaternion for the output
            outputQuaternion = new Quaternion();
        }
        
        //If one Quaternion is made up of for 0 then we dont have to multiply
        if(inputQuaternion.getX() == 0 && inputQuaternion.getY() == 0 && inputQuaternion.getZ() == 0 && inputQuaternion.getW() == 0) {
            outputQuaternion.set(0, 0, 0, 0);
            return outputQuaternion;
        }
        
        //Long multiplication sequenz which multiplies Q * P * Q`
        float ix = inputQuaternion.getX(), iy = inputQuaternion.getY(), iz = inputQuaternion.getZ(), iw = inputQuaternion.getW() ;
        float qx = this.getX(), qy = this.getY(), qz = this.getZ(), qw = this.getW() ;        
        float w = (qw * iw) - (qx * ix) - (qy * iy) - (qz * iz);
        float x = (qx * iw) + (qw * ix) + (qy * iz) - (qz * iy);
        float y = (qy * iw) + (qw * iy) + (qz * ix) - (qx * iz);
        float z = (qz * iw) + (qw * iz) + (qx * iy) - (qy * ix);

        //Sets the new Values in the outputQuaternion
        outputQuaternion.set(w, x, y, z);
        
        //Returns the outputQuaternion
        return outputQuaternion;
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

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	@Override
    public String toString() {
    	return "\""+this.getClass().getSimpleName()+"\""+" : {\n\t\"x\" = "+x+",\n\t\"y\" = "+y+",\n\t\"z\" = "+z+",\n\t\"w\" = "+w+" \n}";
    }
    
}