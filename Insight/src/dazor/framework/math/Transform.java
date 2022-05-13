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
	Vec3f scaleFactor;
	
	public Transform() {
		this(null,null,null);
	}
	
	public Transform(Quaternion rotation) {
		this(rotation,null,null);
	}
	
	public Transform(Vec3f position) {
		this(null,position,null);
	}
	
	public Transform(Quaternion rotation, Vec3f position) {
		this(rotation,position,null);
	}

	public Transform(Vec3f position, Vec3f scale) {
		this(null,position,scale);
	}

	
	public Transform(Quaternion rotation, Vec3f position, Vec3f scale) {
		
		if(rotation == null) {
			rotation = new Quaternion();
		}
		
		if(position == null) {
			position = new Vec3f(0,0,0);
		}
		
		if(scale == null) {
			scale = new Vec3f(1,1,1);
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

	public Vec3f getScale() {
		return scaleFactor;
	}

	public void setScale(Vec3f scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
	
}
