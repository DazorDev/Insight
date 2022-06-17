package dazor.api;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import dazor.framework.Engine;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;

/**
 * 
 * @author Daniel Banic
 *
 */
public interface Insight {

	/**
	 * Creates a instance of the Insight Engine
	 * @return
	 */
	public static Insight create() {
		try {
			return (Insight) Class.forName(Engine.class.getName()).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Copies a instance of the Insight Engine
	 * @param insight
	 * @return
	 */
	public Insight create(Insight insight);
	
	/**
	 * creates a Window<br>
	 * will call {@link #createWindow(Dimension)} or {@link #createWindow(int, int)} to set default Size
	 */
	public void createWindow();
	
	/**
	 * creates a Window with defined width and height
	 * @param width
	 * @param height
	 */
	public void createWindow(int width, int height);
	
	/**
	 * creates a Window with defined width and height
	 * @param width
	 * @param height
	 */
	public void createWindow(Dimension dimension);
	
	 /** creates a Window with defined width and height
	 * @param width
	 * @param height
	 */
	public void createWindow(String title, Dimension dimension);	
	
	/**
	 * toggles the state of the window to
	 */
	public void toggleWindow();
	
	/**
	 * Sets the Window´s current state
	 * @param state
	 */
	public void setWindowState(boolean state);
	
	/**
	 * 
	 * @return
	 */
	public JFrame getWindow();
	
	public void render();
	
	public boolean isRunning();
	
	/**
	 * Adds a Mesh to the Internal RenderHandler 
	 * @param mesh
	 */
	public void addMesh(Mesh mesh);
	
	/**
	 * Removes a Mesh from the RenderHandler
	 * @param mesh
	 */
	public void removeMesh(Mesh mesh);
	
	/**
	 * 
	 * @param meshIndex
	 */
	public void removeMesh(int meshIndex);
	
	/**
	 * 
	 * @param meshIndex
	 * @return
	 */
	public Mesh getMesh(int meshIndex);	
	
	/**
	 * 
	 * @param camera
	 */
	public void addCamera(Camera camera);
	
	/**
	 * 
	 * @param cameraIndex
	 */
	public void removeCamera(int cameraIndex);
	
	/**
	 * 
	 * @param cameraIndex
	 * @return
	 */
	public Camera getCamera(int cameraIndex);
	
	/**
	 * 
	 * @return
	 */
	public Camera getActiveCamera();
	
	/**
	 * Adds a Camera to the Engine and after that sets this Camera as the active Camera for the Engine
	 * @param camera
	 */
	public void setActiveCamera(Camera camera);
	
	/**
	 * 
	 * @param cameraIndex
	 */
	public void setActiveCamera(int cameraIndex);

	public void addShader(IShader shader);
	
	public IShader getShader(int shaderIndex);

	public int getShaderAmount();
	
	public default IShader[] getAllShader() {
		int shaderAmount = getShaderAmount();
		IShader[] shader = new IShader[shaderAmount];
		for(int i=0;i!= shaderAmount;i++) {
			shader[i] = getShader(i);
		}
		return shader;
	}
}
