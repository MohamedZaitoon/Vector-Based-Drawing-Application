package eg.edu.alexu.csd.oop.draw.cs40_45;

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
		// shps.add(RoundRectangle.class);
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
			// TODO: handle exception
		}

	}

	@Override
	public void load(String path) {
		File file = new File(path);
		String name = file.getName();
		FileReader fr;
		BufferedReader br;
		StringBuilder xString = new StringBuilder();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				xString.append(s + "\n");
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (name.matches(".*\\.[Xx][Mm][Ll]")) {
			ParseXml parser = new ParseXml();
			parser.getFromXml(this, xString);
		} else if (name.matches(".*\\.[Jj][Ss][Oo][Nn]")) {
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

}
