package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Rectangle extends XShape{
	private final int NOPOINTS = 4;
	public Rectangle() {
		super();
	}
	
	@Override
	public void draw(Object canvas) {
		this.fillColor = new Color(this.fillColor.getRed(),this.fillColor.getGreen(),
				this.fillColor.getBlue(),
				this.prop.get("alpha").intValue());
		int xp = (int) (this.prop.get(this.x2).intValue());
		int yp = (int) (this.prop.get(this.y2).intValue());
		int[] x = {this.position.x, this.position.x,xp, xp};
		int[] y = {this.position.y, yp,yp, this.position.y};
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
		Rectangle copy = new Rectangle();
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
