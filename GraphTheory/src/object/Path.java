package object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.io.Serializable;

public class Path extends Object implements Serializable{
	private Node[] extremities = new Node[2];
	private Color color = Color.GRAY;
	private double weight;
	
	public Path() {
		
	}
	
	public Path(Node n1, Node n2) {
		extremities[0] = n1;
		extremities[1] = n2;
	}
	
	public void draw(Graphics2D g2d, AffineTransform at) {
		g2d.setColor(color);
		Line2D.Double line = new Line2D.Double(extremities[0].getPostion().getX(),extremities[0].getPostion().getY(),extremities[1].getPostion().getX(),extremities[1].getPostion().getY());
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(at.createTransformedShape(line));
		
	}
	
	public Node[] getNodes() {
		return extremities;
	}
	
	public void setNodes(Node[] nodes) {
		extremities = nodes;
	}
	
	public void setNodeOne(Node node) {
		extremities[0] = node;
	}
	
	public void setNodeTwo(Node node) {
		extremities[1] = node;
	}

	public void setNodes(Node node, Node node2) {
		extremities[0] = node;
		extremities[1] = node2;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
