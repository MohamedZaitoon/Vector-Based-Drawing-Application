package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Square extends XShape {

	private final int NOPOINTS = 4;

	public Square() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		int xp = (int) (this.prop.get(this.x2)+0);
		int yp = (int) (this.prop.get(this.y2)+0);
		int dx = xp - this.position.x;
//		int dy = yp - this.position.y;
//		dx = dx<dy?dx:dy;
		if((yp > this.position.y && xp < this.position.x)
				|| (yp < this.position.y && xp > this.position.x)) {
			dx = -dx;
		}
		int[] x = {this.position.x, this.position.x,xp, xp};
		int[] y = {this.position.y, this.position.y+dx,this.position.y+dx, this.position.y};
		
		
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
