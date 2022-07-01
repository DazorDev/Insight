package dazor.framework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import dazor.api.IListener;
import dazor.api.IShader;
import dazor.api.Insight;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;
import dazor.framework.rendering.Renderer;
import dazor.framework.util.JFrameHelper;
import dazor.framework.util.TimeHandler;

public class Engine implements Insight {

	JFrame frame;
	Renderer renderHandler;
	ArrayList<Camera> cameras = new ArrayList<>();
	Camera activeCamera;
	
	
	
	private boolean isRunning = false;
	
	private Runnable benchmark = new Runnable() {
		@Override
		public void run() {
			benchMarkRun();
		}
	};
	
	
	public Engine() {
		
	}
	
	private Engine(Insight insight) {
		this.frame = insight.getWindow();
		
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
		renderHandler = new Renderer(dimension.width,dimension.height);
	}
	
	@Override
	public void setWindowSize(Dimension dimension) {
		renderHandler.setDimensions(dimension);
	}	

	@Override
	public void setWindowSize(int width, int height) {
		renderHandler.setDimensions(width, height);
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
	public void addShader(IShader shader) {
		this.renderHandler.addShader(shader);
	}

	@Override
	public IShader getShader(int shaderIndex) {
		return renderHandler.getShader(shaderIndex);
	}

	@Override
	public int getShaderAmount() {
		return renderHandler.getShaderAmount();
	}
	
	@Override
	public void render() {
		Thread t = new Thread(benchmark);
		t.start();
	}
	
	private void benchMarkRun() {
		TimeHandler th = new TimeHandler();
		int tempCount = 0;
		frame.createBufferStrategy(2);
		Graphics g = frame.getGraphics();
		while(true) {
			th.calculateCallsPerSecond();
			if(th.getCallsPerSecond() != tempCount) {
				tempCount = th.getCallsPerSecond();
				frame.setTitle("Current FPS : "+tempCount);
			}
			renderHandler.render(g);
		}
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void registerListener(IListener listener) {
		frame.addKeyListener(listener);
		frame.addMouseListener(listener);
		frame.addComponentListener(listener);
	}

	@Override
	public void removeListener(IListener listener) {
		frame.removeKeyListener(listener);
		frame.removeMouseListener(listener);
		frame.removeComponentListener(listener);
	}

}
