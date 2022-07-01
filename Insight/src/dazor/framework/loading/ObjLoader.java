package dazor.framework.loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dazor.framework.math.Vec2f;
import dazor.framework.math.Vec3f;
import dazor.framework.math.Vertex;
import dazor.framework.models.Face;
import dazor.framework.models.Mesh;

public class ObjLoader {

	public static Mesh load(String fileUrl) {
		Mesh loadedMesh = new Mesh();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(fileUrl))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
//		    	System.out.println(line);
		    	String[] tokens = line.split(" ");
		    	handleToken(tokens,loadedMesh);
		    }
//		    System.out.println(loadedMesh.getPolygons());
		    return loadedMesh;	    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Mesh load(File file) {

		Mesh mesh = new Mesh();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			readFile(reader, mesh);	
		    return mesh;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void readFile(BufferedReader reader, Mesh loadedMesh) throws IOException {
	    String line;
	    while ((line = reader.readLine()) != null) {
	    	String[] tokens = line.split(" ");
	    	handleToken(tokens,loadedMesh);
	    }
	}
	
	private static void handleToken(String[] tokens, Mesh loadedMesh) {
		if(tokens[0].equals("v")) {
			loadedMesh.addVertexPoint(new Vec3f(Float.valueOf(tokens[1]),Float.valueOf(tokens[2]),Float.valueOf(tokens[3])));
			return;
		}
		
		if(tokens[0].equals("vt")) {
			loadedMesh.addUvPoint(new Vec2f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2])));
			return;
		}
		
		if(tokens[0].equals("vn")) {
			loadedMesh.addNormalPoints(new Vec3f(Float.valueOf(tokens[1]),Float.valueOf(tokens[2]),Float.valueOf(tokens[3])));
			return;
		}
		
		if(tokens[0].equals("f")) {
			if(tokens.length-1 != 3) {
				return;
			}
			
			if(tokens[1].contains("//")) {
				String[] positions = tokens[1].split("//");
				int temp1 = Integer.valueOf(positions[0])-1;
				int tempNormal1 = Integer.valueOf(positions[1])-1;
				Vertex v1 = new Vertex(loadedMesh.getVertexPoint(temp1), null, loadedMesh.getNormalPoints(tempNormal1));
				positions = tokens[2].split("//");
				int temp2 = Integer.valueOf(positions[0])-1;
				int tempNormal2 = Integer.valueOf(positions[1])-1;
				Vertex v2 = new Vertex(loadedMesh.getVertexPoint(temp2), null, loadedMesh.getNormalPoints(tempNormal2));
				positions = tokens[3].split("//");
				int temp3 = Integer.valueOf(positions[0])-1;
				int tempNormal3 = Integer.valueOf(positions[1])-1;
				Vertex v3 = new Vertex(loadedMesh.getVertexPoint(temp3), null, loadedMesh.getNormalPoints(tempNormal3));
				Face bs = new Face(v1,v2,v3);
				loadedMesh.addPolygon(bs);
				return;
			}
			
			if(tokens[1].contains("/")) {
				String[] positions = tokens[1].split("/");
				if(positions.length == 3) {
					int temp1 = Integer.valueOf(positions[0])-1;
					int tempUv1 = Integer.valueOf(positions[1])-1; 
					int tempNormal1 = Integer.valueOf(positions[2])-1;
					Vertex v1 = new Vertex(loadedMesh.getVertexPoint(temp1), loadedMesh.getUvPoint(tempUv1), loadedMesh.getNormalPoints(tempNormal1));
					positions = tokens[2].split("/");
					int temp2 = Integer.valueOf(positions[0])-1;
					int tempUv2 = Integer.valueOf(positions[1])-1;
					int tempNormal2 = Integer.valueOf(positions[2])-1;
					Vertex v2 = new Vertex(loadedMesh.getVertexPoint(temp2), loadedMesh.getUvPoint(tempUv2), loadedMesh.getNormalPoints(tempNormal2));
					positions = tokens[3].split("/");
					int temp3 = Integer.valueOf(positions[0])-1;
					int tempUv3 = Integer.valueOf(positions[1])-1;
					int tempNormal3 = Integer.valueOf(positions[2])-1;
					Vertex v3 = new Vertex(loadedMesh.getVertexPoint(temp3), loadedMesh.getUvPoint(tempUv3), loadedMesh.getNormalPoints(tempNormal3));
					Face bs = new Face(v1,v2,v3);
					loadedMesh.addPolygon(bs);
					return;
				}
				
				int temp1 = Integer.valueOf(positions[0])-1;
				int tempUv1 = Integer.valueOf(positions[1])-1; 
				Vertex v1 = new Vertex(loadedMesh.getVertexPoint(temp1), loadedMesh.getUvPoint(tempUv1), null);
				positions = tokens[2].split("/");
				int temp2 = Integer.valueOf(positions[0])-1;
				int tempUv2 = Integer.valueOf(positions[1])-1;
				Vertex v2 = new Vertex(loadedMesh.getVertexPoint(temp2), loadedMesh.getUvPoint(tempUv2), null);
				positions = tokens[3].split("/");
				int temp3 = Integer.valueOf(positions[0])-1;
				int tempUv3 = Integer.valueOf(positions[1])-1;
				Vertex v3 = new Vertex(loadedMesh.getVertexPoint(temp3), loadedMesh.getUvPoint(tempUv3), null);
				Face bs = new Face(v1,v2,v3);
				loadedMesh.addPolygon(bs);
				return;
				
			}
			
			int temp1 = Integer.valueOf(tokens[1])-1;
			Vertex v1 = new Vertex(loadedMesh.getVertexPoint(temp1), null, null);
			int temp2 = Integer.valueOf(tokens[2])-1;
			Vertex v2 = new Vertex(loadedMesh.getVertexPoint(temp2), null, null);
			int temp3 = Integer.valueOf(tokens[3])-1;
			Vertex v3 = new Vertex(loadedMesh.getVertexPoint(temp3), null, null);
			Face bs = new Face(v1,v2,v3);
			loadedMesh.addPolygon(bs);
		}
	}	
}
