package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

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
	public void refresh(Graphics canvas) {
		Graphics2D can = (Graphics2D)canvas;
		
	}

	@Override
	public void addShape(Shape shape) {
		if (shape != null ) {
			shapes.add(shape);
			checkFullHistory();
			history.push(new Status('a', -1, shape));
		}
	}

	@Override
	public void removeShape(Shape shape) {
		if (shape != null) {
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

	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		if (oldShape != null || newShape != null || shapes.size() == 0) {
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

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return null;
	}

	@Override
	public void undo() {
		rhistory.push(history.peek());
		Status s = history.pop();
		switch (s.status) {
		case 'a':
			if (history.size() > 0) {
				shapes.remove(history.size() - 1);
			}
			break;
		case 'r':
			shapes.add(s.index, s.shape);
			break;
		case 'u':
			rhistory.getFirst().shape = shapes.get(s.index);
			shapes.remove(s.index);
			shapes.add(s.index, s.shape);
			break;

		}

	}

	@Override
	public void redo() {
		if (history.size() > 0) {
			Status s = rhistory.pop();
			switch (s.status) {
			case 'a':
				this.addShape(s.shape);
				break;
			case 'r':
				this.removeShape(s.shape);
				break;
			case 'u':
				this.updateShape(shapes.get(s.index), s.shape);;
				break;

			}
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

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
		if(this.history.size() == 20) this.history.removeLast();
	}
}
