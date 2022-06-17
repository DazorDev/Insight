package dazor.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import dazor.framework.loading.ObjLoader;
import dazor.framework.math.ImageRotation;
import dazor.framework.math.Quaternion;
import dazor.framework.math.Vec3f;
import dazor.framework.models.Face;
import dazor.framework.models.Mesh;

public class Test {

	static float globalScale = 1;
	static BufferedImage image;
	static BufferedImage newImage;
	public static void main(String[] args) {
		Quaternion q = new Quaternion(1,0,0,0);	
		Vec3f offset = new Vec3f(500,500,0);
		JFrame f = new JFrame();
		f.setSize(1000,1000);
		f.setDefaultCloseOperation(3);
		JFrame chooserFrame = new JFrame();
		chooserFrame.setVisible(false);
		JFileChooser chooser = new JFileChooser();

		chooser.setVisible(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Obj and image", "obj", "png","jpg");
		chooser.setFileFilter(filter);
		chooserFrame.getContentPane().add(chooser);
		chooserFrame.pack();
		chooserFrame.setSize(500, 450);
		
		View view = new View();
		JPanel jPanel = new JPanel();
		JSlider wSlider = new JSlider(JSlider.VERTICAL,-1000, 1000,0);	
		JSlider xSlider = new JSlider(JSlider.VERTICAL,-1000, 1000,0);		
		JSlider ySlider = new JSlider(JSlider.VERTICAL,-1000, 1000,0);		
		JSlider zSlider = new JSlider(JSlider.VERTICAL,-1000, 1000,0);		
		JButton fileChooserButton = new JButton("Choose File");
		fileChooserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooserFrame.setVisible(true);
				chooserFrame.getContentPane().add(chooser);
				chooser.setVisible(true);
			}
		});
		JTextField wField = new JTextField(20);
		JTextField xField = new JTextField(20);
		JTextField yField = new JTextField(20);
		JTextField zField = new JTextField(20);
		
		ActionListener aL = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(zField.getText());
				if(!zField.getText().equals("")) {
					q.setZ(Integer.valueOf(zField.getText()));
					zSlider.setValue(Integer.valueOf(zField.getText()));
				}
				if(!yField.getText().equals("")) {
					q.setY(Integer.valueOf(yField.getText()));
					ySlider.setValue(Integer.valueOf(yField.getText()));
				}
				if(!xField.getText().equals("")) {
					q.setX(Integer.valueOf(xField.getText()));
					xSlider.setValue(Integer.valueOf(xField.getText()));
				}
				if(!wField.getText().equals("")) {
					q.setW(Integer.valueOf(wField.getText()));
					wSlider.setValue(Integer.valueOf(wField.getText()));
				}
				view.update(q, offset);
			}
			
		};
		chooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(chooser.getSelectedFile().toString());
				if(chooser.accept(chooser.getSelectedFile())) {
					if(getExtensionByStringHandling(chooser.getSelectedFile().toString()).get().equals("obj")) {
						System.out.println("kkkk");
						Mesh mesh = ObjLoader.load(chooser.getSelectedFile().toString());
						view.setPolygons(mesh.getPolygons());
						view.update(q, offset);
						return;
					}
					try {
						image = ImageIO.read(chooser.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				view.update(q, offset);
			}
		});
		
		
		ChangeListener changeListener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {				
				q.setZ(zSlider.getValue());
				zField.setText(String.valueOf(zSlider.getValue()));
				q.setY(ySlider.getValue());
				yField.setText(String.valueOf(ySlider.getValue()));
				q.setX(xSlider.getValue());
				xField.setText(String.valueOf(xSlider.getValue()));
				q.setW(wSlider.getValue());
				wField.setText(String.valueOf(wSlider.getValue()));
				view.update(q, offset);
			}
		};
		
		MouseAdapter ml = new MouseAdapter() {
			boolean pressed = false;
			
			float tempX;
			float tempY;
			
			@Override
			public void mousePressed(MouseEvent e) {
				tempX = e.getX();
				tempY = e.getY();
				pressed = true;
			}
			
			public void mouseReleased(MouseEvent e) {
				pressed = false;
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!pressed) return;
				offset.addLocal(e.getX()-tempX, e.getY()-tempY, 0);
				view.update(q, offset);
				tempX = e.getX();
				tempY = e.getY();
			}
			
			
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				globalScale += e.getWheelRotation()*-0.5;
				view.update(q, offset);
			}

		};
		
		wSlider.addChangeListener(changeListener);
		xSlider.addChangeListener(changeListener);
		ySlider.addChangeListener(changeListener);
		zSlider.addChangeListener(changeListener);
	
		wField.addActionListener(aL);
		xField.addActionListener(aL);
		yField.addActionListener(aL);
		zField.addActionListener(aL);
		

		JPanel inputPanel = new JPanel();
		JPanel panelPanel = new JPanel();
		LayoutManager box = new BoxLayout(inputPanel, BoxLayout.Y_AXIS);
		LayoutManager box2 = new BoxLayout(panelPanel, BoxLayout.Y_AXIS);
		inputPanel.setLayout(box);
		panelPanel.setLayout(box2);
		panelPanel.setPreferredSize(new Dimension(100, 500));
		wField.setMaximumSize(wField.getPreferredSize());
		xField.setMaximumSize(wField.getPreferredSize());
		yField.setMaximumSize(wField.getPreferredSize());
		zField.setMaximumSize(wField.getPreferredSize());
		
		
		jPanel.add(wSlider);
		jPanel.add(xSlider);
		jPanel.add(ySlider);
		jPanel.add(zSlider);
		jPanel.add(fileChooserButton);
		inputPanel.add(wField);
		inputPanel.add(xField);
		inputPanel.add(yField);
		inputPanel.add(zField);
		jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		f.addMouseListener(ml);
		f.addMouseMotionListener(ml);
		f.addMouseWheelListener(ml);
		panelPanel.add(jPanel);
		panelPanel.add(inputPanel);
		f.getContentPane().add(chooser);
		f.add(view);
		f.add(panelPanel, BorderLayout.EAST);
		f.pack();
		f.setSize(1000,1000);
		q.setZ(zSlider.getValue());
		q.setY(ySlider.getValue());
		q.setX(xSlider.getValue());
		q.setW(wSlider.getValue());
		view.update(q, offset);
		f.setVisible(true);
	}
	
	static class View extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<Face> oldFaces = new ArrayList<>();
		ArrayList<Face> processedPolygons = new ArrayList<>();
		boolean processing = false;
		public View() {
		}

		public void setFaces(ArrayList<Face> faces) {
			oldFaces.clear();
			processedPolygons.clear();
			for(Face f : faces) {
				oldFaces.add(f);
				processedPolygons.add(new Face(f));
			}
		}
		
		public void addPolygons(ArrayList<Face> poly) {
			oldFaces.addAll(poly);
			processedPolygons.addAll(poly);
		}
		
		public void setPolygons(ArrayList<Face> arrayList) {
			oldFaces.clear();
			processedPolygons.clear();
			oldFaces.addAll(arrayList);
			processedPolygons.addAll(arrayList);
		}
		
		public void update(Quaternion q, Vec3f offset) {
			
			processing = true;
			if(image != null) {
				newImage = rotateImage(image);
			}
			for(int i=0; i!= oldFaces.size(); i++) {
				processedPolygons.set(i, oldFaces.get(i).rotate(q));
				processedPolygons.get(i).scale(globalScale);
				processedPolygons.get(i).move(offset);
			}
			processing = false;
			
			repaint();
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			g.clearRect(0, 0, getWidth(), getHeight());
			if(processing) return;
			processedPolygons.sort( (f1, f2) -> {
				return -1*Float.compare(f1.getZ(), f2.getZ());
			});
			if(image == null) {
				System.out.println("no image");
				return;
			}
			newImage = rotateImage(newImage);
			g.drawImage(newImage, 0, 0, null);
			processedPolygons.forEach( polygon -> {	
				polygon.drawUVMap(g, newImage);
				polygon.paintImagePolygon(g, newImage);
//				polygon.drawPolygon(g);
			});
		}
	}
	
	public static Optional<String> getExtensionByStringHandling(String filename) {
	    return Optional.ofNullable(filename)
	      .filter(f -> f.contains("."))
	      .map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	
	private static BufferedImage rotateImage(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -image.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}
}
