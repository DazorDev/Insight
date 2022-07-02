package dazor.framework.math;

/**
 * 
 * @author Daniel Banic
 */
public class Transform {

	/**
	 * 
	 */
	Quaternion rotationOfObject;
	
	/**
	 * 
	 */
	Vec3f positionIn3dSpace;

	/**
	 * 
	 */
	float scaleFactor;
	
	/**
	 * 
	 */
	public Transform() {
		this(null,null,1);
	}
	
	/**
	 * 
	 * @param rotation
	 */
	public Transform(Quaternion rotation) {
		this(rotation,null,1);
	}
	
	/**
	 * 
	 * @param position
	 */
	public Transform(Vec3f position) {
		this(null,position,1);
	}
	
	/**
	 * 
	 * @param rotation
	 * @param position
	 */
	public Transform(Quaternion rotation, Vec3f position) {
		this(rotation,position,1);
	}

	/**
	 * 
	 * @param position
	 * @param scale
	 */
	public Transform(Vec3f position, float scale) {
		this(null,position,scale);
	}

	public Transform(Transform transform) {
		this.rotationOfObject = new Quaternion(transform.rotationOfObject);
		this.positionIn3dSpace = new Vec3f(transform.positionIn3dSpace);
		this.scaleFactor = transform.scaleFactor;
	}
	
	/**
	 * 
	 * @param rotation
	 * @param position
	 * @param scale
	 */
	public Transform(Quaternion rotation, Vec3f position, float scale) {
		
		if(rotation == null) {
			rotation = new Quaternion();
		}
		
		if(position == null) {
			position = new Vec3f(0,0,0);
		}
		
		rotationOfObject = rotation;
		positionIn3dSpace = position;
		scaleFactor = scale;
	}
	
	/**
	 * 
	 * @return
	 */
	public Quaternion getRotation() {
		return rotationOfObject;
	}

	/**
	 * 
	 * @param rotation
	 */
	public void setRotation(Quaternion rotation) {
		this.rotationOfObject = rotation;
	}

	/**
	 * 
	 * @return
	 */
	public Vec3f getPosition() {
		return positionIn3dSpace;
	}

	/**
	 * 
	 * @param positionIn3dSpace
	 */
	public void setPosition(Vec3f positionIn3dSpace) {
		this.positionIn3dSpace = positionIn3dSpace;
	}

	/**
	 * 
	 * @return
	 */
	public float getScale() {
		return scaleFactor;
	}

	/**
	 * 
	 * @param scaleFactor
	 */
	public void setScale(float scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
}
