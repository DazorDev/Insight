package dazor.api;

import dazor.framework.buffer.ColorBuffer;
import dazor.framework.math.Mat2f;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;

/**
 * 
 * @author Daniel Banic
 *
 */
public interface IShader {
	
	//The first Part of this interface consist of all the functions a Shader should / could use to process data
	
	/**
	 * Simple debug function which is called before the actual rendering
	 */
	public void preDraw();
	
	/**
	 * Simple debug function which is called after the actual rendering
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
	 * @param firstVec
	 * @param i
	 * @return
	 */
	default Vec2f add(Vec2f firstVec, float i) {
		return firstVec.add(new Vec2f(i,i));
	}
	
	
	
	/**
	 * 
	 * @param inputVec
	 * @param value
	 * @param outputVec
	 * @return
	 */
	default Vec3f add(Vec3f inputVec, float value, Vec3f outputVec) {
		if(inputVec == null) {
			inputVec = new Vec3f();
		}
		if(outputVec == null) {
			outputVec = new Vec3f(value,value,value);
		}	  
		addLocal(outputVec, inputVec);  
		return outputVec;
	}
	
	/**
	 * 
	 * @param firstVec
	 * @param secondVec
	 * @return
	 */
	default Vec3f add(Vec3f firstVec, Vec3f secondVec) {
		return add(firstVec, secondVec, null);
	}
	
	/**
	 * 
	 * @param firstVec
	 * @param secondVec
	 * @return
	 */
	default Vec3f add(Vec3f inputVec, float value) {
		return inputVec.add(value,value,value);
	}
	
	/**
	 * 
	 * @param firstVec
	 * @param value
	 * @return
	 */
	default void addLocal(Vec3f inputVec, float value) {
		inputVec.addLocal(value,value,value);
	}
	

	default void addLocal(Vec3f inputVec, float i, float j, float k) {
		inputVec.addLocal(i, j, k);
	}
	
	/**
	 * 
	 * @param firstVec
	 * @param secondVec
	 * @param outputVec
	 * @return
	 */
	default Vec3f add(Vec3f firstVec, Vec3f secondVec, Vec3f outputVec) {
		if(firstVec == null) {
			firstVec = new Vec3f();
		}
		  
		if(secondVec == null) {
			secondVec = new Vec3f();
		}
		if(outputVec == null) {
			outputVec = new Vec3f(firstVec);
		}
		addLocal(outputVec, secondVec); 
		return outputVec;
	}
	
	default Vec2f add(Vec2f multiply, Vec2f random2) {
		return multiply.add(random2);
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param secondVector
	 */
	default void addLocal(Vec3f inputVector, Vec3f secondVector) {
		inputVector.addLocal(secondVector);
	}
	
	/**
	 * 
	 * @param inputVec
	 * @param value
	 * @return
	 */
	default Vec3f subtract(Vec3f inputVec, float value) {
		return subtract(inputVec, value, null);
	}
	
	/**
	 * 
	 * @param inputVec
	 * @param value
	 * @param outputVec
	 * @return
	 */
	default Vec3f subtract(Vec3f inputVec, float value, Vec3f outputVec) {
		if(inputVec == null) {
			inputVec = new Vec3f();
		}
		if(outputVec == null) {
			outputVec = new Vec3f(value,value,value);
		}	  
		subtractLocal(outputVec, inputVec);    
		return outputVec;
	}
	
	/**
	 * 
	 * @param firstVec
	 * @param secondVec
	 * @return
	 */
	default Vec3f subtract(Vec3f firstVec, Vec3f secondVec) {
		return subtract(firstVec, secondVec, null);
	}
	
	default Vec2f subtract(Vec2f firstVec, Vec2f secondVec) {
		return firstVec.subtract(secondVec);
	}
	
	/**
	 * 
	 * @param firstVec
	 * @param secondVec
	 * @param outputVec
	 * @return
	 */
	default Vec3f subtract(Vec3f firstVec, Vec3f secondVec, Vec3f outputVec) {
		if(firstVec == null) {
			firstVec = new Vec3f();
		}
		  
		if(secondVec == null) {
			secondVec = new Vec3f();
		}

		outputVec = firstVec.subtract(secondVec);
		return outputVec;
	}
	
	/**
	 * 
	 * @param inputVector
	 * @param secondVector
	 */
	default void subtractLocal(Vec3f inputVector, Vec3f secondVector) {
		inputVector.subtractLocal(secondVector);
	}
	
	default Vec2f multiply(Mat2f inputMat, Vec2f inputVec) {
		return multiply(inputMat, inputVec, null);
	}
	
	default Vec2f multiply(Mat2f inputMat, Vec2f inputVec, Vec2f outputVec) {
		if(inputMat == null) {
			inputMat = new Mat2f();
		}
		
		if(inputVec == null) {
			inputVec = new Vec2f();
		}
	
		outputVec = inputMat.multiply(inputVec);
		return outputVec;
	}
	
	default Vec3f multiply(Vec3f firstVec, Vec3f secondVec) {
		return multiply(firstVec, secondVec, null);
	}
	
	default Vec3f multiply(Vec3f firstVec, Vec3f secondVec, Vec3f outputVec) {
		if(firstVec == null) {
			firstVec = new Vec3f();
		}
		
		if(secondVec == null) {
			secondVec = new Vec3f();
		}
	
		outputVec = firstVec.crossProduct(secondVec);
		return outputVec;
	}
	
	default Vec3f multiply(Vec3f inputVec, float value) {
		return multiply(inputVec, value, null);
	}
	
	default Vec3f multiply(Vec3f inputVec, float value, Vec3f outputVec) {
		if(inputVec == null) {
			inputVec = new Vec3f();
		}
		
		if(outputVec == null) {
			outputVec = new Vec3f(inputVec);
		}
		
		multiplyLocal(outputVec, value);
		return outputVec;
	}
	
	default void multiplyLocal(Vec3f inputVec, float value) {
		inputVec.scaleLocal(value);
	}

	default Vec2f multiply(Vec2f fragCoord, float i) {
		// TODO Auto-generated method stub
		
		return fragCoord.scale(i);
	}
	
	default float cos(float input) {
		return (float) Math.cos(input);
	}
	
	default Vec3f cos(Vec3f inputVec) {
		return cos(inputVec, null);
	}
	
	default Vec3f cos(Vec3f inputVec, Vec3f outputVec) {
		if(outputVec == null) {
			outputVec = new Vec3f();
		}
		
		outputVec.setX(Math.cos(inputVec.getX()));
		outputVec.setY(Math.cos(inputVec.getY()));
		outputVec.setZ(Math.cos(inputVec.getZ()));
		

		return outputVec;
	}
	
	default void cosLocal(Vec3f inputVec) {
		inputVec.setX(Math.cos(inputVec.getX()));
		inputVec.setY(Math.cos(inputVec.getY()));
		inputVec.setZ(Math.cos(inputVec.getZ()));
	}
	
	default float sin(float input) {
		return (float) Math.sin(input);
	}
	
	default Vec3f sin(Vec3f inputVec) {
		return sin(inputVec, null);
	}
	
	default Vec3f sin(Vec3f inputVec, Vec3f outputVec) {
		if(outputVec == null) {
			outputVec = new Vec3f();
		}
		
		outputVec.setX(Math.sin(inputVec.getX()));
		outputVec.setY(Math.sin(inputVec.getY()));
		outputVec.setZ(Math.sin(inputVec.getZ()));
		

		return outputVec;
	}
	
	default void sinLocal(Vec3f inputVec) {
		inputVec.setX(Math.sin(inputVec.getX()));
		inputVec.setY(Math.sin(inputVec.getY()));
		inputVec.setZ(Math.sin(inputVec.getZ()));
	}
	
	default float tan(float input) {
		return (float) Math.tan(input);
	}
	
	default Vec3f tan(Vec3f inputVec) {
		return tan(inputVec, null);
	}
	
	default Vec3f tan(Vec3f inputVec, Vec3f outputVec) {
		if(outputVec == null) {
			outputVec = new Vec3f();
		}
		
		outputVec.setX(Math.tan(inputVec.getX()));
		outputVec.setY(Math.tan(inputVec.getY()));
		outputVec.setZ(Math.tan(inputVec.getZ()));
		

		return outputVec;
	}
	
	default void tanLocal(Vec3f inputVec) {
		inputVec.setX(Math.tan(inputVec.getX()));
		inputVec.setY(Math.tan(inputVec.getY()));
		inputVec.setZ(Math.tan(inputVec.getZ()));
	}
	
	default float dot(Vec3f firstInputVec, Vec3f secondInputVec) {
		return firstInputVec.dotProduct(secondInputVec);
	}	
	
	default Vec3f cross(Vec3f firstInputVec, Vec3f secondInputVec) {
		return firstInputVec.crossProduct(secondInputVec);
	}	
	
	default float dot(Vec2f firstInputVec, Vec2f secondInputVec) {
		return firstInputVec.dotProduct(secondInputVec);
	}	
	
	default float dot(Mat2f firstInputMat, Mat2f secondInputMat) {
		return firstInputMat.dotProduct(secondInputMat);
	}	
	
	default float fract(float d) {
		return d - floor(d);
	}
	
	default float floor(float input) {
		return (float) Math.floor(input);
	}
	
	default float abs(float input) {
		return (float) Math.abs(input);
	}
	
	default float ceil(float input) {
		return (float) Math.ceil(input);
	}
	
	default float round(float input) {
		return (float) Math.round(input);
	}
	
	default float log(float input) {
		return (float) Math.log(input);
	}
	
}
