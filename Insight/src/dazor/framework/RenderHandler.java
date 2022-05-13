package dazor.framework;

import java.util.ArrayList;

import dazor.api.IRenderHandler;
import dazor.framework.models.Mesh;

public class RenderHandler implements IRenderHandler {

	ArrayList<Mesh> meshes = new ArrayList<>();
	
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
	
}
