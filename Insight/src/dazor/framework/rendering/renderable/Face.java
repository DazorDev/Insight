package dazor.framework.rendering.renderable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import dazor.framework.math.Quaternion;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class Face {

	/**
	 * Vertex array which will hold up to three different Verticies
	 * It is limited to three to have it at most be an triangle
	 */
	Vertex[] verticies = new Vertex[3];
	
	int filledSpace = 0;
	
	
	/**
	 * Default Constructor, does nothing
	 */
	public Face() {}
	
	/**
	 * Creates a new Face from an Face which copies all the Values but creates new Objects
	 * @param face which has the Values this face will get
	 */
	public Face(Face face) {
		//For each Vertex create a new Vertex Object, add it´s values to it and add it to this face
		for(Vertex vertex : face.verticies) {
			addVertex(new Vertex(vertex));
		}
		
		this.filledSpace = face.filledSpace;
		
	}
	
	/**
	 * A Constructor which adds Up to three Verticies to this Face
	 * Will only do so, if the amount of verticies is equal to 3
	 * @param verticies an Vertex array which should have a size of 3
	 */
	public Face(Vertex... verticies) {
		
		//Check if the amount of Verticies is inside of bounds for this verticies array
		if(verticies.length > 3) {
			return;
		}			
		//Adds each Vertex to this faces verticies array
		for(Vertex v : verticies) {
			Vertex newVertex = new Vertex(v);
			this.addVertex(newVertex);
		}
	}
	
	/**
	 * Adds a Vertex to this Faces verticies
	 * @param vertex the Vertex which will be added to this Face
	 */
	public void addVertex(Vertex vertex) {
		//Check if once a new Vertex is added the Array would be bigger than 3
		if(filledSpace+1 > 3) {
			return;
		}
		//Adds the Vertex to the verticies array	
		verticies[filledSpace] = vertex;
		filledSpace++;
	}
	
	/**
	 * Return the Vertex at the specified vertexIndex
	 * returns null if the vertexIndex is out of bounds for the Array
	 * @param vertexIndex
	 * @return
	 */
	public Vertex getVertex(int vertexIndex) {
		//Check If the vertexIndex is outside of the length of the verticies array
		if(vertexIndex > verticies.length) {
			return null;
		}
		//Return the Vertex at the Index
		return verticies[vertexIndex];
	}
	
	/**
	 * Removes a Vertex from this faces verticies Array
	 * It uses a know vertex as input, if it is inside this face then remove it
	 * @param vertex which will be removed from this Face
	 */
	public void removeVertex(Vertex vertex) {
		//Iterate over each Vertex in the verticiesArray and check each
		for (int i = 0; i < verticies.length; i++) {
			//Check if the Vertex at i is not equal to the inputVertex
			if(verticies[i] != vertex) {
				//If it is not the inputVertex then continue with the next
				continue;
			}
			//If it is the same null the Vertex at i and return out of the methode
			filledSpace--;
			verticies[i] = null;
			return;
		}
	}
	
	/**
	 * Removes a Vertex of this faces verticies Array
	 * Is uses an int as input, if the int is over 3 then break out of the methode else null the Vertex
	 * @param vertexIndex the index of the Vertex which will be removed
	 */
	public void removeVertex(int vertexIndex) {
		//Checks if the vertexIndex is inside of bounds
		if(vertexIndex > 2) {
			//If it´s not then break out of the methode
			return;
		}

		//If it is in bounds then null it
		verticies[vertexIndex] = null;	
	}
	
	public Face rotate(Quaternion quaternion) {
		return rotate(quaternion, null);
	}
	
	public Face rotate(Quaternion inputQuaternion, Face outputFace) {
		if(outputFace == null) {
			outputFace = new Face(this);
		}
		outputFace.rotateLocal(inputQuaternion);
		return outputFace;
	}
	
	
	public void rotateLocal(Quaternion inputQuaternion) {
		for(Vertex vertex : this.getVerticies()) {
			inputQuaternion.rotateLocal(vertex.getPosition());
		}
	}
	
	public Vertex[] getVerticies() {
		return this.verticies;
	}
	
	public void paintPolygon(Graphics g) {	
		Polygon p = toPolygon();
		g.setColor(Color.black);
		g.setClip(p);
		g.fillPolygon(p);
	}
	

	
	/**
	 * @param g
	 * @param image
	 */
	public void paintImagePolygon(Graphics g,BufferedImage image) {
		Polygon p = toPolygon();
		float minPosX = 999999999;
		float minPosY = 999999999;
		
		float maxPosX = 0;
		float maxPosY = 0;
		
		float minSourceX = 999999999;
		float minSourceY = 999999999;
		
		float maxSourceX = 0;
		float maxSourceY = 0;
		
		for(Vertex v : verticies) {
			
			if(v.getPosition().getX() < minPosX) {
				minPosX = v.getPosition().getX();
			}
			if(v.getPosition().getY() < minPosY) {
				minPosY = v.getPosition().getY();
			}		
			
			if(v.getPosition().getX() > maxPosX) {
				maxPosX = v.getPosition().getX();
			}
			if(v.getPosition().getY() > maxPosY) {
				maxPosY = v.getPosition().getY();
			}
			
			if(v.getUvCoordinates().getX() < minSourceX) {
				minSourceX = v.getUvCoordinates().getX();
			}
			if(v.getUvCoordinates().getY() < minSourceY) {
				minSourceY = v.getUvCoordinates().getY();
			}
			
			if(v.getUvCoordinates().getX() > maxSourceX) {
				maxSourceX = v.getUvCoordinates().getX();
			}
			if(v.getUvCoordinates().getY() > maxSourceY) {
				maxSourceY = v.getUvCoordinates().getY();
			}
			
		}		
		g.setClip(p);
		g.drawImage(image, (int)minPosX, (int)minPosY,(int)maxPosX,(int)maxPosY,(int)(minSourceX*image.getWidth()),(int)(minSourceY*image.getHeight()),(int)(maxSourceX*image.getWidth()),(int)(maxSourceY*image.getHeight()),null);
		g.setClip(null);
	}
	
	public void drawUVMap(Graphics g,BufferedImage image) {
		Polygon newPolygon = new Polygon();
		for(Vertex v : verticies) {
			Vec2f tempVec = v.getUvCoordinates();		
			newPolygon.addPoint((int)(tempVec.getX()*image.getWidth()), (int)(tempVec.getY()*image.getHeight()));
		}
		g.setColor(Color.yellow);
		g.drawPolygon(newPolygon);
	}
	
	public void drawPolygon(Graphics g) {
		Polygon p = toPolygon();
		g.drawPolygon(p);
	}
	
	private Polygon toPolygon() {
		Polygon polygon = new Polygon();
		for(Vertex v : verticies) {
			polygon.addPoint((int)v.getPosition().getX(), (int)v.getPosition().getY());
		}
		return polygon;
	}

	public void move(Vec3f offset) {
		for(Vertex v : verticies) {
			v.getPosition().addLocal(offset);
		}
	}
	
	public void scale(float factor) {
		for(Vertex v : verticies) {
			v.getPosition().scaleLocal(factor);
		}			
	}
	
	public float getZ() {
		float sum =0;
		for(Vertex v : verticies) {
			sum += v.getPosition().getZ();
		}
		return sum;
		
		
	}
	
}
