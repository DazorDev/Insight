package dazor.test.testshader;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class OnlyGreenChannelShader implements IShader {

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
		Vec3f tempCol = c.getColorVector(fragCoord);
		tempCol.setX(0);
		tempCol.setZ(0);
		return tempCol;
	}

	@Override
	public void processGemotry() {
		// TODO Auto-generated method stub
		
	}

}
