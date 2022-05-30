package dazor.test;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class ye implements IShader {

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
	public Vec3f processPixel(Vec2f fragCoord, ColorBuffer c, float t) {
		// TODO Auto-generated method stub
		Vec3f output = new Vec3f(Math.cos(fragCoord.getX()+t),Math.cos(fragCoord.getY()+t),Math.cos(fragCoord.getX()+t));
		
		
		return output;
	}

	@Override
	public void processGemotry() {
		// TODO Auto-generated method stub
		
	}

}
