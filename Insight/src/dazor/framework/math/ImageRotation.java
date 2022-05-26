package dazor.framework.math;

import java.awt.image.BufferedImage;

public class ImageRotation {

	public static BufferedImage rotateImage(Quaternion quaternion, BufferedImage image) {
		if(image == null) {
			return null;
		}
		BufferedImage outputImage = new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
		for(int i = 0; i!=image.getWidth(); i++) {
			for(int j = 0; j!= image.getHeight(); j++) {
				Vec2f tempVec = new Vec2f(i,j);
				quaternion.rotateLocal(tempVec);
				if(Math.round(tempVec.getX()) < 0) continue;
				if(Math.round(tempVec.getX()) >= image.getWidth()) continue;
				if(Math.round(tempVec.getY()) < 0) continue;
				if(Math.round(tempVec.getY()) >= image.getHeight()) continue;
				outputImage.setRGB(Math.round(tempVec.getX()), Math.round(tempVec.getY()), image.getRGB(i, j));		
			}
		}	
		return outputImage;
	}	
}