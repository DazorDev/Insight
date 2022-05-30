package dazor.test;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public class InterpolationTest {

	public static void main(String[] args) {
		
		Vec3f pos1 = new Vec3f(1,0,0);
		Vec2f uv1 = new Vec2f(0,0);
		Vec3f pos2 = new Vec3f(0,1,0);
		Vec2f uv2 = new Vec2f(1,0);
		Vec3f pos3 = new Vec3f(1,1,0);
		Vec2f uv3 = new Vec2f(1,1);
		
		Vertex v1 = new Vertex(pos1,uv1);
		Vertex v2 = new Vertex(pos2,uv2);
		Vertex v3 = new Vertex(pos3,uv3);
		
		IShader s = new InterpolationShader();
		
	}
	
	private static class InterpolationShader implements IShader {

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
			
			return null;
		}

		@Override
		public Vec3f processPixel(Vec2f fragCoord, ColorBuffer c, float t) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void processGemotry() {
			// TODO Auto-generated method stub
			
		}
		
		
		
	}
	
}
