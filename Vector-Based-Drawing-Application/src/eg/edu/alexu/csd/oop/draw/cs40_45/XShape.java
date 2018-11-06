package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class XShape implements Shape {
	protected Point position;
	protected Point secondPoint;
	public Point getSecondPoint() {
		return secondPoint;
	}

	public void setSecondPoint(Point secondPoint) {
		this.secondPoint = secondPoint;
	}
	protected Color color;
	protected Color fillColor;
	protected Map<String, Double>prop;
	
	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.prop = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		return this.prop;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setFillColor(Color color) {

		this.fillColor = color;
	}

	@Override
	public Color getFillColor() {
		return this.fillColor;
	}

	public abstract void draw(Graphics canvas) ;
	public abstract Object clone() throws CloneNotSupportedException; 

}
