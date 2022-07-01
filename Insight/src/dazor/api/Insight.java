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

	String engineReference = Engine.class.getName();
	
	/**
	 * Creates a instance of the Insight Engine
	 * @return
	 */
	public static Insight create() {
		try {
			//Create a Insight engine using reflection
			//It uses reflection to later support modding of some kind
			return (Insight) Class.forName(engineReference).getDeclaredConstructor().newInstance();
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
	 * Sets the Window큦 current state
	 * @param state 
	 */
	public void setWindowState(boolean state);
	
	/**
	 * Sets the window큦 current size using a Dimension
	 * @param dimension
	 */
	public void setWindowSize(Dimension dimension);
	
	/**
	 * Sets the window큦 current size using an integer for the width of the frame and an integer for the height of the frame
	 * @param widht 
	 * @param height
	 */
	public void setWindowSize(int width, int height);
	
	/**
	 * Returns the window the insight engine is using to disply it큦 contents
	 * @return the jframe the engine uses
	 */
	public JFrame getWindow();
	
	/**
	 * 
	 */
	public void render();
	
	/**
	 * 
	 * @return
	 */
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

	/**
	 * 
	 * @param shader
	 */
	public void addShader(IShader shader);
	
	/**
	 * Returns the shader at the index
	 * @param shaderIndex the index of a shader 
	 * @return either a Shader at the specified index or null if no shader is present
	 */
	public IShader getShader(int shaderIndex);

	/**
	 * 
	 * @return
	 */
	public int getShaderAmount();
	
	/**
	 * 
	 * @param listener
	 */
	public void registerListener(IListener listener);
	
	/**
	 * 
	 * @param listener
	 */
	public void removeListener(IListener listener);
	
	/**
	 * 
	 * @return
	 */
	public default IShader[] getAllShader() {
		int shaderAmount = getShaderAmount();
		IShader[] shader = new IShader[shaderAmount];
		for(int i=0;i!= shaderAmount;i++) {
			shader[i] = getShader(i);
		}
		return shader;
	}
}
