package dazor.framework.models;

import java.util.ArrayList;

import dazor.framework.math.Transform;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;
import dazor.framework.rendering.image.Texture;

public class Mesh {

	ArrayList<Face> faces = new ArrayList<Face>();
	ArrayList<Vec3f> vertexPoints = new ArrayList<>();
	ArrayList<Vec2f> uvPoints = new ArrayList<>();
	ArrayList<Vec3f> normalPoints = new ArrayList<>();
	Transform transform = new Transform();
	Texture texture = new Texture();
	
	public Mesh() {
		
	}
	
	public Mesh(Mesh mesh) {
		mesh.faces.forEach(face -> {
			this.faces.add(new Face(face));
		});
		this.transform = new Transform(mesh.transform);
		this.texture = new Texture();
	}

	public Mesh offset(Transform transform) {
		return offset(null,transform);
	}
	
	public Mesh offset(Mesh mesh,Transform transform) {
		if(mesh == null) {
			mesh = new Mesh();
		}
		mesh.removeAll();
		for(Face face : this.faces) {
			Face tempFace = face.rotate(transform.getRotation());
			tempFace.scale(transform.getScale());
			tempFace.move(transform.getPosition());
			mesh.addPolygon(tempFace);
		}
		return mesh;
	}
	
	public void removeAll() {
		faces = new ArrayList<>();
	}
	
	public void offsetLocal(Transform transform) {
		faces.forEach(face -> {
			for(Vertex v : face.getVerticies()) {
				Vec3f pos = v.getPosition();
				pos.scaleLocal(transform.getScale());
				transform.getRotation().rotateLocal(pos);
				pos.addLocal(transform.getPosition());
			}
		});
	}
	
	public void center() {
		float largestX = 0;
		float largestY = 0;
		float largestZ = 0;
		for(Vec3f vertex : vertexPoints) {
			float x = Math.abs(vertex.getX());
			if(x > largestX) {
				largestX = x;
			}
			float y = Math.abs(vertex.getX());
			if(y > largestY) {
				largestY = y;
			}
			float z = Math.abs(vertex.getX());
			if(z > largestZ) {
				largestZ = z;
			}
		}
		Vec3f readjustmentVector = new Vec3f(largestX, largestY, largestZ);
		readjustmentVector.scaleLocal(-0.5f);
		offsetLocal(new Transform(readjustmentVector));
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
