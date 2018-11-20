package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class XShape implements Shape {
	protected Point position;
	protected Color color;
	protected Color fillColor;
	protected final String x2 ="x2",y2="y2";
	protected Map<String, Double> prop;

	public XShape() {
		this.position = new Point();
		this.prop = new HashMap<>();
		this.color = Color.BLACK;
		this.fillColor = Color.WHITE;
		this.prop.put(x2, (double) -1100);
		this.prop.put(y2, (double) -1100);
	}
	@Override
	public void setPosition(Object position) {
		this.position = (Point) position;
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
	public void setColor(Object color) {
		this.color = (Color) color;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setFillColor(Object color) {

		this.fillColor = (Color) color;
	}

	@Override
	public Color getFillColor() {
		return this.fillColor;
	}

	public abstract void draw(Object canvas);

	public abstract Object clone() throws CloneNotSupportedException;

}
