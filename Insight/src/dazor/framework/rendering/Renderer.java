package dazor.framework.rendering;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;
import dazor.framework.buffer.DepthBuffer;
import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;
import dazor.framework.util.TimeHandler;

/**
 * Renderer Class which will handle the use of Shaders and meshes
 * @author Daniel Banic
 */
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
		setDimensions(width, height);
	}
	
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		this.depthBuffer.initValues(width, height);
		this.colorBuffer.initValues(width, height);
		image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
	}
	
	public void setDimensions(Dimension dimension) {
		setDimensions(dimension.width,dimension.height);
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
		copyToScreen();
		g.drawImage(image, 0, 0, null);
	}
	
	public void addShader(IShader shader) {
		this.shader.add(shader);
	}

	/**
	 * Loops over every single position which will be affected by the fragment / pixel shader 
	 * for the moment it is every single position on the screen
	 */
	private void loopOverWindow() {
		//Loop over every position of the window
		for(int yCoordinate = 0; yCoordinate < height; yCoordinate++) {
			for(int xCoordinate = 0; xCoordinate < width; xCoordinate++) {
				//Create a temporary Vector for the coordiante which will be used inside the shader
				Vec2f tempCoord = new Vec2f(xCoordinate,yCoordinate);
				//Loop over every shader 
				for(IShader shader : shader) {				
					//Set the resulting color of the shader operations at the x and y position inside the colorBuffer
					Vec3f col = shader.processPixel(tempCoord, colorBuffer, time);
					colorBuffer.setColor(xCoordinate, yCoordinate, col);	
				}
			}
		}
	}

	private void copyToScreen() {
		//Loop over every position of the colorBuffer
		for(int y = 0; y!= colorBuffer.height; y++) {
			for(int x = 0; x!= colorBuffer.width; x++) {
				//If it is somehow out of bounds continue with the next position
				if(x >= image.getWidth() || y >= image.getHeight()) continue;			
				//Set the color to the image which will be rendered to the screen
				image.setRGB(x, y, colorBuffer.getIntRGB(x, y));
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
