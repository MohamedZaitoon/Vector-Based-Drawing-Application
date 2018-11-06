package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Rectangle extends XShape{
	private final int NOPOINTS = 4;
	public Rectangle() {
		super();
	}
	
	@Override
	public void draw(Graphics canvas) {
		int xp = (int) (this.prop.get(this.x2)+0);
		int yp = (int) (this.prop.get(this.y2)+0);
		int[] x = {this.position.x, this.position.x,xp, xp};
		int[] y = {this.position.y, yp,yp, this.position.y};
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(2));
		can.setPaint(this.getColor());
		can.drawPolygon(x, y, NOPOINTS);
		can.setPaint(this.getFillColor());
		can.fillPolygon(x, y, NOPOINTS);
		
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		return null;
	}

}
