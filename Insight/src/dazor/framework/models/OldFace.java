package dazor.framework.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import dazor.framework.math.Quaternion;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;

@Deprecated
public class OldFace {

	ArrayList<Vec3f> verticies = new ArrayList<>();

	ArrayList<Vec2f> textureCoordiants = new ArrayList<>();
	
	Color color;
	
	public OldFace(Vec3f...vec3fs) {
		for(Vec3f v : vec3fs) {
			this.verticies.add(v);
		}
	}
	
	public void addVertex(Vec3f inputVertex) {
		this.verticies.add(inputVertex);
	}
	
	public OldFace(Color color, Vec3f...vec3fs) {
		for(Vec3f v : vec3fs) {
			this.verticies.add(v);
		}
		this.color = color;
	}
	

	public ArrayList<Vec3f> getVerticies() {
		return this.verticies;
	}
	
	public OldFace rotate(Quaternion q) {
		ArrayList<Vec3f> tempPolygon = new ArrayList<>();
		for(Vec3f v : verticies) {
			tempPolygon.add(q.rotate(v));
		}
		return new OldFace(tempPolygon.toArray(new Vec3f[verticies.size()]));
	}
	
	public OldFace rotate(Quaternion q, Vec3f offset, float scale) {
		ArrayList<Vec3f> tempPolygon = new ArrayList<>();
		for(Vec3f v : verticies) {
			tempPolygon.add(q.rotate(v).scale(scale).add(offset));
		}
		return new OldFace(this.color, tempPolygon.toArray(new Vec3f[verticies.size()]));
	}
	
	public void paintPolygon(Graphics g) {	
		java.awt.Polygon p = new java.awt.Polygon();
		verticies.forEach(v -> p.addPoint((int)v.getX(), (int)v.getY()));
		g.setColor(Color.black);
		if(this.color != null) {
			g.setColor(color);
			g.fillPolygon(p);
		}

	}
	
	public void drawPolygon(Graphics g) {
		Polygon p = new Polygon();
		verticies.forEach(v -> p.addPoint((int)v.getX()+5, (int)v.getY()+5));
		g.drawPolygon(p);
	}
	
	public float getZ() {
		float sum = 0;
		for(Vec3f v : verticies) {
			sum += v.getZ();
		}
		return sum;
	}
	
	public float getZ2() {
		float highestZ = 0;
		for(Vec3f v : verticies) {
			if(highestZ >= v.getZ()) {
				continue;
			}
			highestZ = v.getZ();
		}
		return highestZ;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
