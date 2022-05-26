package dazor.framework;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dazor.api.IRenderHandler;
import dazor.api.Insight;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;
import dazor.framework.rendering.Renderer;
import dazor.framework.util.JFrameHelper;

public class Engine implements Insight {

	JFrame frame;
	JPanel panel;
	IRenderHandler renderHandler;
	
	ArrayList<Camera> cameras = new ArrayList<>();
	Camera activeCamera;
	
	public Engine() {
	}
	
	private Engine(Insight insight) {
		this.frame = insight.getWindow();
		this.renderHandler = insight.getRenderHandler();
	}
	
	@Override
	public Insight create(Insight insight) {
		return new Engine(this);
	}

	@Override
	public void createWindow() {
		createWindow(JFrameHelper.getScreenSize());
	}

	@Override
	public void createWindow(int width, int height) {
		createWindow(new Dimension(width,height));
	}

	@Override
	public void createWindow(Dimension dimension) {
		createWindow("Insight", dimension);
	}

	@Override
	public void createWindow(String title, Dimension dimension) {
		if(this.frame != null) {
			return;
		}
		frame = new JFrame(title);
		frame.setIgnoreRepaint(true);
		frame.setSize(dimension);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}
	
	@Override
	public void toggleWindow() {
		frame.setVisible(!frame.isVisible());
	}

	@Override
	public void setWindowState(boolean state) {
		frame.setVisible(state);
	}
	
	public JFrame getWindow() {
		return this.frame;
	}

	@Override
	public void addMesh(Mesh mesh) {
		this.renderHandler.addMesh(mesh);
	}

	@Override
	public void removeMesh(Mesh mesh) {
		this.renderHandler.removeMesh(mesh);
	}

	@Override
	public void removeMesh(int meshIndex) {
		this.renderHandler.removeMesh(meshIndex);
	}

	@Override
	public Mesh getMesh(int meshIndex) {
		return this.renderHandler.getMesh(meshIndex);
	}

	@Override
	public void addCamera(Camera camera) {
		if(cameras.isEmpty()) {
			activeCamera = camera;
		}
		
		if(renderHandler != null) {
			renderHandler.setCamera(activeCamera);
		}
		
		cameras.add(camera);
	}

	@Override
	public void removeCamera(int cameraIndex) {
		if(activeCamera == cameras.get(cameraIndex)) {
			return;
		}
		cameras.remove(cameraIndex);
	}

	@Override
	public Camera getCamera(int cameraIndex) {
		return cameras.get(cameraIndex);
	}

	@Override
	public Camera getActiveCamera() {
		return activeCamera;
	}

	@Override
	public void setActiveCamera(Camera camera) {
		addCamera(camera);
		this.activeCamera = camera;
	}

	@Override
	public void setActiveCamera(int cameraIndex) {
		this.activeCamera = cameras.get(cameraIndex);
	}
	
	@Override
	public void setRenderHandler(IRenderHandler handler) {
		if(handler == null) {
			handler = new Renderer();
		}
		
		this.renderHandler = handler;
		
		if(renderHandler.getCamera() == null && this.activeCamera != null) {
			renderHandler.setCamera(activeCamera); 
		};
		
	}

	@Override
	public IRenderHandler getRenderHandler() {
		return this.renderHandler;
	}


}
