package dazor.test.testshader;

import java.awt.image.BufferedImage;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.loading.Loader;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class MappingShader implements IShader {
	
	BufferedImage image = Loader.loadImage("./res/MissingTextureMeme.jpg");
	
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
		Vec2f uv = fragCoord.scale(1f/c.width, 1f/c.height);
		uv.scaleLocal(image.getWidth(),image.getHeight());
		int intRGB = image.getRGB((int) uv.getX()%image.getWidth(), (int) uv.getY()%image.getHeight());
		Vec3f output = new Vec3f(c.getColorFromInt(intRGB));
		return output;
	}

	@Override
	public void processGemotry() {
		// TODO Auto-generated method stub
	}

}
