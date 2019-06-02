package object;

import java.awt.Point;
import java.awt.Shape;

import util.Vector;

public class Object {
	protected Vector position = new Vector();
	protected Shape shape;
	protected String name = "No name";
	
	public boolean isInside(Point e){
		if(shape.contains(e.getX(), e.getY())) 
			return true;
		return false;
	}
	
	public Vector getPostion() {
		return position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public void translate(double dx,double dy) {
		position.translate(dx,dy);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
