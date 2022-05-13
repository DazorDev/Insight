package dazor.framework.math;

public class Vertex {


	/**
	 * 
	 */
	Vec3f position;
	
	/**
	 * 
	 */
	Vec2f uvCoordinates;
	
	/**
	 * 
	 */
	Vec3f normal;
	
	/**
	 * 
	 */
	public Vertex() {
		this(null,null,null);
	}
	
	/**
	 * 
	 */
	public Vertex(Vertex vertex) {
		this(new Vec3f(vertex.getPosition()),new Vec2f(vertex.getUvCoordinates()),new Vec3f(vertex.getNormal()));
	}
	
	/**
	 * 
	 * @param position
	 */
	public Vertex(Vec3f position) {
		this(position,null,null);
	}
	
	/**
	 * 
	 * @param position
	 */
	public Vertex(Vec3f position, Vec3f normal) {
		this(position,null,normal);
	}
	
	/**
	 * 
	 * @param position
	 * @param uv
	 */
	public Vertex(Vec3f position, Vec2f uv, Vec3f normal) {

		this.position = position;
		this.uvCoordinates = uv;
		this.normal = normal;
		
		if(this.position == null) {
			this.position = new Vec3f();
		}
		
		if(this.uvCoordinates == null) {
			this.uvCoordinates = new Vec2f();
		}
		
		if(this.normal == null) {
			this.normal = new Vec3f();
		}
	}

	public Vertex rotate(Quaternion quaternion) {
		return rotate(quaternion, null);
	}
	
	public Vertex rotate(Quaternion quaternion, Vertex outputVertex) {
		if(outputVertex == null) {
			outputVertex = new Vertex();
		}
		
		Vec3f outputPosition = quaternion.rotate(position);
		Vec2f outputUv 		 = quaternion.rotate(uvCoordinates);
		Vec3f outputNormal   = quaternion.rotate(normal);
		
		outputVertex.setPosition(outputPosition);
		outputVertex.setUvCoordinates(outputUv);
		outputVertex.setNormal(outputNormal);
		return outputVertex;
	}
	
	public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.position = position;
	}

	public Vec2f getUvCoordinates() {
		return uvCoordinates;
	}

	public void setUvCoordinates(Vec2f uvCoordinates) {
		this.uvCoordinates = uvCoordinates;
	}

	public Vec3f getNormal() {
		return normal;
	}

	public void setNormal(Vec3f normal) {
		this.normal = normal;
	}
	
	
}
