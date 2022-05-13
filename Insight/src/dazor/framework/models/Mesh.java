package dazor.framework.models;

import java.util.ArrayList;

import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;

public class Mesh {

	ArrayList<Face> faces = new ArrayList<Face>();
	
	ArrayList<Vec3f> vertexPoints = new ArrayList<>();
	ArrayList<Vec2f> uvPoints = new ArrayList<>();
	ArrayList<Vec3f> normalPoints = new ArrayList<>();
	
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
	

}
