package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class ParseJson {

	public String parseToJson(DrawingEngine e) {
		Engine engine = (Engine)e;
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{\"shapes\":\n[\n");
		for (Shape x : engine.getShapes()) {
			try {
			jsonString.append("{\"Class\":\""+x.getClass().getName()+"\",\n");
			jsonString.append("\"map\":\n[\n");
			HashMap<String, Double> map = ((HashMap<String, Double>) x.getProperties());
			if (map == null) {
				map = new HashMap<>();
				map.put("x2", (double) -1100);
				map.put("y2", (double) -1100);
			}
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				jsonString.append("\"" + entry.getKey() + "\":\"" + entry.getValue() + "\",\n" );
			}
			jsonString = new StringBuilder(jsonString.substring(0, jsonString.length()-2));
			jsonString.append("\n],\n");
			Point p = new Point();
			if (x.getPosition() != null)
				p = ((Point) x.getPosition());
			jsonString.append("\"x\":\""+p.getX()+"\",\n");
			jsonString.append("\"y\":\""+p.getY()+"\",\n");
			Color cl = (Color) x.getColor();
			if (cl == null)
				cl = Color.white;
			jsonString.append("\"color\":\""+cl.getRGB()+"\",\n");
			cl = (Color) x.getFillColor();
			if (cl == null)
				cl = Color.white;
			jsonString.append("\"fillcolor\":\""+cl.getRGB()+"\"");
			}catch (Throwable t) {
	        }
			jsonString.append("\n},\n");
		}
		jsonString = new StringBuilder(jsonString.substring(0, jsonString.length()-2));
		
		jsonString.append("\n]\n}\n");
		//System.out.println(jsonString.toString());
		return jsonString.toString();
		
	}
	
	
	public void getFromJson(DrawingEngine engine, StringBuilder jsonString) {
		Engine en = (Engine) engine;
		ArrayList<Shape> shapes = new ArrayList<>();
		Pattern p ;
		Matcher m;
		if(jsonString.length() != 0) {
			String json = jsonString.append(",").toString().trim();
			//System.out.println(json);
			String patternS = "\\{\"shapes\":\\[(.*)\\]\\}";
			try {
				
			p = Pattern.compile(patternS);
			m = p.matcher(json);
			if(m.find()) {
				System.out.println(m.group(1));
				String patternC = "(\\{\"Class\":\"([^,]*)\","
						+ "\"map\":\\["
						+ "((\"[^,]*\":\"[^,]*\",?)*)"
						+ "\\],"
						+ "((\"[^,]*\":\"[^,]*\",?)*)\\})*";
				Matcher mc = Pattern.compile(patternC).matcher(m.group(1));
				System.out.println(mc.groupCount());
				while(mc.find()) {
					if(mc.group(1) == null) continue;
					Class shapeClass = null;
					Object loadedShape = null;
					try {
						shapeClass = Class.forName(mc.group(2));// class name
						loadedShape = shapeClass.newInstance();
						Matcher props = Pattern.compile("(\"([^,]*)\":\"([^,]*)\")").matcher(mc.group(3));// properties
						Map<String, Double> properties = new HashMap<>();
						while (props.find()) {
							properties.put(props.group(2), Double.parseDouble(props.group(3)));
						}
						((Shape) loadedShape).setProperties(properties);
						Matcher match = Pattern.compile("\"x\":\"([^,]*)\"," + "\"y\":\"([^,]*)\"," + "\"color\":\"([^,]*)\","
								+ "\"fillcolor\":\"([^,]*)\"").matcher(mc.group(5));// position & color
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
					shapes.add(((Shape) loadedShape));

				}
				en.setShapes(shapes);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
