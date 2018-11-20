package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Circle extends XShape {

	public Circle() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		int minx =  Math.min(this.position.x , this.getProperties().get(this.x2).intValue());
		int miny = Math.min(this.position.y , this.getProperties().get(this.y2).intValue() );
		
		int x = (int) Math.abs(this.position.x -this.prop.get(this.x2));
		int y = (int) Math.abs(this.position.y -this.prop.get(this.y2));
		int radius = (int) Math.sqrt(x*x+y*y);
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setPaint(this.getColor());
		can.setStroke(new BasicStroke(2));
		can.drawOval(minx, miny, radius, radius);
		can.setPaint(this.getFillColor());
		can.fillOval(minx, miny, radius, radius);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
