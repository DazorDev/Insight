package dazor.api;

import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public interface IShader {
	
	  public void preDraw();

	  public void postDraw();
	    
	  public Vertex processVertex(Vertex vertex);
	    
	  public default Vec3f processPixel(int x, int y, ColorBuffer c, float t) {
		  return processPixel(new Vec2f(x,y),c,t);
	  }

	  public Vec3f processPixel(Vec2f fragCoord, ColorBuffer c, float t);
	  
	  public void processGemotry();
	  
}
