package dazor.api;

import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

public interface IShader {
	
	//The first Part of this interface consist of all the functions a Shader should / could use to process data
	
	/**
	 * 
	 */
	public void preDraw();
	
	/**
	 * 
	 */
	public void postDraw();
	
	/**
	 * 
	 * @param vertex
	 * @return
	 */
	public Vertex processVertex(Vertex vertex);
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @param t
	 * @return
	 */
	public default Vec3f processPixel(int x, int y, ColorBuffer c, float t) {
		return processPixel(new Vec2f(x,y),c,t);
	}
	
	/**
	 * 
	 * @param fragCoord
	 * @param c
	 * @param t
	 * @return
	 */
	public Vec3f processPixel(Vec2f fragCoord, ColorBuffer c, float t);
	
	/**
	 * 
	 */
	public void processGemotry();
	
	
	
	//The second part of the interface consists of helper functions for easier processing of the data
	
	/**
	 * 
	 * @param inputRGB
	 * @param value
	 * @return
	 */
	default Vec3f addColor(Vec3f inputRGB, float value) {
		return addColor(inputRGB, value, null);
	}
	
	/**
	 * 
	 * @param inputRGB
	 * @param value
	 * @param outputRGB
	 * @return
	 */
	default Vec3f addColor(Vec3f inputRGB, float value, Vec3f outputRGB) {
		if(inputRGB == null) {
			inputRGB = new Vec3f();
		}
		if(outputRGB == null) {
			outputRGB = new Vec3f(value,value,value);
		}	  
		addColorLocal(outputRGB, inputRGB);  
		return outputRGB;
	}
	
	/**
	 * 
	 * @param firstRGB
	 * @param secondRGB
	 * @return
	 */
	default Vec3f addColor(Vec3f firstRGB, Vec3f secondRGB) {
		return addColor(firstRGB, secondRGB, null);
	}
	
	/**
	 * 
	 * @param firstRGB
	 * @param secondRGB
	 * @param outputRGB
	 * @return
	 */
	default Vec3f addColor(Vec3f firstRGB, Vec3f secondRGB, Vec3f outputRGB) {
		if(firstRGB == null) {
			firstRGB = new Vec3f();
		}
		  
		if(secondRGB == null) {
			secondRGB = new Vec3f();
		}
		if(outputRGB == null) {
			outputRGB = new Vec3f(firstRGB);
		}
		addColorLocal(outputRGB, secondRGB); 
		return outputRGB;
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param secondVector
	 */
	default void addColorLocal(Vec3f inputVector, Vec3f secondVector) {
		inputVector.addLocal(secondVector);
	}
	
	/**
	 * 
	 * @param inputRGB
	 * @param value
	 * @return
	 */
	default Vec3f subtractColor(Vec3f inputRGB, float value) {
		return subtractColor(inputRGB, value, null);
	}
	
	/**
	 * 
	 * @param inputRGB
	 * @param value
	 * @param outputRGB
	 * @return
	 */
	default Vec3f subtractColor(Vec3f inputRGB, float value, Vec3f outputRGB) {
		if(inputRGB == null) {
			inputRGB = new Vec3f();
		}
		if(outputRGB == null) {
			outputRGB = new Vec3f(value,value,value);
		}	  
		subtractColorLocal(outputRGB, inputRGB);    
		return outputRGB;
	}
	
	/**
	 * 
	 * @param firstRGB
	 * @param secondRGB
	 * @return
	 */
	default Vec3f subtractColor(Vec3f firstRGB, Vec3f secondRGB) {
		return subtractColor(firstRGB, secondRGB, null);
	}
	
	/**
	 * 
	 * @param firstRGB
	 * @param secondRGB
	 * @param outputRGB
	 * @return
	 */
	default Vec3f subtractColor(Vec3f firstRGB, Vec3f secondRGB, Vec3f outputRGB) {
		if(firstRGB == null) {
			firstRGB = new Vec3f();
		}
		  
		if(secondRGB == null) {
			secondRGB = new Vec3f();
		}

		outputRGB = firstRGB.subtract(secondRGB);
		return outputRGB;
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param secondVector
	 */
	default void subtractColorLocal(Vec3f inputVector, Vec3f secondVector) {
		inputVector.subtractLocal(secondVector);
	}
	
	default Vec3f multiplyColor(Vec3f firstRGB, Vec3f secondRGB) {
		return multiplyColor(firstRGB, secondRGB, null);
	}
	
	default Vec3f multiplyColor(Vec3f firstRGB, Vec3f secondRGB, Vec3f outputRGB) {
		if(firstRGB == null) {
			firstRGB = new Vec3f();
		}
		
		if(secondRGB == null) {
			secondRGB = new Vec3f();
		}
	
		outputRGB = firstRGB.crossProduct(secondRGB);
		return outputRGB;
	}
	
	default Vec3f multiplyColor(Vec3f inputRGB, float value) {
		return multiplyColor(inputRGB, value, null);
	}
	
	default Vec3f multiplyColor(Vec3f inputRGB, float value, Vec3f outputRGB) {
		if(inputRGB == null) {
			inputRGB = new Vec3f();
		}
		
		if(outputRGB == null) {
			outputRGB = new Vec3f(inputRGB);
		}
		
		multiplyColorLocal(outputRGB, value);
		return outputRGB;
	}
	
	default void multiplyColorLocal(Vec3f inputRGB, float value) {
		inputRGB.scaleLocal(value);
	}
}
