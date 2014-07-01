package uitest;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import cc4102.tarea3.algorithm.ConvexHullAlgorithm;
import cc4102.tarea3.algorithm.Kruskal;
import cc4102.tarea3.algorithm.NearestPointAlgorithm;
import cc4102.tarea3.algorithm.Prim;
import cc4102.tarea3.algorithm.TSPAlgorithm;
import cc4102.tarea3.algorithm.UndirectedGraph;
import cc4102.tarea3.algorithm.TSPAlgorithm.TSPAlgorithmResults;
import cc4102.tarea3.geom.Arc;
import cc4102.tarea3.geom.Point;


public class MainTestWindow {
	List<Point> points;
	List<Point> path;
	Point selected;
	JFrame jframe;
	
	public MainTestWindow() {
		points = new ArrayList<Point>();
	}
	
	public void start() {
		//jframe = new MyJFrame(new NearestPointAlgorithm());
		//jframe = new MyTreeJFrame();
		jframe = new MyPrimTreeJFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(500, 500);
		jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainTestWindow().start();
	}
	
	class MyJFrame extends JFrame {
		BufferedImage bi;
		final TSPAlgorithm algorithm;
		
		public MyJFrame(TSPAlgorithm algorithm) {
			this.algorithm = algorithm;
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
				 
			paintPoints(g);
			drawLines(g);
			jframeg.drawImage(bi, 0,0,null);
		}
		
		protected void paintPoints(Graphics g) {
			for(Point p : points) {
				if(p == selected)
					g.setColor(Color.red);
				else {
					g.setColor(Color.black);					
				}
				int radius = 10;
				g.fillArc((int)p.getX()-radius/2,(int)p.getY()-radius/2, radius, radius, 0, 360);
			}
		}
		
		protected void drawLines(Graphics g) {
			g.setColor(Color.green);
			if(points.size() >= 3) {
				TSPAlgorithmResults results = algorithm.run(points.toArray(new Point[]{}));
				for(int i=0 ; i < results.circuit.size() ; i++) {
					if(i==0)
						g.setColor(Color.red);
					else g.setColor(Color.green);
					Point p = results.circuit.get(i);
					Point q = results.circuit.get((i+1)%results.circuit.size());
					
					g.drawLine((int)p.getX(), (int)p.getY(), (int)q.getX(), (int)q.getY());
				}
				g.setColor(Color.ORANGE);
				g.drawString("length:"+results.length, 15, 40);
			}
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
	
	class MyTreeJFrame extends MyJFrame{
		Kruskal kruskal;
		public MyTreeJFrame() {
			super(null);
			kruskal = new Kruskal();
		}
		
		@Override
		protected void drawLines(Graphics g) {
			g.setColor(Color.green);
			if(points.size() >= 3) {
				List<Arc> results = kruskal.computeMST(points);
				for(Arc arc : results) {
					Point p = arc.getP1();
					Point q = arc.getP2();
					
					g.drawLine((int)p.getX(), (int)p.getY(), (int)q.getX(), (int)q.getY());
				}
			}
		}
	}
	
	class MyPrimTreeJFrame extends MyJFrame{
		Kruskal kruskal;
		public MyPrimTreeJFrame() {
			super(null);
			kruskal = new Kruskal();
		}
		
		@Override
		protected void drawLines(Graphics g) {
			if(points.size() < 3) return;
			g.setColor(Color.green);
			UndirectedGraph<Point> G = new UndirectedGraph<Point>();
			
			for(int i =0; i < points.size(); i++){
				for(int j = i+1; j < points.size(); j++){
					Point p1 = points.get(i);
					Point p2 = points.get(j);
					G.addNode(p1);
					G.addNode(p2);
					G.addEdge(p1, p2, p1.distance(p2));
				}
			}
			UndirectedGraph<Point> res = Prim.mst(G);
			
			for(Point point1 : res){
				for(Point point2 : res.edgesFrom(point1).keySet()) {
					g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());
				}
			}
		}
	}
}
