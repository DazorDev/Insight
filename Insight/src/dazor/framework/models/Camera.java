package dazor.framework.models;

import dazor.api.IObject;
import dazor.framework.math.Quaternion;
import dazor.framework.math.Transform;
import dazor.framework.math.Vec3f;

public class Camera implements IObject {

	private Transform transform = new Transform();
	
	public Camera() {
		setPosition(new Vec3f());
	}
	
	public Camera(int x, int y, int z) {
		setPosition(new Vec3f(x,y,z));
	}

	public Vec3f getPosition() {
		return transform.getPosition();
	}

	public void setPosition(Vec3f position) {
		this.transform.setPosition(position); 
	}
	
	public Quaternion getRotation() {
		return transform.getRotation();
	}
	
	public void setRotation(Quaternion quaternion) {
		this.transform.setRotation(quaternion);
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	
}
