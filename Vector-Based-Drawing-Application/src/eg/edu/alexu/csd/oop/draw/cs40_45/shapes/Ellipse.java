package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Ellipse extends XShape{

	public Ellipse() {
		super();
	}
	@Override
	public void draw(Graphics canvas) {
		int minx =  Math.min(this.position.x , this.getProperties().get(this.x2).intValue());
		int miny = Math.min(this.position.y , this.getProperties().get(this.y2).intValue() );
		int width = (int) Math.abs(this.position.x -this.getProperties().get(this.x2));
		int hieght = (int) Math.abs(this.position.y -this.getProperties().get(this.y2));
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(2));
		can.setPaint(this.getFillColor());
		can.fillOval(minx, miny, width, hieght);
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
