package dazor.test.testshader;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

/**
 * Used for testing and debugging purposes to check if the color buffer had caused problems ( it did )
 * @author Daniel Banic
 */
public class UsingBufferShader implements IShader {

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
		return c.getColorVector(fragCoord);
	}

	@Override
	public void processGemotry() {
		// TODO Auto-generated method stub
		
	}

}
