package dazor.framework.loading;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dazor.framework.models.Mesh;

public class Loader {
	
	public static BufferedImage loadImage(String path) {
		if(path == null) {
			return null;
		}
		return readImage(new File(path));
	}
	
	public static BufferedImage loadImage(File file) {
		if(file == null) {
			return null;
		}
		return readImage(file);
	}
	
	public static Mesh loadMesh(String path) {
		if(path == null) {
			return null;
		}
		return ObjLoader.load(path);
	}
	
	public static Mesh loadMesh(File file) {
		if(file == null) {
			return null;
		}
		return ObjLoader.load(file.toString());
	}
	
	private static BufferedImage readImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
