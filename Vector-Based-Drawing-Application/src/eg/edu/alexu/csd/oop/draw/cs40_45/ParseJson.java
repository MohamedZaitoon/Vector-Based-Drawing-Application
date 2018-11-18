package eg.edu.alexu.csd.oop.draw.cs40_45;

import java.awt.Color;
import java.awt.Point;
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
		jsonString.append("{\"shapes\" :[\n");
		for (Shape x : engine.getShapes()) {
			jsonString.append("{\"Class\" :\""+x.getClass().getName()+"\",\n");
			jsonString.append("\"map\" :[\n");
			HashMap<String, Double> map = ((HashMap<String, Double>) x.getProperties());
			if (map == null) {
				map = new HashMap<>();
				map.put("x2", (double) -1100);
				map.put("y2", (double) -1100);
			}
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				jsonString.append("\"" + entry.getKey() + "\" :\"" + entry.getValue() + "\",\n" );
			}
			jsonString.append("],\n");
			Point p = new Point();
			if (x.getPosition() != null)
				p = ((Point) x.getPosition());
			jsonString.append("\"x\" :\""+p.getX()+"\",\n");
			jsonString.append("\"y\" :\""+p.getY()+"\",\n");
			Color cl = (Color) x.getColor();
			if (cl == null)
				cl = Color.white;
			jsonString.append("\"color\" :\""+cl.getRGB()+"\",\n");
			cl = (Color) x.getFillColor();
			if (cl == null)
				cl = Color.white;
			jsonString.append("\"fillcolor\" :\""+cl.getRGB()+"\"\n},\n");
			
		}
		jsonString = new StringBuilder(jsonString.substring(0, jsonString.length()-2));
		
		jsonString.append("\n]\n}\n");
		//System.out.println(jsonString.toString());
		return jsonString.toString();
		
	}
	
	
	public void getFromJson(DrawingEngine engine, StringBuilder jsonString) {
		Engine en = (Engine) engine;
		Pattern p ;
		Matcher m;
		if(jsonString.length() != 0) {
			String json = jsonString.toString();
			String patternS = "\\{\"shapes\" :["
					+ "((\n.*)*)"
					+ "\n]\n\\}\n";
			try {
			p = Pattern.compile(patternS);
			m = p.matcher(json);
			if(m.find()) {
				System.out.println(m.group(1));
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
