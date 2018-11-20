package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Square extends XShape {

	private final int NOPOINTS = 4;

	public Square() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		this.fillColor = new Color(this.fillColor.getRed(),this.fillColor.getGreen(),
				this.fillColor.getBlue(),
				this.prop.get("alpha").intValue());
		int xp = (int) (this.prop.get(this.x2).intValue());
		int yp = (int) (this.prop.get(this.y2).intValue());
		int dx = xp - this.position.x;
		int dy = yp - this.position.y;
		dx = Math.abs(dx)<Math.abs(dy)?Math.abs(dx):Math.abs(dy);
		dy = dx;
		if((yp > this.position.y && xp < this.position.x)) {
			dx = -dx;
		}else if(yp < this.position.y && xp > this.position.x) {
			dy = -dy;
		}else if(yp < this.position.y && xp< this.position.x) {
			dx = -dx;
			dy = -dy;
		}
		int[] x = {this.position.x, this.position.x,this.position.x+dx, this.position.x+dx};
		int[] y = {this.position.y, this.position.y+dy,this.position.y+dy, this.position.y};
		this.prop.put(this.x2, (double) this.position.x+dx);
		this.prop.put(this.y2, (double) this.position.y+dy);
		
		
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(this.prop.get("thickness").intValue()));
		can.setPaint(this.getColor());
		can.drawPolygon(x, y, NOPOINTS);
		can.setPaint(this.getFillColor());
		can.fillPolygon(x, y, NOPOINTS);
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Square copy = new Square();
		copy.setPosition(new Point(this.position.x, this.position.y));
		copy.setColor(new Color(this.color.getRGB()));
		copy.setFillColor(new Color(this.fillColor.getRed(), this.fillColor.getGreen()
				, this.fillColor.getBlue(),this.fillColor.getAlpha()));
		Map<String, Double> map = new HashMap<>();
		for (Map.Entry<String, Double> entry : this.prop.entrySet()) {
			map.put(entry.getKey() , entry.getValue());
		}
		copy.setProperties(map);
		return copy;
	}
 
}
