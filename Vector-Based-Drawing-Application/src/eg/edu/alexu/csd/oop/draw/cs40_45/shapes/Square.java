package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Square extends XShape {

	private final int NOPOINTS = 4;

	@Override
	public void draw(Graphics canvas) {
		int dx = this.secondPoint.x - this.position.x;
		
		int[] x = {this.position.x, this.position.x,this.secondPoint.x, this.secondPoint.x};
		int[] y = {this.position.y, this.position.y+dx,this.position.y+dx, this.position.y};
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(2));
		can.setPaint(this.getFillColor());
		can.drawPolygon(x, y, NOPOINTS);
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
