package dazor.framework.buffer;

import dazor.framework.math.Vec2f;
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
	 * The conversion between the internally used 0f - 1f and 255 base color base the java framework uses
	 * It is used to convert the values between one and another and is here to provied clarity
	 */
	private static final int RGB_CONVERSION = 255;
	
	/**
	 * The amount of bits a number will be shifted to the side
	 * This is used in the conversion from 3 values r g b to one single integer which will represent the color in its entirety
	 */
	private static final int SINGLE_BIT_SHIFT = 8;
	
	/**
	 * Is the double of {@link #singleBitShift} and basically serves the purpose to shift it to the left 2 times
	 */
	private static final int DOUBLE_BIT_SHIFT = SINGLE_BIT_SHIFT * 2;
	
	
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
	 * Basics constructor which calls {@link #ColorBuffer(int, int)} with default parameters
	 */
	public ColorBuffer() {
		initValues(0,0);
	}
	
	/**
	 * Creates an array with the given inputs as the hyothetical width and length of the array
	 * It will use these values to calculate how big the buffer should be so that we can store all the values we need
	 * It uses a 1 dimensional array to try to squeeze more efficiency out of the already slow java framework
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
		//Checks if the coordinate is outside of colorBuffer area
		if(dataPos+2 > data.length || dataPos < 0) return;
		//Sets the individual rgb values to the corresponding place inside the array
		data[dataPos]   = r;
		data[dataPos+1] = g;
		data[dataPos+2] = b;
	}
	
	/**
	 * Sets the rgb values at screenposition x,y inside {@link #data} to the array큦 values given as parameter
	 * @param x horizontal position on screen
	 * @param y vertical position on screen
	 * @param col float array which has 3 values
	 */
	public void setColor(int x, int y, float[] col) {
		setColor(x,y,col[0],col[1],col[2]);
	}
	
	/**
	 * Sets the rgb values at screenposition x,y inside {@link #data} to the Vec3f큦 values given as parameter
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
		return x*3+y*width*3;
	}
	
	/**
	 * Return the Color values of the Pixel with the given coordinates
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @return a float array which contains the rgb values of the pixel
	 */
	public float[] getColor(int x, int y) {
		
		//Gets the current Postion in the Data Array
		int currentDataPosition = getDataPos(x,y);
		
		//If the currentDataPosition is either smaller or bigger than the size of the array return an array with three 0 values
		if(currentDataPosition < 0 || currentDataPosition+2 > data.length) {
			return new float[] {0,0,0};
		}
		
		//Create a new float array of size = 3
		float[] col = new float[3];
		
		//For each possible space in the array loop over the data array
		for(int i=0; i!=3; i++) {
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
	
	/**
	 * Small convenience function which allows a vector to be used instead of two int큦 as position
	 * @param fragCoord the screen pos
	 * @return a Vec3f which contains the rgb values of the pixel
	 */
	public Vec3f getColorVector(Vec2f fragCoord) {
		return getColorVector((int)fragCoord.getX(),(int)fragCoord.getY());
	}
	
	/**
	 * Returns the entire colorBuffer array without any conversion taking place
	 * @return all the {@link #data} this Buffer contains
	 */
	public float[] getAll() {
		return data;
	}
	
	/**
	 * Returns the entire colorBuffer array with conversion to the java intrgb system
	 * @return {@link #data} converted to an array of int rgb큦 for each pixel
	 */
	public int[] getAllIntRGB() {
		//creates an array of integers which will store the int rgb큦
		int[] colArray = new int[width*height];
		//The current positions inside the color array
		int colPos = 0;

		//Loops over every single pixel
		for(int i=0; i!= height; i++) {
			for(int j=0; j!=width; j++) {
				
				//fetches the intrgb of the current pixel position and add it to the Array
				colArray[colPos] = getIntRGB(i,j);
				//Increase the current position inside the intrgb color array
				colPos++;
			}
		}		
		//return the color array
		return colArray;
	}
	
	/**
	 * Returns the int RGB and an given x and y coordiante on the screen
	 * @param x position on the screen
	 * @param y position on the screen
	 * @return the Color in the IntRGB format
	 */
	public int getIntRGB(int x, int y) {
		//fetches the position inside the array
		int dataPos = getDataPos(x, y);
		//If the dataPos is outside of the data array return 0 as color
		if(dataPos+2 > data.length || dataPos < 0) return 0;
		//return the color from the position
		return getIntFromDataPos(dataPos);
	}
	
	/**
	 * Returns an color from a given position inside the array
	 * @param arrayPosition
	 * @return
	 */
	private int getIntFromDataPos(int arrayPosition) {
		//Uses the position to get the rest of the rgb coordiantes simply adding 1 
		return getIntFromColor(data[arrayPosition],data[arrayPosition+1],data[arrayPosition+2]);
	}
	
	/**
	 * Determins the IntRGB value from 3 sperate red, green and blue values
	 * @param red position of the red color value
	 * @param green position of the green color value
	 * @param blue position the blue color value
	 * @return the color in an Intrgb format 
	 */
	public int getIntFromColor(float red, float green, float blue){
		//Set the Rgb equal to red
		int rgb = (int) (red * RGB_CONVERSION);
		//Push the current rgb 8 bits to the left and then add the green value to the beginning of the number
		//This basically puts the blue value to the end of the number
		rgb = (rgb << SINGLE_BIT_SHIFT) + (int) (green * RGB_CONVERSION);
		//Push the current rgb bits another 8 bits to the left and add the blue value to the number 
		rgb = (rgb << SINGLE_BIT_SHIFT) + (int) (blue * RGB_CONVERSION);
		//Return the rgb
		return rgb;
	}
	
	/**
	 * Determins the r g b values from an integer 
	 * @param rgbInt a color in the IntRGB format containing the 3 color values
	 * @return an array of 3 floats between 0f - 1f which hold the color
	 */
	public float[] getColorFromInt(int rgbInt) {
		//Pushes the rgbInt 16 bits to the right so red is the furthermost byte then turn off all other bytes to get the value for red
		float red = (rgbInt >> DOUBLE_BIT_SHIFT) & 0xFF;
		//Pushes the rgbInt 8 bits to the right so green is the furthermost byte then turn off all other bytes to get the number value for gren
		float green = (rgbInt >> SINGLE_BIT_SHIFT) & 0xFF;
		//Blue is already the byte furthermost right so we just have to turn all other off
		float blue = rgbInt & 0xFF;
		
		//Convert the values to the used 0 to 1 system
		red /= RGB_CONVERSION;
		green /= RGB_CONVERSION;
		blue /= RGB_CONVERSION;
		
		//Return an array of all the values
		return new float[] {red,green,blue};
	}
	
}
