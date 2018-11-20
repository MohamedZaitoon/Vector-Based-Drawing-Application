package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs40_45.Engine;
import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;

@SuppressWarnings("serial")
public class XCanvas extends JComponent{
	public DrawingEngine engine;
	private Point firstPoint;
	private Shape shape;
	private Shape selected;
	private Shape copyOfSelected;
	private boolean drawing = false;
	private boolean isSelected = false;

	public void setShape(Shape shape/*,Color color,Color fillColor,Double thickness*/) {
		this.shape = shape;
		/*shape.setColor(color);
		shape.setFillColor(fillColor);
		shape.getProperties().put("thickness", thickness);*/
	}

	public void paint(Graphics g) {
		engine.refresh(g);
		if (drawing) {
			shape.draw(g);
		}

	}

	public XCanvas() {
		engine = new Engine();
		shape = null;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked");
				isSelected = false;
				selectShape(new Point(e.getX(), e.getY()));
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (shape != null) {
					drawing = true;

					firstPoint = new Point(e.getX(), e.getY());
					setSecondPoint(e.getX(), e.getY());
					shape.setPosition(firstPoint);
					repaint();
				}else if(isSelected) {
					
					if(((Engine)engine).inBoundryOfShape(selected,new Point(e.getX(),e.getY()))) {
						firstPoint = new Point(e.getX(),e.getY());
						repaint();
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (drawing) {
					setSecondPoint(e.getX(), e.getY());
					engine.addShape(shape);

					drawing = false;
					shape = null;
					repaint();
				}else if(isSelected) {
					Point newPoint = (Point) selected.getPosition();
					int x2 = selected.getProperties().get("x2").intValue();
					int y2 = selected.getProperties().get("y2").intValue();
					
					selected.setPosition(copyOfSelected.getPosition());
					selected.getProperties().put("x2",copyOfSelected.getProperties().get("x2"));
					selected.getProperties().put("y2",copyOfSelected.getProperties().get("y2"));
					
					copyOfSelected.setPosition(newPoint);
					copyOfSelected.getProperties().put("x2", (double) x2);
					copyOfSelected.getProperties().put("y2", (double) y2);
					engine.updateShape(selected, copyOfSelected);
					firstPoint = null;
					isSelected = false;
					repaint();
					
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (drawing) {
					setSecondPoint(e.getX(), e.getY());
					repaint();
				}else if(isSelected && firstPoint != null ) {
					int dx = e.getX() - firstPoint.x;
					int dy = e.getY() - firstPoint.y;
					firstPoint = new Point(e.getX(),e.getY());
					Point position = ((Point)selected.getPosition());
					int x2 =selected.getProperties().get("x2").intValue();
					int y2 =selected.getProperties().get("y2").intValue();
							
					position.x +=dx;
					position.y +=dy;
					selected.setPosition(position);
					selected.getProperties().put("x2", (double) x2+dx);
					selected.getProperties().put("y2", (double) y2+dy);
					repaint();
				}
				
			}
		});
	}
	public void save(String path) {
		engine.save(path);
		repaint();
	}
	public void load(String path) {
		engine.load(path);
		repaint();
	}
	public void undo() {
		engine.undo();
		repaint();
	}
	public void redo() {
		engine.redo();
		repaint();
	}
	public void delete() {
		if(selected != null && isSelected) {
			engine.removeShape(selected);
			repaint();
		}
	}
	private void setSecondPoint(int x, int y) {
		shape.getProperties().put("x2", (double) x);
		shape.getProperties().put("y2", (double) y);
	}
	private void selectShape(Point p) {
		Shape[] sh = engine.getShapes();
		int s = sh.length;
		for(int i = s-1; i >= 0; i--) {
			System.out.println(sh[i].getClass().getSimpleName()+" :"+((Engine)engine).inBoundryOfShape(sh[i], p));
			if(((Engine)engine).inBoundryOfShape(sh[i], p)) {
				selected = (XShape) sh[i];
				try {
					copyOfSelected = (Shape) selected.clone();
				} catch (CloneNotSupportedException e) {
					System.out.println("Fail to copy");
					e.printStackTrace();
				}
				isSelected = true;
				break;
			}
		}
		
		
	}
}
