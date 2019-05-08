package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import javax.swing.JLabel;

public class Node extends Object{
	private final double radius = 20;
	private final Color color = Color.cyan;
	private static int counter = 0;
	public int nodeNum = counter;
	
	public Node() {
		
	}
	
	public Node(Point p) {
		position.setPosition(p.getX()-radius/2, p.getY()-radius/2);;
		shape = new Ellipse2D.Double(position.getX(), position.getY(), radius, radius);
		counter++;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fill(shape);
		shape = new Ellipse2D.Double(position.getX(), position.getY(), radius, radius);
	}
}
