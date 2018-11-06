package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Line extends XShape{

	public Line() {
		super();
	}
	@Override
	public void draw(Graphics canvas) {
		int x = (int) (this.prop.get(this.x2)+0);	// x of end Point
		int y = (int) (this.prop.get(this.y2)+0); // Y of end point
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(2));
		can.setPaint(this.color);
		can.drawLine(x, y,this.position.x, this.position.y);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
