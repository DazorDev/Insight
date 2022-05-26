package dazor.test;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class TestShader implements IShader {

	@Override
	public void preDraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postDraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vertex processVertex(Vertex vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processGemotry() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vec3f processPixel(Vec2f fragCoord, ColorBuffer c, float t) {
//		Vec2f uv = fragCoord.scale(1f/c.width, 1f/c.height);
		Vec2f uv = fragCoord.subtract(new Vec2f(c.width*.5f,c.height*.5f)).scale(1f/c.height);
		Vec3f tempVec = new Vec3f(uv.getX(),uv.getY(),uv.getX());
		tempVec.setX((0.5+0.5f*Math.cos(-t+tempVec.getX()+0))*255f);
		tempVec.setY((0.5+0.5f*Math.cos(-t+tempVec.getY()+2))*255f);
		tempVec.setZ((0.5+0.5f*Math.cos(-t+tempVec.getZ()+4))*255f);
		return tempVec;
	}
	
}
