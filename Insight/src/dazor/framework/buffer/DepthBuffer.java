package dazor.framework.buffer;

public class DepthBuffer {

	/**
	 * The horizontal length of the Buffer
	 */
	public int width;
	
	/**
	 * The vertical length of the Buffer
	 */
	public int height;
	
	/**
	 * The float array which will hold all the depth values
	 */
	float[] data;
	
	/**
	 * 
	 */
	public DepthBuffer() {
		initValues(0,0);
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public DepthBuffer(int width, int height) {
		initValues(width,height);
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void initValues(int width, int height) {
		this.width = width;
		this.height = height;
		data = new float[width*height];
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getDataPos(int x, int y) {
		return y*width+x;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public float getData(int x, int y) {
		return data[getDataPos(x,y)];
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setData(int x, int y, float z) {
		int posInData = getDataPos(x,y);
		float dataStored = data[posInData];
		if(z > dataStored) {
			return;
		}
		data[posInData] = z;
	}
	
}
