package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import eg.edu.alexu.csd.oop.draw.cs40_45.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JMenuItem;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormSpecs;

import eg.edu.alexu.csd.oop.draw.cs40_45.XShape;
import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainController extends JFrame {

	private JPanel contentPane;
	private XCanvas panel_draw = new XCanvas();
	private Color color = Color.BLACK;
	private Color colorFill = Color.WHITE;
	JPanel panel_6 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainController frame = new MainController();
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
	public MainController() {
		setBackground(new Color(40, 45, 57));
		setTitle("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 460);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBackground(new Color(40, 45, 57));
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menu.setForeground(Color.WHITE);
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("New");
		menuItem.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_File_10px.png")));
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Open");
		menuItem_1.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Open_10px.png")));
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("Save as");
		menu_1.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Save_10px.png")));
		menu.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("XML");
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("jSon");
		menu_1.add(menuItem_3);
		
		JMenu menu_2 = new JMenu("Edit");
		menu_2.setForeground(Color.WHITE);
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("Undo");
		menuItem_4.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Undo_10px.png")));
		menu_2.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Redo");
		menuItem_5.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Redo_10px.png")));
		menu_2.add(menuItem_5);
		
		JMenu menu_3 = new JMenu("Help");
		menu_3.setForeground(Color.WHITE);
		menuBar.add(menu_3);
		
		JMenuItem menuItem_6 = new JMenuItem("Help Contents");
		menuItem_6.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Help_10px.png")));
		menu_3.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 45, 57));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[100px:n,grow][10.00:n][550:550,grow][70,grow]", "[100px:n,grow,fill][10:n,grow]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(58, 62, 74));
		panel.setBorder(null);
		contentPane.add(panel, "cell 0 0");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 0};
		gbl_panel.rowHeights = new int[]{200, 95, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(new Color(58, 62, 74));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panel.add(tabbedPane, gbc_tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(58, 62, 74));
		tabbedPane.addTab("", new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Polygon_20px_1.png")), panel_2, null);
		tabbedPane.setBackgroundAt(0, new Color(58, 62, 74));
		panel_2.setLayout(new MigLayout("", "[66:66,grow,center]", "[30:n,grow][30:n,grow][30:n,grow][30:n,grow][30:n,grow][30:n,grow]"));
		
		JButton button_0 = new JButton("");
		button_0.setBorder(null);
		button_0.setBackground(new Color(58, 62, 74));
		button_0.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Line_22px.png")));
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_draw.setShape(new Line());
			}
		});
		panel_2.add(button_0, "cell 0 0,grow");
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_draw.setShape(new Triangle());
			}
		});
		button_1.setBorder(null);
		button_1.setBackground(new Color(58, 62, 74));
		button_1.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Triangle_22px.png")));
		panel_2.add(button_1, "cell 0 1,grow");
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_draw.setShape(new Square());
			}
		});
		button_2.setBorder(null);
		button_2.setBackground(new Color(58, 62, 74));
		button_2.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Square_22px.png")));
		panel_2.add(button_2, "cell 0 2,grow");
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_draw.setShape(new Rectangle());
			}
		});
		button_3.setBorder(null);
		button_3.setBackground(new Color(58, 62, 74));
		button_3.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Rectangle_22px.png")));
		panel_2.add(button_3, "cell 0 3,grow");
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_draw.setShape(new Ellipse());
			}
		});
		button_4.setBorder(null);
		button_4.setBackground(new Color(58, 62, 74));
		button_4.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Ellipse_22px.png")));
		panel_2.add(button_4, "cell 0 4,grow");
		
		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_draw.setShape(new Circle());
			}
		});
		button_5.setBorder(null);
		button_5.setBackground(new Color(58, 62, 74));
		button_5.setIcon(new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Filled_Circle_22px_1.png")));
		panel_2.add(button_5, "cell 0 5,grow");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(58, 62, 74));
		tabbedPane.addTab("", new ImageIcon(MainController.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Toolbox_20px_1.png")), panel_3, null);
		tabbedPane.setBackgroundAt(1, new Color(58, 62, 74));
		panel_3.setLayout(new MigLayout("", "[66:66,grow]", "[30:n][30:n][30:n][30:n][30:n][30:n]"));
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_3.add(button_6, "cell 0 0,grow");
		
		JButton button_7 = new JButton("");
		panel_3.add(button_7, "cell 0 1,grow");
		
		JButton button_8 = new JButton("");
		panel_3.add(button_8, "cell 0 2,grow");
		
		JButton button_9 = new JButton("");
		panel_3.add(button_9, "cell 0 3,grow");
		
		JButton button_10 = new JButton("");
		panel_3.add(button_10, "cell 0 4,grow");
		
		JButton button_11 = new JButton("");
		panel_3.add(button_11, "cell 0 5,grow");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(58, 62, 74));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new MigLayout("", "[3.5:n][70:70px]", "[5:n][68:68px]"));
		
		JPanel panel_8 = new JPanel();
		panel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				color = JColorChooser.showDialog(null, "Pick Outline Color", color);
				if (color == null) {
					color = (Color) panel_draw.getShape().getColor();
				}
				panel_8.setBackground(color);
				panel_draw.setColor(color);
			}
		});
		panel_8.setBackground(Color.BLACK);
		panel_8.setBorder(null);
		panel_1.add(panel_8, "cell 1 1,grow");
		
		JPanel panel_9 = new JPanel();
		panel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				colorFill = JColorChooser.showDialog(null, "Pick Fill Color", colorFill);
				if (colorFill == null) {
					colorFill = (Color) panel_draw.getShape().getFillColor();
				}
				panel_9.setBackground(colorFill);
				panel_draw.setColorFill(colorFill);
			}
		});
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_8.setLayout(gl_panel_8);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBackground(new Color(58, 62, 74));
		contentPane.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[547px,grow 150]", "[344px,grow]"));
		
		
		panel_4.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(new BorderLayout(0, 0));
		panel_6.add(panel_draw);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(58, 62, 74));
		panel_5.setBorder(null);
		contentPane.add(panel_5, "cell 3 0,grow");
		panel_5.setLayout(new MigLayout("", "[80.00,grow]", "[40.00]"));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(null);
		panel_10.setBackground(new Color(58, 62, 74));
		panel_5.add(panel_10, "cell 0 0,grow");
		panel_10.setLayout(new MigLayout("", "[][]", "[35:n]"));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBackground(new Color(58, 62, 74));
		panel_10.add(btnNewButton, "cell 0 0,grow");
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(58, 62, 74));
		panel_10.add(btnNewButton_1, "cell 1 0,grow");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(40, 45, 57));
		contentPane.add(panel_7, "cell 2 1,grow");
		panel_7.setLayout(new MigLayout("", "[][30:n,left][][][30:n,left][][][][][]", "[]"));
		
		JLabel lblX = new JLabel("X:");
		lblX.setForeground(Color.WHITE);
		lblX.setBackground(Color.WHITE);
		panel_7.add(lblX, "cell 0 0");
		
		JLabel labelX = new JLabel("");
		labelX.setForeground(Color.WHITE);
		panel_7.add(labelX, "cell 1 0");
		
		JLabel lblY = new JLabel("Y:");
		lblY.setForeground(Color.WHITE);
		panel_7.add(lblY, "cell 3 0");
		
		JLabel labelY = new JLabel("");
		labelY.setForeground(Color.WHITE);
		panel_7.add(labelY, "cell 4 0");
		panel_draw.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				labelX.setText(String.valueOf(arg0.getX()));
				labelY.setText(String.valueOf(arg0.getY()));
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				labelX.setText(String.valueOf(e.getX()));
				labelY.setText(String.valueOf(e.getY()));
			}
		});
	}
}
