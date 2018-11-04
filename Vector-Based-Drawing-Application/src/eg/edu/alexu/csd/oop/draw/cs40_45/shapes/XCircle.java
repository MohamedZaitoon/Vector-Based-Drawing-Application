package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.Graphics;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class XCircle extends XShape {
	private Integer radius;
	
	public XCircle() {
		super();
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(this.getColor());
		canvas.fillOval(this.getPosition().x, this.getPosition().y, this.radius, this.radius);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
