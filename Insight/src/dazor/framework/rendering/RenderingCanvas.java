package dazor.framework.rendering;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class RenderingCanvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BufferedImage image;
	public int width;
	public int height;
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, width, height, null);
		repaint();
	}
	
}
