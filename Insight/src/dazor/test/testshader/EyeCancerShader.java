package dazor.test.testshader;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class EyeCancerShader implements IShader {

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
		uv.subtractLocal(new Vec2f(0.5f,0.5f));
		uv.scaleLocal(120f);
		float col = (float) (uv.getX() * uv.getY() + t * 180);
		Vec3f tempVec = new Vec3f(val2col((int) (col)));
		return tempVec;
	}
	
	private Vec3f val2col(int val) {
	    while(val<0f) val += 360f;
	    int v = val % 360;
	    float c = (float) ((v % 60f) / 59.0f);
	    if(v >= 300f) return new Vec3f(1.f  , 0.f  , 1.f-c);
	    if(v >= 240f) return new Vec3f(   c, 0.f  , 1.f  );
	    if(v >= 180f) return new Vec3f(0.f  , 1.f-c, 1.f  );
	    if(v >= 120f) return new Vec3f(0.f  , 1.f  ,    c);
	    if(v >=  60f) return new Vec3f(1.f-c, 1.f  , 0.f  );
	                  return new Vec3f(1.f  ,    c, 0.f  );
	}

	@Override
	public void processGemotry() {
		// TODO Auto-generated method stub
		
	}

}
