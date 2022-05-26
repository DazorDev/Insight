package dazor.test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import dazor.api.IShader;
import dazor.framework.buffer.ColorBuffer;

public class TestButMoreRobust {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(3);
		ColorBuffer c = new ColorBuffer(800,800);
		Graphics g = frame.getGraphics();
		BufferedImage bu = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		IShader shader = new EyeCancerShader();
        Runnable task = () -> {		
        	float t = 0;
        	while(true) {
        		for(int y= 0; y!=c.height; y++) {
        			for(int x=0; x!= c.width; x++) {
        				c.setColor(x, y, shader.processPixel(x, y, c, t).scale(255));
        				bu.setRGB(x, y, c.getIntRGB(x, y));
        			}
        		}
        		t += 0.1f;
        		g.drawImage(bu, 0, 0, null);
        	}
        };
        Thread thread = new Thread(task);
        thread.start();
	}
}
