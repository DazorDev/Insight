package dazor.framework.rendering.wavefront;

import java.util.ArrayList;

import dazor.framework.math.Transform;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;
import dazor.framework.models.Face;
import dazor.framework.rendering.image.Texture;

public class ObjObject {

	ArrayList<Face> faces = new ArrayList<Face>();
	ArrayList<Vec3f> vertexPoints = new ArrayList<>();
	ArrayList<Vec2f> uvPoints = new ArrayList<>();
	ArrayList<Vec3f> normalPoints = new ArrayList<>();
	Transform transform = new Transform();
	Texture texture = new Texture();
	
	public ObjObject() {
		
	}
	
	public ObjObject(ObjObject objObject) {
		objObject.faces.forEach(face -> {
			this.faces.add(new Face(face));
		});
		this.transform = new Transform(objObject.transform);
		this.texture = new Texture();
	}

	public ObjObject offset(Transform transform) {
		return offset(null,transform);
	}
	
	public ObjObject offset(ObjObject objObject,Transform transform) {
		if(objObject == null) {
			objObject = new ObjObject(this);
		}
		objObject.offsetLocal(transform);
		return objObject;
	}
	
	public void offsetLocal(Transform transform) {
		faces.forEach(face -> {
			for(Vertex v : face.getVerticies()) {
				transform.getRotation().rotateLocal(v.getPosition());
				
				v.getPosition().addLocal(transform.getPosition());
			}
		});
	}
	
	public void addPolygon(Face face) {
		this.faces.add(face);
	}

	public ArrayList<Face> getPolygons() {
		return this.faces;
	}

	public ArrayList<Vec3f> getVertexPoints() {
		return vertexPoints;
	}

	public ArrayList<Vec2f> getUvPoints() {
		return uvPoints;
	}

	public ArrayList<Vec3f> getNormalPoints() {
		return normalPoints;
	}
	
	public Vec3f getVertexPoint(int i) {
		return vertexPoints.get(i);
	}

	public Vec2f getUvPoint(int i) {
		return uvPoints.get(i);
	}

	public Vec3f getNormalPoints(int i) {
		return normalPoints.get(i);
	}

	public void addVertexPoint(Vec3f vertexPos) {
		vertexPoints.add(vertexPos);
	}

	public void addUvPoint(Vec2f uvCoordiante) {
		uvPoints.add(uvCoordiante);
	}

	public void addNormalPoints(Vec3f normal) {
		normalPoints.add(normal);
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	

}
