package dazor.framework;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import dazor.framework.models.Mesh;

public class RenderingCanvas extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	ArrayList<Mesh> meshes = new ArrayList<>();
	
	@Override()
	public void paintComponent(Graphics g) {
		
	}
	
}
