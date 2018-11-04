package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.XCircle;

public class Main extends JFrame {

	private JPanel contentPane;
	Canvas panel;
	Graphics g;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		g = panel.getGraphics();
		contentPane.add(panel, BorderLayout.CENTER);
		
		XCircle c = new XCircle();
		 c.setRadius(20);
		 c.setPosition(new Point(60,60));
		 c.setColor(Color.BLACK);
		 c.setFillColor(Color.BLUE);
		 c.draw(g);
		
		
	}

}
