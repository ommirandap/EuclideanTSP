import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import cc4102.tarea3.algorithm.ConvexHullAlgorithm;
import cc4102.tarea3.algorithm.NearestPointAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.geom.Point;


public class MainTestWindow {
	List<Point> points;
	List<Point> path;
	Point selected;
	//TSPAlgorithm algorithm = new ConvexHullAlgorithm();
	TSPAlgorithm algorithm = new NearestPointAlgorithm();
	JFrame jframe;
	
	public MainTestWindow() {
		points = new ArrayList<Point>();
	}
	
	public void start() {
		jframe = new MyJFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(500, 500);
		jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainTestWindow().start();
	}
	
	class MyJFrame extends JFrame {
		BufferedImage bi;
		
		public MyJFrame() {
			bi = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
			//setBackground(Color.white);
			MyMouseAdapter mAdapter = new MyMouseAdapter();
			addMouseListener(mAdapter);
			addMouseMotionListener(mAdapter);
		}
		
		@Override
		public void paintComponents(Graphics g) {
			super.paintComponents(g);
		}
		
		@Override
		public void paint(Graphics jframeg) {
			//super.paint(g);
			Graphics g = bi.getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, 500, 500);
			TSPAlgorithmResults results;
				 
			for(Point p : points) {
				if(p == selected)
					g.setColor(Color.red);
				else {
					g.setColor(Color.black);					
				}
				int radius = 10;
				g.fillArc((int)p.getX()-radius/2,(int)p.getY()-radius/2, radius, radius, 0, 360);
			}
			g.setColor(Color.green);
			if(points.size() >= 3) {
				results = algorithm.run(points.toArray(new Point[]{}));
				for(int i=0 ; i < results.circuit.size() ; i++) {
					if(i==0)
						g.setColor(Color.red);
					else g.setColor(Color.green);
					Point p = results.circuit.get(i);
					Point q = results.circuit.get((i+1)%results.circuit.size());
					
					g.drawLine((int)p.getX(), (int)p.getY(), (int)q.getX(), (int)q.getY());
				}
			}
			jframeg.drawImage(bi, 0,0,null);
		}
	}
	
	class MyMouseAdapter extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("pressed");
			Point mp = new Point(e.getX(), e.getY());
			double distance = 10;
			for(Point p : points) {
				if((p.distance(mp) < distance)) {
					selected = p;
					distance = selected.distance(mp);
				}
			}
			if(selected != null && e.getButton() == 3) {
				points.remove(selected);
				jframe.repaint();
				return;
			}
			if(selected == null) {
				System.out.println("new point");
				selected = new Point(e.getX(), e.getY());
				points.add(selected);
				jframe.repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if(selected != null) {
				selected.setX(e.getX());
				selected.setY(e.getY());
				jframe.repaint();
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("released");
			selected = null;
			jframe.repaint();
		}
	}
}
