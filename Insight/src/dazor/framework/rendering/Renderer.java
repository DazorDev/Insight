package dazor.framework.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.buffer.DepthBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;
import dazor.framework.util.TimeHandler;

public class Renderer {

	ArrayList<Mesh> meshes = new ArrayList<>();
	ArrayList<Mesh> usedMeshes = new ArrayList<>();
	Camera camera;
	ArrayList<IShader> shader = new ArrayList<>();
	DepthBuffer depthBuffer = new DepthBuffer();
	ColorBuffer colorBuffer = new ColorBuffer();
	BufferedImage image;
	float time = 0;
	int width;
	int height;
	TimeHandler timeHandler = new TimeHandler();
	
	public Renderer() {
		this(0,0);
	}
	
	public Renderer(int width, int height) {
		this.width = width;
		this.height = height;
		this.depthBuffer.initValues(width, height);
		this.colorBuffer.initValues(width, height);
		image = new BufferedImage(width,height, BufferedImage.TYPE_INT_BGR);
	}
	
	
	public Camera getCamera() {
		return this.camera;
	}
	
	
	public void setCamera() {
		setCamera(null);
	}
	
	public void setCamera(Camera camera) {
		if(camera == null) {
			camera = new Camera();
		}
		this.camera = camera;
	}
	
	
	public void addMesh(Mesh mesh) {
		meshes.add(mesh);
	}

	
	public void removeMesh(Mesh mesh) {
		meshes.remove(mesh);
	}

	
	public void removeMesh(int meshIndex) {
		meshes.remove(meshIndex);
	}

	
	public Mesh getMesh(int meshIndex) {
		return meshes.get(meshIndex);
	}
	
	public void update() {
		timeHandler.calculateDeltaTime();
		time += timeHandler.getDeltaTimeInSeconds();
		if(camera == null) {
			return;
		}		
		usedMeshes.clear();
		meshes.forEach(mesh -> {
			this.usedMeshes.add(mesh.offset(camera.getTransform()));
		});
		
	}
	
	public void render(Graphics g) {
		update();
		usedMeshes.forEach(mesh -> {
			mesh.getPolygons().forEach(polygon -> {
				polygon.paintImagePolygon(g,mesh.getTexture().getImage());
			});
		});
		loopOverWindow();
		for(int y = 0; y!= colorBuffer.height; y++) {
			for(int x = 0; x!= colorBuffer.width; x++) {
				image.setRGB(x, y, colorBuffer.getIntRGB(x, y));
			}
		}      		
		g.drawImage(image, 0, 0, null);
	}
	
	public void addShader(IShader shader) {
		this.shader.add(shader);
	}

	private void loopOverWindow() {
		for(int yCoordinate = 0; yCoordinate != height; yCoordinate++) {
			for(int xCoordinate = 0; xCoordinate != width; xCoordinate++) {
				Vec2f tempCoord = new Vec2f(xCoordinate,yCoordinate);
				for(IShader shader : shader) {
					colorBuffer.setColor(xCoordinate, yCoordinate, shader.processPixel(tempCoord, colorBuffer, time));
				}
			}
		}
	}
	
	public int getShaderAmount() {
		return this.shader.size();
	}
	
	public IShader getShader(int shaderIndex) {
		return this.shader.get(shaderIndex);
	}
	
}
