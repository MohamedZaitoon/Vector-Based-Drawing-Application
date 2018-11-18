package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Triangle extends XShape{

	private final int NOPOINTS = 3;
	public Triangle() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		int xp = (int) (this.prop.get(this.x2)+0);
		int yp = (int) (this.prop.get(this.y2)+0);
		int dx = xp - this.position.x;
		int[] x = {this.position.x, xp,this.position.x-dx};
		int[] y = {this.position.y, yp,yp};
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(2));
		can.setPaint(this.getFillColor());
		can.fillPolygon(x, y, NOPOINTS);
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
