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
		//create values for every smallest and largest length in the x, y and z directions
		float largestX = 0;
		float largestY = 0;
		float largestZ = 0;
		float smallestX = 0;
		float smallestY = 0;
		float smallestZ = 0;
		
		//Create these temporary values to store the vertex points of each vertex in vertexPoints
		float x = 0;
		float y = 0;
		float z = 0;
		//Go over every single Vertexpoint and check the length of each of the directions
		for(Vec3f vertex : vertexPoints) {
			//Assign all the x,y and z values to the temporary values created outside the loop
			x = vertex.getX();
			y = vertex.getY();
			z = vertex.getZ();

			//Go over every single one of the of the values and check if it´s either smaller or larger than the already
			//present value and then assign the larger value to the value storage
			largestX  =  getBigger(largestX,  x);
			largestY  =  getBigger(largestY,  z);
			largestZ  =  getBigger(largestZ,  y);			
			smallestX = getSmaller(smallestX, x);
			smallestY = getSmaller(smallestY, y);
			smallestZ = getSmaller(smallestZ, z);
		}
		Vec3f readjustmentVector = new Vec3f(largestX-Math.abs(smallestX), largestY-Math.abs(smallestY), largestZ-Math.abs(smallestZ));
		readjustmentVector.scaleLocal(-0.5f);
		offsetLocal(new Transform(readjustmentVector));
	}
	
	/**
	 * Return the smaller Value of the two inputs
	 * @param storedValue
	 * @param newValue
	 * @return
	 */
	private float getSmaller(float inputValue1, float inputValue2) {
		//Checks if inputValue2 is smaller than inputValue1
		//If it´s smaller return inputValue2 otherwise return inputValue1
		return inputValue2 < inputValue1 ? inputValue2 : inputValue1;
	}
	
	/**
	 * Return the bigger Value of the two inputs
	 * @param storedValue
	 * @param newValue
	 * @return
	 */
	private float getBigger(float storedValue, float newValue) {
		//Checks if inputValue2 is bigger than inputValue1
		//If it´s bigger return inputValue2 otherwise return inputValue1
		return newValue > storedValue ? newValue : storedValue;
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
