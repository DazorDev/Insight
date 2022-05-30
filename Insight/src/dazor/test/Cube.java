package dazor.test;

import java.awt.Color;
import java.util.ArrayList;

import dazor.framework.math.Quaternion;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;
import dazor.framework.models.Face;

public class Cube {

	static int i =0;
	
	ArrayList<Face> oldFaces = new ArrayList<>();
	
	public Cube() {
		this(Color.black, 0,0,0);
		i++;
	}
	
	public Cube(int x, int y, int z) {
		this(Color.black, x,y,z);
	}
	
	public Cube(Color color,int x, int y, int z) {
		Face p0 = new Face();
		p0.addVertex(new Vertex(new Vec3f(1,0,0)));
		p0.addVertex(new Vertex(new Vec3f(0,1,0)));
		p0.addVertex(new Vertex(new Vec3f(1,1,0)));
		oldFaces.add(p0);
		oldFaces.sort( (v1, v2) -> {
			return Float.compare(v1.getZ(),v2.getZ());
		});
		
	}
	
	public float getZ() {
		float sum = 0;
		for(Face p : oldFaces) {
			sum += p.getZ();
		}
		return sum;
	}
	
	public ArrayList<Face> getPolygons() {
		return this.oldFaces;
	}
	
	public void setPolygons(ArrayList<Face> oldFaces) {
		this.oldFaces = oldFaces;
	}
	
	public Cube rotate(Quaternion q,Vec3f offset, float scale) {
		Cube c = new Cube();
		ArrayList<Face> newPolys = new ArrayList<>();
		this.oldFaces.forEach( polygon -> {
			newPolys.add(polygon.rotate(q));
		});
		c.setPolygons(newPolys);
		return c;
	}
	
}
