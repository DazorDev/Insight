package dazor.api;

import java.awt.Graphics;

import dazor.framework.models.Camera;
import dazor.framework.models.Mesh;

public interface IRenderHandler {

	public void addMesh(Mesh mesh);
	
	public void removeMesh(Mesh mesh);
	
	public void removeMesh(int meshIndex);
	
	public Mesh getMesh(int meshIndex);
	
	public Camera getCamera();

	public void setCamera();
	
	public void setCamera(Camera camera);

	public void update();
	
	public void render(Graphics g);
}
