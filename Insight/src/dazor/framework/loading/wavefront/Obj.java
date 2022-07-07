package dazor.framework.loading.wavefront;

import java.util.ArrayList;

import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;

public class Obj {

	/** Private constructor so that a Obj file can´t directly be created */
	private Obj() {
		vertexPoints = new ArrayList<>();
		textureCoordinates = new ArrayList<>();
		normalPoints = new ArrayList<>();
		
	}
	
	public static Obj create() {
		return null;
	}
	
	/** ArrayLists to store all the data contained inside a Wavefront file */
	
	/** A list of all vertexPoints of this Obj object */
	ArrayList<Vec3f> vertexPoints;
	
	/** A list of all the textureCoordinates of this Obj object */
	ArrayList<Vec2f> textureCoordinates;
	
	/** A list of all the textureCoordinates of this Obj object */
	ArrayList<Vec3f> normalPoints;
	
	/** 
	 * A list of all the faces of this Obj<br>
	 * it holds the information of which vertex is connected to which other vertex
	 */
	ArrayList<Vec3f> connectedVerticies;
	
}
