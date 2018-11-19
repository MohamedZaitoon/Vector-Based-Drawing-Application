package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs40_45.Engine;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Square;

@SuppressWarnings("serial")
public class XCanvas extends JPanel {
	private DrawingEngine engine;
	private Point firstPoint;
	private Shape shape;
	private boolean drawing = false;
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void paint(Graphics g) {
		engine.refresh(g);
		if(drawing) {
			shape.draw(g);
		}		
	}

	public XCanvas() {
		engine = new Engine();
		shape = new Square();
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(shape != null) {
					drawing = true;
				
					firstPoint = new Point(e.getX(), e.getY());
					setSecondPoint(e.getX(),e.getY());
					shape.setPosition(firstPoint);

					repaint();
				}
			}

			public void mouseReleased(MouseEvent e) {
				if(drawing) {
					setSecondPoint(e.getX(),e.getY());
					engine.addShape(shape);
					
					drawing = false;
					shape = new Rectangle();
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
