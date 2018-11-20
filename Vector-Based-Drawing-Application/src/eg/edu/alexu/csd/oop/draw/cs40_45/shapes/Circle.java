package eg.edu.alexu.csd.oop.draw.cs40_45.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

public class Circle extends XShape {

	public Circle() {
		super();
	}
	@Override
	public void draw(Object canvas) {
		this.fillColor = new Color(this.fillColor.getRed(),this.fillColor.getGreen(),
				this.fillColor.getBlue(),
				this.prop.get("alpha").intValue());
		int minx =  Math.min(this.position.x , this.getProperties().get(this.x2).intValue());
		int miny = Math.min(this.position.y , this.getProperties().get(this.y2).intValue() );
		
		int w = (int) Math.abs(this.position.x - this.prop.get(this.x2));
		int h = (int) Math.abs(this.position.y - this.prop.get(this.y2));
		w = w<h?w:h;
		if(miny == this.getPosition().y) {
			h = w;
		}else if(minx == this.position.x) {
			h=w;
			miny = this.position.y - h;
		}else {
			minx = this.position.x -w;
			miny = this.position.y -w;
			h = w;
		}
		this.prop.put("minx", (double) minx);
		this.prop.put("miny", (double) miny);
		this.prop.put("diameter", (double) w);
		
		
		Graphics2D can = (Graphics2D) canvas;
		can.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		can.setPaint(Color.red);
		can.setStroke(new BasicStroke(this.prop.get("thickness").intValue()));
		can.drawOval(minx, miny, w, w);
		can.setPaint(this.getFillColor());
		can.fillOval(minx, miny, w, w);
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Circle copy = new Circle();
		copy.setPosition(new Point((int) this.position.getX(), (int) this.position.getY()));
		copy.setColor(new Color(this.color.getRGB()));
		copy.setFillColor(new Color(this.fillColor.getRed(), this.fillColor.getGreen()
				, this.fillColor.getBlue(),this.fillColor.getAlpha()));
		Map<String, Double> map = new HashMap<>();
		for (Map.Entry<String, Double> entry : this.prop.entrySet()) {
			map.put(entry.getKey() , entry.getValue());
			System.out.println(map.get(entry.getKey()));
		}
		copy.setProperties(map);
		return copy;
	}

}
