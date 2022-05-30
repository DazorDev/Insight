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
		Vec2f uv = fragCoord.scale(1f/c.width, 1f/c.height);
//		Vec2f uv = fragCoord.subtract(new Vec2f(c.width*.5f,c.height*.5f)).scale(1f/c.height);
		Vec3f output = new Vec3f(uv.getX(),uv.getY(),uv.getX());
		output.setX((0.5+0.5f*Math.cos(t+output.getX()+0)));
		output.setY((0.5+0.5f*Math.cos(t+output.getY()+2)));
		output.setZ((0.5+0.5f*Math.cos(t+output.getZ()+4)));
		Vec3f tempVec = c.getColorVector((int)fragCoord.getX(), (int)fragCoord.getY());
		tempVec.scaleLocal(1f/255f);
		output.addLocal(tempVec);
		output.scaleLocal(1f/2f);
		return output;
	}
	
}
