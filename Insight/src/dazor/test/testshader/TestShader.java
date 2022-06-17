package dazor.test.testshader;

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
		Vec3f output = new Vec3f(uv.getX(),uv.getY(),uv.getX());
		output = addColor(multiplyColor(cos(addColor(output, t).add(0,2,4)),.5f),.5f);
		return output;
	}
	
}
