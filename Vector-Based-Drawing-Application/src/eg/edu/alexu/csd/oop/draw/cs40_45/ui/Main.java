package eg.edu.alexu.csd.oop.draw.cs40_45.ui;

import eg.edu.alexu.csd.oop.draw.cs40_45.shapes.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Main extends JWindow {

	private JPanel contentPane;
	private Graphics canvas;
	private XCanvas panel;
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
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40, 45, 57));
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(400, 400, 20, 20));
		setContentPane(contentPane);
		JPanel p = new JPanel();
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(715, 0, 35, 35);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Shutdown_25px_1.png")));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBackground(new Color(40, 45, 57));
		btnNewButton.setBounds(0, 0, 35, 35);
		panel_1.add(btnNewButton);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(79, 86, 104));
		panel_6.setBounds(140, 39, 600, 400);
		contentPane.add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 5, 590, 390);
		panel_6.add(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		Line line = new Line();
		panel = new XCanvas();
		panel.setBounds(5, 5, 590, 390);
		panel_2.add(panel);
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(79, 86, 104));
		panel_3.setBounds(0, 39, 130, 400);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(58, 62, 74));
		tabbedPane.setBounds(0, 0, 130, 230);
		
		panel_3.add(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(79, 86, 104));
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(79, 86, 104));
		ImageIcon shapes = new ImageIcon(
				this.getClass().getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Polygon_20px_1.png"));
		tabbedPane.addTab(null, shapes, panel_5);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		tabbedPane.setEnabledAt(0, true);
		panel_5.setLayout(new MigLayout("", "[40px][36px][46px]", "[27px][27px][27px][27px][27px][32px]"));
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
		});
		button_1.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Line_15px.png")));
		button_1.setBackground(new Color(79, 86, 104));
		panel_5.add(button_1, "cell 0 0,grow");
		
		JButton button_2 = new JButton("");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		button_2.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Triangle_15px.png")));
		button_2.setBackground(new Color(79, 86, 104));
		panel_5.add(button_2, "cell 2 0,grow");
		
		JLabel lblLine = new JLabel("Line");
		lblLine.setForeground(Color.WHITE);
		panel_5.add(lblLine, "cell 0 1,alignx center,growy");
		
		JLabel lblTriangle = new JLabel("Triangle");
		lblTriangle.setForeground(Color.WHITE);
		panel_5.add(lblTriangle, "cell 2 1,alignx center,growy");
		
		JButton button_3 = new JButton("");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		button_3.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Rectangle_15px.png")));
		button_3.setBackground(new Color(79, 86, 104));
		panel_5.add(button_3, "cell 0 2,grow");
		
		JButton button_4 = new JButton("");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		button_4.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Square_15px.png")));
		button_4.setBackground(new Color(79, 86, 104));
		panel_5.add(button_4, "cell 2 2,grow");
		
		JLabel lblRectangle = new JLabel("Rectangle");
		lblRectangle.setForeground(Color.WHITE);
		panel_5.add(lblRectangle, "cell 0 3,alignx center,growy");
		
		JLabel lblSquare = new JLabel("Square");
		lblSquare.setForeground(Color.WHITE);
		panel_5.add(lblSquare, "cell 2 3,alignx center,growy");
		
		JButton button_5 = new JButton("");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		button_5.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Ellipse_15px.png")));
		button_5.setBackground(new Color(79, 86, 104));
		panel_5.add(button_5, "cell 0 4,grow");
		
		JButton button_6 = new JButton("");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		button_6.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Circle_15px.png")));
		button_6.setBackground(new Color(79, 86, 104));
		panel_5.add(button_6, "cell 2 4,grow");
		
		JLabel lblEllipse = new JLabel("Ellipse");
		lblEllipse.setForeground(Color.WHITE);
		panel_5.add(lblEllipse, "cell 0 5,alignx center,growy");
		
		JLabel lblCircle = new JLabel("Circle");
		lblCircle.setForeground(Color.WHITE);
		panel_5.add(lblCircle, "cell 2 5,alignx center,growy");
		shapes = new ImageIcon(
				this.getClass().getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Toolbox_20px_1.png"));
		tabbedPane.addTab(null, shapes, panel_4);
		panel_4.setLayout(new MigLayout("", "[62][23][62]", "[27px][27px][27px][27px][27px][32]"));
		
		JButton button_7 = new JButton("");
		button_7.setBackground(new Color(79, 86, 104));
		panel_4.add(button_7, "cell 0 0,grow");
		
		JButton button_8 = new JButton("");
		button_8.setBackground(new Color(79, 86, 104));
		panel_4.add(button_8, "cell 2 0,grow");
		
		JLabel lblSelect = new JLabel("Select");
		lblSelect.setForeground(Color.WHITE);
		panel_4.add(lblSelect, "cell 0 1,alignx center,growy");
		
		JLabel lblMove = new JLabel("Move");
		lblMove.setForeground(Color.WHITE);
		panel_4.add(lblMove, "cell 2 1,alignx center,growy");
		
		JButton button_9 = new JButton("");
		button_9.setBackground(new Color(79, 86, 104));
		panel_4.add(button_9, "cell 0 2,grow");
		
		JButton button_10 = new JButton("");
		button_10.setBackground(new Color(79, 86, 104));
		panel_4.add(button_10, "cell 2 2,grow");
		
		JLabel lblResize = new JLabel("Resize");
		lblResize.setForeground(Color.WHITE);
		panel_4.add(lblResize, "cell 0 3,alignx center,growy");
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setForeground(Color.WHITE);
		panel_4.add(lblDelete, "cell 2 3,alignx center,growy");
		
		JButton button_11 = new JButton("");
		button_11.setBackground(new Color(79, 86, 104));
		panel_4.add(button_11, "cell 0 4,grow");
		
		JButton button_12 = new JButton("");
		button_12.setBackground(new Color(79, 86, 104));
		panel_4.add(button_12, "cell 2 4,grow");
		
		JLabel lblCopy = new JLabel("Copy");
		lblCopy.setForeground(Color.WHITE);
		panel_4.add(lblCopy, "cell 0 5,alignx center,growy");
		
		JLabel lblPaste = new JLabel("Paste");
		lblPaste.setForeground(Color.WHITE);
		panel_4.add(lblPaste, "cell 2 5,alignx center,growy");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(40, 45, 57));
		menuBar.setBounds(0, 0, 716, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_File_10px.png")));
		mnNewMenu.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Open_10px.png")));
		mnNewMenu.add(mntmOpen);
		
		JMenu mnSaveAs = new JMenu("Save as");
		mnSaveAs.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Save_10px.png")));
		mnNewMenu.add(mnSaveAs);
		
		JMenuItem mntmXml = new JMenuItem("XML");
		mnSaveAs.add(mntmXml);
		
		JMenuItem mntmJson = new JMenuItem("jSon");
		mnSaveAs.add(mntmJson);
		
		JMenu mnNewMenu_2 = new JMenu("Edit");
		mnNewMenu_2.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Undo");
		mntmNewMenuItem_2.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Undo_10px.png")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Redo");
		mntmNewMenuItem_3.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Redo_10px.png")));
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		mnNewMenu_1.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmHelpContents = new JMenuItem("Help Contents");
		mntmHelpContents.setIcon(new ImageIcon(Main.class.getResource("/eg/edu/alexu/csd/oop/draw/cs40_45/ui/Images/icons8_Help_10px.png")));
		mnNewMenu_1.add(mntmHelpContents);
		
	}
}
