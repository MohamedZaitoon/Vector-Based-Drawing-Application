package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Ellipse extends XShape{

	public Ellipse() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		this.fillColor = new Color(this.fillColor.getRed(),this.fillColor.getGreen(),
				this.fillColor.getBlue(),
				this.prop.get("alpha").intValue());
		int minx =  Math.min(this.position.x , this.getProperties().get(this.x2).intValue());
		int miny = Math.min(this.position.y , this.getProperties().get(this.y2).intValue() );
		int width = (int) Math.abs(this.position.x -this.getProperties().get(this.x2));
		int hieght = (int) Math.abs(this.position.y -this.getProperties().get(this.y2));
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setPaint(this.getColor());
		can.setStroke(new BasicStroke(2));
		can.setStroke(new BasicStroke(this.prop.get("thickness").intValue()));
		can.setPaint(this.color);
		can.drawOval(minx, miny, width, hieght);
		can.setPaint(this.getFillColor());
		can.fillOval(minx, miny, width, hieght);
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Ellipse copy = new Ellipse();
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
