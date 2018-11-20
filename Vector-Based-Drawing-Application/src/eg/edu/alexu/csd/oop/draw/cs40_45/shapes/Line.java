package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Line extends XShape{

	public Line() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		int x = (int) (this.prop.get(this.x2)+0);	// x of end Point
		int y = (int) (this.prop.get(this.y2)+0); // Y of end point
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setStroke(new BasicStroke(this.prop.get("thickness").intValue()));
		can.setPaint(this.color);
		can.drawLine(x, y,this.position.x, this.position.y);
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Line copy = new Line();
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
