package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.RoundRectangle;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Triangle;

public class Engine implements DrawingEngine {

	private List<Shape> shapes;
	private Deque<Status> history;
	private Deque<Status> rhistory;

	public Engine() {
		this.shapes = new ArrayList<>();
		this.history = new LinkedList<>();
		this.rhistory = new LinkedList<>();
	}

	@Override
	public void refresh(Object canvas) {
		for (Shape x : shapes) {
			x.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		if (shape != null) {
			add(shape);
			if (rhistory.size() > 0)
				rhistory = new LinkedList<>();
		}
	}

	private void add(Shape shape) {
		shapes.add(shape);
		history.push(new Status('a', shapes.size() - 1, shape));
		checkFullHistory();
	}

	@Override
	public void removeShape(Shape shape) {
		if (shape != null) {
			remove(shape);
			if (rhistory.size() > 0)
				rhistory = new LinkedList<>();

		}

	}

	private void remove(Shape shape) {
		int size = shapes.size();
		for (int i = 0; i < size; i++) {
			if (shapes.get(i) == shape) {
				this.shapes.remove(i);
				checkFullHistory();
				history.push(new Status('r', i, shape));
				break;
			}
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		if (oldShape != null || newShape != null || shapes.size() == 0) {
			update(oldShape, newShape);
			if (rhistory.size() > 0)
				rhistory = new LinkedList<>();

		}

	}

	private void update(Shape oldShape, Shape newShape) {
		int size = shapes.size();
		for (int i = 0; i < size; i++) {
			if (shapes.get(i) == oldShape) {
				shapes.remove(i);
				shapes.add(i, newShape);
				checkFullHistory();
				history.push(new Status('u', i, oldShape));
				break;
			}
		}
	}

	@Override
	public Shape[] getShapes() {
		if (shapes.size() == 0)
			return new Shape[0];
		int size = shapes.size();
		Shape[] shps = new Shape[size];
		for (int i = 0; i < size; i++) {
			shps[i] = shapes.get(i);
		}
		return shps;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = (ArrayList<Shape>) shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		List<Class<? extends Shape>> shps = new LinkedList<>();
		shps.add(Circle.class);
		shps.add(Ellipse.class);
		shps.add(Square.class);
		shps.add(Line.class);
		shps.add(Rectangle.class);
		shps.add(Triangle.class);
		return shps;
	}

	@Override
	public void undo() {
		if (this.history.size() != 0) {
			rhistory.push(history.peek());
			Status s = history.pop();
			switch (s.status) {
			case 'a':
				shapes.remove(s.index.intValue());

				break;
			case 'r':
				shapes.add(s.index.intValue(), s.shape);
				break;
			case 'u':
				rhistory.getFirst().shape = shapes.get(s.index.intValue());
				shapes.remove(s.index.intValue());
				shapes.add(s.index.intValue(), s.shape);
				break;

			}
		}

	}

	@Override
	public void redo() {
		if (this.rhistory.size() != 0) {
			Status s = rhistory.pop();
			switch (s.status) {
			case 'a':
				add(s.shape);
				break;
			case 'r':
				this.remove(s.shape);
				break;
			case 'u':
				this.update(shapes.get(s.index.intValue()), s.shape);
				;
				break;

			}
		}
	}

	@Override
	public void save(String path) {
		File file = new File(path);
		String s = file.getName();
		try {

			FileWriter fw = new FileWriter(file);
			BufferedWriter bf = new BufferedWriter(fw);
			if (s.matches(".*\\.[Xx][Mm][Ll]")) {
				ParseXml parser = new ParseXml();
				bf.write(parser.parseToXML(this));
			} else if (s.matches(".*\\.[Jj][Ss][Oo][Nn]")) {
				ParseJson parser = new ParseJson();
				bf.write(parser.parseToJson(this));
			}
			bf.close();
			fw.close();
		} catch (Exception e) {
		}

	}

	@Override
	public void load(String path) {
		File file = new File(path);
		String name = file.getName();
		FileReader fr;
		BufferedReader br;
		StringBuilder xString = new StringBuilder();
		boolean isXml = name.matches(".*\\.[Xx][Mm][Ll]");
		boolean isJson = name.matches(".*\\.[Jj][Ss][Oo][Nn]");
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String s;
			if (isXml) {
				while ((s = br.readLine()) != null) {
					xString.append(s + "\n");
				}
			}else if(isJson){
				while ((s = br.readLine()) != null) {
					xString.append(s);
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (isXml) {
			ParseXml parser = new ParseXml();
			parser.getFromXml(this, xString);
		} else if (isJson) {
			ParseJson parser = new ParseJson();
			parser.getFromJson(this, xString);
		}

	}

	private class Status {
		Character status;
		Integer index;
		Shape shape;

		Status(Character status, Integer index, Shape shape) {
			this.status = status;
			this.index = index;
			this.shape = shape;

		}
	}
	
	private void checkFullHistory() {
		if (this.history.size() > 20)
			this.history.removeLast();
		if (this.rhistory.size() > 20)
			this.rhistory.removeLast();
	}

	public boolean inBoundryOfShape(Shape s, Point p) {
		switch(s.getClass().getSimpleName()) {
		case "Line": return inLine(s,p);
		case "Circle": return inCircle(s,p);
		case "Ellipse": return inEllipse(s,p);
		case "Rectangle": return inRect(s,p);
		case "Square": return inRect(s,p);
		case "Triangle": return inTriangle(s,p);
		}
		return false;
	}

	private boolean inTriangle(Shape s, Point p) {
		Point position = (Point) s.getPosition();
		int x3 = s.getProperties().get("x2").intValue();
		int y3 = s.getProperties().get("y2").intValue();
		int dx = x3 - position.x;
		int x1 = position.x-dx;
		int y1 = y3;
		double a = areaOfTriangle(x1, y1, position.x, position.y, x3, y3); 
		double a1 = areaOfTriangle(p.x, p.y, position.x, position.y, x3, y3) ;
		double a2 = areaOfTriangle(x1, y1, p.x, p.y, x3, y3) ;
		double a3 = areaOfTriangle(x1, y1, position.x, position.y, p.x, p.y) ;
		return ((a1+a2+a3)==a);
	}
	private double areaOfTriangle(int x1,int y1,int x2,int y2,int x3,int y3) {
		return Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2;
	}
	private boolean inRect(Shape s, Point p) {
		Point position = (Point) s.getPosition();
		int x = s.getProperties().get("x2").intValue();
		int y = s.getProperties().get("y2").intValue();
		int x1 = Math.min(position.x, x);
		int x2 = Math.max(position.x, x);
		int y1 = Math.min(position.y, y);
		int y2 = Math.max(position.y, y);
		if(p.x>=x1&&p.x<=x2&&p.y>=y1&&p.y<=y2) {
			return true;
		}
		return false;
	}

	private boolean inEllipse(Shape s, Point p) {
		Point position = (Point) s.getPosition();
		int x2 = s.getProperties().get("x2").intValue();
		int y2 = s.getProperties().get("y2").intValue();
		int minx = Math.min(position.x , x2);
		int miny = Math.min(position.y , y2 );
		int a = (int) Math.abs(position.x -x2)/2;
		int b = (int) Math.abs(position.y -y2)/2;
		Point center = new Point(minx+a,miny+b);
		double x = p.x - center.x;
		double y = p.y - center.y;
		double r = ((x*x)/(a*a))+((y*y)/(b*b));
		if(r<=1) {
			return true;
		}
		return false;
	}

	private boolean inCircle(Shape s, Point p) {
		int radius = s.getProperties().get("diameter").intValue()/2;
		Point center = new Point(s.getProperties().get("minx").intValue()+radius,
				s.getProperties().get("miny").intValue()+radius);
		double x = p.x - center.x;
		double y = p.y - center.y;
		double r = Math.sqrt(x*x+y*y);
		if(r <= radius) {
			return true;
		}
		return false;
	}

	private boolean inLine(Shape s, Point p) {
		Point position = (Point) s.getPosition();
		int x2 = s.getProperties().get("x2").intValue();
		int y2 = s.getProperties().get("y2").intValue();
		double slop = (position.y -y2)/(1.0* position.x-x2);
		if(p.y <= position.y+slop*(p.x-position.x)+5&&p.y >= position.y+slop*(p.x-position.x)-5)
			return true;
		return  false;
	}
}
