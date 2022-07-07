package dazor.framework.loading;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dazor.framework.models.Mesh;

/**
 * The universal loader class used to load things into memory from either a File or a String
 * It will then return the thing that is requested
 * it´s current capabilities are images and obj files
 * @author Daniel Banic
 */
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
	
	public static Mesh loadObj(String path) {
		if(path == null) {
			return null;
		}
		return ObjLoader.load(path);
	}
	
	public static Mesh loadObj(File file) {
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
