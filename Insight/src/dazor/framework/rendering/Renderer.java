package dazor.framework.rendering;

import java.awt.Graphics;
import java.util.ArrayList;

import dazor.api.IRenderHandler;
import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;

public class Renderer implements IRenderHandler {

	ArrayList<Mesh> meshes = new ArrayList<>();
	ArrayList<Mesh> usedMeshes = new ArrayList<>();
	Camera camera;
	
	public Renderer() {
		
	}
	
	@Override
	public Camera getCamera() {
		// TODO Auto-generated method stub
		return this.camera;
	}
	
	@Override
	public void setCamera() {
		setCamera(null);
	}
	
	public void setCamera(Camera camera) {
		if(camera == null) {
			camera = new Camera();
		}
		this.camera = camera;
	}
	
	@Override
	public void addMesh(Mesh mesh) {
		meshes.add(mesh);
	}

	@Override
	public void removeMesh(Mesh mesh) {
		meshes.remove(mesh);
	}

	@Override
	public void removeMesh(int meshIndex) {
		meshes.remove(meshIndex);
	}

	@Override
	public Mesh getMesh(int meshIndex) {
		return meshes.get(meshIndex);
	}
	
	public void update() {		
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
		g.clearRect(0, 0, 1920, 1080);
		usedMeshes.forEach(mesh -> {
			mesh.getPolygons().forEach(polygon -> {
				polygon.paintImagePolygon(g,mesh.getTexture().getImage());
			});
		});
	}
}
