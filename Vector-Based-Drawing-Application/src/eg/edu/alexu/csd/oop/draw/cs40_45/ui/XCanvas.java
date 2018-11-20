package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs40_45.Engine;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.*;

@SuppressWarnings("serial")
public class XCanvas extends JPanel {
	private DrawingEngine engine;
	private Point firstPoint;
	private Shape shape = new Line();
	private Color color = Color.BLACK;
	private Color colorFill = Color.WHITE;
	private boolean drawing = false;
	public void setShape(Object shape) {
		this.shape = (Shape) shape;
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public void setColor(Object color) {
		this.color = (Color) color;
	}
	
	public void setColorFill(Object colorFill) {
		this.colorFill = (Color) colorFill;
	}

	public void paint(Graphics g) {
		engine.refresh(g);
		if(drawing) {
			shape.draw(g);
		}		
	}
	
	public XCanvas() {
		engine = new Engine();
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(shape != null) {
					drawing = true;
					firstPoint = new Point(e.getX(), e.getY());
					setSecondPoint(e.getX(),e.getY());
					shape.setColor(color);
					shape.setFillColor(colorFill);
					shape.setPosition(firstPoint);
					repaint();
				}
			}

			public void mouseReleased(MouseEvent e) {
				if(drawing) {
					setSecondPoint(e.getX(),e.getY());
					engine.addShape(shape);
					
					drawing = false;
					Class<? extends Object> cls = shape.getClass();
					try {
						shape = (Shape) cls.newInstance();
						shape.setColor(color);
						shape.setFillColor(colorFill);
					} catch (InstantiationException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					repaint();
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				if(drawing) {
					setSecondPoint(e.getX(),e.getY());
					repaint();
				}
			}
		});
	}

	private void setSecondPoint(int x, int y) {
		shape.getProperties().put("x2", (double) x);
		shape.getProperties().put("y2", (double) y);
	}
}
