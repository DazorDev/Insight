package dazor.framework.rendering.threads;

import java.util.ArrayList;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;

public class ShaderCalculator {
	
	Thread shaderThread;
	private int height;
	private int width;
	private ArrayList<IShader> shader;
	private ColorBuffer colorBuffer;
	private float time;
	
	public ShaderCalculator() {
		shaderThread = new Thread(new CalculatorRunnable());
	}
	
	public void start() {
		shaderThread.start();
	}
	
	private class CalculatorRunnable implements Runnable {

		@Override
		public void run() {
			while(true) {
				//Loop over every position of the window
				for(int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
					for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
						//Create a temporary Vector for the coordiante which will be used inside the shader
						Vec2f tempCoord = new Vec2f(xCoordinate,yCoordinate);
						//Loop over every shader 
						for(IShader shader : shader) {				
							//Set the resulting color of the shader operations at the x and y position inside the colorBuffer
							Vec3f col = shader.processPixel(tempCoord, colorBuffer, time);
							colorBuffer.setColor(xCoordinate, yCoordinate, col);	
						}
					}
				}	
			}
		}	
	}

}
