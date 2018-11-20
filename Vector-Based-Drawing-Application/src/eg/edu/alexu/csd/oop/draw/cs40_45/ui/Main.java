package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.Triangle;
import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel contentPane;
	private XCanvas canvas;
	private JList list;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenuItem mntmSave;
	private JMenuItem mntmLoad;
	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;
	private JButton circle;
	private JButton ellips;
	private JButton triangle;
	private JButton line;
	private JButton square;
	private JButton rectangle;
	private JPanel panel;
	private JLabel coor;
	private JButton Undo;
	private JButton Redo;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton Delete;
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
		setBounds(100, 100, 806, 440);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		
		mntmRedo = new JMenuItem("Redo");
		mnEdit.add(mntmRedo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		canvas = new XCanvas();
		
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				coor.setText("X :"+e.getX()+" Y :"+e.getY());
			}
		});
		contentPane.add(canvas, BorderLayout.CENTER);
		canvas.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel_1.setLayout(fl_panel_1);
		
		circle = new JButton("Circle");
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setShape(new Circle());
			}
		});
		panel_1.add(circle);
		
		ellips = new JButton("Ellipse");
		ellips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setShape(new Ellipse());
			}
		});
		panel_1.add(ellips);
		
		triangle = new JButton("Triangle");
		panel_1.add(triangle);
		
		triangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setShape(new Triangle());
			}
		});
		
		line = new JButton("Line");
		panel_1.add(line);
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setShape(new Line());
			}
		});
		square = new JButton("Square");
		panel_1.add(square);
		square.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setShape(new Square());
			}
		});
		rectangle = new JButton("Rectangle");
		panel_1.add(rectangle);
		
		Undo = new JButton("Undo");
		Undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.undo();
			}
		});
		panel_1.add(Undo);
		
		Redo = new JButton("Redo");
		Redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.redo();
			}
		});
		panel_1.add(Redo);
		
		btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.save("save.xml");
				System.out.println("savaed");
			}
		});
		panel_1.add(btnSave);
		
		btnLoad = new JButton("load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.load("save.xml");
				System.out.println("loaded");
			}
		});
		panel_1.add(btnLoad);
		
		Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.delete();
			}
		});
		panel_1.add(Delete);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		coor = new JLabel("New label");
		panel.add(coor);
		
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas.setShape(new Rectangle());
			}
		});
		
	}
	

	
}
