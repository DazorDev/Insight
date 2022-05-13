package dazor.framework;

import java.awt.Dimension;

import javax.swing.JFrame;

import dazor.api.IRenderHandler;
import dazor.api.Insight;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;
import dazor.framework.util.JFrameHelper;

public class Engine implements Insight {

	JFrame frame;
	IRenderHandler renderHandler;
	
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

	}

	@Override
	public Mesh getMesh(int meshIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCamera(Camera camera) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCamera(int cameraIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Camera getCamera(int cameraIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRenderHandler(IRenderHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IRenderHandler getRenderHandler() {
		return this.renderHandler;
	}

}
