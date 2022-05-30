package dazor.framework.buffer;

import dazor.framework.math.Vec3f;

/**
 * This class is basically a Buffer for rgb values for the screen.
 * It is used to hold the Values produced by the Shaders 
 * After the shaders are done processing the data of this Colorbuffer will be painted on the screen
 * @author Daniel Banic
 *
 */
public class ColorBuffer {

	/**
	 * The width of the ColorBuffer
	 * This Value will be used to inizialze the size of the data array and to calculate the Position of a given Pixel inside {@link #data} 
	 */
	public int width;
	
	/**
	 * The height of the ColorBuffer
	 * This Value will be used to initialize the size of the data array
	 */
	public int height;
	
	/**
	 * The data this Buffer will hold
	 * It will hold rgb values for every pixel of width and height 
	 * This means the size of this Array is equal to 3*width*height
	 */
	float[] data;
	
	/**
	 * 
	 */
	public ColorBuffer() {
		initValues(0,0);
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public ColorBuffer(int width, int height) {
		initValues(width,height);
	}
	
	/**
	 * Initializies the values of the Colorbuffer
	 * The Values which will be initialized are {@link #width}, {@link #height} and {@link #data}
	 * @param width the width of the Colorbuffer
	 * @param height the height of the Colorbuffer
	 */
	public void initValues(int width, int height) {
		//Sets width and height the input Values
		this.width = width;
		this.height = height;
		
		//Calculates the Length of the data Array
		//This is done by calculating 3 * width * height 
		//This works because for each x coordinate we have a y coordinate which represent a Pixel
		//Then this has to be multiplied by 3 because each Pixel posses 3 different rgb values
		data = new float[3*width*height];
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param rgb
	 */
	public void setColor(int x, int y, int rgb) {
		
	}
	
	/**
	 * Sets the rgb values at screenposition x,y inside {@link #data} to the r g b values given as parameter
	 * @param x horizontal position on screen
	 * @param y vertical position on screen
	 * @param r red value which ranges from 0 - 1f
	 * @param g green value which ranges from 0 - 1f
	 * @param b green value which ranges from 0 - 1f
	 */
	public void setColor(int x, int y, float r, float g, float b) {
		//Get the Pixel coordinate
		int dataPos = getDataPos(x, y);
		
		//Sets the individual rgb values to the corresponding place inside the array
		data[dataPos]   = r*255f;
		data[dataPos+1] = g*255f;
		data[dataPos+2] = b*255f;
	}
	
	/**
	 * Sets the rgb values at screenposition x,y inside {@link #data} to the array´s values given as parameter
	 * @param x horizontal position on screen
	 * @param y vertical position on screen
	 * @param col float array which has 3 values
	 */
	public void setColor(int x, int y, float[] col) {
		setColor(x,y,col[0],col[1],col[2]);
	}
	
	/**
	 * Sets the rgb values at screenposition x,y inside {@link #data} to the Vec3f´s values given as parameter
	 * @param x horizontal position on screen
	 * @param y vertical position on screen
	 * @param col Vector which posses 3 values 
	 */
	public void setColor(int x, int y, Vec3f col) {
		setColor(x,y,col.getVector());
	}
	
	/**
	 * Return the current Position which pixel is targeted from x and y coordinates
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the Position of the Data 
	 */
	private int getDataPos(int x, int y) {
		//This calculates the current position in the data array, this multiplied x*3 + y*width*3
		//This works because we know that the r position of the x coordinate is equal to y*width*3 because it simply is the equation for three full lines
		//Then add the x coordinate to the data which is also multiplied by 3 to skip all other pixel data
		int currentDataPosition = x*3+y*width*3;
		return currentDataPosition;
	}
	
	/**
	 * Return the Color values of the Pixel with the given coordinates
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @return a float array which contains the rgb values of the pixel
	 */
	public float[] getColor(int x, int y) {
		
		//Create a new float array of size = 3
		float[] col = new float[3];
		
		//Gets the current Postion in the Data Array
		int currentDataPosition = getDataPos(x,y);
		
		//For each possible space in the array loop over the data array
		for(int i=0; i!=2; i++) {
			//Then lastly add the i value to lop over all the rgb values and set these in the col array
			col[i] = data[currentDataPosition+i];
		}
		
		//After that return the color
		return col;
	}

	/**
	 * Return the Color values of the Pixel with the given coordinates
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @return a Vec3f which contains the rgb values of the pixel
	 */
	public Vec3f getColorVector(int x, int y) {
		return new Vec3f(getColor(x,y));
	}
	
	public float[] getAll() {
		return data;
	}
	
	public int[] getAllIntRGB() {
		int[] colArray = new int[width*height];
		int colPos = 0;
		for(int i=0; i!= height; i++) {
			for(int j=0; j!=width; j++) {
				colArray[colPos] = getIntRGB(i,j);
				colPos++;
			}
		}
		return colArray;
	}
	
	public int getIntRGB(int x, int y) {
		int pos = getDataPos(x, y);
		return getIntFromDataPos(pos);
	}
	
	private int getIntFromDataPos(int pos) {
		return getIntFromColor((int)data[pos],(int)data[pos+1],(int)data[pos+2]);
	}
	
	public int getIntFromColor(int red, int green, int blue){
		int rgb = red;
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
		return rgb;
	}
	
	public float[] getColorFromInt(int rgbInt) {
		float red = (rgbInt >> 16) & 0xFF;
		float green = (rgbInt >> 8) & 0xFF;
		float blue = rgbInt & 0xFF;
		red /= 255f;
		green /= 255f;
		blue /= 255f;
		return new float[] {red,green,blue};
	}
	
}
