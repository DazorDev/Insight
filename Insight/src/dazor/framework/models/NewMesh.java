package dazor.framework.models;

import java.util.ArrayList;

import dazor.framework.loading.wavefront.Obj;
import dazor.framework.math.Transform;

public class NewMesh {

	
	ArrayList<Face> faces;
	ArrayList<NewMesh> subMesh;
	Transform transform;

	private NewMesh() {}
	
	public static NewMesh create(Obj obj) {
		return null;
	}
}
