package dazor.test;

import java.awt.Color;
import java.util.ArrayList;

import dazor.framework.math.Quaternion;
import dazor.framework.math.Vec3f;
import dazor.framework.models.OldFace;

public class Cube {

	static int i =0;
	
	ArrayList<OldFace> oldFaces = new ArrayList<>();
	
	public Cube() {
		this(Color.black, 0,0,0);
		i++;
	}
	
	public Cube(int x, int y, int z) {
		this(Color.black, x,y,z);
	}
	
	public Cube(Color color,int x, int y, int z) {
//		Polygon p0 = new Polygon(color,  new Vec3f(0+x,0+y,0+z), new Vec3f(0+x,0+y,1+z), new Vec3f(0+x,1+y,0+z));
//		Polygon p1 = new Polygon(color,  new Vec3f(0+x,1+y,0+z), new Vec3f(0+x,1+y,1+z), new Vec3f(0+x,0+y,1+z));
//		Polygon p2 = new Polygon(color,  new Vec3f(1+x,1+y,1+z), new Vec3f(1+x,1+y,0+z), new Vec3f(1+x,0+y,1+z));
//		Polygon p3 = new Polygon(color,  new Vec3f(1+x,0+y,1+z), new Vec3f(1+x,0+y,0+z), new Vec3f(1+x,1+y,0+z));
//		Polygon p4 = new Polygon(color,  new Vec3f(0+x,0+y,0+z), new Vec3f(1+x,0+y,0+z), new Vec3f(0+x,1+y,0+z));
//		Polygon p5 = new Polygon(color,  new Vec3f(0+x,1+y,0+z), new Vec3f(1+x,0+y,0+z), new Vec3f(1+x,1+y,0+z));
//		Polygon p6 = new Polygon(color,  new Vec3f(1+x,1+y,1+z), new Vec3f(0+x,1+y,1+z), new Vec3f(1+x,0+y,1+z));
//		Polygon p7 = new Polygon(color,  new Vec3f(1+x,0+y,1+z), new Vec3f(0+x,1+y,1+z), new Vec3f(0+x,0+y,1+z));
//		Polygon p8 = new Polygon(color,  new Vec3f(0+x,1+y,0+z), new Vec3f(1+x,1+y,0+z), new Vec3f(1+x,1+y,1+z));
//		Polygon p9 = new Polygon(color,  new Vec3f(0+x,1+y,1+z), new Vec3f(0+x,1+y,0+z), new Vec3f(1+x,1+y,1+z));
//		Polygon p10 = new Polygon(color, new Vec3f(1+x,0+y,1+z), new Vec3f(0+x,0+y,1+z), new Vec3f(0+x,0+y,0+z));
//		Polygon p11 = new Polygon(color, new Vec3f(1+x,0+y,0+z), new Vec3f(1+x,0+y,1+z), new Vec3f(0+x,0+y,0+z));
	
		OldFace p0 = new OldFace(new Vec3f( 1.000000, -1.000000, -1.000000),
								 new Vec3f( 1.000000, -1.000000,  1.000000),
								 new Vec3f(-1.000000, -1.000000,  1.000000),
								 new Vec3f(-1.000000, -1.000000, -1.000000),
								 new Vec3f( 1.000000,  1.000000, -0.999999),
								 new Vec3f( 0.999999,  1.000000,  1.000001),
								 new Vec3f(-1.000000,  1.000000,  1.000000),
								 new Vec3f(-1.000000,  1.000000, -1.000000));
		
		oldFaces.add(p0);
//		polygons.add(p1);
//		polygons.add(p2);
//		polygons.add(p3);
//		polygons.add(p4);
//		polygons.add(p5);
//		polygons.add(p6);
//		polygons.add(p7);
//		polygons.add(p8);
//		polygons.add(p9);
//		polygons.add(p10);
//		polygons.add(p11);
		oldFaces.sort( (v1, v2) -> {
			return Float.compare(v1.getZ(),v2.getZ());
		});
		
	}
	
	public float getZ() {
		float sum = 0;
		for(OldFace p : oldFaces) {
			sum += p.getZ();
		}
		return sum;
	}
	
	public ArrayList<OldFace> getPolygons() {
		return this.oldFaces;
	}
	
	public void setPolygons(ArrayList<OldFace> oldFaces) {
		this.oldFaces = oldFaces;
	}
	
	public Cube rotate(Quaternion q,Vec3f offset, float scale) {
		Cube c = new Cube();
		ArrayList<OldFace> newPolys = new ArrayList<>();
		this.oldFaces.forEach( polygon -> {
			newPolys.add(polygon.rotate(q,offset,scale));
		});
		c.setPolygons(newPolys);
		return c;
	}
	
}
