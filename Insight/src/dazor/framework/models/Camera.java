package dazor.framework.models;

import dazor.api.IObject;
import dazor.framework.math.Vec3f;

public class Camera implements IObject {

	private Vec3f position;
	
	public Camera() {
		setPosition(new Vec3f());
	}
	
	public Camera(int x, int y, int z) {
		setPosition(new Vec3f(x,y,z));
	}

	public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.position = position;
	}
	
}
