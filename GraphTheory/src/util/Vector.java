package util;

import java.awt.Point;

public class Vector {
	double x,y;
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	public Point getPoint() {
		Point p = new Point();
		p.setLocation(x, y);
		return p;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(double dx,double dy) {
		this.x += dx;
		this.y += dy;
	}
	
}
