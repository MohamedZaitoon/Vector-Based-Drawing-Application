package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class ParseXml {

	public String parseToXML(DrawingEngine element) {
		StringBuilder xmlString = new StringBuilder();
		Engine en = (Engine) element;
		xmlString.append("<Engine>\n");
		Shape[] shapes = en.getShapes();
		StringBuilder ss = null;
		for (Shape x : shapes) {
			try {
				ss = new StringBuilder();
				// System.out.println(x.getClass().getName());
				ss.append("<shape id=\"" + x.getClass().getName() + "\">\n");

				ss.append("<map>\n");
				HashMap<String, Double> map = ((HashMap<String, Double>) x.getProperties());
				if (map == null) {
					map = new HashMap<>();
					map.put("x2", (double) -1100);
					map.put("y2", (double) -1100);
				}
				for (Map.Entry<String, Double> entry : map.entrySet()) {
						ss.append("<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">\n");
					}
				ss.append("</map>\n");

				Point p = new Point();
				if (x.getPosition() != null)
					p = ((Point) x.getPosition());
				ss.append("<x>" + p.getX() + "</x>\n");
				ss.append("<y>" + p.getY() + "</y>\n");
				Color cl = (Color) x.getColor();
				if (cl == null)
					cl = Color.white;
				ss.append("<color>" + cl.getRGB() + "</color>\n");
				cl = (Color) x.getFillColor();
				if (cl == null)
					cl = Color.white;
				ss.append("<fillcolor>" + cl.getRGB() + "</fillcolor>\n");

			} catch (Exception e) {
				System.out.println("Shape can't save");
			}
			ss.append("</shape>\n");
			xmlString.append(ss.toString());

		}

		xmlString.append("</Engine>\n");
		// System.out.println(xmlString.toString());
		return xmlString.toString();
	}

	public void getFromXml(DrawingEngine engine, StringBuilder xmlString) {
		Engine en = (Engine) engine;
		if (xmlString.length() != 0) {
			// get Engine
			String xml = xmlString.toString();
			String patternE = "<Engine>" 
			+ "(\n<shape id=\".*\">" 
			+ "(\n.*)*" 
			+ "\n</shape>)*" + "\n</Engine>";
			Pattern p = Pattern.compile(patternE);
			Matcher m = p.matcher(xml);

			if (m.find()) {
				// get all shape from xml
				String xmlShapes = m.group(1);
				String patternS = "(<shape id=\"(.*)\">\n" 
						+"<map>\n"
						+"((<.*>.*</.*>\n)*)" 
						+"</map>\n"
						+ "((<.*>.*</.*>\n)*)"
						+ "</shape>\n)";
				Matcher ms = Pattern.compile(patternS).matcher(xmlShapes+"\n");
				while (ms.find()) {
					
					Class shapeClass = null;
					Object loadedShape = null;
					try {
						shapeClass = Class.forName(ms.group(2));// class name
						loadedShape = shapeClass.newInstance();
						Matcher props = Pattern.compile("(<(.*)>(.*)</.*>\n)").matcher(ms.group(3));// properties
						Map<String, Double> properties = new HashMap<>();
						while (props.find()) {
							properties.put(props.group(2), Double.parseDouble(props.group(3)));
						}
						((Shape) loadedShape).setProperties(properties);
						Matcher match = Pattern.compile("<x>(.*)</x>\n" + "<y>(.*)</y>\n" + "<color>(.*)</color>\n"
								+ "<fillcolor>(.*)</fillcolor>\n").matcher(ms.group(5));// position & color
						if (match.find()) {
							Double x = Double.parseDouble(match.group(1));
							Double y = Double.parseDouble(match.group(2));
							((Shape) loadedShape).setPosition(new Point(x.intValue(), y.intValue()));
							((Shape) loadedShape).setColor(Color.decode(match.group(3)));
							((Shape) loadedShape).setFillColor(Color.decode(match.group(4)));

						}

					} catch (ClassNotFoundException e) {
						// System.out.println("Class Invalid");
						e.printStackTrace();
					} catch (InstantiationException e) {
						// System.out.println("Can't Instatiate from class");
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					en.addShape((Shape) loadedShape);

				}
			}
		}
	}
	

}
