package dazor.api;

import dazor.framework.models.Mesh;

public interface IRenderHandler {

	public void addMesh(Mesh mesh);
	
	public void removeMesh(Mesh mesh);
	
	public void removeMesh(int meshIndex);
	
	public Mesh getMesh(int meshIndex);
	
}
