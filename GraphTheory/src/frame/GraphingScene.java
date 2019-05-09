package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.panel.InformationPanel;
import object.Node;
import object.Path;
import save.DataModel;
import util.Vector;

public class GraphingScene extends JPanel implements Runnable {
	private Thread th;
	private Dimension dimension;
	private DataModel dt;
	private boolean isRunning = false;
	private final long SLEEP = 3;
	private InformationPanel informationPanel;
	private double xPrecedent, yPrecedent, dx, dy;
	private Node selectedNode = new Node();
	private Path tempPath;
	private boolean nodeSelected = false;
	private boolean mouseClicked = false;
	private boolean newPath = false;
	private boolean firstNodeSelected = false;

	public GraphingScene(Dimension dimension) {
		setBounds(0,0,(int)dimension.getWidth(),(int)dimension.getHeight());
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if(nodeSelected && mouseClicked) {
					dx = arg0.getX() - xPrecedent;
					dy = arg0.getY() - yPrecedent;
					xPrecedent = arg0.getX();
					yPrecedent = arg0.getY();
					selectedNode.translate(dx,dy);
				}
				
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nodeSelected = false;
				selectedNode = new Node();
				for (Node node : dt.getNodes()) {
					if (node.isInside(arg0.getPoint())) {
						nodeSelected = true;
						selectedNode = node;
					}
				}
				if (nodeSelected && arg0.getButton() == MouseEvent.BUTTON1) { //Left click
					informationPanel.update(selectedNode);
					informationPanel.setVisible(true);
				} else {
					informationPanel.setVisible(false);
				}
				
				if(firstNodeSelected) {
					tempPath.setNodeTwo(selectedNode);
					firstNodeSelected = false;
					dt.addPath(tempPath);
				}
				
				if(nodeSelected && newPath) {
					tempPath = new Path();
					tempPath.setNodes(selectedNode, new Node(arg0.getX(),arg0.getY()));
					firstNodeSelected = true;
					newPath = false;
				}
				
				
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				mouseClicked = false;
				nodeSelected = false;
			}
			@Override
			public void mousePressed(MouseEvent e) {
				for (Node node : dt.getNodes()) {
					if (node.isInside(e.getPoint())) {
						nodeSelected = true;
						mouseClicked = true;
						selectedNode = node;
					}
				}
				if(nodeSelected && e.getButton() == MouseEvent.BUTTON3) { //Right click
					Vector positionSouris = new Vector(e.getPoint());
					mouseClicked = true;
					xPrecedent = positionSouris.getX();
					yPrecedent = positionSouris.getY();
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int keyCode = arg0.getKeyCode();
				Point pos = MouseInfo.getPointerInfo().getLocation();
				pos.setLocation(pos.getX() - getLocationOnScreen().getX(), pos.getY() - getLocationOnScreen().getY());
				
				switch (keyCode) {
				case KeyEvent.VK_N:
					dt.addNode(new Node(pos));
					break;
				case KeyEvent.VK_D:
					dt.deleteNode(pos);
					break;
				case KeyEvent.VK_P:
					newPath = true;
					break;
				}
			}

			
		});
		this.dimension = dimension;
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		informationPanel = new InformationPanel();
		informationPanel.setBounds(10, 11, 81, 59);
		add(informationPanel);
		
		JLabel newLbl = new JLabel("N = New Node");
		newLbl.setBounds(10, getHeight()-115, 200, 14);
		add(newLbl);
		
		JLabel deleteLbl = new JLabel("D = Delete Node");
		deleteLbl.setBounds(10, getHeight()-95, 200, 14);
		add(deleteLbl);
		informationPanel.setVisible(false);
		
		JLabel dragLbl = new JLabel("Right Click = Drag Node");
		dragLbl.setBounds(10, getHeight()-75, 200, 14);
		add(dragLbl);
		informationPanel.setVisible(false);
		
		JLabel infoLbl = new JLabel("Left Click = Node's info");
		infoLbl.setBounds(10, getHeight()-55, 200, 14);
		add(infoLbl);
		informationPanel.setVisible(false);

	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for(Path path : dt.getPaths()) {
			path.draw(g2d);
		}
		for (Node node : dt.getNodes()) {
			node.draw(g2d);
		}
		
		informationPanel.update(selectedNode);
	}

	@Override
	public void run() {
		while (isRunning) {
			repaint();

			try {
				th.sleep(SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void start() {
		if (!isRunning) {
			isRunning = true;
			th = new Thread(this);
			th.start();
		}
	}

	public void stop() {
		isRunning = false;
	}

	public void setDt(DataModel dt) {
		this.dt = dt;
	}
}
