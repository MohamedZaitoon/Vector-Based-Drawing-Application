package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Circle extends XShape {

	@Override
	public void draw(Graphics canvas) {
		int x = Math.abs(this.position.x -this.secondPoint.x);
		int y = Math.abs(this.position.y -this.secondPoint.y);
		int radius = (int) Math.sqrt(x*x+y*y);
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(2));
		can.setPaint(this.getFillColor());
		can.fillOval(this.position.x, this.position.y, radius, radius);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
