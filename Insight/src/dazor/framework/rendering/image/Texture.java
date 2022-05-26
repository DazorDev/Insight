package dazor.framework.rendering.image;

import java.awt.image.BufferedImage;

import dazor.framework.loading.Loader;

public class Texture {

	BufferedImage image;
	
	public Texture() {
		this(null);
	}
	
	public Texture(BufferedImage image) {
		if(image == null) {
			image = Loader.loadImage(System.getProperty("user.dir")+"\\res\\MissingTextureMeme.jpg");
		}
		this.image = image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
